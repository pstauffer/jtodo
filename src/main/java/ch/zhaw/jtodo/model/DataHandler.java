package ch.zhaw.jtodo.model;

import java.util.List;

import ch.zhaw.jtodo.dao.IDAOFactory;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;

public class DataHandler implements IDataHandler {

	private IDAOFactory daoFactory;

	public DataHandler(IDAOFactory factory) {
		this.daoFactory = factory;
	}

	@Override
	public void createTask(Task task) {
		try {
			daoFactory.getTaskDAO().write(task);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Task> getAllTasks() {
		return daoFactory.getTaskDAO().findAll();
	}

	public List<Category> getAllCategorys() {
		return daoFactory.getCategoryDAO().findAll();
	}

	@Override
	public Task getSpecificTask() {
		// TODO Auto-generated method stub
		return null;
	}

}
