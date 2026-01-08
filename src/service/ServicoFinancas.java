package service;

import models.TipoTransacao;
import models.Transacao;
import repository.TransacaoDAO; // Agora usamos o DAO

import java.util.List;

public class ServicoFinancas {

    // O serviço agora depende do DAO (Banco de Dados)
    private TransacaoDAO dao;

    public ServicoFinancas(TransacaoDAO dao) {
        this.dao = dao;
    }

    public void adicionarTransacao(Transacao t) {
        if (t.getValor() <= 0) {
            System.out.println("Valor inválido: deve ser maior que zero.");
            return;
        }
        // O DAO salva direto no MySQL. Não precisa "salvar arquivo" depois.
        dao.salvar(t);
    }

    public List<Transacao> getTransacoes() {
        // Pede para o DAO buscar tudo no banco agora
        return dao.listar();
    }

    public double calcularSaldo() {
        double saldoAtual = 0;
        // Pega a lista fresca do banco e calcula
        List<Transacao> transacoes = dao.listar();

        for (Transacao tr : transacoes) {
            if (tr.getTipo() == TipoTransacao.RECEITA) {
                saldoAtual += tr.getValor();
            } else if (tr.getTipo() == TipoTransacao.DESPESA) {
                saldoAtual -= tr.getValor();
            }
        }
        return saldoAtual;
    }
}