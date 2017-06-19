package test_strutturali;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import p11_casale_francesca.Abbonamento;
import p11_casale_francesca.Film;
import p11_casale_francesca.Programmazione;
import p11_casale_francesca.SQLDatabase;
import p11_casale_francesca.Sconto;

public class SQLDatabaseErrorTest {

private static SQLDatabase db = null;
	
	@Before
	public void setUp() throws Exception{
		db = new SQLDatabase();
	}
	
	@Test
	public void testInsertFilm() throws SQLException, ClassNotFoundException, ParseException{
		
		db.connect();
		Film f = new Film(3,"Venom",2018,"Ruben Fleischer","Tom Hardy","azione",
				          "Arriva il nemico di Spider Man",175);
		Film f1 = new Film(4,"C'era una volta il West",1968,"Sergio Leone", 
				           "Claudia Cardinale, Henry Fonda, Jason Robards","western",null,165);
		Film f2 = new Film(5,"Porco Rosso",1992,"Hayao Miyazaki","Shuichiro Moriyama, Akio Otsuka, Tokiko Kato",
				           "animazione",
				           "Il aviatore Marco Pagot si trasforma per un sortilegio in un maiale antropomorfo",93);
		Film f3 = new Film(6,"Hunger Games",2012,"Gary Ross","Jennifer Lawrence, Josh Hutcherson, Liam Hemsworth",
		                   "avventura","Possa la fortuna sempre essere a vostro favore",142);
		Film f4 = new Film(7,"Suspiria",1977,"Dario Argento","Jessica Harper, Stefania Casini, Joan Bennet",
                           "orrore","Susy Benner entra nella Accademia di danza che si rivelerà un covo di streghe",95);
		Programmazione p = new Programmazione(11,"21/06/2017 21:45",0);
		Programmazione p1 = new Programmazione(12,"23/06/2016 21:45",2);
		Programmazione p2 = new Programmazione(13,"25/06/2017 21:45",2);
		Programmazione p3 = new Programmazione(14,"25/06/2017 21:45",2);
		f2.p.add(p);
		f3.p.add(p1);
		f3.p.add(p2);
		f4.p.add(p3);
	
		db.insertFilm(f);
		boolean isFound = db.selectFilm(f);
        assertFalse("Gestione errore fallita ",isFound);
        
    	db.insertFilm(f1);
        boolean isFound1 = db.selectFilm(f1);
        assertFalse("Gestione errore fallita ",isFound1);
        
        db.insertFilm(f2);
		boolean isFound2 = db.selectFilm(f2);
        assertTrue("Gestione errore fallita ",isFound2);
        
        db.insertFilm(f3);
		boolean isFound3 = db.selectFilm(f3);
		//System.err.println("isFound3 "+isFound3);
        assertTrue("Gestione errore fallita ",isFound3);
        
        db.insertFilm(f4);
		boolean isFound4 = db.selectFilm(f4);
		//System.err.println("isFound4 "+isFound4);
        assertTrue("Gestione errore fallita ",isFound4);
        		
		db.c.close();
      
	}
	
	@Test
	public void testInsertSconto() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		
		Sconto s = new Sconto(11,"Offerta 3D",0.00,"Tutti","31/12/2017");
		Sconto s1 = new Sconto(12,"Sconto 10%",7.00,"Tutti","31/12/2016");
		
		db.insertSconto(s);
		boolean isFound = db.selectSconto(s);
        assertFalse("Gestione errore fallita ",isFound);
        
        db.insertSconto(s1);
		boolean isFound1 = db.selectSconto(s1);
        assertFalse("Gestione errore fallita ",isFound1);
              
	    db.c.close();
	   
	}
	
	@Test
	public void testInsertAbbonamento() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		
		Abbonamento a = new Abbonamento(11,"Abbonamento annuale",0.00,"Tutti","21/06/2018");
		Abbonamento a1 = new Abbonamento(12,"Abbonamento semestrale",120.00,"Tutti","18/09/2016");
		
		db.insertAbbonamento(a);
		boolean isFound = db.selectAbbonamento(a);
        assertFalse("Gestione errore fallita ",isFound);
        
        db.insertAbbonamento(a1);
		boolean isFound1 = db.selectAbbonamento(a1);
        assertFalse("Gestione errore fallita ",isFound1);
          
	    db.c.close();
	   
	}
		
	@Test
	public void testSelectFilm() throws SQLException, ClassNotFoundException{
		db.connect();
		Film f = new Film(8,"V per Vendetta",2005,"James McTeigue","Hugo Weaving, Natalie Portman, Stephen Rea","azione",
			     		  "La storia di un misterioso e rivoluzionario individuo, V, in un Regno Unito distopico", 175);
		
		boolean isFound= db.selectFilm(f);
		assertFalse("Gestione errore fallita ",isFound);
		
		db.c.close();
	}
	
	@Test
	public void testSelectSconto() throws SQLException, ClassNotFoundException{
		db.connect();
		Sconto s = new Sconto(13,"Ridotto Donna",7.00,"Donne","31/12/2017");
		
		boolean isFound = db.selectSconto(s);
		assertFalse("Gestione errore fallita ",isFound);
		
		
		db.c.close();
	}
	
	@Test
	public void testSelectAbbonamento() throws SQLException, ClassNotFoundException{
		db.connect();
		
		Abbonamento a = new Abbonamento(12,"Abbonamento semestrale",120.00,"Tutti","31/12/2017");
		boolean isFound = db.selectAbbonamento(a);
		
		assertFalse("Gestione errore fallita ",isFound);
		
	}

}
