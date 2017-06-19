package test_strutturali;
import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import p11_casale_francesca.Abbonamento;
import p11_casale_francesca.Film;
import p11_casale_francesca.Programmazione;
import p11_casale_francesca.SQLDatabase;
import p11_casale_francesca.Sconto;

public class SQLDatabaseTest {

private static SQLDatabase db = null;
	
	@Before
	public void setUp() throws Exception{
		db = new SQLDatabase();
	}
	
	@Test
	public void testConnect() throws Exception{
		
		db.connect();
		assertNotNull("Connessione nulla",db.c);
		assertNotNull("Statement nullo",db.stmt);

	}
	
	@Test
	public void testCreate() throws Exception{
		
		db.connect();
		db.create();
		db.connect();
		
		String checkTableFilm = "SELECT * FROM FILM";
		PreparedStatement psf = db.c.prepareStatement(checkTableFilm);
		ResultSet rsf = psf.executeQuery();
		assertNotNull("Creazione tabella fallita ",rsf);
	    
		String checkTableSconto = "SELECT * FROM SCONTO";
		PreparedStatement pss = db.c.prepareStatement(checkTableSconto);
		ResultSet rss = pss.executeQuery();
		assertNotNull("Creazione tabella fallita ",rss);
	      
		String checkTableAbbonamento = "SELECT * FROM ABBONAMENTO";
		PreparedStatement psa = db.c.prepareStatement(checkTableAbbonamento);
		ResultSet rsa = psa.executeQuery();
		assertNotNull("Creazione tabella fallita ",rsa);
	      
		String checkTableProgrammazione = "SELECT * FROM PROGRAMMAZIONE";
		PreparedStatement psp = db.c.prepareStatement(checkTableProgrammazione);
		ResultSet rsp = psp.executeQuery();
		assertNotNull("Creazione tabella fallita ",rsp);
	     
	    db.c.close();
	}
	
	@Test
	public void testInsertFilm() throws SQLException, ClassNotFoundException, ParseException{
		
		db.connect();
		Film f = new Film(1,"Wonder Woman",2017,"Patty Jenkins","Gal Gadot, Chris Pine, Robin Wright","azione",
			     "Diana Prince diventa Wonder Woman",175);
		Film f1 = new Film(2,"Pirati dei Caraibi",2017,"Espen Sandberg","Johnny Depp, Geoffrey Rush, Orlando Bloom",
				"azione","Capitan Jack Sparrow affronta il terrificante Capitano Salazar",135);
		Film f2 = new Film(3,"Pirati dei Caraibi",2003,"Gore Verbinski","Johnny Depp, Geoffrey Rush, Orlando Bloom",
				"azione","Capitan Jack Sparrow vuole sbarazzarsi della Maledizione della Prima Luna",143);
		Programmazione p = new Programmazione(1,"21/06/2017 21:45",2);
		Programmazione p1 = new Programmazione(2,"23/06/2017 21:45",2);
		Programmazione p2 = new Programmazione(3,"25/06/2017 21:45",2);
		f.p.add(p);
		f1.p.add(p1);
		f2.p.add(p2);
		
		db.insertFilm(f);
		boolean isFound = db.selectFilm(f);
        assertTrue("Inserimento film fallito ",isFound);
        
    	db.insertFilm(f1);
        boolean isFound1 = db.selectFilm(f1);
        assertTrue("Inserimento film fallito ",isFound1);
        
        db.insertFilm(f2);
        boolean isFound2 = db.selectFilm(f2);
        assertTrue("Inserimento film fallito ",isFound2);
		
		db.c.close();
      
	}
	
	@Test
	public void testInsertSconto() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		
		Sconto s = new Sconto(1,"Convenzione Studenti",6.00,"Studenti forniti di tesserino universitario","31/12/2017");
		Sconto s1 = new Sconto(2,"Convenzione Over 65",6.30,"Adulti di età superiore a 65 anni","31/12/2017");
		Sconto s2 = new Sconto(3,"Ridotto Coop",6.70,"Possessori della tessera Coop","31/12/2017");
		Sconto s3 = new Sconto(4,"Ridotto Bambini",7.00,"Bambini di età inferiore ai 10 anni","31/12/2017");
		//Sconto s4 = new Sconto(9,"Offerta 3D",0.00,"Tutti","31/12/2017");
		//Sconto s5 = new Sconto(10,"Sconto 10%",7.00,"Tutti","31/12/2016");
		
		db.insertSconto(s);
		boolean isFound = db.selectSconto(s);
        assertTrue("Inserimento sconto fallito ",isFound);
        
        db.insertSconto(s1);
		boolean isFound1 = db.selectSconto(s1);
        assertTrue("Inserimento sconto fallito ",isFound1);
        
        db.insertSconto(s2);
		boolean isFound2 = db.selectSconto(s2);
        assertTrue("Inserimento sconto fallito ",isFound2);
        
        db.insertSconto(s3);
		boolean isFound3 = db.selectSconto(s3);
        assertTrue("Inserimento sconto fallito ",isFound3);
        
        /*db.insertSconto(s4);
		boolean isFound4 = db.selectSconto(s4);
        assertTrue("Inserimento sconto fallito ",isFound4);
        
        db.insertSconto(s5);
		boolean isFound5 = db.selectSconto(s5);
        assertTrue("Inserimento sconto fallito ",isFound5);*/
	      
