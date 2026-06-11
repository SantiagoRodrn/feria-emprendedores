package com.feria.modelos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto("Empanadas", 500.0, 10);
    }

    // --- restarStock ---

    @Test
    void restarStock_cantidadValida_restaCorrectamente() {
        producto.restarStock(3);
        assertEquals(7, producto.getStock());
    }

    @Test
    void restarStock_cantidadExacta_dejaCero() {
        producto.restarStock(10);
        assertEquals(0, producto.getStock());
    }

    @Test
    void restarStock_stockInsuficiente_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> producto.restarStock(11));
    }

    @Test
    void restarStock_cantidadCero_noModificaStock() {
        producto.restarStock(0);
        assertEquals(10, producto.getStock());
    }

    // --- valorTotal ---

    @Test
    void valorTotal_calculaCorrecto() {
        assertEquals(5000.0, producto.valorTotal());
    }

    @Test
    void valorTotal_stockCero_retornaCero() {
        Producto productoSinStock = new Producto("Sin stock", 200.0, 0);
        assertEquals(0.0, productoSinStock.valorTotal());
    }

    // --- hayStockBajo ---

    @Test
    void hayStockBajo_stockMenorA5_retornaTrue() {
        Producto productoConStockBajo = new Producto("Poco", 100.0, 4);
        assertTrue(productoConStockBajo.hayStockBajo());
    }

    @Test
    void hayStockBajo_stockIgualA5_retornaFalse() {
        // el umbral es < 5, entonces 5 no es stock bajo
        Producto productoEnLimite = new Producto("Limite", 100.0, 5);
        assertFalse(productoEnLimite.hayStockBajo());
    }

    @Test
    void hayStockBajo_stockMayorA5_retornaFalse() {
        assertFalse(producto.hayStockBajo());
    }

    @Test
    void hayStockBajo_stockCero_retornaTrue() {
        Producto productoVacio = new Producto("Vacio", 100.0, 0);
        assertTrue(productoVacio.hayStockBajo());
    }
}
