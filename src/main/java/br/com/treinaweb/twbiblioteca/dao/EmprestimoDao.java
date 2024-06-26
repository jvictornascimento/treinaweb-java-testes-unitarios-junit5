package br.com.treinaweb.twbiblioteca.dao;

import br.com.treinaweb.twbiblioteca.models.Emprestimo;
import br.com.treinaweb.twbiblioteca.services.EmprestimoService;

import java.util.List;

public interface EmprestimoDao {
    List<Emprestimo> buscarTodos();
}
