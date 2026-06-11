package com.feria.utils;

import java.util.ArrayList;
import java.util.List;

import com.feria.modelos.Producto;

abstract public class ProductoUtils {
    static public List<Producto> crearListaProductos(List<String> nombres, List<Double> precios, List<Integer> stocks) {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            for (int i = 0; i < nombres.size(); i++) {
                Producto producto = new Producto(nombres.get(i), precios.get(i), stocks.get(i));
                listaProductos.add(producto);
            }
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Las tres listas con información sobre productos (nombres, precios, stocks) deben tener el mismo tamaño");
        }
        return listaProductos;
    }
}
