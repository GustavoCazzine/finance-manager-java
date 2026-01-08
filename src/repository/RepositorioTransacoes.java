package repository;

import models.TipoTransacao;
import models.Transacao;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTransacoes {
    //Atributos
    private final List<Transacao> transacoes = new ArrayList<>();
    //Metodos
    public boolean adicionarTransacao(Transacao t){
        return transacoes.add(t);
    }

    public List<Transacao> listarTransacoes(){
        return transacoes;
    }
}
