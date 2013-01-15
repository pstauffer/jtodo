package ch.zhaw.jtodo.controller;

import java.util.Observer;

import ch.zhaw.jtodo.domain.Task;

/**
 * 
 * MVC => Controller Interface für die Controller Klasse GUIController
 * 
 * @author pascal
 */
public interface IGUIController {

	public void addObserver(Observer view);

	public void getInitalData();

	public void getCategory(int catID);

	void addTask(Task task);

	void update(Task task);

	public void getPriority(int selectedID);

	// Implement methods used in gui here

}
