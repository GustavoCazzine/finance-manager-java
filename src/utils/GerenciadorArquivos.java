package utils;

import models.TipoTransacao;
import models.Transacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private static final String NOME_ARQUIVO = "dados.csv";

    public void salvarTransacoes(List<Transacao> transacoes){
        try (BufferedWriter writer = new BufferedWriter((new FileWriter(NOME_ARQUIVO)))){
            for (Transacao tr : transacoes){
                writer.write(tr.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public List<Transacao> carretarTransacoes(){
        List<Transacao> transacoes = new ArrayList<>();
        File arquivo = new File(NOME_ARQUIVO);
        if(!arquivo.exists()){
            return transacoes;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))){
            String linha;

            while ((linha = reader.readLine()) != null){
                String[] partes = linha.split(";");
                String descricao = partes[0];
                double valor = Double.parseDouble(partes[1]);
                TipoTransacao tipo = TipoTransacao.valueOf(partes[2]);

                Transacao t = new Transacao(descricao, valor, tipo);
                transacoes.add(t);
            }
        } catch (IOException e){
            System.err.println("Erro ao ler arquivo " + e.getMessage());
        }
        return transacoes;
    }
}
