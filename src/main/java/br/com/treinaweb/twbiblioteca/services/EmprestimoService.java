package br.com.treinaweb.twbiblioteca.services;

import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Emprestimo;
import br.com.treinaweb.twbiblioteca.models.Obra;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    private static final int  DIAS_PARA_DEVOLUÇÃO = 3;

    public Emprestimo novo(Cliente cliente, List<Obra> obras){
        var emprestimo = new Emprestimo();

        var dataEmprestimo = LocalDate.now();
        var dataDevolucao = dataEmprestimo.plusDays(DIAS_PARA_DEVOLUÇÃO);

        emprestimo.setCliente(cliente);
        emprestimo.setLivros(obras);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);

        return emprestimo;
    }
}
