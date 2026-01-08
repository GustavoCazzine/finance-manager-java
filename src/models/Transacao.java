package models;

import models.TipoTransacao;

public class Transacao {
    //Atributos
    private String descricao;
    private double valor;
    private TipoTransacao tipo;
    //Construtor
    public Transacao(String descricao, double valor, TipoTransacao tipo) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    //Metodos
    public String toCSV(){
        return getDescricao() + ";" + getValor() + ";" + getTipo();
    }

    //Metodos especiais
    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }
    //toString
    @Override
    public String toString() {
        return "Transacao{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", tipo=" + tipo +
                '}';
    }
}
