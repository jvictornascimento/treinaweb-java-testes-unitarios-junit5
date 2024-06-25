package br.com.treinaweb.twbiblioteca.model;

import br.com.treinaweb.twbiblioteca.models.Autor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AutorTest {
    @Test
    void quandoMetodoEstaVivoForChamadoComDataFalecimentoNulaDeveRetornarTrue(){
        var autor =new Autor();

        var estaVivo = autor.estaVivo();
        assertTrue(estaVivo);
    }
    @Test
    void quandoMetodoForChamadoComDataFalecimetnoPreenchidaDeveRetornarFalse(){
        //cenario
        var autor = new Autor();
        autor.setDataFalecimento(LocalDate.now());

        //execução
        var estaVivo= autor.estaVivo();

        //verificação
        assertFalse(estaVivo);
    }
}
