package comanda.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import comanda.controller.dto.response.ComandaResponse;
import comanda.entity.*;
import comanda.form.MesaUsoForm;
import comanda.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comanda")
public class ComandasController {

    @Autowired
    private IComandasService serviceComandas;

    @Autowired
    private IItemComandasService serviceComandasItem;

    @Autowired
    private IMesasService mesasService;
    @Autowired
    private IUsuarioLocalesService usuarioLocalesService;

    @GetMapping("/comanda")
    public List<Comanda> buscarTodas() {
        return serviceComandas.buscarTodas();
    }

    @GetMapping("/comanda/{id}")
    public Optional<Comanda> buscarComanda(@PathVariable("id") int idComanda) {
        return serviceComandas.buscarComanda(idComanda);
    }

    @GetMapping("/consumos/{idMesa}")
    public List<ItemComanda> buscarConsumos(@PathVariable("idMesa") int idMesa) throws ComandaServiceException {
        Mesa mesa = mesasService.buscarMesa(idMesa);
        Comanda comanda = serviceComandas.buscarComandaPorMesaPorEstado(mesa, new Estado(2));
        return serviceComandasItem.buscarItemsPorComanda(comanda);
    }

    @PostMapping("/comandas")
    public List<ComandaResponse> comandas(@RequestParam String fecha, @RequestBody Usuario usuario) throws ComandaServiceException, ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (fecha != null && StringUtils.isNotEmpty(fecha)) {
            date = sdf.parse(fecha);
        }
        fecha = sdf.format(date);
        List<ComandaResponse> response = new ArrayList<>();
        List<Comanda> list;
        Optional<UsuarioLocal> localOpt;
       
        List<Comanda> allComandas = serviceComandas.buscarTodas();  

        // Verifica que el usuario no sea nulo antes de buscar el UsuarioLocal
        if (usuario != null) {
            localOpt = usuarioLocalesService.buscarUsuarioLocal(usuario.getId());

            // Utiliza localOpt si es necesario hacer algo con ella
            if (localOpt.isPresent()) {
                list = allComandas.stream()
                    .filter(x -> x.getMesa().getLocal().getId() == localOpt.get().getLocal().getId())
                    .collect(Collectors.toList());
            } else {
                // Manejo de la situación en la que localOpt es nulo
                return new ArrayList<>();
            }
        } else {
            // Manejo de la situación en la que usuario es nulo
            return new ArrayList<>();
        }

        // Ahora, este bloque de código está dentro del bloque else
        List<Comanda> comandas = serviceComandas.buscarComandaPorFecha(sdf.parse(fecha));
        list = comandas.stream().filter(x -> x.getMesa().getLocal().getId() == localOpt.get().getLocal().getId()).collect(Collectors.toList());

        list.forEach(x -> {
            List<ItemComanda> items = serviceComandasItem.buscarItemsPorComanda(x);
            response.add(new ComandaResponse(x, items));
        });

        return response;
    }
    
    @PostMapping("/admincomandas")
    public List<ComandaResponse> comandas2(@RequestParam String fecha, @RequestBody Usuario usuario) throws ComandaServiceException, ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (fecha != null && StringUtils.isNotEmpty(fecha)) {
            date = sdf.parse(fecha);
        }
        fecha = sdf.format(date);
        List<ComandaResponse> response = new ArrayList<>();
        List<Comanda> list;
        if (usuario != null && usuario.getRol().getNombre().equalsIgnoreCase("ADMIN")) {
            list = serviceComandas.buscarComandaPorFecha(sdf.parse(fecha));
        } else {
            Optional<UsuarioLocal> localOpt = usuarioLocalesService.buscarUsuarioLocal(usuario.getId());
            if (!localOpt.isPresent()) {
                return new ArrayList<>();
            }
            List<Comanda> comandas = serviceComandas.buscarComandaPorFecha(sdf.parse(fecha));
            list = comandas.stream().filter(x -> x.getMesa().getLocal().getId() == localOpt.get().getId()).collect(Collectors.toList());
        }
        list.forEach(x -> {
            List<ItemComanda> items = serviceComandasItem.buscarItemsPorComanda(x);
            response.add(new ComandaResponse(x, items));
        });
        return response;

    }

    @PostMapping("/comanda")
    public Comanda guardar(@RequestBody Comanda comanda) {
        serviceComandas.guardar(comanda);
        return comanda;
    }

    @PostMapping("/close/{tableId}")
    public Comanda cerrar(@PathVariable("tableId") int tableId) throws ComandaServiceException {
        Mesa mesa = mesasService.buscarMesa(tableId);
        Comanda comandaPrevia = serviceComandas.buscarComandaPorMesaPorEstado(mesa, new Estado(Estado.ESTADO_EN_CURSO));
        if (comandaPrevia == null) {
            throw new ComandaServiceException("01", "Mesa no encontrada");
        }
        List<ItemComanda> items = serviceComandasItem.buscarItemsPorComanda(comandaPrevia);
        comandaPrevia.setEstado(new Estado(Estado.ESTADO_CERRADA));
        comandaPrevia.setFecha(new Date());

        double total = 0;
        for (ItemComanda item : items) {
            total += item.getPrecio();
        }
        comandaPrevia.setTotal(total);
        serviceComandas.guardar(comandaPrevia);
        mesa.setEstado(new Estado(Estado.ESTADO_DISPONIBLE));
        mesasService.guardar(mesa);
        return comandaPrevia;
    }

    @PostMapping("/orden")
    public Comanda ordener(@RequestBody MesaUsoForm form) throws ComandaServiceException {
        Mesa mesa = mesasService.buscarMesa(form.getTableId());
        Estado estado = new Estado(2);
        Comanda comandaPrevia = serviceComandas.buscarComandaPorMesaPorEstado(mesa, estado);
        Comanda comanda = new Comanda(mesa);
        if (comandaPrevia != null) {
            comanda = comandaPrevia;
        } else {
            comanda = serviceComandas.guardar(comanda);
        }

        for (Producto producto : form.getCart()) {
            ItemComanda item = new ItemComanda();
            item.setProducto(producto);
            item.setCantidad(1);
            item.setComanda(comanda);
            item.setPrecio(producto.getPrecio());
            serviceComandasItem.guardar(item);
        }

        return comanda;
    }

    
    @PostMapping("/comanda/{id}/crearitemcomanda")
    public Comanda buscarComanda(@PathVariable("id") @RequestBody Comanda comanda) {
        serviceComandas.crearItemComanda(comanda);
        return comanda;
    }

    @PutMapping("/comanda")
    public Comanda modificar(@RequestBody Comanda comanda) {
        serviceComandas.guardar(comanda);
        return comanda;
    }

    @DeleteMapping("/comanda/{id}")
    public String eliminar(@PathVariable("id") int idComanda) {
        serviceComandas.eliminar(idComanda);
        return "Registro Eliminado";
    }
}
