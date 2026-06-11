package com.feria.servicios;

import java.util.Arrays;
import java.util.List;

import com.feria.descuentos.Descuento;
import com.feria.descuentos.DescuentoPorCantidad;
import com.feria.descuentos.DescuentoPorMonto;
import com.feria.modelos.Venta;

abstract public class CalculadorDescuentos {

    static private final List<Descuento> descuentos = Arrays.asList(new DescuentoPorCantidad(), new DescuentoPorMonto());

    static public double calcular(Venta venta) {

        double total = venta.getCantidad() * venta.getPrecioUnitario();

        for (Descuento descuento : descuentos) {
            total = descuento.aplicar(total, venta);
        }

        return total;
    }
}