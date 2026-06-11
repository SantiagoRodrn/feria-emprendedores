package com.feria.categorias;

public interface Categoria {
    public String getNombre();

    default public boolean equals(Categoria categoria) {
        return getNombre().equals(categoria.getNombre());
    }
}