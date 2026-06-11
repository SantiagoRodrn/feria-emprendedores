package com.feria.servicios;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.feria.categorias.Comida;
import com.feria.modelos.Emprendedor;
import com.feria.modelos.Producto;
import com.feria.modelos.Venta;

class CalculadorDescuentosTest {

    private Venta crearVenta(int cantidad, double precioUnitario) {
        Emprendedor emprendedor = new Emprendedor("Ana", "12345678", "ana@test.com", new Comida());
        Producto producto = new Producto("Empanada", precioUnitario, 100);
        return new Venta(emprendedor, producto, cantidad, precioUnitario, LocalDate.now());
    }

    // Sin descuentos: cantidad <= 10 y total <= 5000
    @Test
    void calcular_sinDescuentos_retornaTotalExacto() {
        Venta venta = crearVenta(5, 200.0); // total = 1000, cantidad = 5
        assertEquals(1000.0, CalculadorDescuentos.calcular(venta));
    }

    // Descuento por cantidad: cantidad > 10 => 10% off
    @Test
    void calcular_cantidadMayorA10_aplica10PorcientoDescuento() {
        Venta venta = crearVenta(11, 100.0); // total = 1100
        assertEquals(990, CalculadorDescuentos.calcular(venta));
    }

    // Caso límite: cantidad exactamente 10 NO aplica descuento
    @Test
    void calcular_cantidadExactamente10_noAplicaDescuento() {
        Venta venta = crearVenta(10, 100.0); // total = 1000
        assertEquals(1000.0, CalculadorDescuentos.calcular(venta));
    }

    // Descuento por monto: total > 5000 => 5% off
    @Test
    void calcular_totalMayorA5000_aplica5PorcientoDescuento() {
        Venta venta = crearVenta(1, 6000.0); // total = 6000
        assertEquals(6000.0 * 0.95, CalculadorDescuentos.calcular(venta));
    }

    // Caso límite: total exactamente 5000 NO aplica descuento por monto
    @Test
    void calcular_totalExactamente5000_noAplicaDescuentoPorMonto() {
        Venta venta = crearVenta(10, 500.0); // total = 5000, cantidad = 10 (no aplica ninguno)
        assertEquals(5000.0, CalculadorDescuentos.calcular(venta));
    }

    // Ambos descuentos: cantidad > 10 y total resultante > 5000
    @Test
    void calcular_ambosDescuentos_aplicaAmbos() {
        Venta venta = crearVenta(20, 400.0); // total = 8000
        // primero 10% por cantidad: 8000 * 0.9 = 7200
        // luego 5% por monto: 7200 * 0.95 = 6840
        assertEquals(6840.0, CalculadorDescuentos.calcular(venta));
    }
}
