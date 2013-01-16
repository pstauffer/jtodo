package ch.zhaw.jtodo.domain;

import java.util.Date;

/**
 * 
 * Klasse für das Task Object
 * 
 * @author yannik
 */
public class Task implements java.io.Serializable {

	private int id;
	private String name;
	private String description;
	private Integer categoryid;
	private Integer priorityid;
	private Date date;
	private Integer status;
	private Date modifiydate;

	/**
	 * default constructor für das task object
	 */
	public Task() {
	}

	/**
	 * kompletter constructor für das task object mit
	 * name,description,categoryid,priorityid,date,status und modifydate als
	 * parameter
	 */
	public Task(String name, String description, Integer categoryid,
			Integer priorityid, Date date, Integer status, Date modifiydate) {
		this.name = name;
		this.description = description;
		this.categoryid = categoryid;
		this.priorityid = priorityid;
		this.date = date;
		this.status = status;
		this.modifiydate = modifiydate;
	}

	/**
	 * gibt mir die id des task object zurück
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * setzt mir die id des task object
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * gibt mir den namen des task object zurück
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setzt mir den namen des task object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gibt mir die description des task object zurück
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * setzt mir die description des task object
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * gibt mir die categoryid des task object zurück
	 */
	public Integer getCategoryid() {
		return this.categoryid;
	}

	/**
	 * setzt mir die categoryid des task object
	 */
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	/**
	 * gibt mir die priority des task object zurück
	 */
	public Integer getPriorityid() {
		return this.priorityid;
	}

	/**
	 * setzt mir die priorityid des task object
	 */
	public void setPriorityid(Integer priorityid) {
		this.priorityid = priorityid;
	}

	/**
	 * gibt mir das datum des task object zurück
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * setzt mir dad datum des task object
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * gibt mir den status des task object zurück
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * setzt mir den status des task object
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * gibt mir das modifydatum des task object zurück
	 */
	public Date getModifiydate() {
		return this.modifiydate;
	}

	/**
	 * setzt mir das modifydate des task object
	 */
	public void setModifiydate(Date modifiydate) {
		this.modifiydate = modifiydate;
	}

	/**
	 * überschreibt die toString Methode, gibt den task namen zurück
	 */
	@Override
	public String toString() {
		return name;
	}
}
