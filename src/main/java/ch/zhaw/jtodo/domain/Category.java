package ch.zhaw.jtodo.domain;

/**
 * 
 * Klasse für das Category Object
 * 
 * @author yannik
 */
public class Category implements java.io.Serializable {

	private int id;
	private String name;

	/**
	 * default constructor für das category object
	 */
	public Category() {
	}

	/**
	 * miniamler constructor für das category object mit id als parameter
	 */
	public Category(int id) {
		this.id = id;
	}

	/**
	 * kompletter constructor für das category object mit id und name als
	 * parameter
	 */
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * gibt mir die id des category object zurück
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * setzt mir die id des category object
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * gibt mir den namen des category object zurück
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setzt mir den namen des category object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * überschreibt die toString Methode, gibt den category namen zurück
	 */
	@Override
	public String toString() {
		return name;
	}

}
