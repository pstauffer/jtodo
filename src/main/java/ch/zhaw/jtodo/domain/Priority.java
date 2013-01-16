package ch.zhaw.jtodo.domain;

/**
 * 
 * Klasse für das Priority Object
 * 
 * @author yannik
 */
public class Priority implements java.io.Serializable {

	private int id;
	private String name;

	/**
	 * default constructor für das priority object
	 */
	public Priority() {
	}

	/**
	 * miniamler constructor für das priority object mit id als parameter
	 */
	public Priority(int id) {
		this.id = id;
	}

	/**
	 * kompletter constructor für das priority object mit id und name als
	 * parameter
	 */
	public Priority(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * gibt mir die id des priority object zurück
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * setzt mir die id des priority object
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * gibt mir den namen des priority object zurück
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setzt mir den namen des priority object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * überschreibt die toString Methode, gibt den priority namen zurück
	 */
	@Override
	public String toString() {
		return name;
	}

}
