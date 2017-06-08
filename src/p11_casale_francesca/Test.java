import java.sql.SQLException;
import java.text.ParseException;

// TODO: Auto-generated Javadoc
/**
 * La classe Test consente la verifica dei casi d'uso.
 */
public class Test {

	/**
	 * Il metodo main.
	 *
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		SQLDatabase db = new SQLDatabase();
		db.create();
		//db.insertCinema();
		Film f = new Film(1,"Wonder Woman",2017,"Patty Jenkins","Gal Gadot, Chris Pine, Robin Wright","azione",
				"Diana Prince diventa Wonder Woman",175);
		Film f1 = new Film(2,"Pirati dei Caraibi",2017,"Espen Sandberg","Johnny Depp, Geoffrey Rush, Orlando Bloom",
				"azione","Capitan Jack Sparrow affronta il terrificante Capitano Salazar",135);
		/*Film f2 = new Film(3,"Pirati dei Caraibi",2003,"Gore Verbinski","Johnny Depp, Geoffrey Rush, Orlando Bloom",
				"azione","Capitan Jack Sparrow vuole sbarazzarsi della Maledizione della Prima Luna",143);*/
		Programmazione p = new Programmazione(1,"21/06/2017 21:45",2);
		f.p.add(p);
		db.insertFilm(f);
		Programmazione p1 = new Programmazione(2,"23/06/2017 21:45",2);
		f1.p.add(p1);
		db.insertFilm(f1);
		//db.updateProgrammazione(p);
		//db.updateProgrammazione(p1);
		db.selectFilm(f);
		db.selectFilm(f1);
		
		Sconto s = new Sconto(1,"Convenzione Studenti",6.00,"Studenti forniti di tesserino universitario","31/12/2017");
		Sconto s1 = new Sconto(2,"Convenzione Over 65",6.30,"Adulti di età superiore a 65 anni","31/12/2017");
		Sconto s2 = new Sconto(3,"Ridotto Coop",6.70,"Possessori della tessera Coop","31/12/2017");
		Sconto s3 = new Sconto(4,"Ridotto Bambini",7.00,"Bambini di età inferiore ai 10 anni","31/12/2017");
		db.insertSconto(s);
		db.insertSconto(s1);
		db.insertSconto(s2);
		db.insertSconto(s3);
		//db.updateSconto(s);
		//db.updateSconto(s1);
		//db.updateSconto(s2);
		//db.updateSconto(s3);
		db.selectSconto(s);
		db.selectSconto(s1);
		db.selectSconto(s2);
		db.selectSconto(s3);
		Abbonamento a = new Abbonamento(1,"Abbonamento 5 ingressi",30.00,"Tutti","18/09/2017");
		Abbonamento a1 = new Abbonamento(2,"Abbonamento 10 ingressi",55.00,"Tutti","18/09/2017");
		Abbonamento a2 = new Abbonamento(3,"Abbonamento under 26",50.00,"Ragazzi di età inferiore ai 26 anni",
				                         "30/09/2017");
		Abbonamento a3 = new Abbonamento(4,"Abbonamento mensile",21.00,"Tutti","21/07/2017");
		db.insertAbbonamento(a);
		db.insertAbbonamento(a1);
		db.insertAbbonamento(a2);
		db.insertAbbonamento(a3);
		//db.updateAbbonamento(a);
		//db.updateAbbonamento(a1);
		//db.updateAbbonamento(a2);
		//db.updateAbbonamento(a3);
		db.selectAbbonamento(a);
		db.selectAbbonamento(a1);
		db.selectAbbonamento(a2);
		db.selectAbbonamento(a3);
		
	}

}
