package ch.zhaw.jtodo.domain;

import java.util.Date;


/**
 * 
 * @author yk
 *
 */
public class Reminder  implements java.io.Serializable {


    // Fields    

     private int id;
     private Date date;
     private Integer taskid;


    // Constructors

    /** default constructor */
    public Reminder() {
    }

	/** minimal constructor */
    public Reminder(int id) {
        this.id = id;
    }
    
    /** full constructor */
    public Reminder(int id, Date date, Integer taskid) {
        this.id = id;
        this.date = date;
        this.taskid = taskid;
    }
    

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTaskid() {
        return this.taskid;
    }
    
    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }
   








}
