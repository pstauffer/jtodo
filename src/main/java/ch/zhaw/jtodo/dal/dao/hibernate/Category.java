package ch.zhaw.jtodo.dal.dao.hibernate;
// default package
// Generated Nov 13, 2012 5:11:22 PM by Hibernate Tools 3.1.0.beta4



/**
 * Category generated by hbm2java
 */

public class Category  implements java.io.Serializable {


    // Fields    

     private int id;
     private String name;


    // Constructors

    /** default constructor */
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
   








}