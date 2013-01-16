package ch.zhaw.jtodo.domain;

import java.util.Date;

/**
 * 
 * Klasse fŸr das Reminder Object
 * 
 * @author yannik
 */
public class Reminder implements java.io.Serializable {

	private int id;
	private Date date;
	private Integer taskid;

	/**
	 * default constructor fŸr das reminder object
	 */
	public Reminder() {
	}

	/**
	 * miniamler constructor fŸr das reminder object mit id als parameter
	 */
	public Reminder(int id) {
		this.id = id;
	}

	/**
	 * kompletter constructor fŸr das reminder object mit id, datum und taskid
	 * als parameter
	 */
	public Reminder(int id, Date date, Integer taskid) {
		this.id = id;
		this.date = date;
		this.taskid = taskid;
	}

	/**
	 * gibt mir die id des reminder object zurŸck
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * setzt mir die id des reminder object
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * gibt mir das datum des reminder object zurŸck
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * setzt mir das datum des reminder object
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * gibt mir die taskid des reminder object zurŸck
	 */
	public Integer getTaskid() {
		return this.taskid;
	}

	/**
	 * setzt mir die taskid des reminder object
	 */
	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

}
