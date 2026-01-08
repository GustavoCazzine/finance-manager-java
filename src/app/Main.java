package app;

import models.TipoTransacao;
import models.Transacao;
import repository.RepositorioTransacoes;
import service.ServicoFinancas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //Iniciando repository
        RepositorioTransacoes repository = new RepositorioTransacoes();
        //Iniciando serviços
        ServicoFinancas servico = new ServicoFinancas(repository);
        //Iniciando Scanner
        Scanner entradaUsuario = new Scanner(System.in);
        //Iniciando loop principal
        while (true){
            //Iniciando variaveis mais utilizadas
            String descUsuario;
            double valUsuario;
            int opcaoUsuario;

            //Exibir menu
            System.out.println("""
                    
                    === FINANÇAS APP ===
                    1. Adicionar Receita
                    2. Adicionar Despesa
                    3. Exibir Saldo
                    4. Sair
                    Escolha uma opção:
                    """);
            //Usuario escolhe uma opção
            if (entradaUsuario.hasNextInt()){
                opcaoUsuario = entradaUsuario.nextInt();
            } else {
                System.out.println("Opção inválida! Digite um número.");
                entradaUsuario.nextLine();
                continue;
            }

            //Switch para guiar o usuario
            switch (opcaoUsuario){
                //Adicionar RECEITA
                case 1:
                    registrarEntrada(servico, entradaUsuario, TipoTransacao.RECEITA);

                    break;
                //Adicionar DESPESA
                case 2:
                    registrarEntrada(servico, entradaUsuario, TipoTransacao.DESPESA);

                    break;
                //Ver saldo atual
                case 3:
                    System.out.println("=== FINANÇAS APP ===\n");
                    System.out.printf("Saldo atual: %.2f", servico.calcularSaldo());
                    break;
                //Encerrar sistema
                case 4:
                    System.out.println("Finalizando programa...");
                    entradaUsuario.close();
                    return;
                //Qualquer opção inválida
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    entradaUsuario.nextLine();
                    continue;
            }
        }

    }

    private static void registrarEntrada(ServicoFinancas servico, Scanner entradaUsuario, TipoTransacao tipo) {
        String descUsuario;
        double valUsuario;
        try{
            String textoTipo = (tipo == TipoTransacao.RECEITA) ? "receita" : "despesa";
            //Entrada dos dados
            System.out.println("=== FINANÇAS APP ===\n");
            entradaUsuario.nextLine();
            System.out.println("Descrição da " + textoTipo + ":");
            descUsuario = entradaUsuario.nextLine().trim().toLowerCase();
            System.out.println("Valor:");
            valUsuario = entradaUsuario.nextDouble();
            //Registrando transaçao
            servico.adicionarTransacao(new Transacao(descUsuario, valUsuario, tipo));
            System.out.println(textoTipo + " registrada com sucesso.");
            return;
        } catch (InputMismatchException e){
            System.err.println("Erro: Caractere inválido inserido. Por favor, digite corretamente.");
            entradaUsuario.nextLine();
        }
    }
}
