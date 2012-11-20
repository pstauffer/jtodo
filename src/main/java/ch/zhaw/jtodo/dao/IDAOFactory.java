package ch.zhaw.jtodo.dao;

/**
 * Interface zur Bereitstellung der einzelnen DAO objekte
 * @author yannik
 *
 */
public interface IDAOFactory {
	
	/**
	 * Gibt das Interface zu einem generischen CategoryDAO zurück
	 * @return ICategoryDAO
	 */
	ICategoryDAO getCategoryDAO();
	
	/**
	 * Gibt das Interface zu einem generischen PriorityDAO zurück
	 * @return IPriorityDAO
	 */
	IPriorityDAO getPriorityDAO();
	
	/**
	 * Gibt das Interface zu einem generischen TaskDAO zurück
	 * @return ITaskDAO
	 */
	ITaskDAO getTaskDAO();
	
	/**
	 * Gibt das Interface zu einem generischen ReminderDAO zurück
	 * @return IReminderDAO
	 */
	IReminderDAO getReminderDAO();
}
