package br.com.treinaweb.twbiblioteca.model;

import br.com.treinaweb.twbiblioteca.models.Autor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutorTest {
    @Test
    public void testaMedotoEstaVivo(){
        var autor =new Autor();

        var estaVivo = autor.estaVivo();
        Assertions.assertEquals(true,estaVivo);


    }
}
