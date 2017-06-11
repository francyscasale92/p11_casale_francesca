import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class UpdateSQLDatabaseTest {

	private static SQLDatabase db = null;
	
	@Before
	public void setUp() throws Exception{
		db = new SQLDatabase();
	}
	
	@Test
	public void testUpdateDataProgrammazione() throws SQLException, ParseException, ClassNotFoundException{
		db.connect();
		Programmazione p = new Programmazione(1,"21/06/2017 21:45",2);
		String data = "22/06/2017 21:45";
		db.updateDataProgrammazione(p, data);
		db.connect();
		String queryCheck = "SELECT * FROM PROGRAMMAZIONE WHERE ID = ? AND DATA = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, p.getId());
    	ps.setString(2,data);
		assertNotNull("Modifica fallita ",ps.executeQuery());
        db.c.close();
	}
        
	@Test
	public void testUpdateSalaProgrammazione() throws SQLException, ClassNotFoundException{  
		db.connect();
		Programmazione p = new Programmazione(2,"23/06/2017 21:45",2);
	    int sala = 7;
	    db.updateSalaProgrammazione(p, sala);
	    db.connect();
		String queryCheck = "SELECT * FROM PROGRAMMAZIONE WHERE ID = ? AND SALA = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, p.getId());
    	ps.setInt(2,sala);
		assertNotNull("Modifica fallita ",ps.executeQuery());
        db.c.close();
			
	}
	
	@Test
	public void testUpdateNomeAbbonamento() throws SQLException, ClassNotFoundException{
		db.connect();
		Abbonamento a = new Abbonamento(1,"Abbonamento 5 ingressi",30.00,"Tutti","18/09/2017");
		String nome = "5 ingressi";
		db.updateNomeAbbonamento(a, nome);
		db.connect();
		String queryCheck = "SELECT * FROM ABBONAMENTO WHERE ID = ? AND NOME = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, a.getId());
    	ps.setString(2,nome);
		assertNotNull("Modifica fallita ",ps.executeQuery());
	    
	    db.c.close();
	}
	
	@Test
	public void testUpdatePrezzoAbbonamento() throws SQLException, ClassNotFoundException{
		db.connect();
		Abbonamento a = new Abbonamento(2,"Abbonamento 10 ingressi",55.00,"Tutti","18/09/2017");
		double prezzo = 56.00;
		db.updatePrezzoAbbonamento(a, prezzo);
		db.connect();
		String queryCheck = "SELECT * FROM ABBONAMENTO WHERE ID = ? AND PREZZO = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, a.getId());
    	ps.setDouble(2,prezzo);
		assertNotNull("Modifica fallita ",ps.executeQuery());
	    db.c.close();
	   
	}
	
	@Test
	public void testUpdateBeneficiariAbbonamento() throws SQLException, ClassNotFoundException{
		db.connect();
		Abbonamento a = new Abbonamento(3,"Abbonamento under 26",50.00,"Ragazzi di età inferiore ai 26 anni",
				                        "30/09/2017");
		String clienti = "Minori di 26 anni";
		db.updateBeneficiariAbbonamento(a, clienti);
		db.connect();
		String queryCheck = "SELECT * FROM ABBONAMENTO WHERE ID = ? AND BENEFICIARI = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, a.getId());
    	ps.setString(2,clienti);
		assertNotNull("Modifica fallita ",ps.executeQuery());
	    db.c.close();
	}
	
	@Test
	public void testUpdateScadenzaAbbonamento() throws SQLException, ParseException, ClassNotFoundException{
		db.connect();
		Abbonamento a = new Abbonamento(4,"Abbonamento mensile",21.00,"Tutti","21/07/2017");
		String scadenza = "22/07/2017";
		db.updateScadenzaAbbonamento(a, scadenza);
		db.connect();
		String queryCheck = "SELECT * FROM ABBONAMENTO WHERE ID = ? AND SCADENZA = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, a.getId());
    	ps.setString(2,scadenza);
		assertNotNull("Modifica fallita ",ps.executeQuery());
		db.c.close();
	}
		
	@Test
	public void testUpdateNomeSconto() throws SQLException, ClassNotFoundException{
		db.connect();
		Sconto s = new Sconto(1,"Convenzione Studenti",6.00,"Studenti forniti di tesserino universitario","31/12/2017");
		String nome = "Ridotto Universitari";
		
		db.updateNomeSconto(s, nome);
		db.connect();
		String queryCheck = "SELECT * FROM SCONTO WHERE ID = ? AND NOME = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, s.getId());
    	ps.setString(2,nome);
		assertNotNull("Modifica fallita ",ps.executeQuery());
	    
	    db.c.close();
	}
	
	@Test
	public void testUpdatePrezzoSconto() throws SQLException, ClassNotFoundException{
		db.connect();
		Sconto s = new Sconto(2,"Convenzione Over 65",6.30,"Adulti di età superiore a 65 anni","31/12/2017");
		double prezzo = 6.20;
		
		db.updatePrezzoSconto(s, prezzo);
		db.connect();
		String queryCheck = "SELECT * FROM SCONTO WHERE ID = ? AND PREZZO = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, s.getId());
    	ps.setDouble(2,prezzo);
		assertNotNull("Modifica fallita ",ps.executeQuery());
		
	    db.c.close();
	}
	
	@Test
	public void testUpdateBeneficiariSconto() throws SQLException, ClassNotFoundException{
		db.connect();
		Sconto s = new Sconto(3,"Ridotto Coop",6.70,"Possessori della tessera Coop","31/12/2017");
		String clienti = "Soci Coop";
		
		db.updateBeneficiariSconto(s, clienti);
		db.connect();
		String queryCheck = "SELECT * FROM SCONTO WHERE ID = ? AND BENEFICIARI = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, s.getId());
    	ps.setString(2,clienti);
		assertNotNull("Modifica fallita ",ps.executeQuery());
		
	    db.c.close();
	}
	
	@Test
	public void testUpdateScadenzaSconto() throws SQLException, ParseException, ClassNotFoundException{
		db.connect();
		Sconto s = new Sconto(4,"Ridotto Bambini",7.00,"Bambini di età inferiore ai 10 anni","31/12/2017");
		String scadenza = "21/11/2017";
		
		db.updateScadenzaSconto(s, scadenza);
		db.connect();
		String queryCheck = "SELECT * FROM SCONTO WHERE ID = ? AND SCADENZA = ?";
    	PreparedStatement ps = db.c.prepareStatement(queryCheck);
    	ps.setInt(1, s.getId());
    	ps.setString(2,scadenza);
		assertNotNull("Modifica fallita ",ps.executeQuery());
		
		db.c.close();
	}
	
}
