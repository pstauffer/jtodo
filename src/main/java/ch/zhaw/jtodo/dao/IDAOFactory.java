package ch.zhaw.jtodo.dao;

/**
 * Interface zur Bereitstellung der einzelnen DAO objekte
 * @author yannik
 *
 */
public interface IDAOFactory {
	
	/**
	 * Gibt das Interface zu einem generischen CategoryDAO zur�ck
	 * @return ICategoryDAO
	 */
	ICategoryDAO getCategoryDAO();
	
	/**
	 * Gibt das Interface zu einem generischen PriorityDAO zur�ck
	 * @return IPriorityDAO
	 */
	IPriorityDAO getPriorityDAO();
	
	/**
	 * Gibt das Interface zu einem generischen TaskDAO zur�ck
	 * @return ITaskDAO
	 */
	ITaskDAO getTaskDAO();
	
	/**
	 * Gibt das Interface zu einem generischen ReminderDAO zur�ck
	 * @return IReminderDAO
	 */
	IReminderDAO getReminderDAO();
}
