package com.feria.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidadoresTest {

    // --- validarEmail ---

    @Test
    void validarEmail_emailValido_retornaTrue() {
        assertTrue(Validadores.validarEmail("ana@gmail.com"));
    }

    @Test
    void validarEmail_sinArroba_retornaFalse() {
        assertFalse(Validadores.validarEmail("anagmail.com"));
    }

    @Test
    void validarEmail_emailNull_retornaFalse() {
        assertFalse(Validadores.validarEmail(null));
    }

    @Test
    void validarEmail_emailMuyCorto_retornaFalse() {
        // menos de 5 caracteres: "a@b" tiene 3
        assertFalse(Validadores.validarEmail("a@b"));
    }

    @Test
    void validarEmail_exactamenteCincoCaracteres_retornaTrue() {
        // "a@b.c" tiene exactamente 5 caracteres y tiene @
        assertTrue(Validadores.validarEmail("a@b.c"));
    }

    // --- validarNombre ---

    @Test
    void validarNombre_nombreValido_retornaTrue() {
        assertTrue(Validadores.validarNombre("Ana"));
    }

    @Test
    void validarNombre_nombreDeUnCaracter_retornaFalse() {
        assertFalse(Validadores.validarNombre("A"));
    }

    @Test
    void validarNombre_nombreNull_retornaFalse() {
        assertFalse(Validadores.validarNombre(null));
    }

    @Test
    void validarNombre_exactamenteDosCaracteres_retornaTrue() {
        assertTrue(Validadores.validarNombre("AB"));
    }

    // --- validarTelefono ---

    @Test
    void validarTelefono_telefonoValido_retornaTrue() {
        assertTrue(Validadores.validarTelefono("12345678"));
    }

    @Test
    void validarTelefono_telefonoCorto_retornaFalse() {
        assertFalse(Validadores.validarTelefono("1234567"));
    }

    @Test
    void validarTelefono_telefonoNull_retornaFalse() {
        assertFalse(Validadores.validarTelefono(null));
    }

    // --- validarPrecio ---

    @Test
    void validarPrecio_precioPositivo_retornaTrue() {
        assertTrue(Validadores.validarPrecio(100.0));
    }

    @Test
    void validarPrecio_precioCero_retornaFalse() {
        assertFalse(Validadores.validarPrecio(0.0));
    }

    @Test
    void validarPrecio_precioNegativo_retornaFalse() {
        assertFalse(Validadores.validarPrecio(-1.0));
    }

    // --- validarStock ---

    @Test
    void validarStock_stockCero_retornaTrue() {
        assertTrue(Validadores.validarStock(0));
    }

    @Test
    void validarStock_stockPositivo_retornaTrue() {
        assertTrue(Validadores.validarStock(10));
    }

    @Test
    void validarStock_stockNegativo_retornaFalse() {
        assertFalse(Validadores.validarStock(-1));
    }
}
