package ch.zhaw.jtodo.domain;

/**
 * 
 * @author yk
 *
 */
public class Priority implements java.io.Serializable {

	// Fields

	private int id;
	private String name;

	// Constructors

	/** default constructor */
	public Priority() {
	}

	/** minimal constructor */
	public Priority(int id) {
		this.id = id;
	}

	/** full constructor */
	public Priority(int id, String name) {
		this.id = id;
		this.name = name;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
