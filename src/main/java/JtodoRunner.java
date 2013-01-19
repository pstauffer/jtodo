
import ch.zhaw.jtodo.dao.DAOFactory;
import ch.zhaw.jtodo.model.DataHandler;
import ch.zhaw.jtodo.view.JtodoView;

/**
 * 
 * Runner MainClass for JToDo
 * 
 * @author pascal stauffer
 */
public class JtodoRunner {

	/**
	 * mainClass
	 */
	public static void main(String[] args) {
		
		DataHandler model = new DataHandler(new DAOFactory());
		JtodoView view = new JtodoView(model);

	}
}
