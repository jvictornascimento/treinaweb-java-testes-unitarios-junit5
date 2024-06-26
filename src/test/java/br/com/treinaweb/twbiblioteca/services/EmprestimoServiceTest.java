package br.com.treinaweb.twbiblioteca.services;

import br.com.treinaweb.twbiblioteca.builders.ClienteBuilder;
import br.com.treinaweb.twbiblioteca.builders.ObraBuilder;
import br.com.treinaweb.twbiblioteca.enums.Reputacao;
import br.com.treinaweb.twbiblioteca.models.Obra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmprestimoServiceTest {

    private EmprestimoService emprestimoService;

    @BeforeEach
    void setUp(){
       emprestimoService = new EmprestimoService();
    }
    @Test
    void quandoMetodoForChamadoDeveRetornarUmEmprestimo(){
        // cenario
        var cliente  = ClienteBuilder.builder().build();
        var obra = ObraBuilder.builder().build();

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
        var cliente  = ClienteBuilder.builder().reputacao(Reputacao.RUIM).build();
        var obra = ObraBuilder.builder().build();

        //execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }
    @Test
    void quandoMetodoForChamadoComCLienteDeReputacaoRegularDeveRetornarUmEmpretimoComDevolucaoParaTresDias(){
        // cenario
        var cliente  = ClienteBuilder.builder().build();
        var obra = ObraBuilder.builder().build();

        //execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }
    @Test
    void quandoMetodoForChamadoComCLienteDeReputacaoBoaDeveRetornarUmEmpretimoComDevolucaoParaCincoDias(){
        // cenario
        var cliente  = ClienteBuilder.builder().reputacao(Reputacao.BOA).build();
        var obra = ObraBuilder.builder().build();

        //execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoUmMetodoForChamadoComObrasNulasDeveLancarUmaExcecaoDoTipoIligalArgumentException(){

        var cliente  = ClienteBuilder.builder().build();
        var mensagemEsperada = "Obra não pode ser nulo nem vazio";

        var exceptions = assertThrows(IllegalArgumentException.class, ()-> emprestimoService.novo(cliente,null));
        assertEquals(mensagemEsperada,exceptions.getMessage());
    }

    @Test
    void quandoUmMetodoForChamadoComObrasVaziasDeveLancarUmaExcecaoDoTipoIligalArgumentException(){

        var cliente  = ClienteBuilder.builder().build();
        var obras = new ArrayList<Obra>();
        var mensagemEsperada = "Obra não pode ser nulo nem vazio";

        var exceptions = assertThrows(IllegalArgumentException.class, ()-> emprestimoService.novo(cliente,obras));
        assertEquals(mensagemEsperada,exceptions.getMessage());
    }
    @Test
    void quandoUmMetodoForChamadoComClienteNuloDeveLancarUmaExcecaoDoTipoIligalArgumentException(){

        var obra = ObraBuilder.builder().build();
        var mensagemEsperada = "Cliente não pode ser nulo";

        var exceptions = assertThrows(IllegalArgumentException.class, ()-> emprestimoService.novo(null,List.of(obra)));
        assertEquals(mensagemEsperada,exceptions.getMessage());
    }

}