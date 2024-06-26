package br.com.treinaweb.twbiblioteca.builders;

import br.com.treinaweb.twbiblioteca.models.Autor;

import java.time.LocalDate;

public class AutorBuilder {
    private Autor autor;
    public static AutorBuilder builder(){
    var builder = new AutorBuilder();
    var autor = new Autor(1L, "Autor teste", LocalDate.now(),null);
    builder.autor = autor;

    return builder;
    }
    public Autor build(){
        return autor;
    }



}
