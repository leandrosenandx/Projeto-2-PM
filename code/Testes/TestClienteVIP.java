import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
/**
* <h1>TestClienteVIP</h1>
* Classe Testes para as funções de ClienteVIP.
* @author Gabriel Alejandro Figueiro Galindo
* @since 07/10/2023
*/
public class TestClienteVIP{
    private ClienteVIP vip;
    private ContaInvestimento investimento;
    private ContaRendaFixa renda;

    @Before
    public void setUp(){
        vip = new ClienteVIP("Alice Italia", "646.752.570-72", "Aacacos8@$");//Cria um cliente vip;
        investimento = new ContaInvestimento(34885, 4000.0, -3);//Cria uma conta investimento;
        renda = new ContaRendaFixa(45665, 999.0, 20);
        vip.adicionarConta(investimento);//Da a conta ao cliente;
        vip.adicionarConta(renda);
    }
    
    //Testa a função aplicarTaxaMensal();
    @Test
    public void TestAplicarMensalidade(){
        assertEquals(4000, investimento.getSaldo());
        assertEquals(999, renda.getSaldo());
        vip.aplicarTaxaMensal();

        //Pega o dia;
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();

        if(currentDay == 1){
            //Caso seja o primeiro dia do mês;
            assertEquals(3970, investimento.getSaldo());
            assertEquals(999, renda.getSaldo());
        }
        else{
            //Caso não seja o primeiro dia do mês;
            assertEquals(4000, investimento.getSaldo());
            assertEquals(999, renda.getSaldo());
        }
    }

    //Testa a função acumularPontosMensais();
    @Test
    public void TestAcumularPontosMensais(){
        vip.acumularPontosMensais();

        //Pega o dia;
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();

        if(currentDay == 1){
            //Caso seja o primeiro dia do mês;
            assertEquals(35, vip.getPontosFidelidade());
        }
        else{
            //Caso não seja o primeiro dia do mês;
            assertEquals(0, vip.getPontosFidelidade());
        }
    }

    //Testa a função acumularPontosPorSaldo();
    @Test
    public void TestAcumularPontosPorSaldo(){
        vip.acumularPontosPorSaldo();
        assertEquals(60, vip.getPontosFidelidade());//Verifica se está certo;
    }
}