package ch.zhaw.jtodo.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestGui extends JFrame {
	private JTextField textField;

	public TestGui() {
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		JCheckBox chckbxTask = new JCheckBox("Task 1");
		chckbxTask.setBounds(25, 90, 74, 23);
		panel.add(chckbxTask);

		JCheckBox chckbxTask_1 = new JCheckBox("Task 2");
		chckbxTask_1.setBounds(25, 120, 74, 23);
		panel.add(chckbxTask_1);

		textField = new JTextField();
		textField.setBounds(25, 21, 189, 28);
		panel.add(textField);
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(226, 23, 74, 27);
		panel.add(comboBox);

		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.setBounds(300, 22, 117, 29);
		panel.add(btnAddTask);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewTask = new JMenuItem("New Task");
		mnNewMenu.add(mntmNewTask);

		JMenuItem mntmClose = new JMenuItem("Close");
		mnNewMenu.add(mntmClose);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mnAbout.add(mntmHelp);

		JMenuItem mntmVersion = new JMenuItem("Version");
		mnAbout.add(mntmVersion);
	}
}
