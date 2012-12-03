package ch.zhaw.jtodo.model;

import java.util.List;

import ch.zhaw.jtodo.dao.IDAOFactory;
import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Task;

public class DataHandler implements IDataHandler {
	
	private IDAOFactory daoFactory;
	
	public DataHandler(IDAOFactory factory){
		this.daoFactory = factory;
	}
	
	@Override
	public void createTask() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Task> getAllTasks() {
		return daoFactory.getTaskDAO().findAll();
	}
	
	public List<Category> getAllCategorys(){
		return daoFactory.getCategoryDAO().findAll();
	}
	
	@Override
	public Task getSpecificTask() {
		// TODO Auto-generated method stub
		return null;
	}

}
