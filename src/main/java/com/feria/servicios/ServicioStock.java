package com.feria.servicios;

import com.feria.modelos.Producto;

public class ServicioStock implements IServicioStock {

    @Override
    public void actualizarStock(Producto producto, int cantidad) {
        producto.restarStock(cantidad);
    }
}