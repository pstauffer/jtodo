package ch.zhaw.jtodo.domain;

/**
 * 
 * @author yk
 *
 */
public class Category implements java.io.Serializable {

	private int id;
	private String name;

	/** 
	 * default constructor 
	 **/
	public Category() {
	}

	/** minimal constructor */
	public Category(int id) {
		this.id = id;
	}

	/** full constructor */
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

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
