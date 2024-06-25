package br.com.treinaweb.twbiblioteca.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AutorTest {

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