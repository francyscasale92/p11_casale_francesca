
// TODO: Auto-generated Javadoc
/**
 * La classe Abbonamento.
 */
public class Abbonamento {
	
	/** identificativo */
	private int id;
	
	/** nome */
	private String nome;
	
	/** prezzo */
	private double prezzo;
	
	/** beneficiari */
	private String beneficiari;
	
	/** scadenza */
	private String scadenza;
	
	/**
	 * Costruttore che istanzia un nuovo abbonamento.
	 *
	 * @param id
	 * @param nome
	 * @param prezzo
	 * @param beneficiari
	 * @param scadenza
	 */
	public Abbonamento(int id, String nome, double prezzo, String beneficiari, String scadenza) {
		super();
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.beneficiari = beneficiari;
		this.scadenza = scadenza;
	}

	/**
	 * Ottiene l'identificativo.
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
	 * Ottine il nome.
	 *
	 * @return il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Imposta il nome.
	 *
	 * @param nome il nuovo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Ottiene il prezzo.
	 *
	 * @return il prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Imposta il prezzo.
	 *
	 * @param prezzo il nuovo prezzo
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Ottiene i beneficiari.
	 *
	 * @return i beneficiari
	 */
	public String getBeneficiari() {
		return beneficiari;
	}

	/**
	 * Imposta i beneficiari.
	 *
	 * @param beneficiari i nuovi beneficiari
	 */
	public void setBeneficiari(String beneficiari) {
		this.beneficiari = beneficiari;
	}

	/**
	 * Ottiene la scadenza.
	 *
	 * @return la scadenza
	 */
	public String getScadenza() {
		return scadenza;
	}

	/**
	 * Imposta la scadenza.
	 *
	 * @param scadenza la nuova scadenza
	 */
	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
	
}
