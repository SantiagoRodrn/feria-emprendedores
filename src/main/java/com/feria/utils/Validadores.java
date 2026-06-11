package com.feria.utils;

import com.feria.modelos.Emprendedor;

abstract public class Validadores {

    public static boolean validarEmail(String email) {
        if (email == null) return false;
        if (!email.contains("@")) return false;
        if (email.length() < 5) return false;
        return true;
    }

    public static boolean validarTelefono(String telefono) {
        if (telefono == null) return false;
        if (telefono.length() < 8) return false;
        return true;
    }

    public static boolean validarPrecio(double precio) {
        return precio > 0;
    }

    public static boolean validarStock(int stock) {
        return stock >= 0;
    }

    public static boolean validarNombre(String nombre) {
        return !(nombre == null || nombre.length() < 2);
    }

    public static boolean validarEmprendedorCompleto(Emprendedor emprendedor) {
        if (emprendedor == null) return false;
        if (!validarEmail(emprendedor.getEmail())) return false;
        if (!validarTelefono(emprendedor.getTelefono())) return false;
        if (!validarNombre(emprendedor.getNombre())) return false;
        return true;
    }
}