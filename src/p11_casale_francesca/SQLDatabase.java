
import java.sql.*;
import java.util.Date;
import java.text.*;

// TODO: Auto-generated Javadoc
/**
 * La classe SQLDatabase gestisce le operazioni del database che il gestore intende eseguire.
 * @author Francesca 
 */
public class SQLDatabase{
	
	/** Oggetto Connection che rappresenta la connessione fisica col database server.*/
	Connection c = null;
	
	/** Oggetto Statement per la creazione e la presentazione di un'istruzione SQL nel database*/
	Statement stmt = null;
	
	/**
	 * La funzione connect() mostra come collegarsi a un database esistente. 
	 * Se il database non esiste, verrà creato un oggetto database.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	// connessione database
	public void connect() throws SQLException, ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:test.db");
		stmt = c.createStatement();
	}
	
	/**
	 * La funzione create() creare nel database creato in precedenza le tabelle
	 * FILM, SCONTO, ABBONAMENTO e PROGRAMMAZIONE
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	// creazione tabelle
	public void create() throws ClassNotFoundException, SQLException{
    
		connect();
		
    	String sqlFilm = null;
	    String sqlSconto = null;
	    String sqlAbbonamento = null;
	    String sqlProgrammazione = null;
  
	    DatabaseMetaData meta = c.getMetaData();
	   
    	ResultSet res = meta.getTables(null, null, "FILM", null);
	    if(!res.next()){
	    	sqlFilm = "CREATE TABLE FILM " +
		              "(ID INT PRIMARY KEY     NOT NULL," +
		              " TITOLO         TEXT    NOT NULL, " + 
		              " ANNO		   INT     NOT NULL, " + 
		              " REGISTA        TEXT, " + 
		              " LISTAATTORI    TEXT, " + 
		              " GENERE    	   TEXT, " +
		              " TRAMA   	   TEXT, " +
		              " DURATA         INT	   NOT NULL)"; 
		    stmt.executeUpdate(sqlFilm);
		    //Tabella FILM creata
        }
     
	    res = meta.getTables(null, null, "SCONTO", null);
	    if(!res.next()){
	    	sqlSconto = "CREATE TABLE SCONTO " +
		    		  	 "(ID INT PRIMARY KEY   NOT NULL," +
		                 " NOME         TEXT    NOT NULL, " + 
		                 " PREZZO		REAL    NOT NULL, " + 
		                 " BENEFICIARI  TEXT	NOT NULL, " + 
		                 " SCADENZA     TEXT	NOT NULL)";
		     stmt.executeUpdate(sqlSconto);
		     //Tabella SCONTO creata
	     }
	 
	     res = meta.getTables(null, null, "ABBONAMENTO", null);
	     if(!res.next()){
		     sqlAbbonamento = "CREATE TABLE ABBONAMENTO " +
		    		  	      "(ID INT PRIMARY KEY   NOT NULL," +
		                      " NOME         TEXT    NOT NULL, " + 
		                      " PREZZO		 REAL    NOT NULL, " + 
		                      " BENEFICIARI  TEXT	 NOT NULL, " + 
		                      " SCADENZA     TEXT	 NOT NULL)";
		     stmt.executeUpdate(sqlAbbonamento);
		     //Tabella ABBONAMENTO creata
	     }
	      
	     res = meta.getTables(null, null, "PROGRAMMAZIONE", null);
	     if(!res.next()){
		     sqlProgrammazione = "CREATE TABLE PROGRAMMAZIONE " +
		    		  	         "(ID INT PRIMARY KEY   NOT NULL," +
		                         " DATA         TEXT    NOT NULL, " + 
		                         " SALA	 		INT     NOT NULL)";
		     stmt.executeUpdate(sqlProgrammazione);
		     //Tabella PROGRAMMAZIONE creata
	     }
	     
	     stmt.close();
	     c.close();
		     
		//Fine dell'operazione di creazione tabelle
	}
	
	// Inserimenti
	
	/**
	 *  La funzione insertFilm() consente di inserire un film nella tabella FILM
	 *  e la relativa programmazione nella tabella PROGRAMMAZIONE
	 *
	 * @param f il Film che si intende inserire
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public void insertFilm(Film f) throws ClassNotFoundException, SQLException, ParseException{
		connect();
		// data corrente (di oggi)
		Date today = new Date();
	   
	    c.setAutoCommit(false);
	    	
	    String sqlF=null;
	    // Questa query è necessaria per verificare lo scenario alternativo "record già aggiunto"
	    // D'ora in poi questo commento sarà omesso per tutti gli analoghi check
	    String queryCheckF = "SELECT * FROM FILM WHERE ID = ?";
	    PreparedStatement psf = c.prepareStatement(queryCheckF);
	    psf.setInt(1, f.getId());
	    ResultSet rsf = psf.executeQuery();
	    
	    // conversione da int a Date
	    DateFormat df = new SimpleDateFormat("yyyy");
	    Date year = df.parse(String.valueOf(f.getAnnoPubblicazione()));
	     
	    // Scenario alternativo: mancano alcuni campi obbligatori del film
	    if (f.getId()<=0||f.getTitolo()==null||f.getAnnoPubblicazione()<=0||f.getRegista()==null||
	    	  f.getListaAttori()==null||f.getGenere()==null||f.getTrama()==null||f.getDurata()<=0){
	    	  System.err.println("Mancano alcuni campi obbligatori del film!");
	    // Scenario alternativo: la data di produzione è futura
	    } else if (year.after(today)){
	    	System.err.println("Errore! L'anno di produzione del film è futuro!");
	    	  
	    } else{
	    	// se il film non è stato aggiunto precedentemente lo aggiungo
	    	if (!rsf.next()) {
	    		sqlF = "INSERT INTO FILM (ID,TITOLO,ANNO,REGISTA,LISTAATTORI,GENERE,TRAMA,DURATA) " +
	    				"VALUES ('"+f.getId()+"','"+f.getTitolo()+"',"+f.getAnnoPubblicazione()+",'"+f.getRegista()+
                  	         	  "','"+f.getListaAttori()+"','"+f.getGenere()+"','"+f.getTrama()+"',"+f.getDurata()+
                  	         	  ");"; 
	    		stmt.executeUpdate(sqlF);
	    	  //Record FILM aggiunto
	    	}
	    }
	      
	    // inserimento relativa programmazione
	    for(int i=0; i<f.p.size(); i++){
	    	String sqlP=null;
		    Programmazione prog = f.p.get(i);
		    String queryCheckP = "SELECT * FROM PROGRAMMAZIONE WHERE ID = ?";
		    PreparedStatement psp = c.prepareStatement(queryCheckP);
		    psp.setInt(1, f.p.get(i).getId());
		    ResultSet rsp = psp.executeQuery();
		    
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    // conversione da String a Date
		    Date date = formatter.parse(prog.getData());
		    
		    // controllo sovrapposizioni programmazioni
		    String queryCheckProg = "SELECT * FROM PROGRAMMAZIONE WHERE DATA = ? AND SALA = ?";
	    	PreparedStatement psp1 = c.prepareStatement(queryCheckProg);
			psp1.setString(1, prog.getData());
	    	psp1.setInt(2,prog.getSala());
			ResultSet rsp1 = psp1.executeQuery();
			int count = 0;
		    while(rsp1.next()) {
		        count++;
		    }
		    
		    // Scenario alternativo: mancano alcuni campi obbligatori della programmazione
		    if (prog.getId()<=0||prog.getData()==null||prog.getSala()<=0){
		    	System.err.println("Mancano alcuni campi obbligatori della programmazione!");
		    // Scenario alternativo: la data della programmazione è passata
		    }else if(date.before(today)){
			    System.err.println("Errore! La data inserita è passata!");
			}else if(count>1){
		    	System.err.println("Errore! La sala "+prog.getSala()+" è già occupata nel giorno: "+prog.getData());
		    	//System.exit(0);
		    }else{
		    	if (!rsp.next()) {
		    		sqlP = "INSERT INTO PROGRAMMAZIONE (ID,DATA,SALA) " +
	                  	   "VALUES ("+prog.getId()+",'"+prog.getData()+"',"+prog.getSala()+");"; 
		    		stmt.executeUpdate(sqlP);
		    		//Record PROGRAMMAZIONE aggiunto
		        }
		    }
    		      
	      }
	      
	      stmt.close();
	      c.commit();
	      c.close();
	   
	    //Fine operazione inserimento film
	}
	
	/**
	 *  La funzione insertSconto() consente di inserire uno sconto nella tabella SCONTO
	 *
	 * @param s lo Sconto che si intende inserire
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public void insertSconto(Sconto s) throws ClassNotFoundException, SQLException, ParseException{
		connect();
		// data di oggi
		Date today = new Date();
	    
	      c.setAutoCommit(false);
	    	
	      String sql=null;
	      String queryCheck = "SELECT * FROM SCONTO WHERE ID = ?";
	      PreparedStatement ps = c.prepareStatement(queryCheck);
	      ps.setInt(1, s.getId());
	      ResultSet rs = ps.executeQuery();
	      
	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	      // conversione da String a Date
	      Date date = formatter.parse(s.getScadenza());
	      
	      // Scenario alternativo: mancano alcuni campi obbligatori dello sconto
	      if (s.getId()<=0||s.getNome()==null||s.getPrezzo()<=0.00||s.getBeneficiari()==null||s.getScadenza()==null){
	    	  System.err.println("Mancano alcuni campi obbligatori dello sconto!");
		  // Scenario alternativo: data passata
	      }else if(date.before(today)){
	    	  System.err.println("Errore! La data di scadenza inserita è passata!");
	      }else{
		      if (!rs.next()) {
		    	  sql = "INSERT INTO SCONTO (ID,NOME,PREZZO,BENEFICIARI,SCADENZA) " +
		              	"VALUES ("+s.getId()+",'"+s.getNome()+"',"+s.getPrezzo()+",'"+s.getBeneficiari()+"','"+
		    			         s.getScadenza()+"');"; 
		    	  stmt.executeUpdate(sql);
		    	  //Record SCONTO aggiunto
		      }
	      }
	      
	      stmt.close();
	      c.commit();
	      c.close();
	  
	    //Fine operazione inserimento sconto
	}
	
	/**
	 *  La funzione insertAbbonamento() consente di inserire un abbonamento nella tabella ABBONAMENTO
	 *
	 * @param a l'abbonamento che si intende inserire
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public void insertAbbonamento(Abbonamento a) throws ClassNotFoundException, SQLException, ParseException{
		connect();
		Date today = new Date();
	    
	      c.setAutoCommit(false);
	    	
	      String sql=null;
	      String queryCheck = "SELECT * FROM ABBONAMENTO WHERE ID = ?";
	      PreparedStatement ps = c.prepareStatement(queryCheck);
	      ps.setInt(1, a.getId());
	      ResultSet rs = ps.executeQuery();
	      
	      
	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	      // conversione da String a Date
	      Date date = formatter.parse(a.getScadenza());
	      
	      
	      // Scenario alternativo: mancano alcuni campi obbligatori dell'abbonamento
	      if (a.getId()<=0||a.getNome()==null||a.getPrezzo()<=0.00||a.getBeneficiari()==null||a.getScadenza()==null){
	    	  System.err.println("Mancano alcuni campi obbligatori dell'abbonamento!");
		      //System.exit(0);
	      }
	      // Scenario alternativo: data di scadenza passata
	      else if(date.before(today)){
	    	  System.err.println("Errore! La data di scadenza inserita è passata!");
		      //System.exit(0);
	      }
	      else{
		      if (!rs.next()) {
		    	  sql = "INSERT INTO ABBONAMENTO (ID,NOME,PREZZO,BENEFICIARI,SCADENZA) " +
	                  	   "VALUES ("+a.getId()+",'"+a.getNome()+"',"+a.getPrezzo()+",'"+a.getBeneficiari()+"','"+
		    			   a.getScadenza()+"');"; 
		    	  stmt.executeUpdate(sql);
		    	  //Record ABBONAMENTO aggiunto
		      }
	      }
	   
	      stmt.close();
	      c.commit();
	      c.close();
	    
	    //System.out.println("Fine operazione inserimento abbonamento");
	}
	
	// Rimozioni
	
	/**
	 *  La funzione deleteFilm() consente di rimuovere un film dalla tabella FILM
	 *
	 * @param f il Film che si intende rimuovere
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void deleteFilm(Film f) throws ClassNotFoundException, SQLException{
		
		connect();
		c.setAutoCommit(false);
		String queryCheck = "DELETE FROM FILM WHERE ID = ?";
		PreparedStatement ps = c.prepareStatement(queryCheck);
		ps.setInt(1, f.getId());
		ps.executeUpdate();
		c.commit();
		stmt.close();
		c.close();
	    
	}
	
	/**
	 *  La funzione deleteSconto() consente di rimuovere uno sconto dalla tabella SCONTO
	 *
	 * @param s lo Sconto che si intende rimuovere
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void deleteSconto(Sconto s) throws ClassNotFoundException, SQLException{
		connect();
	    
	    c.setAutoCommit(false);
	    	
	    String queryCheck = "DELETE FROM SCONTO WHERE ID = ?";
	    PreparedStatement ps = c.prepareStatement(queryCheck);
	    ps.setInt(1, s.getId());
	    ps.executeUpdate();
	    c.commit();
	    stmt.close(); 
	    c.close();
	    
	}
	
	/**
	 *  La funzione deleteAbbonamento() consente di rimuovere un abbonamento dalla tabella ABBONAMENTO
	 *
	 * @param a l'abbonamento che si intende rimuovere
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void deleteAbbonamento(Abbonamento a) throws ClassNotFoundException, SQLException{
		connect();
	    
	    c.setAutoCommit(false);
	    	
	    String queryCheck = "DELETE FROM ABBONAMENTO WHERE ID = ?";
	    PreparedStatement ps = c.prepareStatement(queryCheck);
	    ps.setInt(1, a.getId());
	    ps.executeUpdate();
	    c.commit();
	    stmt.close();
	    c.close();
	    
	}
	
	// Modifiche
	
	/**
	 *  La funzione updateDataProgrammazione() consente di modificare la data di una programmazione di un film.
	 *  Dopo aver modificato tale data, la tabella PROGRAMMAZIONE sarà aggiornata
	 *
	 * @param p la Programmazione che si intende modificare
	 * @param data la nuova data
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public void updateDataProgrammazione(Programmazione p, String data) 
			throws ClassNotFoundException, SQLException, ParseException{
		connect();
		c.setAutoCommit(false);
		// data di oggi
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    // conversione da String a Date
	    Date date = formatter.parse(p.getData());
	    // Scenario alternativo: sala già occupata
		// Controllo...
	    String checkSala = "SELECT * FROM PROGRAMMAZIONE WHERE DATA = ? AND SALA = ?";
    	PreparedStatement ps = c.prepareStatement(checkSala);
		ps.setString(1, data);
    	ps.setInt(2,p.getSala());
		ResultSet rs = ps.executeQuery();
		int count = 0;
	    while(rs.next()) {
	        count++;
	    }
	    if(count>1){
	    	// scenario alternativo verificato
	    	System.err.println("Errore! La sala "+p.getSala()+" è già occupata per più film "
	    						+ "nello stesso giorno: "+p.getData());
	    }
	    // Scenario alternativo: data passata
	    else if(date.before(today)){
        	System.err.println("Errore! La data inserita è passata!");
        }
        else{
		    String queryCheck = "UPDATE PROGRAMMAZIONE set DATA =? WHERE ID = ?";
		    PreparedStatement ps1 = c.prepareStatement(queryCheck);
		    ps1.setString(1, data);
		    ps1.setInt(2, p.getId());
		    ps1.executeUpdate();
        }
		c.commit();  
	    stmt.close();
	    c.close();
	}
	
	/**
	 *  La funzione updateSalaProgrammazione() consente di modificare la sala di una programmazione di un film.
	 *  Dopo aver modificato tale sala, la tabella PROGRAMMAZIONE sarà aggiornata
	 *
	 * @param p la Programmzione che si intende modificare
	 * @param sala la nuova sala
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void updateSalaProgrammazione(Programmazione p, int sala) throws ClassNotFoundException, SQLException{
		connect();
		c.setAutoCommit(false);
					
		// Scenario alternativo: sala già occupata
		// Controllo...
	    String checkSala1 = "SELECT * FROM PROGRAMMAZIONE WHERE DATA = ? AND SALA = ?";
    	PreparedStatement ps2 = c.prepareStatement(checkSala1);
		ps2.setString(1, p.getData());
    	ps2.setInt(2,sala);
		ResultSet rs1 = ps2.executeQuery();
		int count1 = 0;
	    while(rs1.next()) {
	        count1++;
	    }
	    if(count1>1){
	    	// scenario alternativo verificato
	    	System.err.println("Errore! La sala "+sala+" è già occupata per più film "
	    						+ "nello stesso giorno: "+p.getData());
	    }
	    else{
		    String queryCheck1 = "UPDATE PROGRAMMAZIONE set SALA =? WHERE ID = ?";
		    PreparedStatement ps3 = c.prepareStatement(queryCheck1);
		    ps3.setInt(1, sala);
		    ps3.setInt(2, p.getId());
		    ps3.executeUpdate();
	    }

		c.commit();  
	    stmt.close();
	    c.close();
	
	}
	
	/**
	 *  La funzione updateNomeSconto() consente di modificare il nome di uno sconto.
	 *  Dopo aver modificato tale nome, la tabella SCONTO sarà aggiornata
	 *
	 * @param s lo Sconto che si intende modificare
	 * @param nome il nuovo nome
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	// modifica sconto
	public void updateNomeSconto(Sconto s, String nome) throws ClassNotFoundException, SQLException{
		connect();
		c.setAutoCommit(false);
		// Scenario alternativo: nome nullo
		if(nome==null){
			System.err.println("Errore! Nome non valido!");
		}
		else{	
		    String queryCheck = "UPDATE SCONTO set NOME =? WHERE ID = ?";
		    PreparedStatement ps = c.prepareStatement(queryCheck);
		    ps.setString(1, nome);
		    ps.setInt(2, s.getId());
		    ps.executeUpdate();
		}  
		
		c.commit();
		stmt.close();
		c.close();
	}
	
	/**
	 *  La funzione updatePrezzoSconto() consente di modificare il prezzo di uno sconto.
	 *  Dopo aver modificato tale prezzo, la tabella SCONTO sarà aggiornata
	 *
	 * @param s lo Sconto che si intende modificare
	 * @param prezzo il nuovo prezzo
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	public void updatePrezzoSconto(Sconto s, double prezzo) throws ClassNotFoundException, SQLException {
		connect();
		c.setAutoCommit(false);
		// Scenario alternativo: prezzo nullo
		if(prezzo==0.00){
			System.err.println("Errore! Prezzo non valido!");
		}
		else{	
		    String queryCheck = "UPDATE SCONTO set PREZZO =? WHERE ID = ?";
		    PreparedStatement ps = c.prepareStatement(queryCheck);
		    ps.setDouble(1, prezzo);
		    ps.setInt(2, s.getId());
		    ps.executeUpdate();
		}  
		
		c.commit();
		stmt.close();
		c.close();
	}
			
	/**
	 *  La funzione updateBeneficiariSconto() consente di modificare i beneficiari di uno sconto.
	 *  Dopo aver modificato tale dato, la tabella SCONTO sarà aggiornata
	 *
	 * @param s lo Sconto che si intende modificare
	 * @param beneficiari il nuovo campo beneficiari
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	public void updateBeneficiariSconto(Sconto s, String beneficiari) throws ClassNotFoundException, SQLException {
		connect();
		c.setAutoCommit(false);		
						
		// Scenario alternativo: beneficiari nullo
	    if(beneficiari==null){
	    	System.err.println("Errore! Campo Beneficiari non valido!");
		}
	    else{	
		    String queryCheck2 = "UPDATE SCONTO set BENEFICIARI =? WHERE ID = ?";
		    PreparedStatement ps2 = c.prepareStatement(queryCheck2);
		    ps2.setString(1, beneficiari);
		    ps2.setInt(2, s.getId());
		    ps2.executeUpdate();
		    
	    }
	    
	    c.commit();
		stmt.close();
		c.close();
	}
	
	/**
	 *  La funzione updateScadenzaSconto() consente di modificare la scadenza di uno sconto.
	 *  Dopo aver modificato tale dato, la tabella SCONTO sarà aggiornata
	 *
	 * @param s lo Sconto che si intende modificare
	 * @param scadenza la nuova scadenza
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	
	public void updateScadenzaSconto(Sconto s, String scadenza) 
			throws ClassNotFoundException, SQLException, ParseException {
		connect();
		c.setAutoCommit(false);	
			
		Date today = new Date();
		// Verifico scenario alternativo: data di scadenza passata
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    // conversione da String a Date
	    Date date = formatter.parse(s.getScadenza());
		if(date.before(today)){
        	System.err.println("Errore! La scadenza inserita è passata!");
    
        }
		else{
		    String queryCheck = "UPDATE SCONTO set SCADENZA =? WHERE ID = ?";
		    PreparedStatement ps = c.prepareStatement(queryCheck);
		    ps.setString(1, scadenza);
		    ps.setInt(2, s.getId());
		    ps.executeUpdate();
		}  
		
		c.commit();
		stmt.close();
		c.close();
	
	}
	
	/**
	 *  La funzione updateNomeAbbonamento() consente di modificare il nome di un abbonamento.
	 *  Dopo aver modificato tale nome, la tabella ABBONAMENTO sarà aggiornata
	 *
	 * @param a l'abbonamento che si intende modificare
	 * @param nome il nuovo nome
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	// modifica abbonamento
	public void updateNomeAbbonamento(Abbonamento a, String nome) throws ClassNotFoundException, SQLException{
		connect();
		c.setAutoCommit(false);
		// Scenario alternativo: nome nullo
		if(nome==null){
			System.err.println("Errore! Nome non valido!");
		}
		else{	
		    String queryCheck = "UPDATE ABBONAMENTO set NOME =? WHERE ID = ?";
		    PreparedStatement ps = c.prepareStatement(queryCheck);
		    ps.setString(1, nome);
		    ps.setInt(2, a.getId());
		    ps.executeUpdate();
		}  
		
		c.commit();
		stmt.close();
		c.close();
	}
	
	/**
	 *  La funzione updatePrezzoAbbonamento() consente di modificare il prezzo di un abbonamento.
	 *  Dopo aver modificato tale prezzo, la tabella ABBONAMENTO sarà aggiornata
	 *
	 * @param a l'abbonamneto che si intende modificare
	 * @param prezzo il nuovo prezzo
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	public void updatePrezzoAbbonamento(Abbonamento a, double prezzo) throws ClassNotFoundException, SQLException {
		connect();
		c.setAutoCommit(false);
		// Scenario alternativo: prezzo nullo
		if(prezzo==0.00){
			System.err.println("Errore! Prezzo non valido!");
		}
		else{	
		    String queryCheck = "UPDATE ABBONAMENTO set PREZZO =? WHERE ID = ?";
		    PreparedStatement ps = c.prepareStatement(queryCheck);
		    ps.setDouble(1, prezzo);
		    ps.setInt(2, a.getId());
		    ps.executeUpdate();
		}  
		
		c.commit();
		stmt.close();
		c.close();
	}
			
	/**
	 *  La funzione updateBeneficiariAbbonamento() consente di modificare i beneficiari di un abbonamento.
	 *  Dopo aver modificato tale dato, la tabella ABBONAMENTO sarà aggiornata
	 *
	 * @param a l'abbonamento che si intende modificare
	 * @param beneficiari il nuovo campo beneficiari
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	public void updateBeneficiariAbbonamento(Abbonamento a, String beneficiari) throws ClassNotFoundException, SQLException {
		connect();
		c.setAutoCommit(false);		
						
		// Scenario alternativo: beneficiari nullo
	    if(beneficiari==null){
	    	System.err.println("Errore! Campo Beneficiari non valido!");
		}
	    else{	
		    String queryCheck2 = "UPDATE ABBONAMENTO set BENEFICIARI =? WHERE ID = ?";
		    PreparedStatement ps2 = c.prepareStatement(queryCheck2);
		    ps2.setString(1, beneficiari);
		    ps2.setInt(2, a.getId());
		    ps2.executeUpdate();
		    
	    }
	    
	    c.commit();
		stmt.close();
		c.close();
	}
	
	/**
	 *  La funzione updateScadenzaAbbonamento() consente di modificare la scadenza di un abbonamento.
	 *  Dopo aver modificato tale dato, la tabella ABBONAMENTO sarà aggiornata
	 *
	 * @param a l'abbonamento che si intende modificare
	 * @param scadenza la nuova scadenza
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	
	public void updateScadenzaAbbonamento(Abbonamento a, String scadenza) 
			throws ClassNotFoundException, SQLException, ParseException {
		connect();
		c.setAutoCommit(false);	
			
		Date today = new Date();
		// Verifico scenario alternativo: data di scadenza passata
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    // conversione da String a Date
	    Date date = formatter.parse(a.getScadenza());
		if(date.before(today)){
        	System.err.println("Errore! La scadenza inserita è passata!");
    
        }
		else{
		    String queryCheck = "UPDATE ABBONAMENTO set SCADENZA =? WHERE ID = ?";
		    PreparedStatement ps = c.prepareStatement(queryCheck);
		    ps.setString(1, scadenza);
		    ps.setInt(2, a.getId());
		    ps.executeUpdate();
		}  
		
		c.commit();
		stmt.close();
		c.close();
	
	}
	
	// Ricerche
	
	/**
	 *  La funzione selectFilm() consente di cercare un film dalla tabella FILM tramite il suo titolo.
	 *  Se il Film è trovato, saranno visualizzati la sua scheda e l'elenco completo della sua programmazione.
	 *
	 * @param f il Film che si intende cercare
	 * @return true, se il Film è stato trovato
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean selectFilm(Film f) throws ClassNotFoundException, SQLException{
		System.out.println("**Inizio ricerca film**");
		connect();

		boolean check = false;
	    c.setAutoCommit(false);
	    	
	    String queryCheck = "SELECT * FROM FILM WHERE TITOLO = ?";
	    PreparedStatement ps = c.prepareStatement(queryCheck);
	    ps.setString(1, f.getTitolo());
	    ResultSet rs = ps.executeQuery();
	      
	    while(rs.next()) {
	    	int id = rs.getInt("id");
	        String titolo = rs.getString("titolo");
	        int anno = rs.getInt("anno");
	        String regista = rs.getString("regista");
	        String listaattori = rs.getString("listaattori");
	        String genere = rs.getString("genere");
	        String trama = rs.getString("trama");
	        int durata = rs.getInt("durata");
	        System.out.println( "ID = " + id );
	        System.out.println( "TITOLO = " + titolo );
	        System.out.println( "ANNO = " + anno );
	        System.out.println( "REGISTA = " + regista );
	        System.out.println( "LISTA ATTORI = " + listaattori );
	        System.out.println( "GENERE = " + genere );
	        System.out.println( "TRAMA = " + trama );
	        System.out.println( "DURATA = " + durata );
	        System.out.println();
	        check = true;
	    }
	      
	    for(int i =0; i<f.p.size(); i++){
	    	Programmazione prog= f.p.get(i);
	        String queryCheck1 = "SELECT * FROM PROGRAMMAZIONE WHERE ID = ?";
	    	PreparedStatement ps1 = c.prepareStatement(queryCheck1);
			ps1.setInt(1,prog.getId());
			ResultSet rs1 = ps1.executeQuery();
			      
			while(rs1.next()) {	
				int id = rs1.getInt("id");
		        String data = rs1.getString("data");
		        int sala = rs1.getInt("sala");
		        System.out.println( "ID = " + id );
		        System.out.println( "DATA = " + data );
		        System.out.println( "SALA = " + sala );
		        System.out.println();
			}
	    }
	      
	    if(check==false){
	    	// Scenario alternativo: film inesistente
	    	System.out.println("Film "+f.getTitolo()+" non trovato!");
      	}
	      
	    stmt.close();
	    c.commit();
	    c.close();
	    System.out.println("**Fine ricerca film**");
	    return check;
	}
	
	/**
	 *  La funzione selectSconto() consente di cercare uno Sconto dalla tabella SCONTO tramite il suo nome.
	 *  Se lo Sconto è trovato, sarà visualizzata la sua scheda completa.
	 *
	 * @param s lo Sconto che si intende cercare
	 * @return true, se lo Sconto è stato trovato
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean selectSconto(Sconto s) throws ClassNotFoundException, SQLException{
		System.out.println("**Inizio ricerca sconto**");
		connect();
		boolean check = false;
	    c.setAutoCommit(false);
	    	
	    String queryCheck = "SELECT * FROM SCONTO WHERE NOME = ?";
	    PreparedStatement ps = c.prepareStatement(queryCheck);
	    ps.setString(1, s.getNome());
	    ResultSet rs = ps.executeQuery();
	      
	    while(rs.next()) {
	    	int id = rs.getInt("id");
	        String nome = rs.getString("nome");
	        double anno = rs.getDouble("prezzo");
	        String beneficiari = rs.getString("beneficiari");
	        String scadenza = rs.getString("scadenza");
	        System.out.println( "ID = " + id );
	        System.out.println( "NOME = " + nome );
	        System.out.println( "PREZZO = " + anno );
	        System.out.println( "BENEFICIARI = " + beneficiari );
	        System.out.println( "SCADENZA = " + scadenza );
	        System.out.println();
	        check = true;
	    }
	      
	    if(check==false){
	    	// Scenario alternativo: sconto inesistente
	    	System.out.println("Sconto "+s.getNome()+" non trovato!");
	    }
	      
	    stmt.close();
	    c.commit();
	    c.close();
	    System.out.println("**Fine ricerca sconto**");
	    return check;
	}
	
	/**
	 *  La funzione selectAbbonamento() consente di cercare un Abbonamneto dalla tabella ABBOAMENTO tramite il suo nome.
	 *  Se l'abbonamneto è trovato, sarà visualizzata la sua scheda completa.
	 *
	 * @param a l'abbonamento che si intende cercare
	 * @return true, se l'abbonamento è stato trovato
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean selectAbbonamento(Abbonamento a) throws ClassNotFoundException, SQLException{
		System.out.println("**Inizio ricerca abbonamento**");
		connect();
		boolean check = false;
	    c.setAutoCommit(false);
	    	
	    String queryCheck = "SELECT * FROM ABBONAMENTO WHERE NOME = ?";
	    PreparedStatement ps = c.prepareStatement(queryCheck);
	    ps.setString(1, a.getNome());
	    ResultSet rs = ps.executeQuery();
	      
	    while(rs.next()) {
	    	int id = rs.getInt("id");
	        String nome = rs.getString("nome");
	        double anno = rs.getDouble("prezzo");
	        String beneficiari = rs.getString("beneficiari");
	        String scadenza = rs.getString("scadenza");
	        System.out.println( "ID = " + id );
	        System.out.println( "NOME = " + nome );
	        System.out.println( "PREZZO = " + anno );
	        System.out.println( "BENEFICIARI = " + beneficiari );
	        System.out.println( "SCADENZA = " + scadenza );
	        System.out.println();
	        check = true;
	    }
	      
	    if(check==false){
	    	// Scenario alternativo: abbonamento inesistente
	    	System.out.println("Abbonamento "+a.getNome()+" non trovato!");
	    }
	    
	    stmt.close();
	    c.commit();
	    c.close();
	    System.out.println("**Fine ricerca abbonamento**");
	    return check;
	}
	
}
