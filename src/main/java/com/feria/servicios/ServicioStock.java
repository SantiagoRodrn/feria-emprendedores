package com.feria.servicios;

import com.feria.modelos.Producto;

abstract public class ServicioStock {

    static public void actualizarStock(Producto producto, int cantidad) {
        producto.restarStock(cantidad);
    }
}