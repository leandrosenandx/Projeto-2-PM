import java.time.LocalDate;
/**
* <h1>Transacao</h1>
* Classe Transação para o extrato das contas.
* @author Gabriel Alejandro Figueiro Galindo
* @since 13/10/2023
*/
class Transacao{
    private LocalDate data;//Data em que a transação ocorreu;
    private String descricao;//Descrição da transação;
    private double valor;//Valor da transação;
    private double saldo;//Saldo atual;

    //Construtor;
    public Transacao(LocalDate data, String descricao, double valor, double saldo){
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.saldo = saldo;
    }

    //Getters
    public LocalDate getData(){
        return data;
    }

    public String getDescricao(){
        return descricao;
    }

    public double getValor(){
        return valor;
    }

    public double getSaldo(){
        return saldo;
    }
}
