import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
/**
* <h1>TestContaPoupanca</h1>
* Classe Testes para as funções de ContaPoupanca.
* @author Gabriel Alejandro Figueiro Galindo
* @since 06/10/2023
*/
public class TestContaPoupanca{
    private ClienteVIP VIP;
    private ContaPoupanca poupanca;
    private ContaRendaFixa renda;
    
    @Before
    public void setUp(){
        VIP = new ClienteVIP("Pedro Santos", "818.854.150-87", "Macacos1@#");//Cria um cliente VIP;
        poupanca = new ContaPoupanca(34885, 1000.0);//Cria uma conta poupança;
        VIP.adicionarConta(poupanca);//Da a conta ao cliente;
    }

    //Testa a função getSaldo();
    @Test
    public void testGetSaldo(){
        assertEquals(1000.0, poupanca.getSaldo());
    }

    //Testa a função consultarSaldo();
    @Test
    public void testConsultarSaldo(){
        assertEquals(1000.0, poupanca.consultarSaldo());//Verifica se está certo;
    }

    //Testa a função Sacar();
    @Test
    public void testSacar(){
        double valorSacado = poupanca.Sacar(100);//Saca 100 reais da conta;
        assertEquals(100.0, valorSacado);
        assertEquals(900.0, poupanca.getSaldo());
    }

    //Testa a função transferir();
    @Test
    public void testTransferir(){
        renda = new ContaRendaFixa(42345, 1000.0, 20);
        VIP.adicionarConta(renda);

        poupanca.transferir(renda, 500);//Faz a transferência;

        //Verifica se a transferência foi feita;
        assertEquals(500.0, poupanca.getSaldo());
        assertEquals(1500, renda.getSaldo());
    }
}