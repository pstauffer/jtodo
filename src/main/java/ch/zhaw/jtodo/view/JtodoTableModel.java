package ch.zhaw.jtodo.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ch.zhaw.jtodo.domain.Task;

public class JtodoTableModel extends AbstractTableModel {

	private String[] columnNames = { "Task", "Description", "Category",
			"Priority", "Date", "Status" };

	private ArrayList datalist = new ArrayList();

	public JtodoTableModel() {

	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return datalist.size();
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Task task = (Task) datalist.get(row);
		
		   switch (col) {
		    case 0:
		     return task.getName();
		    case 1:
		     return task.getDescription();
		    case 2:
		     return String.valueOf(task.getCategoryid());
		    case 3:
		     return String.valueOf(task.getPriorityid());
		    case 4:
			     return String.valueOf(task.getStatus());
		    case 5:
		    	 return task.getDate().toString();
		    default:
		     return null;
		   }
	}

	public Task getWidgetAt(int row) {
		return (Task) datalist.get(row);
	}

	public void addTask(Task task) {
		datalist.add(task);
		fireTableDataChanged();
	}

	public void addTaskList(List l) {
		datalist.addAll(l);
		fireTableDataChanged();
	}
	
	public Task removeTaskAt(int row) {
		   Task task = (Task)datalist.remove(row);
		   fireTableDataChanged();
		   return task;
	}
	
	public void removeAllTasks(){
		this.datalist.clear();
	}

}
