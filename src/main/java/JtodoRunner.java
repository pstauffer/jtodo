import ch.zhaw.jtodo.control.JtodoControl;
import ch.zhaw.jtodo.dao.CRUDService;

public class JtodoRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CRUDService().testCrudService();
		new JtodoControl();
	}

}
