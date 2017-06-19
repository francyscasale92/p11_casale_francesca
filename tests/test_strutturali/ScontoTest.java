package test_strutturali;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import p11_casale_francesca.Sconto;

public class ScontoTest {

	private static Sconto s;
	private static Sconto s1;
	private static Sconto s2;
	private static Sconto s3;
	
	@Before
	public void setup(){
		s = new Sconto(1,"Convenzione Studenti",6.00,"Studenti forniti di tesserino universitario","31/12/2017");
		s1 = new Sconto(2,"Convenzione Over 65",6.30,"Adulti di età superiore a 65 anni","31/12/2017");
		s2 = new Sconto(3,"Ridotto Coop",6.70,"Possessori della tessera Coop","31/12/2017");
		s3 = new Sconto(4,"Ridotto Bambini",7.00,"Bambini di età inferiore ai 10 anni","31/12/2017");
	}
	
	@Test
    public void testSetId() throws Exception {
       
		s.setId(5);
		assertEquals("Campo non corrisposto",5, s.getId());
		
		s1.setId(6);
		assertEquals("Campo non corrisposto",6, s1.getId());
		
		s2.setId(7);
		assertEquals("Campo non corrisposto",7, s2.getId());
		
		s3.setId(8);
		assertEquals("Campo non corrisposto",8, s3.getId());
		
    }

	@Test
	public void testSetNome() throws Exception{
		
		s.setNome("Ridotto Universitari");
		assertEquals("Campo non corrisposto","Ridotto Universitari", s.getNome());
		
		s1.setNome("Ridotto over 65");
		assertEquals("Campo non corrisposto","Ridotto over 65", s1.getNome());
		
		s2.setNome("Sconto Coop");
		assertEquals("Campo non corrisposto","Sconto Coop", s2.getNome());
		
		s3.setNome("Convenzione Bambini");
		assertEquals("Campo non corrisposto","Convenzione Bambini", s3.getNome());
	}
	
	@Test
	public void testSetPrezzo() throws Exception{
		
		s.setPrezzo(6.10);
		assertEquals("Campo non corrisposto",6.10, s.getPrezzo(),0.00);
		
		s1.setPrezzo(6.20);
		assertEquals("Campo non corrisposto",6.20, s1.getPrezzo(),0.00);
		
		s2.setPrezzo(6.60);
		assertEquals("Campo non corrisposto",6.60, s2.getPrezzo(),0.00);
		
		s3.setPrezzo(7.10);
		assertEquals("Campo non corrisposto",7.10, s3.getPrezzo(),0.00);
	}
	
	@Test
	public void testSetBeneficiari() throws Exception{
		
		s.setBeneficiari("Studenti Universitari");
		assertEquals("Campo non corrisposto","Studenti Universitari", s.getBeneficiari());
		
		s1.setBeneficiari("Adulti over 65");
		assertEquals("Campo non corrisposto","Adulti over 65", s1.getBeneficiari());
		
		s2.setBeneficiari("Soci Coop");
		assertEquals("Campo non corrisposto","Soci Coop", s2.getBeneficiari());
		
		s3.setBeneficiari("Minori di 10 anni");
		assertEquals("Campo non corrisposto","Minori di 10 anni", s3.getBeneficiari());
	}
	
	@Test
	public void testSetScadenza() throws Exception{
		
		s.setScadenza("28/12/2017");
		assertEquals("Campo non corrisposto","28/12/2017", s.getScadenza());
		
		s1.setScadenza("29/12/2017");
		assertEquals("Campo non corrisposto","29/12/2017", s1.getScadenza());
		
		s2.setScadenza("30/12/2017");
		assertEquals("Campo non corrisposto","30/12/2017", s2.getScadenza());
		
		s3.setScadenza("21/11/2017");
		assertEquals("Campo non corrisposto","21/11/2017", s3.getScadenza());
	}
	
	@Test
    public void testGetId() throws Exception {
       
		assertEquals("Campo non recuperato correttamente",1, s.getId());
		assertEquals("Campo non recuperato correttamente",2, s1.getId());
		assertEquals("Campo non recuperato correttamente",3, s2.getId());
		assertEquals("Campo non recuperato correttamente",4, s3.getId());
		
    }

	@Test
	public void testGetNome() throws Exception{
		
		assertEquals("Campo non recuperato correttamente","Convenzione Studenti", s.getNome());
		assertEquals("Campo non recuperato correttamente","Convenzione Over 65", s1.getNome());
		assertEquals("Campo non recuperato correttamente","Ridotto Coop", s2.getNome());
		assertEquals("Campo non recuperato correttamente","Ridotto Bambini", s3.getNome());
		
	}
	
	@Test
	public void testGetPrezzo() throws Exception{
		
		assertEquals("Campo non recuperato correttamente",6.00, s.getPrezzo(),0.00);
		assertEquals("Campo non recuperato correttamente",6.30, s1.getPrezzo(),0.00);
		assertEquals("Campo non recuperato correttamente",6.70, s2.getPrezzo(),0.00);
		assertEquals("Campo non recuperato correttamente",7.00, s3.getPrezzo(),0.00);
		
	}
	
	@Test
	public void testGetBeneficiari() throws Exception{
		
		assertEquals("Campo non recuperato correttamente","Studenti forniti di tesserino universitario", 
					 s.getBeneficiari());
		assertEquals("Campo non recuperato correttamente","Adulti di età superiore a 65 anni", s1.getBeneficiari());
		assertEquals("Campo non recuperato correttamente","Possessori della tessera Coop", s2.getBeneficiari());
		assertEquals("Campo non recuperato correttamente","Bambini di età inferiore ai 10 anni", s3.getBeneficiari());
		
	}
	
	@Test
	public void testGetScadenza() throws Exception{
		
		assertEquals("Campo non recuperato correttamente","31/12/2017", s.getScadenza());
		assertEquals("Campo non recuperato correttamente","31/12/2017", s1.getScadenza());
		assertEquals("Campo non recuperato correttamente","31/12/2017", s2.getScadenza());
		assertEquals("Campo non recuperato correttamente","31/12/2017", s3.getScadenza());
		
	}
	
}
