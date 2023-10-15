import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
/**
* <h1>TestContaInvestimento</h1>
* Classe Testes para as funções de ContaInvestimento.
* @author Gabriel Alejandro Figueiro Galindo
* @since 07/10/2023
*/
public class TestContaInvestimento{
    private ClienteGold gold;
    private ContaInvestimento investimento;
    private ClienteVIP VIP;
    private ContaPoupanca poupanca;

    @Before
    public void setUp(){
        gold = new ClienteGold("Alice Italia", "646.752.570-72", "Aacacos8@$");//Cria um cliente gold;
        investimento = new ContaInvestimento(34885, 1000.0, -3);//Cria uma conta investimento;
        gold.adicionarConta(investimento);//Da a conta ao cliente;
    }

    //Testa a função consultarSaldo();
    @Test
    public void testConsultarSaldo(){
        assertEquals(1000.0, investimento.consultarSaldo());//Verifica se está certo;
    }

    //Testa a função aplicarRendimentoDiario() com rendimentoDiario positivo;
    @Test
    public void testRendimentoDiarioPositivo(){
        investimento.setRendimentoDiario(3.3);
        investimento.aplicarRendimentoDiario();
        assertEquals(1033, investimento.getSaldo());//Verifica se está certo;
    }

    //Testa a função aplicarRendimentoDiario() com rendimentoDiario negativo;
    @Test
    public void testRendimentoDiarioNegativo(){
        investimento.aplicarRendimentoDiario();
        assertEquals(970, investimento.getSaldo());//Verifica se está certo;
    }

    //Testa a função Sacar() com um rendimentoDiario negativo;
    @Test
    public void testSacarNegativo(){
        double TotalRemovido = investimento.Sacar(100);//Valor sacado + Imposto;
        assertEquals(95.05, TotalRemovido);
        assertEquals(879.9, investimento.getSaldo());
    }

    //Testa a função Sacar() com um rendimentoDiario positivo;
    @Test
    public void testSacarPositivo(){
        investimento.setRendimentoDiario(3.3);
        double TotalRemovido = investimento.Sacar(100);//Valor sacado + Imposto;
        assertEquals(105.45, TotalRemovido);
        assertEquals(922.11, investimento.getSaldo());
    }

    //Testa a função transferir();
    @Test
    public void testTransferir(){
        VIP = new ClienteVIP("Pedro Santos", "818.854.150-87", "Macacos1@#");
        poupanca = new ContaPoupanca(34885, 1000.0);
        VIP.adicionarConta(poupanca);
        investimento.transferir(poupanca, 500);//Faz a transferência;

        //Verifica se a transferência foi feita;
        assertEquals(500.0, investimento.consultarSaldo());
        assertEquals(1500, poupanca.consultarSaldo());
    }
}