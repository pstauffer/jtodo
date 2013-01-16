package ch.zhaw.jtodo.domain;

import java.util.Date;

/**
 * 
 * @author yk
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

	/** default constructor */
	public Task() {
	}

	/** full constructor */
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public Integer getPriorityid() {
		return this.priorityid;
	}

	public void setPriorityid(Integer priorityid) {
		this.priorityid = priorityid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getModifiydate() {
		return this.modifiydate;
	}

	public void setModifiydate(Date modifiydate) {
		this.modifiydate = modifiydate;
	}

	@Override
	public String toString() {
		return name;
	}
}
