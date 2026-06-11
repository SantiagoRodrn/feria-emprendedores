package com.feria.descuentos;

import com.feria.modelos.Venta;

public class DescuentoPorMonto implements Descuento {

    @Override
    public double aplicar(double total, Venta venta) {

        if (total > 5000) {
            return total * 0.95;
        }

        return total;
    }
}