package p11_casale_francesca;

import java.sql.*;
import java.util.*;
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
	 */
	// connessione database
	public void connect(){
		try {
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:test.db");
		    stmt = c.createStatement();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
		//database aperto con successo
	}
	
	/**
	 * La funzione create() creare nel database creato in precedenza le tabelle
	 * FILM, SCONTO, ABBONAMENTO e PROGRAMMAZIONE
	 */
	// creazione tabelle
	public void create(){
    
		connect();
		try {
			
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
	        }else{
	        	//System.out.println("Tabella FILM già esistente!");
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
		     }else{
		    	 //System.out.println("Tabella SCONTO già esistente!");
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
		     }else{
		    	 //System.out.println("Tabella ABBONAMENTO già esistente!"); 
		     }
		      
		     res = meta.getTables(null, null, "PROGRAMMAZIONE", null);
		     if(!res.next()){
			     sqlProgrammazione = "CREATE TABLE PROGRAMMAZIONE " +
			    		  	         "(ID INT PRIMARY KEY   NOT NULL," +
			                         " DATA         TEXT    NOT NULL, " + 
			                         " SALA	 		INT     NOT NULL)";
			     stmt.executeUpdate(sqlProgrammazione);
			     //Tabella PROGRAMMAZIONE creata
		     }else{
		    	 //System.out.println("Tabella PROGRAMMAZIONE già esistente!"); 
		     }
		    
		     stmt.close();
		     c.close();
		      
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
		//Fine dell'operazione di creazione tabelle
	}
	
	// Inserimenti
	
	/**
	 *  La funzione insertFilm() consente di inserire un film nella tabella FILM
	 *  e la relativa programmazione nella tabella PROGRAMMAZIONE
	 *
	 * @param f il Film che si intende inserire
	 */
	public void insertFilm(Film f){
		connect();
		// data corrente (di oggi)
		Date today = new Date();
	    try {
	      c.setAutoCommit(false);
	    	
	      String sqlF=null;
	      // Questa query è necessaria per verificare lo scenario alternativo "record già aggiunto"
	      // D'ora in poi questo commento sarà omesso per tutti gli analoghi check
	      String queryCheckF = "SELECT * FROM FILM WHERE ID = ?";
	      PreparedStatement psf = c.prepareStatement(queryCheckF);
	      psf.setInt(1, f.getId());
	      ResultSet rsf = psf.executeQuery();
	      
	      if (!rsf.next()) {
	    	  sqlF = "INSERT INTO FILM (ID,TITOLO,ANNO,REGISTA,LISTAATTORI,GENERE,TRAMA,DURATA) " +
                  	   "VALUES ('"+f.getId()+"','"+f.getTitolo()+"',"+f.getAnnoPubblicazione()+",'"+f.getRegista()+
                  	   "','"+f.getListaAttori()+"','"+f.getGenere()+"','"+f.getTrama()+"',"+f.getDurata()+");"; 
	    	  stmt.executeUpdate(sqlF);
	    	  //Record FILM aggiunto
	      }else{
	    	  // Scenario alternativo: il film esiste già
	    	  //System.out.println("Il film esiste già!");
	      }
	      
	      // Scenario alternativo: mancano alcuni campi obbligatori del film
	      if (f.getId()<=0||f.getTitolo()==null||f.getAnnoPubblicazione()<=0||f.getRegista()==null||
	    	  f.getListaAttori()==null||f.getGenere()==null||f.getTrama()==null||f.getDurata()<=0){
	    	  System.err.println("Mancano alcuni campi obbligatori del film!");
	    	  System.exit(0);
	      }
	      
	      // Scenario alternativo: la data di produzione è futura
	      DateFormat df = new SimpleDateFormat("yyyy");
	      Date year = null;
	      try {
	    	  // conversione da int a Date
	    	  year = df.parse(String.valueOf(f.getAnnoPubblicazione()));
	      } catch (ParseException e) {
            e.printStackTrace();
	      }
	      if(year.after(today)){
	    	  System.err.println("Errore! L'anno di produzione del film è futuro!");
	    	  System.exit(0);
	      }
	      
	      // inserimento relativa programmazione
	      for(int i=0; i<f.p.size(); i++){
		      String sqlP=null;
		      Programmazione prog = f.p.get(i);
		      String queryCheckP = "SELECT * FROM PROGRAMMAZIONE WHERE ID = ?";
		      PreparedStatement psp = c.prepareStatement(queryCheckP);
		      psp.setInt(1, f.p.get(i).getId());
		      ResultSet rsp = psp.executeQuery();
		      
		      if (!rsp.next()) {
		    	  sqlP = "INSERT INTO PROGRAMMAZIONE (ID,DATA,SALA) " +
	                  	   "VALUES ("+prog.getId()+",'"+prog.getData()+"',"+prog.getSala()+");"; 
		    	  stmt.executeUpdate(sqlP);
		    	  //Record PROGRAMMAZIONE aggiunto
		      }else{
		    	  // Scenario alternativo: la programmazione esiste già
		    	  //System.out.println("La programmazione esiste già!");
		      }
		      
		      // Scenario alternativo: mancano alcuni campi obbligatori della programmazione
		      if (prog.getId()<=0||prog.getData()==null||prog.getSala()<=0){
		    	  System.err.println("Mancano alcuni campi obbligatori della programmazione!");
			      System.exit(0);
		      }
		     
		      // Scenario alternativo: la data della programmazione è passata
		      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		      Date date = null;
		      try {
		    	  // conversione da String a Date
		    	  date = formatter.parse(prog.getData());
		      } catch (ParseException e) {
	            e.printStackTrace();
		      }
		      if(date.before(today)){
		    	  System.err.println("Errore! La data inserita è passata!");
			      System.exit(0);
		      }
		      
		      // Scenario alternativo: la sala è già occupata
		      String queryCheckProg = "SELECT * FROM PROGRAMMAZIONE WHERE DATA = ? AND SALA = ?";
		    	PreparedStatement psp1 = c.prepareStatement(queryCheckProg);
				psp1.setString(1, prog.getData());
		    	psp1.setInt(2,prog.getSala());
				ResultSet rsp1 = psp1.executeQuery();
				int count = 0;
			    while(rsp1.next()) {
			        count++;
			    }
			    if(count>1){
			    	System.err.println("Errore! La sala "+prog.getSala()+" è già occupata nel giorno: "+prog.getData());
			    	System.exit(0);
			    }
		      
	      }
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println("Errore: "+ e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    //Fine operazione inserimento film
	}
	
	/**
	 *  La funzione insertSconto() consente di inserire uno sconto nella tabella SCONTO
	 *
	 * @param s lo Sconto che si intende inserire
	 */
	public void insertSconto(Sconto s){
		connect();
		// data di oggi
		Date today = new Date();
	    try {
	      c.setAutoCommit(false);
	    	
	      String sql=null;
	      String queryCheck = "SELECT * FROM SCONTO WHERE ID = ?";
	      PreparedStatement ps = c.prepareStatement(queryCheck);
	      ps.setInt(1, s.getId());
	      ResultSet rs = ps.executeQuery();
	      
	      if (!rs.next()) {
	    	  sql = "INSERT INTO SCONTO (ID,NOME,PREZZO,BENEFICIARI,SCADENZA) " +
                  	   "VALUES ("+s.getId()+",'"+s.getNome()+"',"+s.getPrezzo()+",'"+s.getBeneficiari()+"','"+
	    			   s.getScadenza()+"');"; 
	    	  stmt.executeUpdate(sql);
	    	  //Record SCONTO aggiunto
	      }else{
	    	  // Scenario alternativo: sconto già inserito
	    	  //System.out.println("Lo sconto esiste già!");
	      }
	       
	      // Scenario alternativo: data di scadenza passata
	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	      Date date = null;
	      try {
	    	  // conversione da String a Date
	    	  date = formatter.parse(s.getScadenza());
	      } catch (ParseException e) {
            e.printStackTrace();
	      }
	      if(date.before(today)){
	    	  System.err.println("Errore! La data di scadenza inserita è passata!");
		      System.exit(0);
	      }
	      
	      // Scenario alternativo: mancano alcuni campi obbligatori dello sconto
	      if (s.getId()<=0||s.getNome()==null||s.getPrezzo()<=0.00||s.getBeneficiari()==null||s.getScadenza()==null){
	    	  System.err.println("Mancano alcuni campi obbligatori dello sconto!");
		      System.exit(0);
	      }
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    //Fine operazione inserimento sconto
	}
	
	/**
	 *  La funzione insertAbbonamento() consente di inserire un abbonamento nella tabella ABBONAMENTO
	 *
	 * @param a l'abbonamento che si intende inserire
	 */
	public void insertAbbonamento(Abbonamento a){
		connect();
		Date today = new Date();
	    try {
	      c.setAutoCommit(false);
	    	
	      String sql=null;
	      String queryCheck = "SELECT * FROM ABBONAMENTO WHERE ID = ?";
	      PreparedStatement ps = c.prepareStatement(queryCheck);
	      ps.setInt(1, a.getId());
	      ResultSet rs = ps.executeQuery();
	      
	      if (!rs.next()) {
	    	  sql = "INSERT INTO ABBONAMENTO (ID,NOME,PREZZO,BENEFICIARI,SCADENZA) " +
                  	   "VALUES ("+a.getId()+",'"+a.getNome()+"',"+a.getPrezzo()+",'"+a.getBeneficiari()+"','"+
	    			   a.getScadenza()+"');"; 
	    	  stmt.executeUpdate(sql);
	    	  //Record ABBONAMENTO aggiunto
	      }else{
	    	  // Scenario alternativo: abbonamento già esistente
	    	  //System.out.println("L'abbonamento esiste già!");
	      }
	      
	      // Scenario alternativo: data di scadenza passata
	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	      Date date = null;
	      try {
	    	  // conversione da String a Date
	    	  date = formatter.parse(a.getScadenza());
	      } catch (ParseException e) {
            e.printStackTrace();
	      }
	      if(date.before(today)){
	    	  System.err.println("Errore! La data di scadenza inserita è passata!");
		      System.exit(0);
	      }
	      
	      // Scenario alternativo: mancano alcuni campi obbligatori dell'abbonamento
	      if (a.getId()<=0||a.getNome()==null||a.getPrezzo()<=0.00||a.getBeneficiari()==null||a.getScadenza()==null){
	    	  System.err.println("Mancano alcuni campi obbligatori dell'abbonamento!");
		      System.exit(0);
	      }
	      
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    //System.out.println("Fine operazione inserimento abbonamento");
	}
	
	// Rimozioni
	
	/**
	 *  La funzione deleteFilm() consente di rimuovere un film dalla tabella FILM
	 *
	 * @param f il Film che si intende rimuovere
	 */
	public void deleteFilm(Film f){
		connect();
	    try {
	      c.setAutoCommit(false);
	      String queryCheck = "DELETE FROM FILM WHERE ID = ?";
	      PreparedStatement ps = c.prepareStatement(queryCheck);
	      ps.setInt(1, f.getId());
	      ps.executeUpdate();
	      c.commit();
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	
	/**
	 *  La funzione deleteSconto() consente di rimuovere uno sconto dalla tabella SCONTO
	 *
	 * @param s lo Sconto che si intende rimuovere
	 */
	public void deleteSconto(Sconto s){
		connect();
	    try {
	      c.setAutoCommit(false);
	    	
	      String queryCheck = "DELETE FROM SCONTO WHERE ID = ?";
	      PreparedStatement ps = c.prepareStatement(queryCheck);
	      ps.setInt(1, s.getId());
	      ps.executeUpdate();
	      c.commit();
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	
	/**
	 *  La funzione deleteAbbonamento() consente di rimuovere un abbonamento dalla tabella ABBONAMENTO
	 *
	 * @param a l'abbonamento che si intende rimuovere
	 */
	public void deleteAbbonamento(Abbonamento a){
		connect();
	    try {
	      c.setAutoCommit(false);
	    	
	      String queryCheck = "DELETE FROM ABBONAMENTO WHERE ID = ?";
	      PreparedStatement ps = c.prepareStatement(queryCheck);
	      ps.setInt(1, a.getId());
	      ps.executeUpdate();
	      c.commit();
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	
	// Modifiche
	
	/**
	 *  La funzione updateProgrammazione() consente di modificare i dati di una programmazione di un film.
	 *  Dopo aver modificato tali dati, la tabella PROGRAMMAZIONE sarà aggiornata
	 *
	 * @param p la Programmzione che si intende modificare
	 */
	public void updateProgrammazione(Programmazione p){
		// data di oggi
		Date today = new Date();
		System.out.println("Quale campo modificare?");
		System.out.println("- 1 per la data");
		System.out.println("- 2 per la sala");
		Scanner choice = new Scanner(System.in);
		int scelta = choice.nextInt();
		connect();
		try {
			c.setAutoCommit(false);
			switch(scelta){
				case 1: System.out.println("Inserire la nuova data");
						Scanner scanner = new Scanner(System.in);
						String data = scanner.nextLine();
						
					    String queryCheck = "UPDATE PROGRAMMAZIONE set DATA =? WHERE ID = ?";
					    PreparedStatement ps = c.prepareStatement(queryCheck);
					    ps.setString(1, data);
					    ps.setInt(2, p.getId());
					    ps.executeUpdate();
					    
					    // Scenario alternativo: data passata
					    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					    Date date = null;
				        try {
				        	// conversione da String a Date
				        	date = formatter.parse(p.getData());
				        } catch (ParseException e) {
				        	e.printStackTrace();
				        }
				        if(date.before(today)){
				        	System.err.println("Errore! La data inserita è passata!");
				        	System.exit(0);
				        }
					  
						break;
				
				case 2: System.out.println("Inserire la nuova sala");
						Scanner hall = new Scanner(System.in);
						int sala = hall.nextInt();
						
					    String queryCheck1 = "UPDATE PROGRAMMAZIONE set SALA =? WHERE ID = ?";
					    PreparedStatement ps1 = c.prepareStatement(queryCheck1);
					    ps1.setInt(1, sala);
					    ps1.setInt(2, p.getId());
					    ps1.executeUpdate();
					    
					    // Scenario alternativo: sala già occupata
					    String queryCheck2 = "SELECT * FROM PROGRAMMAZIONE WHERE DATA = ? AND SALA = ?";
				    	PreparedStatement ps2 = c.prepareStatement(queryCheck2);
						ps2.setString(1, p.getData());
				    	ps2.setInt(2,sala);
						ResultSet rs1 = ps2.executeQuery();
						int count = 0;
					    while(rs1.next()) {
					        count++;
					    }
					    if(count>1){
					    	System.err.println("Errore! La sala "+sala+" è già occupata per più film "
					    						+ "nello stesso giorno: "+p.getData());
					    	System.exit(0);
					    }
					   
						break;
						
				default: System.err.println("Parametro errato!");
						 break;
			}
			
			c.commit();  
		    stmt.close();
		    c.commit();
		    c.close();
		} catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	    }
		
	    
	    
		choice.close();
	}
	
	/**
	 *  La funzione updateSconto() consente di modificare i dati di uno sconto.
	 *  Dopo aver modificato tali dati, la tabella SCONTO sarà aggiornata
	 *
	 * @param S lo Sconto che si intende modificare
	 */
	// modifica sconto
	public void updateSconto(Sconto s){
		System.out.println("Quale campo modificare?");
		System.out.println("- 1 per il nome");
		System.out.println("- 2 per il prezzo");
		System.out.println("- 3 per i beneficiari");
		System.out.println("- 4 per la scadenza");
		Scanner choice = new Scanner(System.in);
		int scelta = choice.nextInt();
		connect();
		switch(scelta){
			case 1: System.out.println("Inserire il nuovo nome");
					Scanner name = new Scanner(System.in);
					String nome = name.nextLine();
					try {
						c.setAutoCommit(false);
					    
						// Scenario alternativo: nome nullo
						if(nome==null){
							System.err.println("Errore! Nome non valido!");
							System.exit(0);
						}
						
					    String queryCheck = "UPDATE SCONTO set NOME =? WHERE ID = ?";
					    PreparedStatement ps = c.prepareStatement(queryCheck);
					    ps.setString(1, nome);
					    ps.setInt(2, s.getId());
					    ps.executeUpdate();
					    c.commit();
					    stmt.close();
					    c.commit();
					    c.close();
				    } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				    }
					break;
			
			case 2: System.out.println("Inserire il nuovo prezzo");
					Scanner prize = new Scanner(System.in);
					double prezzo = prize.nextDouble();
					try {
						c.setAutoCommit(false);
						
						// Scenario alternativo: prezzo nullo
						if(prezzo==0.00){
							System.err.println("Errore! Prezzo invalido!");
							System.exit(0);
						}
						
					    String queryCheck = "UPDATE SCONTO set PREZZO =? WHERE ID = ?";
					    PreparedStatement ps = c.prepareStatement(queryCheck);
					    ps.setDouble(1, prezzo);
					    ps.setInt(2, s.getId());
					    ps.executeUpdate();
					    c.commit();  
					    stmt.close();
					    c.commit();
					    c.close();
				    } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				    }
					break;
			case 3: System.out.println("Inserire i nuovi beneficiari");
					Scanner payees = new Scanner(System.in);
					String beneficiari = payees.nextLine();
					try {
						c.setAutoCommit(false);
						
						// Scenario alternativo: beneficiari nullo
						if(beneficiari==null){
							System.err.println("Errore! Campo Beneficiari non valido!");
							System.exit(0);
						}
						
					    String queryCheck = "UPDATE SCONTO set BENEFICIARI =? WHERE ID = ?";
					    PreparedStatement ps = c.prepareStatement(queryCheck);
					    ps.setString(1, beneficiari);
					    ps.setInt(2, s.getId());
					    ps.executeUpdate();
					    c.commit();  
					    stmt.close();
					    c.commit();
					    c.close();
				    } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				    }
					break;
			case 4: System.out.println("Inserire la nuova scadenza");
					Scanner expiry = new Scanner(System.in);
					String scadenza = expiry.nextLine();
					Date today = new Date();
					try {
						c.setAutoCommit(false);
					    String queryCheck = "UPDATE SCONTO set SCADENZA =? WHERE ID = ?";
					    PreparedStatement ps = c.prepareStatement(queryCheck);
					    ps.setString(1, scadenza);
					    ps.setInt(2, s.getId());
					    ps.executeUpdate();
					    
					    // Scenario alternativo: data di scadenza passata
					    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					    Date date = null;
				        try {
				        	// conversione da String a Date
				        	date = formatter.parse(s.getScadenza());
				        } catch (ParseException e) {
				        	e.printStackTrace();
				        }
				        
						if(date.before(today)){
				        	System.err.println("Errore! La scadenza inserita è passata!");
				        	System.exit(0);
				        }
					    
					    c.commit();  
					    stmt.close();
					    c.commit();
					    c.close();
				    } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				    }
					break;
			default: System.err.println("Parametro errato!");
					 break;
		}
		choice.close();
	}
	
	/**
	 *  La funzione updateAbbonamento() consente di modificare i dati di un abbonamento.
	 *  Dopo aver modificato tali dati, la tabella ABBONAMENTO sarà aggiornata
	 *
	 * @param a l'abbonamento che si intende modificare
	 */
	// modifica abbonamento
	public void updateAbbonamento(Abbonamento a){
		System.out.println("Quale campo modificare?");
		System.out.println("- 1 per il nome");
		System.out.println("- 2 per il prezzo");
		System.out.println("- 3 per i beneficiari");
		System.out.println("- 4 per la scadenza");
		Scanner choice = new Scanner(System.in);
		int scelta = choice.nextInt();
		connect();
		switch(scelta){
			case 1: System.out.println("Inserire il nuovo nome");
					Scanner name = new Scanner(System.in);
					String nome = name.nextLine();
					try {
						c.setAutoCommit(false);
						
						// Scenario alternativo: nome nullo
						if(nome==null){
							System.err.println("Errore! Nome non valido!");
							System.exit(0);
						}
						
					    String queryCheck = "UPDATE ABBONAMENTO set NOME =? WHERE ID = ?";
					    PreparedStatement ps = c.prepareStatement(queryCheck);
					    ps.setString(1, nome);
					    ps.setInt(2, a.getId());
					    ps.executeUpdate();
					    c.commit();
					    stmt.close();
					    c.commit();
					    c.close();
				    } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				    }
					break;
			
			case 2: System.out.println("Inserire il nuovo prezzo");
					Scanner prize = new Scanner(System.in);
					double prezzo = prize.nextDouble();
					try {
						c.setAutoCommit(false);
						
						// Scenario alternativo: prezzo nullo
						if(prezzo==0.00){
							System.err.println("Errore! Prezzo invalido!");
							System.exit(0);
						}
						
					    String queryCheck = "UPDATE ABBONAMENTO set PREZZO =? WHERE ID = ?";
					    PreparedStatement ps = c.prepareStatement(queryCheck);
					    ps.setDouble(1, prezzo);
					    ps.setInt(2, a.getId());
					    ps.executeUpdate();
					    c.commit();  
					    stmt.close();
					    c.commit();
					    c.close();
				    } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				    }
					break;
			case 3: System.out.println("Inserire i nuovi beneficiari");
					Scanner payees = new Scanner(System.in);
					String beneficiari = payees.nextLine();
					try {
						c.setAutoCommit(false);
						
						// Scenario alternativo: beneficiari nullo
						if(beneficiari==null){
							System.err.println("Errore! Campo Beneficiari non valido!");
							System.exit(0);
						}
						
					    String queryCheck = "UPDATE ABBONAMENTO set BENEFICIARI =? WHERE ID = ?";
					    PreparedStatement ps = c.prepareStatement(queryCheck);
					    ps.setString(1, beneficiari);
					    ps.setInt(2, a.getId());
					    ps.executeUpdate();
					    c.commit();  
					    stmt.close();
					    c.commit();
					    c.close();
				    } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				    }
					break;
			case 4: System.out.println("Inserire la nuova scadenza");
					Scanner expiry = new Scanner(System.in);
					String scadenza = expiry.nextLine();
					Date today = new Date();
					try {
						c.setAutoCommit(false);
					    String queryCheck = "UPDATE ABBONAMENTO set SCADENZA =? WHERE ID = ?";
					    PreparedStatement ps = c.prepareStatement(queryCheck);
					    ps.setString(1, scadenza);
					    ps.setInt(2, a.getId());
					    ps.executeUpdate();
					    
					    // Scenario alternativo: data di scadenza passata
					    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					    Date date = null;
				        try {
				        	// conversione da String a Date
				        	date = formatter.parse(a.getScadenza());
				        } catch (ParseException e) {
				        	e.printStackTrace();
				        }
				        
						if(date.before(today)){
				        	System.err.println("Errore! La scadenza inserita è passata!");
				        	System.exit(0);
				        }
					    
					    c.commit();  
					    stmt.close();
					    c.commit();
					    c.close();
				    } catch ( Exception e ) {
				        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				        System.exit(0);
				    }
					break;
			default: System.err.println("Parametro errato!");
					 break;
		}
		choice.close();	
	}
	
	// Ricerche
	
	/**
	 *  La funzione selectFilm() consente di cercare un film dalla tabella FILM tramite il suo titolo.
	 *  Se il Film è trovato, saranno visualizzati la sua scheda e l'elenco completo della sua programmazione.
	 *
	 * @param f il Film che si intende cercare
	 * @return true, se il Film è stato trovato
	 */
	public boolean selectFilm(Film f){
		System.out.println("**Inizio ricerca film**");
		connect();

		boolean check = false;
	    try {
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
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("**Fine ricerca film**");
	    return check;
	}
	
	/**
	 *  La funzione selectSconto() consente di cercare uno Sconto dalla tabella SCONTO tramite il suo nome.
	 *  Se lo Sconto è trovato, sarà visualizzata la sua scheda completa.
	 *
	 * @param s lo Sconto che si intende cercare
	 * @return true, se lo Sconto è stato trovato
	 */
	public boolean selectSconto(Sconto s){
		System.out.println("**Inizio ricerca sconto**");
		connect();
		boolean check = false;
	    try {
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
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("**Fine ricerca sconto**");
	    return check;
	}
	
	/**
	 *  La funzione selectAbbonamento() consente di cercare un Abbonamneto dalla tabella ABBOAMENTO tramite il suo nome.
	 *  Se l'abbonamneto è trovato, sarà visualizzata la sua scheda completa.
	 *
	 * @param a l'abbonamento che si intende cercare
	 * @return true, se l'abbonamento è stato trovato
	 */
	public boolean selectAbbonamento(Abbonamento a){
		System.out.println("**Inizio ricerca abbonamento**");
		connect();
		boolean check = false;
	    try {
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
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("**Fine ricerca abbonamento**");
	    return check;
	}
	
}
