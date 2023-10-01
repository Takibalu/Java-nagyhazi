package run;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import options.Options;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/**
 * This class represents the MenuBar
 */
public class TopMenu {
	
	private JMenuBar menubar;
	private Runner runner;
	/**
	 * Constructor, watches the actions, when we press an item in a menubar
	 * @param runner - the running game
	 */
	public TopMenu(Runner runner)
	{
		this.runner= runner;
		menubar=new JMenuBar();
		menubar.setBackground(Color.LIGHT_GRAY);
		JMenu menu=new JMenu("Menu");
		JMenuItem menuI=new JMenuItem("Menu");
		menuI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Options.option=Options.MENU;
			}
		});
		JMenuItem save=new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
			/**
			 * File chooser for save
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				File selectedFile=new File("easy4");
				System.out.println(selectedFile.getParent());
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getParentDirectory(selectedFile));
				int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
				}
				
				runner.getEditor().save(selectedFile.getName());
				
			}
		});
		
		menu.add(menuI);
		menu.add(save);
		JMenuItem exit=new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menubar.add(menu);
		menubar.add(exit);
	}
	public JMenuBar getMenuBar() { return menubar;}
}
