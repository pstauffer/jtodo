package ch.zhaw.jtodo.controller;

import java.util.Observer;

public interface IGUIController {

	public void addObserver(Observer view);

	public void getInitalData();

	public void addTaskButtonAction();

	// Implement methods used in gui here

}
