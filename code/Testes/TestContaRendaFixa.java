import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
/**
* <h1>TestContaRendaFixa</h1>
* Classe Testes para as funções de ContaRendaFixa.
* @author Gabriel Alejandro Figueiro Galindo
* @since 06/10/2023
*/
public class TestContaRendaFixa{
    private ClienteGold gold;
    private ContaRendaFixa renda;
    private ClienteRegular regular;
    private ContaCorrente corrente;

    @Before
    public void setUp(){
        gold = new ClienteGold("Pedro Santos", "818.854.150-87", "Macacos1@#");//Cria um cliente Gold;
        renda = new ContaRendaFixa(34885, 1000.0, 20);//Cria uma conta Renda Fixa;
        gold.adicionarConta(renda);//Da a conta ao cliente;
    }

    //Testa a função aplicarRendimento();
    @Test
    public void testAplicarRendimento(){
        renda.aplicarRendimento();
        assertEquals(1200.0, renda.getSaldo());//Verifica se está certo;
    }

    //Testa a função Sacar();
    @Test
    public void testSacar(){
        double TotalRemovido = renda.Sacar(100);//Valor sacado + Imposto;
        assertEquals(130, TotalRemovido);
        assertEquals(1040, renda.getSaldo());
    }

    //Testa a função transferir();
    @Test
    public void testTransferir(){
        regular = new ClienteRegular("Maria Carla", "432.795.356-21", "Cacacos3@$");
        corrente = new ContaCorrente(42345, 1000.0);
        regular.adicionarConta(corrente);
        corrente.transferir(renda, 500);//Faz a transferência;
        //Verifica se a transferência foi feita;
        assertEquals(500.0, corrente.consultarSaldo());
        assertEquals(1500, renda.consultarSaldo());
    }
}