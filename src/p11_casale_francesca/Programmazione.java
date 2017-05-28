package p11_casale_francesca;

// TODO: Auto-generated Javadoc
/**
 * La classe Programmazione.
 */
public class Programmazione {

	/**  Identificativo  */
	private int id;
	
	/** Data */
	private String data;
	
	/** Sala */
	private int sala;
	
	/**
	 *  Costruttore che istanzia una nuova programmazione
	 *
	 * @param id
	 * @param data
	 * @param sala 
	 */
	public Programmazione(int id, String data, int sala) {
		super();
		this.id = id;
		this.data = data;
		this.sala = sala;
	}

	/**
	 * Ottiene l'identificativo
	 *
	 * @return l'identificativo
	 */
	public int getId() {
		return id;
	}

	/**
	 * Imposta l'identificativo
	 *
	 * @param id il nuovo identificativo
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Ottiene la data.
	 *
	 * @return la data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Imposta la data.
	 *
	 * @param data la nuova data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Ottiene la sala.
	 *
	 * @return la sala
	 */
	public int getSala() {
		return sala;
	}

	/**
	 * Imposta la sala.
	 *
	 * @param sala la nuova sala
	 */
	public void setSala(int sala) {
		this.sala = sala;
	}
	
}
