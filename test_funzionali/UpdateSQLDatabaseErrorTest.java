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

public class UpdateSQLDatabaseErrorTest {

private static SQLDatabase db = null;
	
	@Before
	public void setUp() throws Exception{
		db = new SQLDatabase();
	}
	
	@Test
	public void testUpdateDataProgrammazione() throws SQLException, ParseException, ClassNotFoundException{
		db.connect();
		Film f = new Film(20,"Amici Miei",1975, "Mario Monicelli","Ugo tognazzi, Gastone Moschin, Philippe Noiret", 
				          "commedia",
		                  "Cinque inseparabili amici fiorentini sulla cinquantina affrontano i loro disagi sfogandosi "
		                  + "con scherzi a danno di malcapitati.",140);
		Programmazione p = new Programmazione(50,"1/07/2017 21:45",2);
		Film f1 = new Film(29,"Interstellar",2014,"Christopher Nolan","Matthew McConaughey, Anne Hathaway, "
				           + "Jessica Chastain","fantascienza","Cooper è ingaggiato dalla NASA per salvare il mondo", 
				           169);
        Programmazione p1 = new Programmazione(59,"14/07/2017 21:45",2);
		
		f.p.add(p);
		f1.p.add(p1);
		db.insertFilm(f);
		db.insertFilm(f1);
		String data = "25/06/2017 21:45";
		String data1 = "24/06/2016 21:45";
		db.updateDataProgrammazione(p, data);
		db.updateDataProgrammazione(p1, data1);
		assertNotEquals("Gestione errore fallita ",data, p.getData());
		assertNotEquals("Gestione errore fallita ",data1, p1.getData());
        db.c.close();
	}
        
	@Test
	public void testUpdateSalaProgrammazione() throws SQLException, ClassNotFoundException, ParseException{  
		db.connect();
		Film f = new Film(21,"La La Land",2016, "Damien Chazelle","Ryan Gosling, Emma Stone, John Legend", "commedia",
				          "Dedicato ai folli e ai sognatori",128);
		Programmazione p = new Programmazione(32,"23/06/2017 21:45",5);
		f.p.add(p);
		db.insertFilm(f);
	    int sala = 7;
	    db.updateSalaProgrammazione(p, sala);
		assertNotEquals("Gestione errore fallita ",sala,p.getSala());
        db.c.close();
			
	}
	
	@Test
	public void testUpdateNomeAbbonamento() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		Abbonamento a = new Abbonamento(30,"Abbonamento 5 ingressi ragazzi",25.00,"Ragazzi di età minore di 21 anni",
				                        "18/09/2017");
		db.insertAbbonamento(a);
		String nome = null;
		db.updateNomeAbbonamento(a, nome);
		assertNotEquals("Gestione errore fallita ",nome,a.getNome());    
	    db.c.close();
	}
	
	@Test
	public void testUpdatePrezzoAbbonamento() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		Abbonamento a = new Abbonamento(31,"Abbonamento 10 ingressi ragazzi",46.00,"Ragazzi di età minore di 21 anni",
				                        "18/12/2017");
		db.insertAbbonamento(a);
		double prezzo = 0.00;
		db.updatePrezzoAbbonamento(a, prezzo);
		assertNotEquals("Gestione errore fallita ",prezzo,a.getPrezzo()); 
	    db.c.close();
	   
	}
	
	@Test
	public void testUpdateBeneficiariAbbonamento() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		Abbonamento a = new Abbonamento(32,"Abbonamento Famiglia",51.00,"Due adulti e due bambini",
				                        "30/09/2017");
		db.insertAbbonamento(a);
		String clienti = null;
		db.updateBeneficiariAbbonamento(a, clienti);
		assertNotEquals("Gestione errore fallita ",clienti,a.getBeneficiari()); 
	    db.c.close();
	}
	
	// test non implementato in quanto NOT NULL constraint
	@Test
	public void testUpdateScadenzaAbbonamento() throws SQLException, ParseException, ClassNotFoundException{
		db.connect();
		Abbonamento a = new Abbonamento(33,"Abbonamento bimensile",42.00,"Tutti","21/08/2017");
		
		String scadenza = "21/08/2016";
		//db.c.close();
		db.insertAbbonamento(a);
		//db.c.close();
		db.updateScadenzaAbbonamento(a, scadenza);
		assertNotEquals("Gestione errore fallita ",scadenza,a.getScadenza()); 
		db.c.close();
	}
		
	@Test
	public void testUpdateNomeSconto() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		Sconto s = new Sconto(30,"Convenzione Famiglia",7.50,"Due adulti e due bambini","31/12/2017");
		String nome = null;
		db.insertSconto(s);
		db.updateNomeSconto(s, nome);
		assertNotEquals("Gestione errore fallita ",nome,s.getNome()); 
	    db.c.close();
	}
	
	@Test
	public void testUpdatePrezzoSconto() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		Sconto s = new Sconto(31,"Convenzione Over 30",7.30,"Adulti di età superiore a 30 anni","31/12/2017");
		double prezzo = 0.00;
		db.insertSconto(s);
		db.updatePrezzoSconto(s, prezzo);
		assertNotEquals("Gestione errore fallita ",prezzo,s.getPrezzo()); 
	    db.c.close();
	}
	
	@Test
	public void testUpdateBeneficiariSconto() throws SQLException, ClassNotFoundException, ParseException{
		db.connect();
		Sconto s = new Sconto(32,"Ridotto Feltrinelli",6.90,"Possessori della tessera Feltrinelli","31/12/2017");
		String clienti = null;
		db.insertSconto(s);
		db.updateBeneficiariSconto(s, clienti);
		assertNotEquals("Gestione errore fallita ",clienti,s.getBeneficiari()); 
	    db.c.close();
	}
	
	// test non implementato in quanto NOT NULL constraint
	@Test
	public void testUpdateScadenzaSconto() throws SQLException, ParseException, ClassNotFoundException{
		db.connect();
		Sconto s = new Sconto(33,"Ridotto Ikea",5.90,"Soci Ikea","31/12/2017");
		String scadenza = "31/12/2016";
		db.insertSconto(s);
		db.updateScadenzaSconto(s, scadenza);
		assertNotEquals("Gestione errore fallita ",scadenza,s.getScadenza()); 
		
		db.c.close();
	}

}
