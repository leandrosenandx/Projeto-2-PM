package MainCode;
import java.time.LocalDate;

import Conta;
/**
* <h1>Cliente Especial</h1>
* Classe Cliente Especial (subclasse de cliente).
* @author Leandro Sena de Andrade Machado
* @since 10/10/2023
*/
public abstract class ClienteEspecial extends Cliente{
    protected final double TaxaMensal;//Taxa mensal;
    protected int pontosFidelidade;//Pontos de fidelidade;
    protected int pontosMensais;//Pontos que o Cliente recebe por mês;
    protected int pontosSaldo;//Pontos que o Cliente recebe por saldo (SaldoPontos);
    protected int saldoPontos;//Valor de saldo que decide quando o cliente recebe pontos;

    //Construtor;
    public ClienteEspecial(String nome, String cpf, String senha, double taxaMensal, 
    int pontosMensais, int pontosSaldo, int saldoPontos){
        super(nome, cpf, senha);
        this.TaxaMensal = taxaMensal;
        this.pontosMensais = pontosMensais;
        this.pontosSaldo = pontosSaldo;
        this.saldoPontos = saldoPontos;
        pontosFidelidade = 0;
    }

    //Getters
    public double getTaxaMensal(){
        return TaxaMensal;
    }
    public int getPontosFidelidade(){
        return pontosFidelidade;
    }
    public int getPontosMensais(){
        return pontosMensais;
    }
    public int getPontosSaldo(){
        return pontosSaldo;
    }
    public int getSaldoPontos(){
        return saldoPontos;
    }

    //Setters
    public void setPontosFidelidade(int pontosFidelidade){
        this.pontosFidelidade = pontosFidelidade;
    }
    public void setPontosMensais(int pontosMensais){
        this.pontosMensais = pontosMensais;
    }
    public void setPontosSaldo(int pontosSaldo){
        this.pontosSaldo = pontosSaldo;
    }
    public void setSaldoPontos(int saldoPontos){
        this.saldoPontos = saldoPontos;
    }

    //Função que cobra a taxa mensal/mensalidade;
    public void aplicarTaxaMensal(){
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        if(currentDay == 1){
            for(Conta conta : contas){
                double saldo = conta.getSaldo();
                if(saldo >= TaxaMensal){
                    saldo -= TaxaMensal;
                    conta.setSaldo(saldo);
                    conta.adicionarTransacao("Taxa Mensal (mensalidade de cliente)", -TaxaMensal, conta.getSaldo());
                    break;
                }
            }
        }
    }

    //Aumento de pontos de fidelidade mensalmente;
    public void acumularPontosMensais(){
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        //Caso seja um novo mês, adiciona o valor de PontosMensais;
        if(currentDay == 1){
            pontosFidelidade += pontosMensais;
        }
    }

    //Aumento de pontos de fidelidade por saldo;
    public void acumularPontosPorSaldo(){
        double saldo;
        int pontosPorSaldo;
        //Olha o saldo de cada conta do cliente;
        for(Conta conta : contas){
            saldo = conta.getSaldo();
            pontosPorSaldo = (int)(saldo / saldoPontos) * pontosSaldo;
            pontosFidelidade += pontosPorSaldo;
        }
    }
}
