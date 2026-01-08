package service;

import models.TipoTransacao;
import models.Transacao;
import repository.RepositorioTransacoes;
import java.util.List;

public class ServicoFinancas {

    private RepositorioTransacoes repositorio;

    public ServicoFinancas(RepositorioTransacoes repositorio) {
        this.repositorio = repositorio;
    }

    public List<Transacao> getTransacoes() {
        return repositorio.listarTransacoes();
    }

    public double calcularSaldo(){
        double saldoAtual = 0;
        // Pede a lista FRESCA para o repositório
        for (Transacao tr : repositorio.listarTransacoes()){
            if (tr.getTipo() == TipoTransacao.RECEITA){
                saldoAtual += tr.getValor();
            }
            if (tr.getTipo() == TipoTransacao.DESPESA){
                saldoAtual -= tr.getValor();
            }
        }
        return saldoAtual;
    }

    public void adicionarTransacao(Transacao t){
        if (t.getValor() <= 0){
            return;
        }
        repositorio.adicionarTransacao(t);
    }
}