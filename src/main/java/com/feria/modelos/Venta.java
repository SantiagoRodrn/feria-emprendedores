package com.feria.modelos;

import java.time.LocalDate;

public class Venta {

    static private int nextIdVenta = 1;
    private String idVenta;
    private Emprendedor emprendedor;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private LocalDate fecha;
    private boolean pagoRealizado;

    private static String getNextIdVenta() {
        return "V" + String.format("%03d", nextIdVenta++);
    }

    public Venta(Emprendedor emprendedor, Producto producto, int cantidad, double precioUnitario, LocalDate fecha) {
        this.idVenta = getNextIdVenta();
        this.emprendedor = emprendedor;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fecha = fecha;
        this.pagoRealizado = false;
    }

    public void registrarPago() {
        this.pagoRealizado = true;
    }

    public void cobrar(double total) { 
        System.out.println("Cobrada venta " + idVenta + " por $" + total); 
    }

    public String getId() {
        return idVenta;
    }

    public void setId(String idVenta) {
        this.idVenta = idVenta;
    }

    public Emprendedor getEmprendedor() {
        return emprendedor;
    }

    public void setEmprendedor(Emprendedor emprendedor) {
        this.emprendedor = emprendedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }
}