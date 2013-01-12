

import ch.zhaw.jtodo.controller.GUIController;
import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.model.DataHandler;
import ch.zhaw.jtodo.view.JtodoView;

public class JtodoRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// just for test purposes, only test
		// new CRUDService().testCrudService();
		DataHandler model = new DataHandler(new DAOFactory());
		JtodoView view = new JtodoView(model);
		
		
		
	}
}