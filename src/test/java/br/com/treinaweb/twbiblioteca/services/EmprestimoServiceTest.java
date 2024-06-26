package br.com.treinaweb.twbiblioteca.services;

import br.com.treinaweb.twbiblioteca.builders.ClienteBuilder;
import br.com.treinaweb.twbiblioteca.builders.EmprestimoBuilder;
import br.com.treinaweb.twbiblioteca.builders.ObraBuilder;
import br.com.treinaweb.twbiblioteca.dao.EmprestimoDao;
import br.com.treinaweb.twbiblioteca.enums.Reputacao;
import br.com.treinaweb.twbiblioteca.models.Obra;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmprestimoServiceTest {

    @Mock
    private EmprestimoDao emprestimoDao;

    @Mock
    private NotificacaoService notificacaoService;

    @InjectMocks
    private EmprestimoService emprestimoService;

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

    @Test
    void quandoMetodoNotificarAtrasoForChamadoDeveRetornarNumeroDeNotificacoes() {
        var emprestimos = List.of(
                EmprestimoBuilder.builder().build(),
                EmprestimoBuilder.builder().dataDevolucao(LocalDate.now().minusDays(1)).build()
        );
        when(emprestimoDao.buscarTodos()).thenReturn(emprestimos);
        emprestimoService.notificarAtraso();
        verify(notificacaoService).notificar(emprestimos.get(1));

    }

}