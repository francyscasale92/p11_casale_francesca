import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class FilmTest {
	
	private static Film f = null;
	private static Film f1 = null;
	
	@Before
	public void setUp() throws Exception{
		f = new Film(1,"Wonder Woman",2017,"Patty Jenkins","Gal Gadot, Chris Pine, Robin Wright","azione",
				     "Diana Prince diventa Wonder Woman",175);
		f1 = new Film(2,"Pirati dei Caraibi",2017,"Espen Sandberg","Johnny Depp, Geoffrey Rush, Orlando Bloom",
				      "azione","Capitan Jack Sparrow affronta il terrificante Capitano Salazar",135);
	}
	
	@Test
    public void testSetId() throws Exception {
       
		f.setId(3);
		assertEquals("Campo non corrisposto",3, f.getId());
		
		f1.setId(4);
		assertEquals("Campo non corrisposto",4, f1.getId());
		
    }

    @Test
    public void testSetTitolo() throws Exception {
       
		f.setTitolo("La Mummia");
		assertEquals("Campo non corrisposto","La Mummia", f.getTitolo());
		
		f1.setTitolo("Baby Boss");
		assertEquals("Campo non corrisposto","Baby Boss", f1.getTitolo());
		
    }

    @Test
    public void testSetAnno() throws Exception {
       
		f.setAnnoPubblicazione(2016);
		assertEquals("Campo non corrisposto",2016, f.getAnnoPubblicazione());
		
		f1.setAnnoPubblicazione(2013);
		assertEquals("Campo non corrisposto",2013, f1.getAnnoPubblicazione());
		
    }

    @Test
    public void testSetRegista() throws Exception {
       
		f.setRegista("Alex Kurtzman");
		assertEquals("Campo non corrisposto","Alex Kurtzman", f.getRegista());
		
		f1.setRegista("Tom McGrath");
		assertEquals("Campo non corrisposto","Tom McGrath", f1.getRegista());
		
    }

    @Test
    public void testSetAttori() throws Exception {
       
		f.setListaAttori("Tom Cruise, Russell Crowe, Sofia Boutella");
		assertEquals("Campo non corrisposto","Tom Cruise, Russell Crowe, Sofia Boutella", f.getListaAttori());
		
		f1.setListaAttori("Alec Baldwin, Steve Buscemi, Lisa Kudrow");
		assertEquals("Campo non corrisposto","Alec Baldwin, Steve Buscemi, Lisa Kudrow", f1.getListaAttori());
		
    }

    @Test
    public void testSetGenere() throws Exception {
       
		f.setGenere("orrore");
		assertEquals("Campo non corrisposto","orrore", f.getGenere());
		
		f1.setGenere("animazione");
		assertEquals("Campo non corrisposto","animazione", f1.getGenere());
		
    }

    @Test
    public void testSetTrama() throws Exception {
       
		f.setTrama("La Mummia si risveglia ai giorni nostri");
		assertEquals("Campo non corrisposto","La Mummia si risveglia ai giorni nostri", f.getTrama());
		
		f1.setTrama("E' la storia di una famiglia che dà il benvenuto al nuovo arrivato");
		assertEquals("Campo non corrisposto","E' la storia di una famiglia che dà il benvenuto al nuovo arrivato", 
					 f1.getTrama());
		
    }

    @Test
    public void testSetDurata() throws Exception {
       
		f.setDurata(110);
		assertEquals("Campo non corrisposto",110, f.getDurata());
		
		f1.setDurata(97);
		assertEquals("Campo non corrisposto",97, f1.getDurata());
		
    }
	
	@Test
    public void testGetId() throws Exception{
 
    	assertEquals("Campo non recuperato correttamente",1, f.getId());
		assertEquals("Campo non recuperato correttamente",2, f1.getId());
		
    }
    
    @Test
    public void testGetTitolo() throws Exception{
    
    	assertEquals("Campo non recuperato correttamente","Wonder Woman", f.getTitolo());
		assertEquals("Campo non recuperato correttamente","Pirati dei Caraibi", f1.getTitolo());
		
    }
    
    @Test
    public void testGetAnno() throws Exception{
 
    	assertEquals("Campo non recuperato correttamente",2017, f.getAnnoPubblicazione());
		assertEquals("Campo non recuperato correttamente",2017, f1.getAnnoPubblicazione());
		
    }
    
    @Test
    public void testGetRegista() throws Exception{
 
    	assertEquals("Campo non recuperato correttamente","Patty Jenkins", f.getRegista());
		assertEquals("Campo non recuperato correttamente","Espen Sandberg", f1.getRegista());
		
    }
    
    @Test
    public void testGetAttori() throws Exception{
 
    	assertEquals("Campo non recuperato correttamente","Gal Gadot, Chris Pine, Robin Wright", f.getListaAttori());
    	assertEquals("Campo non recuperato correttamente","Johnny Depp, Geoffrey Rush, Orlando Bloom", 
					 f1.getListaAttori());
		
    }
    
    @Test
    public void testGetGenere() throws Exception{
 
    	assertEquals("Campo non recuperato correttamente","azione", f.getGenere());
		assertEquals("Campo non recuperato correttamente","azione", f1.getGenere());
		
    }
    
    @Test
    public void testGetTrama() throws Exception{
 
    	assertEquals("Campo non recuperato correttamente","Diana Prince diventa Wonder Woman", f.getTrama());
		assertEquals("Campo non recuperato correttamente",
					 "Capitan Jack Sparrow affronta il terrificante Capitano Salazar", f1.getTrama());
		
    }
    
    @Test
    public void testGetDurata() throws Exception{
 
    	assertEquals("Campo non recuperato correttamente",175, f.getDurata());
		assertEquals("Campo non recuperato correttamente",135, f1.getDurata());
		
    }
    
}
