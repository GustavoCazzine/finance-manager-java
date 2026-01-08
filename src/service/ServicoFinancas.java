package service;

import models.TipoTransacao;
import models.Transacao;
import repository.RepositorioTransacoes;

import java.util.List;

public class ServicoFinancas {
    //Atributos
    private RepositorioTransacoes repositorio;

    public ServicoFinancas(RepositorioTransacoes repositorio) {
        this.repositorio = repositorio;
    }

    //Metodos
    public double calcularSaldo(){
        double saldoAtual = 0;
        List <Transacao> historicoTr = repositorio.listarTransacoes();

        for (Transacao tr : historicoTr){
            if (tr.getTipo() == TipoTransacao.RECEITA){
                saldoAtual += tr.getValor();
            }

            if (tr.getTipo() == TipoTransacao.DESPESA){
                saldoAtual -= tr.getValor();
            }
        }
        return saldoAtual;
    }

    public boolean adicionarTransacao(Transacao t){
        if (t.getValor() <= 0){
            return false;
        }
        repositorio.adicionarTransacao(t);
        return true;
    }
}
