package br.com.treinaweb.twbiblioteca.services;

import br.com.treinaweb.twbiblioteca.enums.Reputacao;
import br.com.treinaweb.twbiblioteca.enums.Tipo;
import br.com.treinaweb.twbiblioteca.models.Autor;
import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Obra;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoServiceTest {
    @Test
    void quandoMetodoForChamadoDeveRetornarUmEmprestimo(){
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente  = new Cliente(1L,"Cliente teste",LocalDate.now(),"123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor teste",LocalDate.now(),null);
        var obra = new Obra(1L,"Obra Teste",100, Tipo.LIVRO,autor);

        //execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(cliente, emprestimo.getCliente());
        assertEquals(List.of(obra), emprestimo.getLivros());
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        assertEquals(LocalDate.now().plusDays(3) ,emprestimo.getDataDevolucao());

    }
    @Test
    void quandoMetodoForChamadoComCLienteDeReputacaoRuimDeveRetornarUmEmpretimoComDevolucaoParaUmDia(){
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente  = new Cliente(1L,"Cliente teste",LocalDate.now(),"123.123.123-11", Reputacao.RUIM);
        var autor = new Autor(1L, "Autor teste",LocalDate.now(),null);
        var obra = new Obra(1L,"Obra Teste",100, Tipo.LIVRO,autor);

        //execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }
    @Test
    void quandoMetodoForChamadoComCLienteDeReputacaoRegularDeveRetornarUmEmpretimoComDevolucaoParaTresDias(){
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente  = new Cliente(1L,"Cliente teste",LocalDate.now(),"123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor teste",LocalDate.now(),null);
        var obra = new Obra(1L,"Obra Teste",100, Tipo.LIVRO,autor);

        //execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }
    @Test
    void quandoMetodoForChamadoComCLienteDeReputacaoBoaDeveRetornarUmEmpretimoComDevolucaoParaCincoDias(){
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente  = new Cliente(1L,"Cliente teste",LocalDate.now(),"123.123.123-11", Reputacao.BOA);
        var autor = new Autor(1L, "Autor teste",LocalDate.now(),null);
        var obra = new Obra(1L,"Obra Teste",100, Tipo.LIVRO,autor);

        //execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoUmMetodoForChamadoComObrasNulasDeveLancarUmaExcecaoDoTipoIligalArgumentException(){

        var emprestimoService = new EmprestimoService();
        var cliente  = new Cliente(1L,"Cliente teste",LocalDate.now(),"123.123.123-11", Reputacao.BOA);
        var mensagemEsperada = "Obra não pode ser nulo nem vazio";

        var exceptions = assertThrows(IllegalArgumentException.class, ()-> emprestimoService.novo(cliente,null));
        assertEquals(mensagemEsperada,exceptions.getMessage());
    }

    @Test
    void quandoUmMetodoForChamadoComObrasVaziasDeveLancarUmaExcecaoDoTipoIligalArgumentException(){

        var emprestimoService = new EmprestimoService();
        var cliente  = new Cliente(1L,"Cliente teste",LocalDate.now(),"123.123.123-11", Reputacao.BOA);
        var obras = new ArrayList<Obra>();
        var mensagemEsperada = "Obra não pode ser nulo nem vazio";

        var exceptions = assertThrows(IllegalArgumentException.class, ()-> emprestimoService.novo(cliente,obras));
        assertEquals(mensagemEsperada,exceptions.getMessage());
    }
    @Test
    void quandoUmMetodoForChamadoComClienteNuloDeveLancarUmaExcecaoDoTipoIligalArgumentException(){

        var emprestimoService = new EmprestimoService();
        var autor = new Autor(1L, "Autor teste",LocalDate.now(),null);
        var obra = new Obra(1L,"Obra Teste",100, Tipo.LIVRO,autor);
        var mensagemEsperada = "Cliente não pode ser nulo";

        var exceptions = assertThrows(IllegalArgumentException.class, ()-> emprestimoService.novo(null,List.of(obra)));
        assertEquals(mensagemEsperada,exceptions.getMessage());
    }

}