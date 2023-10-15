import java.time.LocalDate;
/**
* <h1>Conta Corrente</h1>
* Classe Conta Corrente (subclasse de conta).
* @author Gabriel Alejandro Figueiro Galindo.
* @since 02/10/2023
*/
public class ContaCorrente extends Conta{
    private final double TaxaMensal = 20;//Taxa mensal exclusiva;
    private double SaqueEspecial;//Saque especial até R$200 além do saldo (8% ao mês de juros);

    //Construtor;
    public ContaCorrente(int numeroConta, double saldo){
        super(numeroConta, saldo);
        this.SaqueEspecial = 200.0;
    }

    //Getters
    public double getTaxaMensal(){
        return TaxaMensal;
    }
    public double getSaqueEspecial(){
        return SaqueEspecial;
    }

    //Setters
    public void setSaqueEspecial(double SaqueEspecial){
        this.SaqueEspecial = SaqueEspecial;
    }

    //Função que cobra a taxa mensal (chamar no começo de cada mês);
    public void aplicarTaxaMensal(){
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        if(currentDay == 1){
            double saldoAtual = getSaldo();
            double novoSaldo = saldoAtual - TaxaMensal;
            setSaldo(novoSaldo);
            this.adicionarTransacao("Taxa Mensal (Conta Corrente)", -TaxaMensal, this.getSaldo());
        }
    }

    //Função de saque (possui um Saque especial até R$200);
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
        else if(sacar <= saldoAtual + getSaqueEspecial()){
            //O saque ultrapassa o saldo, aplica juros ao valor excedente.
            double excedente = sacar - saldoAtual;
            double juros = excedente * 0.08;//8% de juros;
            saldoAtual = 0;
            SaqueEspecial -= excedente + juros;
            setSaldo(saldoAtual);
            setSaqueEspecial(SaqueEspecial);
            this.adicionarTransacao("Saque Especial", -sacar, this.getSaldo());
            return sacar;
        } 
        else{
            //O valor a ser sacado é maior do que o limite permitido.
            throw new IllegalArgumentException("Valor de saque excede o limite disponível.");
        }
    }
}
