package com.feria.servicios;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.feria.categorias.Comida;
import com.feria.modelos.Emprendedor;
import com.feria.modelos.Producto;
import com.feria.modelos.Venta;

class GestorFeriaTest {

    private IServicioStock servicioStockMock;
    private GestorFeria gestor;
    private Emprendedor emprendedor;
    private Producto producto;

    @BeforeEach
    void setUp() {
        servicioStockMock = Mockito.mock(IServicioStock.class);
        gestor = new GestorFeria(servicioStockMock);

        emprendedor = new Emprendedor("Carlos", "12345678", "carlos@test.com", new Comida());
        producto = new Producto("Collar", 2000.0, 10);
        emprendedor.agregarProducto(producto);
        gestor.addEmprendedor(emprendedor);
    }

    // =============================================
    // Tests con mocks - registrarVenta
    // =============================================

    @Test
    void registrarVenta_stockSuficiente_ventaQuedaRegistrada() {
        Venta venta = new Venta(emprendedor, producto, 3, 2000.0, LocalDate.now());

        gestor.registrarVenta(venta);

        assertEquals(1, gestor.getVentas().size());
    }

    @Test
    void registrarVenta_stockSuficiente_llamaActualizarStock() {
        Venta venta = new Venta(emprendedor, producto, 3, 2000.0, LocalDate.now());

        gestor.registrarVenta(venta);

        // verifica que el mock fue llamado con los parametros correctos
        verify(servicioStockMock).actualizarStock(producto, 3);
    }

    @Test
    void registrarVenta_stockInsuficiente_noRegistraVenta() {
        Venta venta = new Venta(emprendedor, producto, 3, 2000.0, LocalDate.now());
        // el mock simula que no hay stock suficiente
        doThrow(new IllegalArgumentException("Stock insuficiente"))
                .when(servicioStockMock).actualizarStock(producto, 3);

        gestor.registrarVenta(venta);

        assertEquals(0, gestor.getVentas().size());
    }

    // =============================================
    // TDD - buscarEmprendedorPorNombre
    // =============================================

    @Test
    void buscarEmprendedorPorNombre_nombreExistente_retornaEmprendedor() {
        Optional<Emprendedor> resultado = gestor.buscarEmprendedorPorNombre("Carlos");

        assertTrue(resultado.isPresent());
        assertEquals("Carlos", resultado.get().getNombre());
    }

    @Test
    void buscarEmprendedorPorNombre_nombreNoExistente_retornaEmpty() {
        Optional<Emprendedor> resultado = gestor.buscarEmprendedorPorNombre("Pedro");

        assertFalse(resultado.isPresent());
    }

    @Test
    void buscarEmprendedorPorNombre_nombreNull_retornaEmpty() {
        Optional<Emprendedor> resultado = gestor.buscarEmprendedorPorNombre(null);

        assertFalse(resultado.isPresent());
    }

    @Test
    void buscarEmprendedorPorNombre_busquedaInsensibleAMayusculas_retornaEmprendedor() {
        Optional<Emprendedor> resultado = gestor.buscarEmprendedorPorNombre("CARLOS");

        assertTrue(resultado.isPresent());
    }
}
