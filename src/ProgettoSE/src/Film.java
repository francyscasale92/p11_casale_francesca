import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * La classe Film.
 */
public class Film {
	
	/** Identificativo */
	private int id;
	
	/** Titolo */
	private String titolo;
	
	/** Anno di pubblicazione */
	private int annoPubblicazione;
	
	/** Regista */
	private String regista;
	
	/** Lista degli attori */
	private String listaAttori;
	
	/** Genere */
	private String genere;
	
	/** Trama */
	private String trama;
	
	/** Durata */
	private int durata;
	
	/** ArrayList di programmazioni 
	 * Associazione alla classe Film
	 * 
	 */
	public ArrayList<Programmazione> p = new ArrayList<Programmazione>();
	
	
	/**
	 *  Costruttore che istanzia un nuovo film
	 *
	 * @param id 
	 * @param titolo
	 * @param annoPubblicazione 
	 * @param regista 
	 * @param listaAttori 
	 * @param genere
	 * @param trama
	 * @param durata
	 */
	public Film(int id, String titolo, int annoPubblicazione, String regista, String listaAttori,
			    String genere, String trama, int durata) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.regista = regista;
		this.listaAttori = listaAttori;
		this.genere = genere;
		this.trama = trama;
		this.durata = durata;
	}

	// Getters e Setters
	/**
	 * Ottiene l'dentificativo.
	 *
	 * @return l'identificativo
	 */
	public int getId() {
		return id;
	}

	/**
	 * Imposta l'identificativo.
	 *
	 * @param id il nuovo identificativo
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Ottiene il titolo
	 *
	 * @return il titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Imposta il titolo.
	 *
	 * @param titolo il nuovo titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Ottiene l'anno di pubblicazione.
	 *
	 * @return l'anno di pubblicazione
	 */
	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	/**
	 * Imposta l'anno di pubblicazione.
	 *
	 * @param annoPubblicazione il nuovo anno di pubblicazione
	 */
	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	/**
	 * Ottiene il regista.
	 *
	 * @return il regista
	 */
	public String getRegista() {
		return regista;
	}

	/**
	 * Imposta il regista.
	 *
	 * @param regista il nuovo regista
	 */
	public void setRegista(String regista) {
		this.regista = regista;
	}

	/**
	 * Imposta la lista di attori.
	 *
	 * @return la lista di attori
	 */
	public String getListaAttori() {
		return listaAttori;
	}

	/**
	 * Imposta la lista di attori.
	 *
	 * @param listaAttori la nuova lista di attori
	 */
	public void setListaAttori(String listaAttori) {
		this.listaAttori = listaAttori;
	}

	/**
	 * Ottiene il genere.
	 *
	 * @return il genere
	 */
	public String getGenere() {
		return genere;
	}

	/**
	 * Imposta il genere.
	 *
	 * @param genere il nuovo genere
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}

	/**
	 * Ottieme la trama.
	 *
	 * @return la trama
	 */
	public String getTrama() {
		return trama;
	}

	/**
	 * Imposta la trama.
	 *
	 * @param trama la nuova trama
	 */
	public void setTrama(String trama) {
		this.trama = trama;
	}

	/**
	 * Ottiene la durata.
	 *
	 * @return la durata
	 */
	public int getDurata() {
		return durata;
	}

	/**
	 * Imposta la durata.
	 *
	 * @param durata la nuova durata
	 */
	public void setDurata(int durata) {
		this.durata = durata;
	}

}
