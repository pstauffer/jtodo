package ch.zhaw.jtodo.dao;

public interface IDAOFactory {
	ICategoryDAO getCategoryDAO();
	IPriorityDAO getPriorityDAO();
	ITaskDAO getTaskDAO();
	IReminderDAO getReminderDAO();
}
