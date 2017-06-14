package p11_casale_francesca;

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
		Programmazione p = new Programmazione(1,"12/06/2017 21:45",2);
		Programmazione p1 = new Programmazione(2,"13/06/2017 21:45",1);
		Programmazione p2 = new Programmazione(3,"14/06/2017 21:45",3);
		f.p.add(p);
		f.p.add(p1);
		f.p.add(p2);
		db.insertFilm(f);
		Programmazione p3 = new Programmazione(4,"12/06/2017 21:45",4);
		Programmazione p4 = new Programmazione(5,"13/06/2017 21:45",5);
		f1.p.add(p3);
		f1.p.add(p4);
		db.insertFilm(f1);
		
		//db.updateProgrammazione(p4);
		db.selectFilm(f);
		db.selectFilm(f1);
		//db.checkErrorProgramFilms(p4);
		//db.selectFilm(f);
		Sconto s = new Sconto(1,"Convenzione Studenti",6.00,"Studenti forniti di tesserino universitario","31/12/2017");
		Sconto s1 = new Sconto(2,"Convenzione Over 65",6.30,"Adulti di età superiore a 65 anni","31/12/2017");
		db.insertSconto(s);
		db.insertSconto(s1);
		//db.updateSconto(s);
		//db.selectSconto(s);
		Abbonamento a = new Abbonamento(1,"Abbonamento 5 ingressi",30.00,"Tutti","18/09/2017");
		Abbonamento a1 = new Abbonamento(2,"Abbonamento 10 ingressi",55.00,"Tutti","18/09/2017");
		db.insertAbbonamento(a);
		db.insertAbbonamento(a1);
		
	}

}
