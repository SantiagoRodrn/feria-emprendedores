package com.feria.servicios;

import com.feria.modelos.Venta;

abstract public class GeneradorRecibos {

    static public String generar(Venta venta, double total) {

        return "=== RECIBO DE VENTA ===\n" +
                "Venta ID: " + venta.getId() + "\n" +
                "Fecha: " + venta.getFecha() + "\n" +
                "Producto: " + venta.getProducto().getNombre() + "\n" +
                "Cantidad: " + venta.getCantidad() + "\n" +
                "Precio unitario: $" + venta.getPrecioUnitario() + "\n" +
                "Total con descuentos: $" + total + "\n";
    }
}