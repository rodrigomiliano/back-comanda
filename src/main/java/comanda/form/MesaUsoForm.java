package comanda.form;

import comanda.entity.Producto;

public class MesaUsoForm {
    private int tableId;
    private Producto[] cart;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Producto[] getCart() {
        return cart;
    }

    public void setCart(Producto[] cart) {
        this.cart = cart;
    }
}
