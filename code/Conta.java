import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
/**
* <h1>Conta</h1>
* Classe Conta do cliente.
* @author Leandro Sena de Andrade Machado
* @since 29/09/2023
*/
public abstract class Conta{
    protected int numeroConta;//Número da conta;
    protected double saldo;//Saldo da conta;
    protected List<Transacao> transacoes;//Lista de transações que ocorreram na conta;

    //Construtor;
    public Conta(int numeroConta, double saldo){
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.transacoes = new ArrayList<>();
    }

    //Getters
    public int getNumeroConta(){
        return numeroConta;
    }
    public double getSaldo(){
        return saldo;
    }
    public List<Transacao> getTransacoes(){
        return transacoes;
    }

    //Setters
    public void setNumeroConta(int numeroConta){
        this.numeroConta = numeroConta;
    }
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    public void setTransacoes(List<Transacao> transacoes){
        this.transacoes = transacoes;
    }

    //Método para adicionar uma transação ao histórico;
    public void adicionarTransacao(String descricao, double valor, double valorSaldo){
        Transacao transacao = new Transacao(LocalDate.now(), descricao, valor, valorSaldo);
        transacoes.add(transacao);
    }

    //Método para consultar o saldo de qualquer uma de suas contas a qualquer momento;
    public double consultarSaldo(){
        return this.getSaldo();
    }

    //Método para pegar as transações do ultimos 30 dias;
    public List<Transacao> getTransacoesDosUltimos30Dias(){
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataLimite = dataAtual.minusDays(30);
        List<Transacao> transacoes30Dias = new ArrayList<>();
        for(Transacao transacao : transacoes){
            if(transacao.getData().isAfter(dataLimite)){
                transacoes30Dias.add(transacao);
            }
        }
        return transacoes30Dias;
    }

    //Método para depositar qualquer quantia em suas contas;
    public void Depositar(double deposito){
        double saldoAtual = this.getSaldo();
        saldoAtual += deposito;
        this.setSaldo(saldoAtual);
        this.adicionarTransacao("Deposito", deposito, saldoAtual);
    }

    /*Método para sacar até o limite de saldo disponível em sua conta-corrente. 
    Para a conta poupança, o saque é limitado ao saldo atual. 
    Para contas de renda fixa e investimento, os saques podem incorrer em penalidades ou impostos*/
    public abstract double Sacar(double sacar);

    /*Método para realizar transferências entre suas contas e para contas de outros clientes, 
    desde que não ultrapasse o saldo disponível.*/
    public void transferir(Conta destino, double valor){
        double saldoAtual = getSaldo();
        if(valor <= saldoAtual){
            //O dinheiro a ser transferido está dentro do saldo disponível;
            saldoAtual -= valor;
            setSaldo(saldoAtual);//Tira dinheiro da conta de origem;
            double saldoDestino = destino.getSaldo();
            saldoDestino += valor;
            destino.setSaldo(saldoDestino);//Aumenta o saldo da conta de destino;
            //Adicionar na transação;
            destino.adicionarTransacao("Transferência (recebeu)", valor, destino.getSaldo());
            this.adicionarTransacao("Transferência (enviou)", -valor, this.getSaldo());
        }
        else{
            //Saldo não disponível;
            throw new IllegalArgumentException("Saldo não disponível.");
        }
    }   
}