package comanda.controller;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import comanda.controller.dto.response.ComandaResponse;
import comanda.controller.dto.response.MesaResponse;
import comanda.controller.dto.response.MesaUsoResponse;
import comanda.entity.Comanda;
import comanda.entity.Estado;
import comanda.entity.Mesa;
import comanda.entity.MesaUso;
import comanda.service.mapper.MesaMapper;
import comanda.service.mapper.MesaUsoMapper;
import comanda.service.mapper.ComandaMapper;


//@ComponentScan(basePackages = {"comanda.service.mapper"})
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
class MesaUsosControllerTest {



	private final MesaMapper mesaMapper = MesaMapper.INSTANCE;
	private final ComandaMapper comandaMapper = ComandaMapper.INSTANCE;
	private final MesaUsoMapper mesaUsoMapper = MesaUsoMapper.INSTANCE;

	@Test
	void testBuscarTodasAll() {
		List<Comanda> comandas = new ArrayList<Comanda>();

		MesaUso mesaUso = new MesaUso();
		mesaUso.setId(10);
		mesaUso.setComandas(comandas);

		System.out.println("------");
		System.out.println(">>> mesaUso: " + mesaUso);
		System.out.println("------");

		Comanda comanda = new Comanda();
		comanda.setId(20);
		comanda.setMesaUso(mesaUso);

		System.out.println("------");
		System.out.println(">>> comanda: " + comanda);
		System.out.println("------");

		comandas.add(comanda);

		System.out.println("------");
		System.out.println(">>> mesaUso: " + mesaUso);
		System.out.println("------");

		Mesa mesa = new Mesa();
		mesa.setId(20);
		mesa.setObservacion("ppsp");

		System.out.println("------");
		System.out.println(">>> mesa: "+ mesa);
		System.out.println("------");

		mesaUso.setMesa(mesa);
		System.out.println("------");
		System.out.println(">>> MesaUso: " + mesaUso);
		System.out.println("------");


		ComandaResponse comandaDTO = comandaMapper.mapToComandaDTO(comanda);
		System.out.println("------");
		System.out.println(">>> comandaDTO: " + comandaDTO);
		System.out.println("------");


		MesaResponse mesaDTO = mesaMapper.mapToMesaDto(mesa);
		System.out.println("------");
		System.out.println(">>> mesaDTO: " + mesaDTO);
		System.out.println("------");

		MesaUsoResponse mesaUsoDTO = mesaUsoMapper.mapToMesaUsoDTO(mesaUso);
		System.out.println("------");
		System.out.println(">>> mesaUsoDTO: " + mesaUsoDTO);
		System.out.println("------");

		List<ComandaResponse> comandasDTO = comandaMapper.mapToListComandaDTO(mesaUso.getComandas());
		System.out.println("------");
		System.out.println(">>> comandasDTO: " + comandasDTO);
		System.out.println("------");
		mesaUsoDTO.setComandas(comandasDTO);
		System.out.println("------");
		System.out.println(">>> mesaUsoDTO: " + mesaUsoDTO);
		System.out.println("------");




	}


}
