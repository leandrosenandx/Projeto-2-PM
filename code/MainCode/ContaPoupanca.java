import java.time.LocalDate;
/**
* <h1>Conta Poupança</h1>
* Classe Conta Poupança (subclasse de conta).
* @author Gabriel Alejandro Figueiro Galindo.
* @since 06/10/2023
*/
public class ContaPoupanca extends Conta{
    //Rendimento fixo de 0,5% no dia 5 de cada mês.
    private final double Rendimento = 0.5;

    //Construtor;
    public ContaPoupanca(int numeroConta, double saldo){
        super(numeroConta, saldo);
    }

    //Aplicar o rendimento;
    public void aplicarRendimento(){
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        if(currentDay == 5){
            double saldoAtual = getSaldo();
            double rendimentoMensal = saldoAtual * (Rendimento / 100.0);
            double novoSaldo = saldoAtual + rendimentoMensal;
            setSaldo(novoSaldo);
            this.adicionarTransacao("Rendimento fixo", rendimentoMensal, this.getSaldo());
        }
    }

    //Função de saque;
    @Override
    public double Sacar(double sacar){
        double saldoAtual = getSaldo();
        if(sacar <= saldoAtual){
            //O saque é dentro do saldo disponível, sem aplicar juros.
            saldoAtual -= sacar;
            setSaldo(saldoAtual);
            this.adicionarTransacao("Saque", -sacar, this.getSaldo());
            return sacar;
        }
        else{
            //O valor a ser sacado é maior do que o limite permitido.
            throw new IllegalArgumentException("Valor de saque excede o limite disponível.");
        }
    }
}