import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
/**
* <h1>TestContaCorrente</h1>
* Classe Testes para as funções de ContaCorrente.
* @author Gabriel Alejandro Figueiro Galindo
* @since 29/09/2023
*/
public class TestContaCorrente{
    private ClienteRegular regular;
    private ContaCorrente corrente;
    private ClienteRegular regular2;
    private ContaCorrente corrente2;
    @Before
    public void setUp(){
        regular = new ClienteRegular("Pedro Santos", "818.854.150-87", "Macacos1@#");//Cria um cliente regular;
        corrente = new ContaCorrente(12345, 1000.0);//Cria uma conta Corrente;
        regular.adicionarConta(corrente);//Da a conta ao cliente;
    }

    //Testa a função getSaldo();
    @Test
    public void testGetSaldo(){
        assertEquals(1000.0, corrente.getSaldo());
    }

    //Testa a função getTaxaMensal();
    @Test
    public void testGetTaxaMensal(){
        assertEquals(20, corrente.getTaxaMensal());
    }

    //Testa a função consultarSaldo();
    @Test
    public void testConsultarSaldo(){
        assertEquals(1000.0, corrente.consultarSaldo());
    }

    //Testa a função Depositar();
    @Test
    public void testDepositar(){
        corrente.Depositar(100.5);//deposito 100.5 na conta;
        assertEquals(1100.5, corrente.consultarSaldo());//Verifica se está certo;
    }

    //Testa a função aplicarTaxaMensal();
    @Test
    public void testaplicarTaxaMensal(){
        corrente.aplicarTaxaMensal();//aplica a taxa mensal de 20 reais;

        //Pega o dia;
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();

        if(currentDay == 1){
            //Caso seja o primeiro dia do mês;
            assertEquals(980.0, corrente.getSaldo());
        }
        else{
            //Caso não seja o primeiro dia do mês;
            assertEquals(1000.0, corrente.getSaldo());
        }
    }

    //Testa a função Sacar();
    @Test
    public void testSacar(){
        double valorSacado = corrente.Sacar(100);//Saca 100 reais da conta;
        assertEquals(100.0, valorSacado);
        assertEquals(900.0, corrente.getSaldo());
    }
    @Test
    public void testSacarSaqueEspecial(){
        double valorSacado = corrente.Sacar(1200.0);//Saca 1200 reais da conta;
        assertEquals(1200.0, valorSacado);
        assertEquals(0, corrente.getSaldo());
    }

    //Testa a função transferir();
    @Test
    public void testTransferir(){
        regular2 = new ClienteRegular("Maria Carla", "432.795.356-21", "Cacacos3@$");
        corrente2 = new ContaCorrente(42345, 1000.0);
        regular2.adicionarConta(corrente2);
        corrente.transferir(corrente2, 500);//Faz a transferência;
        //Verifica se a transferência foi feita;
        assertEquals(500.0, corrente.getSaldo());
        assertEquals(1500, corrente2.consultarSaldo());
    }
}