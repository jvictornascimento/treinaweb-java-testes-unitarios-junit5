package br.com.treinaweb.twbiblioteca.services;

import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Emprestimo;
import br.com.treinaweb.twbiblioteca.models.Obra;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {
    public Emprestimo novo(Cliente cliente, List<Obra> obras){
        var emprestimo = new Emprestimo();

        emprestimo.setCliente(cliente);
        emprestimo.setLivros(obras);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(3));

        return emprestimo;
    }
}
