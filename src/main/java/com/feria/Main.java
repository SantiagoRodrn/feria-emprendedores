package com.feria;

import java.time.LocalDate;
import java.util.Arrays;

import com.feria.categorias.Artesania;
import com.feria.categorias.Comida;
import com.feria.modelos.Emprendedor;
import com.feria.modelos.Producto;
import com.feria.modelos.Venta;
import com.feria.servicios.GestorFeria;
import com.feria.servicios.Reportes;
import com.feria.utils.ProductoUtils;
import com.feria.utils.Validadores;

public class Main {

    public static void main(String[] args) {
        GestorFeria gestor = GestorFeria.getGestor();

        Emprendedor emprendedor1 = gestor.registrarEmprendedorConProductos(
            "Ana", "3423456789", "ana@gmail.com", new Comida(),
            ProductoUtils.crearListaProductos(
                Arrays.asList("Empanadas", "Tortas", "Alfajores"),
                Arrays.asList(500.0, 1500.0, 300.0),
                Arrays.asList(50, 10, 100)
            )
        );
        Producto producto1 = new Producto("Collar", 2000.0, 5);
        Producto producto2 = new Producto("Pulsera", 800.0, 20);
        Emprendedor emprendedor2 = new Emprendedor("Carlos", "3423987654", "carlos@hotmail.com", new Artesania(), Arrays.asList(producto1, producto2));
        gestor.addEmprendedor(emprendedor2);

        Venta venta1 = new Venta(emprendedor1, emprendedor1.getProductos().get(0), 10, 500.0, LocalDate.of(2026, 05, 12));
        gestor.registrarVenta(venta1);

        Venta venta2 = new Venta(emprendedor2, producto1, 1, 2000.0, LocalDate.of(2026, 05, 12));
        gestor.registrarVenta(venta2);

        System.out.println(Reportes.generarReportePorCategoria(gestor, new Comida()));

        gestor.procesarVentasPendientesYCobrar();

        Reportes.imprimirResumenEjecutivo(gestor);

        System.out.println("Emprendedor Ana válido? " + Validadores.validarEmprendedorCompleto(gestor.getEmprendedores().get(0)));

        System.out.println(gestor.getEmprendedores().get(0).mostrarInfo());
        System.out.println(gestor.getEmprendedores().get(0).validarConInfo());
    }
}