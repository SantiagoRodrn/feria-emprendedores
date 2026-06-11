package com.feria.descuentos;

import com.feria.modelos.Venta;

public interface Descuento {
    double aplicar(double total, Venta venta);
}