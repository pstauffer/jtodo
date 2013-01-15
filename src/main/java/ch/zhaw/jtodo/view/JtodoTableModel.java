package ch.zhaw.jtodo.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import ch.zhaw.jtodo.domain.Category;
import ch.zhaw.jtodo.domain.Priority;
import ch.zhaw.jtodo.domain.Task;

/**
 * 
 * Klasse für das eigene TableModel, erweitert das AbsstractTableModel
 * 
 * @author yannik
 */
public class JtodoTableModel extends AbstractTableModel {

	private String[] columnNames = { "Task", "Description", "Category",
			"Priority", "Date", "Status" };

	private ArrayList dataList = new ArrayList();
	private Map categoryList = new HashMap<Integer, String>();
	private Map priorityList = new HashMap<Integer, String>();

	public JtodoTableModel() {

	}

	/**
	 * gibt die Anzahl der Spalten zurück
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * gibt die Anzahl der Zeilen zurück
	 */
	@Override
	public int getRowCount() {
		return dataList.size();
	}

	/**
	 * gibt den Spaltennamen einer Spalte zurück
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/**
	 * gibt die Klasse einer Spalte zurück (task,priority,category)
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/**
	 * gibt das Object der Tabelle anhand der Spalte und Zeile zurück
	 */
	@Override
	public Object getValueAt(int row, int col) {
		Task task = (Task) dataList.get(row);

		switch (col) {
		case 0:
			return task.getName();
		case 1:
			return task.getDescription();
		case 2:
			String category = " ";
			try {
				category = (String) this.categoryList.get(task.getCategoryid());
			} catch (Exception e) {
				category = " ";
			}
			return category;
		case 3:
			String priority = " ";
			try {
				priority = (String) this.priorityList.get(task.getPriorityid());
			} catch (Exception e) {
				priority = " ";
			}
			return priority;
		case 4:
			return task.getDate().toString();
		case 5:
			if (task.getStatus() == 1) {
				return new Boolean(true);
			} else if (task.getStatus() == 0) {
				return new Boolean(false);
			}
		default:
			return null;
		}
	}

	public Task getWidgetAt(int row) {
		return (Task) dataList.get(row);
	}

	/**
	 * fügt einen neuen Task in die Taskliste ein
	 */
	public void addTask(Task task) {
		dataList.add(task);
		fireTableDataChanged();
	}

	/**
	 * fügt die Taskliste in die Tabelle ein
	 */
	public void addTaskList(List l) {
		dataList.addAll(l);
		fireTableDataChanged();
	}

	/**
	 * löscht einen Task aus der Tabellle
	 */
	public Task removeTaskAt(int row) {
		Task task = (Task) dataList.remove(row);
		fireTableDataChanged();
		return task;
	}

	/**
	 * löscht alle Task aus der Tabellle
	 */
	public void removeAllTasks() {
		this.dataList.clear();
	}

	/**
	 * setzt die categoryList
	 */
	public void setCategoryList(List<Category> categorys) {
		this.categoryList.clear();
		for (Category cat : categorys) {
			this.categoryList.put(cat.getId(), cat.getName());
		}
	}

	/**
	 * setzt die priorityList
	 */
	public void setPriorityList(List<Priority> prioritys) {
		this.priorityList.clear();
		for (Priority prio : prioritys) {
			this.priorityList.put(prio.getId(), prio.getName());
		}
	}

	/**
	 * setzt ein Object an eine Zeile und Spalte in der Tabelle
	 */
	public void setValueAt(Object object, int row, int col) {
		if (col == 5) {
			Task task = (Task) this.dataList.get(row);
			Boolean bool = (Boolean) object;
			if (bool) {
				task.setStatus(1);
			} else {
				task.setStatus(0);
			}
			fireTableCellUpdated(row, col);
		}
		if (col == 0) {
			Task task = (Task) this.dataList.get(row);
			String newTaskName = (String) object;
			task.setName(newTaskName);
			fireTableCellUpdated(row, col);
		}
		if (col == 1) {
			Task task = (Task) this.dataList.get(row);
			String newTaskDescription = (String) object;
			task.setDescription(newTaskDescription);
			fireTableCellUpdated(row, col);
		}
	}

	/**
	 * gibt mir das Task-Object einer Zeile zurück
	 */
	public Task getValueAtRow(int row) {
		return (Task) this.dataList.get(row);
	}

	/**
	 * definiert, welche Spalten editierbar sind
	 */
	public boolean isCellEditable(int row, int col) {
		// only the name(0),description(1),status(5) column must be editable
		if (col == 0 || col == 1 || col == 5) {
			return true;
		} else {
			return false;
		}
	}

}
