/**
* <h1>Cliente Gold</h1>
* Classe Cliente Gold (subclasse de cliente).
* @author Leandro Sena de Andrade Machado
* @since 07/10/2023
*/
public class ClienteGold extends ClienteEspecial{
    //Construtor;
    public ClienteGold(String nome, String cpf, String senha){
        super(nome, cpf, senha, 10, 10, 10, 1000);
    }
}
