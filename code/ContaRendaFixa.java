/**
* <h1>ContaRendaFixa</h1>
* Classe Conta Renda Fixa (subclasse de conta).
* @author Gabriel Alejandro Figueiro Galindo.
* @since 06/10/2023
*/
public class ContaRendaFixa extends Conta{
    private final double Rendimento;//Rendimento contratado no momento da criação;
    private final double Imposto = 15;//Imposto de 15% sobre o rendimento no saque;

    //Construtor;
    public ContaRendaFixa(int numeroConta, double saldo, double rendimento){
        super(numeroConta, saldo);
        this.Rendimento = rendimento;
    }

    //Método para calcular o rendimento e aplicá-lo à conta;
    public void aplicarRendimento(){
        double saldoAtual = getSaldo();
        double rendimento = saldoAtual * (Rendimento / 100.0);
        double novoSaldo = saldoAtual + rendimento;
        setSaldo(novoSaldo);
        this.adicionarTransacao("Rendimento contratado", rendimento, this.getSaldo());
    }

    //Método para efetuar um saque com imposto;
    @Override
    public double Sacar(double sacar){
        double saldoAtual = getSaldo();
        double rendimento = saldoAtual * (Rendimento / 100.0);

        if(sacar <= saldoAtual){
            //Calcula o valor do imposto sobre o rendimento;
            double impostoRendimento = rendimento * (Imposto / 100.0);

            //Valor do rendimento após o imposto;
            double rendimentoTotal = rendimento - impostoRendimento;

            //Total removido após o imposto do rendimento;
            double TotalRemovido = sacar + impostoRendimento;

            //Atualiza o saldo e retorna o valor sacado;
            saldoAtual += rendimentoTotal;
            saldoAtual -= TotalRemovido;
            setSaldo(saldoAtual);
            this.adicionarTransacao("Saque", -TotalRemovido, this.getSaldo());
            return TotalRemovido;
        } 
        else{
            throw new IllegalArgumentException("Valor de saque excede o limite disponível.");
        }
    }
}