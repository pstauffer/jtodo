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

	/**
	 * Fügt eine view dem Subscriber zu um von Änderungen notifizert zu werden
	 * @param view
	 */
	public void addObserver(Observer view);
	
	/**
	 * Holt alle inital Daten für den Application Startup
	 */
	public void getInitalData();
	
	/**
	 * Schickt den Request ans model für eine bestimmte Kategorie
	 * @param catID gesuchte Kategorie
	 */
	public void getCategory(int catID);
	
	/**
	 * Weisst das model an einen neuen Task ins Datenmodel hinzuzufügen
	 * @param task Neuer Task
	 */
	void addTask(Task task);
	
	/**
	 * Aktualisiert einen bestehenden Task
	 * @param task zu aktualisierender Task
	 */
	void update(Task task);
	
	/**
	 * Schickt den Request an das model für eine bestimmte Priorität
	 * @param selectedID gesuchte Priorität
	 */
	public void getPriority(int selectedID);

}
