import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MainCode.Cliente;
/**
* <h1>Diretoria</h1>
* Classe Diretoria.
* @author Gabriel Alejandro Figueiro Galindo.
* @since 07/10/2023
*/
public class Diretoria{
    //1. Total em custódia para cada tipo de conta;
    public static void TotalCustodia(List<Cliente> clientes){
        double totalContaPoupanca = 0;
        double totalContaCorrente = 0;
        double totalContaRendaFixa = 0;
        double totalContaInvestimento = 0;
        for(Cliente cliente : clientes){
            for(Conta conta : cliente.getContas()){
                if(conta instanceof ContaPoupanca){
                    totalContaPoupanca += conta.getSaldo();
                } 
                else if(conta instanceof ContaCorrente){
                    totalContaCorrente += conta.getSaldo();
                } 
                else if(conta instanceof ContaRendaFixa){
                    totalContaRendaFixa += conta.getSaldo();
                } 
                else if(conta instanceof ContaInvestimento){
                    totalContaInvestimento += conta.getSaldo();
                }
            }
        }
        //Imprime os valores;
        System.out.println("Total em Poupanca: " + totalContaPoupanca);
        System.out.println("Total em Conta Corrente: " + totalContaCorrente);
        System.out.println("Total em Renda Fixa: " + totalContaRendaFixa);
        System.out.println("Total em Investimento: " + totalContaInvestimento);
    }

    //2. Saldo médio de todas as contas.
    public static void SaldoMedio(List<Cliente> clientes){
        double media = 0;
        int totalContas = 0;//mede o total de contas;
        for(Cliente cliente : clientes){
            for(Conta conta : cliente.getContas()){
                totalContas += 1;
                media += conta.getSaldo();
            }
        }
        media = media/totalContas;
        System.out.println("Saldo médio: " + media);
    }

    //3. Número de clientes com saldo total negativo.
    public static void SaldoNegativo(List<Cliente> clientes){
        int numNegativo = 0;//número de clientes com saldo total negativo;
        double saldo;//saldo do cliente;
        for(Cliente cliente : clientes){
            saldo = 0;
            for(Conta conta : cliente.getContas()){
                saldo += conta.getSaldo();
            }
            if(saldo <= 0){numNegativo += 1;}
        }
        System.out.println("Número de clientes com saldo total negativo: " + numNegativo);
    }

    //4. Clientes com maior e menor saldo total.
    public static void ClienteMaioreMenor(List<Cliente> clientes){
        Cliente clienteMaiorSaldo = clientes.get(0);
        Cliente clienteMenorSaldo = clientes.get(0);
        double saldoMaior;
        double saldoMenor;
        double saldo = 0;
        for(int i = 1; i < clientes.size(); i++){
            Cliente cliente = clientes.get(i);
            saldo = 0;
            for(Conta conta : cliente.getContas()){
                saldo += conta.getSaldo();
            }
            saldoMaior = 0;
            saldoMenor = 0;
            for(Conta conta : clienteMaiorSaldo.getContas()){
                saldoMaior += conta.getSaldo();
            }
            for(Conta conta : clienteMenorSaldo.getContas()){
                saldoMenor += conta.getSaldo();
            }
            if(saldo > saldoMaior){
                clienteMaiorSaldo = cliente;
            }
            if(saldo < saldoMenor){
                clienteMenorSaldo = cliente;
            }
        }
        //Imprime os valores;
        System.out.println("Cliente com maior saldo total: " + clienteMaiorSaldo.getNome());
        System.out.println("Cliente com menor saldo total: " + clienteMenorSaldo.getNome());
    }     

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //Cria uma lista de clientes;
        List<Cliente> clientes = new ArrayList<>();
        ClienteRegular cliente1 = new ClienteRegular("Pedro Santos", "818.854.150-87", "Macacos1@#");
        ClienteGold cliente2 = new ClienteGold("Alice Italia", "646.752.570-72", "Aacacos8@$");
        clientes.add(cliente1);
        clientes.add(cliente2);
        //Cria as contas;
        ContaPoupanca contaPoupanca = new ContaPoupanca(1, 1000.0);
        ContaCorrente contaCorrente = new ContaCorrente(2, -2000.0);
        ContaRendaFixa contaRendaFixa = new ContaRendaFixa(3, 3000.0, 30);
        ContaInvestimento contaInvestimento = new ContaInvestimento(4, 4000.0, 1);

        //Da as contas aos clientes;
        cliente1.adicionarConta(contaPoupanca);
        cliente1.adicionarConta(contaCorrente);
        cliente2.adicionarConta(contaRendaFixa);
        cliente2.adicionarConta(contaInvestimento);

        int opcao;//opção do menu selecionada;
        do{
            System.out.println("Menu:");
            System.out.println("1. Total em custódia para cada tipo de conta.");
            System.out.println("2. Saldo médio de todas as contas.");
            System.out.println("3. Número de clientes com saldo total negativo.");
            System.out.println("4. Clientes com maior e menor saldo total.");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            switch(opcao){
                case 1:
                    TotalCustodia(clientes);
                    break;
                case 2:
                    SaldoMedio(clientes);
                    break;
                case 3:
                    SaldoNegativo(clientes);
                    break;
                case 4:
                    ClienteMaioreMenor(clientes);
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Escolha inválida.");
            }
        }while(opcao != 0);
        scanner.close();
    }
}