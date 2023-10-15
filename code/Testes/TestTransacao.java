import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
/**
* <h1>TestTransacao</h1>
* Classe Testes para a classe Transacao.
* @author Gabriel Alejandro Figueiro Galindo
* @since 13/10/2023
*/
public class TestTransacao{
    private ClienteVIP vip;
    private ContaCorrente corrente;
    private ContaInvestimento investimento;

    @Before
    public void setUp(){
        vip = new ClienteVIP("Alice Italia", "646.752.570-72", "Aacacos8@$");//Cria um cliente vip;
        corrente = new ContaCorrente(12345, 1000.0);//Cria uma conta Corrente;
        investimento = new ContaInvestimento(34885, 1000.0, -3);//Cria uma conta investimento;
        vip.adicionarConta(corrente);
        vip.adicionarConta(investimento);
    }

    //Teste com relação ao ato de depositar;
    @Test
    public void testDepositarTrancacao(){
        corrente.Depositar(100.5);//deposito 100.5 na conta;
        List<Transacao> transacao = corrente.getTransacoes();
        assertEquals(1, transacao.size());

        Transacao depositarTransacao = transacao.get(0);
        assertEquals(LocalDate.now(), depositarTransacao.getData());
        assertEquals("Deposito", depositarTransacao.getDescricao());
        assertEquals(100.5, depositarTransacao.getValor());
        assertEquals(1100.5, depositarTransacao.getSaldo());
    }

    //Teste com relação ao ato de sacar para a conta corrente;
    @Test
    public void testSacarTrancacaoCorrente(){
        corrente.Sacar(100);
        corrente.Sacar(1100);
        List<Transacao> transacao = corrente.getTransacoes();
        assertEquals(2, transacao.size());

        Transacao saqueNormal = transacao.get(0);
        Transacao saqueEspecial = transacao.get(1);

        assertEquals(LocalDate.now(), saqueNormal.getData());
        assertEquals(LocalDate.now(), saqueEspecial.getData());

        //verifica os valores da transação de saque normal;
        assertEquals("Saque", saqueNormal.getDescricao());
        assertEquals(-100, saqueNormal.getValor());
        assertEquals(900, saqueNormal.getSaldo());

        //verifica os valores de transação de saque especial;
        assertEquals("Saque Especial", saqueEspecial.getDescricao());
        assertEquals(-1100, saqueEspecial.getValor());
        assertEquals(0, saqueEspecial.getSaldo());

    }

    //Teste com relação ao ato de sacar para a conta investimento;
    @Test
    public void testSacarTrancacaoInvestimento(){
        investimento.Sacar(100);
        List<Transacao> transacao = investimento.getTransacoes();
        assertEquals(1, transacao.size());

        //verifica os valores de transação do saque;
        Transacao saque = transacao.get(0);
        assertEquals(LocalDate.now(), saque.getData());
        assertEquals(-95.05, saque.getValor());
        assertEquals(879.9, saque.getSaldo());
        assertEquals("Saque", saque.getDescricao());
    }

    //Teste que ve a transação após a cobrança de mensalidade;
    @Test
    public void testMesalidadeTrancacaoVIP(){
        vip.aplicarTaxaMensal();
        List<Transacao> transacao = investimento.getTransacoes();
        assertEquals(0, transacao.size());

        //A conta corrente foi adicionada primeiro então a mensalidade foi cobrada nela;
        //Pega o dia;
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();

        if(currentDay == 1){
            //Caso seja o primeiro dia do mês;
            transacao = corrente.getTransacoes();
            assertEquals(1, transacao.size());

            Transacao taxaTransacao = transacao.get(0);
            assertEquals(LocalDate.now(), taxaTransacao.getData());
            assertEquals(-30, taxaTransacao.getValor());
            assertEquals(970.0, taxaTransacao.getSaldo());
        }
        else{
            //Caso não seja o primeiro dia do mês;
            transacao = corrente.getTransacoes();
            assertEquals(0, transacao.size());
        }
    }
}