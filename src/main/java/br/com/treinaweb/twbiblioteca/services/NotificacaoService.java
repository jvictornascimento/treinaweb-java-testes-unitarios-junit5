package br.com.treinaweb.twbiblioteca.services;

import br.com.treinaweb.twbiblioteca.models.Emprestimo;

public interface NotificacaoService {
    void notificar(Emprestimo emprestimo);
}
