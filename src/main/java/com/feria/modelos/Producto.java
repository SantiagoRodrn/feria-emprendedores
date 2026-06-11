package com.feria.modelos;


public class Producto {

    private String nombre;
    private double precio;
    private int stock;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public void restarStock(int cantidad) throws IllegalArgumentException {
        if (getStock() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock para restar");
        }
        stock -= cantidad;
    }

    public double valorTotal() {
        return precio * stock;
    }


    public String imprimir() {
        return nombre + " - $" + precio + " (stock: " + stock + ")";
    }

    public boolean hayStockBajo() {
        return stock < 5;
    }
}