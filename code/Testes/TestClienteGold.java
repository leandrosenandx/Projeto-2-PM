import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
/**
* <h1>TestClienteGold</h1>
* Classe Testes para as funções de ClienteGold.
* @author Gabriel Alejandro Figueiro Galindo
* @since 07/10/2023
*/
public class TestClienteGold{
    private ClienteGold gold;
    private ContaInvestimento investimento;
    private ContaRendaFixa renda;

    @Before
    public void setUp(){
        gold = new ClienteGold("Alice Italia", "646.752.570-72", "Aacacos8@$");//Cria um cliente gold;
        investimento = new ContaInvestimento(34885, 2000.0, -3);//Cria uma conta investimento;
        renda = new ContaRendaFixa(45665, 999.0, 20);
        gold.adicionarConta(investimento);//Da a conta ao cliente;
        gold.adicionarConta(renda);
    }

    //Testa a função aplicarTaxaMensal();
    @Test
    public void TestAplicarTaxaMensal(){
        assertEquals(2000, investimento.getSaldo());
        assertEquals(999, renda.getSaldo());
        gold.aplicarTaxaMensal();

        //Pega o dia;
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();

        if(currentDay == 1){
            //Caso seja o primeiro dia do mês;
            assertEquals(1990, investimento.getSaldo());
            assertEquals(999, renda.getSaldo());
        }
        else{
            //Caso não seja o primeiro dia do mês;
            assertEquals(2000, investimento.getSaldo());
            assertEquals(999, renda.getSaldo());
        }
    }
    
    //Testa a função acumularPontosMensais();
    @Test
    public void TestAcumularPontosMensais(){
        gold.acumularPontosMensais();

        //Pega o dia;
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        if(currentDay == 1){
            //Caso seja o primeiro dia do mês;
            assertEquals(10, gold.getPontosFidelidade());
        }
        else{
            //Caso não seja o primeiro dia do mês;
            assertEquals(0, gold.getPontosFidelidade());
        }
    }

    //Testa a função acumularPontosPorSaldo();
    @Test
    public void TestAcumularPontosPorSaldo(){
        gold.acumularPontosPorSaldo();
        int pontos = gold.getPontosFidelidade();
        assertEquals(20, pontos);//Verifica se está certo;
    }
}
