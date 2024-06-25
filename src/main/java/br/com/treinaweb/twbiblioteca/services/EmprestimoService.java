package br.com.treinaweb.twbiblioteca.services;

import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Emprestimo;
import br.com.treinaweb.twbiblioteca.models.Obra;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {
    public Emprestimo novo(Cliente cliente, List<Obra> obras){
        if(cliente == null){
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        if(obras ==null || obras.isEmpty()){
            throw new IllegalArgumentException("Obra não pode ser nulo nem vazio");
        }

        var emprestimo = new Emprestimo();

        var dataEmprestimo = LocalDate.now();
        var diasParaDevolucao = cliente.getReputacao().obterDiasParaDevolucao();
        var dataDevolucao = dataEmprestimo.plusDays(diasParaDevolucao);

        emprestimo.setCliente(cliente);
        emprestimo.setLivros(obras);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);

        return emprestimo;
    }
}
