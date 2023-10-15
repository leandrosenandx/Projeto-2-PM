/**
* <h1>Cliente VIP</h1>
* Classe Cliente VIP (subclasse de cliente).
* @author Gabriel Alejandro Figueiro Galindo.
* @since 07/10/2023
*/
public class ClienteVIP extends ClienteEspecial{
    //Construtor;
    public ClienteVIP(String nome, String cpf, String senha){
        super(nome, cpf, senha, 30, 35, 30, 2000);
    }
}
