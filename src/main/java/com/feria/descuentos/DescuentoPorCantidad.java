package com.feria.descuentos;

import com.feria.modelos.Venta;

public class DescuentoPorCantidad implements Descuento {

    @Override
    public double aplicar(double total, Venta venta) {

        if (venta.getCantidad() > 10) {
            return total * 0.9;
        }

        return total;
    }
}