package ch.zhaw.jtodo.controller;

import java.util.Date;
import java.util.Observer;

public interface IGUIController {

	public void addObserver(Observer view);

	public void getInitalData();

	void addTaskButtonAction(String taskName, String taskDescription,
			int catID, Date date);

	// Implement methods used in gui here

}
