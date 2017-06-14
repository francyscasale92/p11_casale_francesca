import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProgrammazioneTest {

	private static Programmazione p = null;
	private static Programmazione p1 = null;
	
	@Before
	public void setup(){
		p = new Programmazione(1,"21/06/2017 21:45",2);
		p1 = new Programmazione(2,"23/06/2017 21:45",2);
	}
	
	@Test
	public void testSetId(){
		p.setId(3);
		assertEquals("Campo non corrisposto",3, p.getId());
		
		p1.setId(4);
		assertEquals("Campo non corrisposto",4, p1.getId());
	}
	
	@Test
	public void testSetData(){
		p.setData("22/06/2017 21:45");
		assertEquals("Campo non corrisposto","22/06/2017 21:45", p.getData());
		
		p1.setData("23/06/2017 22:30");
		assertEquals("Campo non corrisposto","23/06/2017 22:30", p1.getData());
	}

	@Test
	public void testSetSala(){
		p.setSala(1);
		assertEquals("Campo non corrisposto",1, p.getSala());
		
		p1.setSala(7);
		assertEquals("Campo non corrisposto",7, p1.getSala());
	}
	
	@Test
	public void testGetId(){
		assertEquals("Campo non recuperato correttamente",1, p.getId());
		assertEquals("Campo non recuperato correttamente",2, p1.getId());
	}
	
	@Test
	public void testGetData(){
		assertEquals("Campo non recuperato correttamente","21/06/2017 21:45", p.getData());
		assertEquals("Campo non recuperato correttamente","23/06/2017 21:45", p1.getData());
	}

	@Test
	public void testGetSala(){
		assertEquals("Campo non recuperato correttamente",2, p.getSala());
		assertEquals("Campo non recuperato correttamente",2, p1.getSala());
	}
	
}
