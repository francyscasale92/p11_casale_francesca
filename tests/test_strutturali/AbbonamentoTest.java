package test_strutturali;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p11_casale_francesca.Abbonamento;

public class AbbonamentoTest {

	private static Abbonamento a;
	private static Abbonamento a1;
	private static Abbonamento a2;
	private static Abbonamento a3;
	
	@Before
	public void setup(){
		a = new Abbonamento(1,"Abbonamento 5 ingressi",30.00,"Tutti","18/09/2017");
		a1 = new Abbonamento(2,"Abbonamento 10 ingressi",55.00,"Tutti","18/09/2017");
		a2 = new Abbonamento(3,"Abbonamento under 26",50.00,"Ragazzi di età inferiore ai 26 anni","30/09/2017");
		a3 = new Abbonamento(4,"Abbonamento mensile",21.00,"Tutti","21/07/2017");
	}
	
	@Test
    public void testSetId() throws Exception {
       
		a.setId(5);
		assertEquals("Campo non corrisposto",5, a.getId());
		
		a1.setId(6);
		assertEquals("Campo non corrisposto",6, a1.getId());
		
		a2.setId(7);
		assertEquals("Campo non corrisposto",7, a2.getId());
		
		a3.setId(8);
		assertEquals("Campo non corrisposto",8, a3.getId());
		
    }

	@Test
	public void testSetNome() throws Exception{
		
		a.setNome("5 ingressi");
		assertEquals("Campo non corrisposto","5 ingressi", a.getNome());
		
		a1.setNome("10 ingressi");
		assertEquals("Campo non corrisposto","10 ingressi", a1.getNome());
		
		a2.setNome("Under 26");
		assertEquals("Campo non corrisposto","Under 26", a2.getNome());
		
		a3.setNome("Mensile");
		assertEquals("Campo non corrisposto","Mensile", a3.getNome());
	}
	
	@Test
	public void testSetPrezzo() throws Exception{
		
		a.setPrezzo(31.00);
		assertEquals("Campo non corrisposto",31.00, a.getPrezzo(),0.00);
		
		a1.setPrezzo(56.00);
		assertEquals("Campo non corrisposto",56.00, a1.getPrezzo(),0.00);
		
		a2.setPrezzo(51.00);
		assertEquals("Campo non corrisposto",51.00, a2.getPrezzo(),0.00);
		
		a3.setPrezzo(22.00);
		assertEquals("Campo non corrisposto",22.00, a3.getPrezzo(),0.00);
	}
	
	@Test
	public void testSetBeneficiari() throws Exception{
		
		a.setBeneficiari("Per tutti");
		assertEquals("Campo non corrisposto","Per tutti", a.getBeneficiari());
		
		a1.setBeneficiari("Per tutti");
		assertEquals("Campo non corrisposto","Per tutti", a1.getBeneficiari());
		
		a2.setBeneficiari("Minori di 26");
		assertEquals("Campo non corrisposto","Minori di 26", a2.getBeneficiari());
		
		a3.setBeneficiari("Per tutti");
		assertEquals("Campo non corrisposto","Per tutti", a3.getBeneficiari());
	}
	
	@Test
	public void testSetScadenza() throws Exception{
		
		a.setScadenza("19/09/2017");
		assertEquals("Campo non corrisposto","19/09/2017", a.getScadenza());
		
		a1.setScadenza("19/09/2017");
		assertEquals("Campo non corrisposto","19/09/2017", a1.getScadenza());
		
		a2.setScadenza("29/09/2017");
		assertEquals("Campo non corrisposto","29/09/2017", a2.getScadenza());
		
		a3.setScadenza("22/07/2017");
		assertEquals("Campo non corrisposto","22/07/2017", a3.getScadenza());
	}
	
	@Test
    public void testGetId() throws Exception {
       
		assertEquals("Campo non recuperato correttamente",1, a.getId());
		assertEquals("Campo non recuperato correttamente",2, a1.getId());
		assertEquals("Campo non recuperato correttamente",3, a2.getId());
		assertEquals("Campo non recuperato correttamente",4, a3.getId());
		
    }

	@Test
	public void testGetNome() throws Exception{
		
		assertEquals("Campo non recuperato correttamente","Abbonamento 5 ingressi", a.getNome());
		assertEquals("Campo non recuperato correttamente","Abbonamento 10 ingressi", a1.getNome());
		assertEquals("Campo non recuperato correttamente","Abbonamento under 26", a2.getNome());
		assertEquals("Campo non recuperato correttamente","Abbonamento mensile", a3.getNome());
		
	}
	
	@Test
	public void testGetPrezzo() throws Exception{
		
		assertEquals("Campo non recuperato correttamente",30.00, a.getPrezzo(),0.00);
		assertEquals("Campo non recuperato correttamente",55.00, a1.getPrezzo(),0.00);
		assertEquals("Campo non recuperato correttamente",50.00, a2.getPrezzo(),0.00);
		assertEquals("Campo non recuperato correttamente",21.00, a3.getPrezzo(),0.00);
		
	}
	
	@Test
	public void testGetBeneficiari() throws Exception{
		
		assertEquals("Campo non recuperato correttamente","Tutti", a.getBeneficiari());
		assertEquals("Campo non recuperato correttamente","Tutti", a1.getBeneficiari());
		assertEquals("Campo non recuperato correttamente","Ragazzi di età inferiore ai 26 anni", a2.getBeneficiari());
		assertEquals("Campo non recuperato correttamente","Tutti", a3.getBeneficiari());
		
	}
	
	@Test
	public void testGetScadenza() throws Exception{
		
		assertEquals("Campo non recuperato correttamente","18/09/2017", a.getScadenza());
		assertEquals("Campo non recuperato correttamente","18/09/2017", a1.getScadenza());
		assertEquals("Campo non recuperato correttamente","30/09/2017", a2.getScadenza());
		assertEquals("Campo non recuperato correttamente","21/07/2017", a3.getScadenza());
		
	}

}
