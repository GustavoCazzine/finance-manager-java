package app;

import utils.ConnectionFactory;
import models.TipoTransacao;
import models.Transacao;
import repository.TransacaoDAO;
import service.ServicoFinancas;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 1. PREPARAÇÃO DA INFRAESTRUTURA (Conexão e DAO)
        System.out.println("Conectando ao banco de dados...");
        Connection conexao = new ConnectionFactory().recuperarConexao();
        TransacaoDAO dao = new TransacaoDAO(conexao);

        // 2. INICIALIZAÇÃO DO SERVIÇO (Injetando o DAO)
        ServicoFinancas servico = new ServicoFinancas(dao);

        Scanner entradaUsuario = new Scanner(System.in);
        System.out.println("Sistema iniciado com sucesso!");

        // 3. LOOP PRINCIPAL
        while (true) {
            System.out.println("""
                    
                    === FINANÇAS APP (MySQL Edition) ===
                    1. Adicionar Receita
                    2. Adicionar Despesa
                    3. Exibir Saldo
                    4. Ver Extrato
                    5. Sair
                    Escolha uma opção:
                    """);

            int opcaoUsuario;
            try {
                opcaoUsuario = entradaUsuario.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números.");
                entradaUsuario.nextLine(); // Limpa buffer
                continue;
            }

            switch (opcaoUsuario) {
                case 1:
                    registrarEntrada(servico, entradaUsuario, TipoTransacao.RECEITA);
                    break;
                case 2:
                    registrarEntrada(servico, entradaUsuario, TipoTransacao.DESPESA);
                    break;
                case 3:
                    System.out.println("=== SALDO ===");
                    System.out.printf("R$ %.2f\n", servico.calcularSaldo());
                    break;
                case 4:
                    System.out.println("=== EXTRATO ===");
                    var lista = servico.getTransacoes();
                    for (Transacao tr : lista) {
                        String sinal = (tr.getTipo() == TipoTransacao.RECEITA) ? "+" : "-";
                        System.out.printf("%s | R$ %.2f (%s)\n", tr.getDescricao(), tr.getValor(), sinal);
                    }
                    System.out.println("--------------------");
                    System.out.printf("SALDO FINAL: R$ %.2f\n", servico.calcularSaldo());
                    break;
                case 5:
                    System.out.println("Fechando conexão e saindo...");
                    try {
                        entradaUsuario.close();
                        conexao.close(); // Muito importante fechar a conexão ao sair!
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Método auxiliar (Clean Code)
    private static void registrarEntrada(ServicoFinancas servico, Scanner scanner, TipoTransacao tipo) {
        try {
            scanner.nextLine(); // Limpa buffer
            String textoTipo = (tipo == TipoTransacao.RECEITA) ? "receita" : "despesa";

            System.out.println("Descrição da " + textoTipo + ":");
            String descricao = scanner.nextLine();

            System.out.println("Valor:");
            double valor = scanner.nextDouble();

            Transacao t = new Transacao(descricao, valor, tipo);
            servico.adicionarTransacao(t); // Salva no banco automaticamente!

            System.out.println("Sucesso! Salvo no banco de dados.");

        } catch (InputMismatchException e) {
            System.out.println("Erro: Valor inválido. Use vírgula para decimais (ex: 50,00).");
            scanner.nextLine();
        }
    }
}