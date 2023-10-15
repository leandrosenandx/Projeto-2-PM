import java.util.ArrayList;
import java.util.List;
/**
* <h1>Cliente</h1>
* Classe Cliente.
* @author Gabriel Alejandro Figueiro Galindo
* @since 29/09/2023
*/
public abstract class Cliente{
    protected String nome;//Nome do Cliente;
    protected String cpf;//CPF do Cliente;
    protected String senha;//Senha do Cliente;
    protected List<Conta> contas;//Lista de contas do cliente;

    //Construtor;
    public Cliente(String nome, String cpf, String senha){
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.contas = new ArrayList<>();
    }
    
    //Getters
    public String getNome(){
        return nome;
    }
    public String getCPF(){
        return cpf;
    }
    public String getSenha(){
        return senha;
    }
    public List<Conta> getContas(){
        return contas;
    }

    //Setters
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCPF(String cpf){
        this.cpf = cpf;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public void setContas(List<Conta> contas){
        this.contas = contas;
    }
    
    //Metodo para adicionar a conta na lista do cliente;
    public void adicionarConta(Conta conta){
        contas.add(conta);
    }
}