	    db.c.close();
	   
	}
	
	@Test
	public void testInsertAbbonamento() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		
		Abbonamento a = new Abbonamento(1,"Abbonamento 5 ingressi",30.00,"Tutti","18/09/2017");
		Abbonamento a1 = new Abbonamento(2,"Abbonamento 10 ingressi",55.00,"Tutti","18/09/2017");
		Abbonamento a2 = new Abbonamento(3,"Abbonamento under 26",50.00,"Ragazzi di età inferiore ai 26 anni",
                						 "30/09/2017");
		Abbonamento a3 = new Abbonamento(4,"Abbonamento mensile",21.00,"Tutti","21/07/2017");
		
		db.insertAbbonamento(a);
		boolean isFound = db.selectAbbonamento(a);
        assertTrue("Inserimento abbonamento fallito ",isFound);
        
        db.insertAbbonamento(a1);
		boolean isFound1 = db.selectAbbonamento(a1);
        assertTrue("Inserimento abbonamento fallito ",isFound1);
        
        db.insertAbbonamento(a2);
		boolean isFound2 = db.selectAbbonamento(a2);
        assertTrue("Inserimento abbonamento fallito ",isFound2);
        
        db.insertAbbonamento(a3);
		boolean isFound3 = db.selectAbbonamento(a3);
        assertTrue("Inserimento abbonamento fallito ",isFound3);
	      
	    db.c.close();
	   
	}
	
	@Test
	public void testDeleteFilm() throws SQLException, ClassNotFoundException{
		db.connect();
		
		Film f = new Film(1,"Wonder Woman",2017,"Patty Jenkins","Gal Gadot, Chris Pine, Robin Wright","azione",
			     "Diana Prince diventa Wonder Woman",175);
		
		db.deleteFilm(f);
		boolean isFound = db.selectFilm(f);
        assertFalse("Rimozione film fallita ",isFound);
		
		db.c.close();
	    
	}
	
	@Test
	public void testDeleteSconto() throws SQLException, ClassNotFoundException{
		db.connect();
		
		Sconto s = new Sconto(1,"Convenzione Studenti",6.00,"Studenti forniti di tesserino universitario","31/12/2017");
		
		db.deleteSconto(s);
		boolean isFound = db.selectSconto(s);
        assertFalse("Rimozione sconto fallita ",isFound);
        
		db.c.close();
	}
	
	@Test
	public void testDeleteAbbonamento() throws SQLException, ClassNotFoundException{
		db.connect();
		Abbonamento a = new Abbonamento(1,"Abbonamento 5 ingressi",30.00,"Tutti","18/09/2017");
	  
		db.deleteAbbonamento(a);
		boolean isFound = db.selectAbbonamento(a);
        assertFalse("Rimozione abbonamento fallita ",isFound);
       
		db.c.close();
	    
	}
	
	@Test
	public void testSelectFilm() throws SQLException, ClassNotFoundException{
		db.connect();
		/*Film f = new Film(1,"Wonder Woman",2017,"Patty Jenkins","Gal Gadot, Chris Pine, Robin Wright","azione",
			     "Diana Prince diventa Wonder Woman",175);*/
		Film f1 = new Film(2,"Pirati dei Caraibi",2017,"Espen Sandberg","Johnny Depp, Geoffrey Rush, Orlando Bloom",
				"azione","Capitan Jack Sparrow affronta il terrificante Capitano Salazar",135);
		
		//boolean isFound = db.selectFilm(f);
		boolean isFound1= db.selectFilm(f1);
		
		//assertTrue("Ricerca fallita",isFound);
		assertTrue("Ricerca fallita",isFound1);
		
	
		db.c.close();
	}
	
	@Test
	public void testSelectSconto() throws SQLException, ClassNotFoundException{
		db.connect();
		//Sconto s = new Sconto(1,"Convenzione Studenti",6.00,"Studenti forniti di tesserino universitario","31/12/2017");
		Sconto s1 = new Sconto(2,"Convenzione Over 65",6.30,"Adulti di età superiore a 65 anni","31/12/2017");
		Sconto s2 = new Sconto(3,"Ridotto Coop",6.70,"Possessori della tessera Coop","31/12/2017");
		Sconto s3 = new Sconto(4,"Ridotto Bambini",7.00,"Bambini di età inferiore ai 10 anni","31/12/2017");
		
		//boolean isFound = db.selectSconto(s);
		boolean isFound1 = db.selectSconto(s1);
		boolean isFound2 = db.selectSconto(s2);
		boolean isFound3 = db.selectSconto(s3);
		
		//assertTrue("Ricerca fallita",isFound);
		assertTrue("Ricerca fallita",isFound1);
		assertTrue("Ricerca fallita",isFound2);
		assertTrue("Ricerca fallita",isFound3);
		
		db.c.close();
	}
	
	@Test
	public void testSelectAbbonamento() throws SQLException, ClassNotFoundException{
		db.connect();
		//Abbonamento a = new Abbonamento(1,"Abbonamento 5 ingressi",30.00,"Tutti","18/09/2017");
		Abbonamento a1 = new Abbonamento(2,"Abbonamento 10 ingressi",55.00,"Tutti","18/09/2017");
		/*Abbonamento a2 = new Abbonamento(3,"Abbonamento under 26",50.00,"Ragazzi di età inferiore ai 26 anni",
                						 "30/09/2017");*/
		Abbonamento a3 = new Abbonamento(4,"Abbonamento mensile",21.00,"Tutti","21/07/2017");
		
		//boolean isFound = db.selectAbbonamento(a);
		boolean isFound1 = db.selectAbbonamento(a1);
		//boolean isFound2 = db.selectAbbonamento(a2);
		boolean isFound3 = db.selectAbbonamento(a3);
		
		//assertTrue("Ricerca fallita",isFound);
		assertTrue("Ricerca fallita",isFound1);
		//assertTrue("Ricerca fallita",isFound2);
		assertTrue("Ricerca fallita",isFound3);
		
	}

}
