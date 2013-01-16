package ch.zhaw.jtodo.controller;

import java.util.Observer;

import ch.zhaw.jtodo.domain.Task;

/**
 * 
 * MVC => Controller Interface f�r die Controller Klasse GUIController
 * 
 * @author pascal
 */
public interface IGUIController {

	/**
	 * F�gt eine view dem Subscriber zu um von �nderungen notifizert zu werden
	 * @param view
	 */
	public void addObserver(Observer view);
	
	/**
	 * Holt alle inital Daten f�r den Application Startup
	 */
	public void getInitalData();
	
	/**
	 * Schickt den Request ans model f�r eine bestimmte Kategorie
	 * @param catID gesuchte Kategorie
	 */
	public void getCategory(int catID);
	
	/**
	 * Weisst das model an einen neuen Task ins Datenmodel hinzuzuf�gen
	 * @param task Neuer Task
	 */
	void addTask(Task task);
	
	/**
	 * Aktualisiert einen bestehenden Task
	 * @param task zu aktualisierender Task
	 */
	void update(Task task);
	
	/**
	 * Schickt den Request an das model f�r eine bestimmte Priorit�t
	 * @param selectedID gesuchte Priorit�t
	 */
	public void getPriority(int selectedID);

}
