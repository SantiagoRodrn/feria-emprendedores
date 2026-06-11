package com.feria.modelos;

import java.util.ArrayList;
import java.util.List;

import com.feria.categorias.Categoria;
import com.feria.utils.Validadores;


public class Emprendedor {

    static private int nextId = 1;
    private String nombre;
    private String id;
    private String telefono;
    private String email;
    private Categoria categoria;
    private List<Producto> productos;

    static private String getNextId() {
        return "E" + String.format("%03d", nextId++);
    }

    public Emprendedor(String nombre, String telefono, String email, Categoria categoria) {
        this.nombre = nombre;
        this.id = getNextId();
        this.telefono = telefono;
        this.email = email;
        this.categoria = categoria;
        this.productos = new ArrayList<>();

        validarConExcepcion();
    }

    public Emprendedor(String nombre, String telefono, String email, Categoria categoria, List<Producto> listaProductos) {
        this(nombre, telefono, email, categoria);
        this.productos = listaProductos;

        validarConExcepcion();
    }

    private void validarConExcepcion() {
        if (!validar()) {
            throw new ExceptionInInitializerError(validarConInfo());
        }
    }

    public List<Producto> getProductosConStockBajo() {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.hayStockBajo()) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    public String mostrarInfo() {
        String info = "Emprendedor: " + nombre + "\n";
        info += "ID: " + id + "\n";
        info += "Contacto: " + telefono + " | " + email + "\n";
        info += "Categoría: " + categoria.getNombre() + "\n";

        info += "Productos:\n";
        for (Producto producto : productos) {
            info += "  - " + producto.getNombre() + " ($" + producto.getPrecio() + ")\n";
        }

        return info;
    }

    public final String validarConInfo() {
        String info = "";
        if (!Validadores.validarNombre(nombre)) {
            info += "! NOMBRE DEMASIADO CORTO: "+ nombre + "\n";
        }
        if (!Validadores.validarEmail(email)) {
            info += "! EMAIL INVALIDO: " + email + "\n";
        }
        return info;
    }

    public final boolean validar() {
        return validarConInfo().equals("");
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }


    public int calcularValorTotalStock() {
        int total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio() * producto.getStock();
        }
        return total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}