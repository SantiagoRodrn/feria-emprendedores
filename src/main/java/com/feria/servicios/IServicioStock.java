package com.feria.servicios;

import com.feria.modelos.Producto;

public interface IServicioStock {
    void actualizarStock(Producto producto, int cantidad);
}
