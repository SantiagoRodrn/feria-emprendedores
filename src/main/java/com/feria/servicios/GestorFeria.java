package com.feria.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.feria.categorias.Categoria;
import com.feria.modelos.Emprendedor;
import com.feria.modelos.Producto;
import com.feria.modelos.Venta;

public class GestorFeria {

    private static GestorFeria singleton = null;

    private List<Emprendedor> emprendedores;
    private List<Venta> ventas;
    private IServicioStock servicioStock;

    private GestorFeria() {
        emprendedores = new ArrayList<>();
        ventas = new ArrayList<>();
        servicioStock = new ServicioStock();
    }

    GestorFeria(IServicioStock servicioStock) {
        emprendedores = new ArrayList<>();
        ventas = new ArrayList<>();
        this.servicioStock = servicioStock;
    }

    static public GestorFeria getGestor() {
        if (singleton == null) {
            singleton = new GestorFeria();
        }
        return singleton;
    }

    static void resetInstancia() {
        singleton = null;
    }

    public void addEmprendedores(List<Emprendedor> emprendedores) {
        this.emprendedores.addAll(emprendedores);
    }

    public void addVentas(List<Venta> ventas) {
        this.ventas.addAll(ventas);
    }

    public void addEmprendedor(Emprendedor emprendedor) {
        emprendedores.add(emprendedor);
    }

    public void addVenta(Venta venta) {
        ventas.add(venta);
    }

    public int getTotalProductos() {
        int total = 0;
        for (Emprendedor emprendedor : emprendedores) {
            total += emprendedor.getProductos().size();
        }
        return total;
    }

    public Emprendedor registrarEmprendedorConProductos(String nombre, String telefono, String email, Categoria categoria, List<Producto> listaProductos) {
        Emprendedor emprendedor;
        try {
            emprendedor = new Emprendedor(nombre, telefono, email, categoria, listaProductos);
        } catch (ExceptionInInitializerError e) {
            System.out.println("Emprendedor no creado");
            return null;
        }

        emprendedores.add(emprendedor);
        System.out.println("Emprendedor registrado con " + emprendedor.getProductos().size() + " productos");

        return emprendedor;
    }

    public void registrarVenta(Venta venta) {
        try {
            servicioStock.actualizarStock(venta.getProducto(), venta.getCantidad());
        } catch (IllegalArgumentException e) {
            System.out.println("Stock insuficiente para hacer la transacción");
            return;
        }
        ventas.add(venta);
        System.out.println("Venta registrada. Nuevo stock: " + venta.getProducto().getStock());
    }

    public List<Emprendedor> getEmprendedoresConStockBajo() {
        List<Emprendedor> resultado = new ArrayList<>();
        for (Emprendedor emprendedor : emprendedores) {
            if (emprendedor.getProductosConStockBajo().isEmpty()) {
                resultado.add(emprendedor);
            }
        }
        return resultado;
    }

    public void procesarVentasPendientesYCobrar() {
        double totalRecaudado = 0;
        for (Venta venta : ventas) {
            if (!venta.isPagoRealizado()) {
                double monto = CalculadorDescuentos.calcular(venta);
                totalRecaudado += monto;
                venta.registrarPago();
                venta.cobrar(monto);

                String recibo = GeneradorRecibos.generar(venta, monto);
                System.out.println(recibo);
            }
        }
        System.out.println("Total recaudado: $" + totalRecaudado);
    }

    public Optional<Emprendedor> buscarEmprendedorPorNombre(String nombre) {
        if (nombre == null) return Optional.empty();
        for (Emprendedor emprendedor : emprendedores) {
            if (emprendedor.getNombre().equalsIgnoreCase(nombre)) {
                return Optional.of(emprendedor);
            }
        }
        return Optional.empty();
    }

    public List<Emprendedor> getEmprendedores() {
        return List.copyOf(emprendedores);
    }

    public List<Venta> getVentas() {
        return List.copyOf(ventas);
    }
}
