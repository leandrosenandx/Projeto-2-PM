/**
* <h1>ContaInvestimento</h1>
* Classe Conta Investimento (subclasse de conta).
* @author Gabriel Alejandro Figueiro Galindo.
* @since 07/10/2023
*/
public class ContaInvestimento extends Conta{
    private double rendimentoDiario;//Rendimento diário (positivo ou negativo).
    private final double Imposto = 15;//Imposto de 15% sobre o rendimento no saque;
    private final double Taxa = 1.5;//taxa de 1,5% sobre o rendimento no saque;

    //Construtor;
    public ContaInvestimento(int numeroConta, double saldo, double rendimentoDiario){
        super(numeroConta, saldo);
        this.rendimentoDiario = rendimentoDiario;
    }

    //Getters
    public double getRendimentoDiario(){
        return rendimentoDiario;
    }

    //Setters
    public void setRendimentoDiario(double rendimentoDiario){
        this.rendimentoDiario = rendimentoDiario;
    }

    //Aplicar o rendimento diário;
    public void aplicarRendimentoDiario(){
        double saldoAtual = getSaldo();
        double rendimentoHoje = saldoAtual * (rendimentoDiario / 100.0);
        double novoSaldo = saldoAtual + rendimentoHoje;
        setSaldo(novoSaldo);
        this.adicionarTransacao("Rendimento Diário", rendimentoHoje, this.getSaldo());
    }

    //Função de saque;
    @Override
    public double Sacar(double sacar){
        double saldoAtual = getSaldo();
        double rendimento = saldoAtual * (rendimentoDiario / 100.0);

        if(sacar <= saldoAtual){
            //Calcula o valor do imposto e taxa sobre o rendimento.
            double impostoSaque = rendimento * (Imposto / 100.0);
            double taxaSaque = rendimento * (Taxa / 100.0);

            //Valor do rendimento após o imposto e a taxa;
            double rendimentoTotal = rendimento - impostoSaque;
            rendimentoTotal -= taxaSaque;

            //Total removido após o imposto e a taxa do rendimento;
            double TotalRemovido = Math.round((sacar + impostoSaque + taxaSaque) * 100.0) / 100.0;
            
            saldoAtual = Math.round((saldoAtual + rendimentoTotal - TotalRemovido) * 100.0) / 100.0;
            setSaldo(saldoAtual);

            this.adicionarTransacao("Saque", -TotalRemovido, this.getSaldo());
            return TotalRemovido;
        }
        else{
            throw new IllegalArgumentException("Valor de saque excede o limite disponível.");
        }
    }
}