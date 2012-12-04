

import ch.zhaw.jtodo.controller.GUIController;

public class JtodoRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// new CRUDService().testCrudService();
		new GUIController();

		// DataHandler handler = new DataHandler(new DAOFactory(null));
		// List<Task> blubb = handler.getAllTasks();
		// System.out.println(blubb.get(1).getName());
		// System.out.println(blubb.get(0).getName());
		// System.out.println(blubb.get(1).getDescription());

	}
}