/*
 * Copyright (c) 2012-2014, Ing. Gabriel Barrera <gmbarrera@gmail.com>
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above 
 * copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES 
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR 
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES 
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN 
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF 
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package ia.battle.gui;

import ia.battle.camp.WarriorLoader;
import ia.battle.camp.WarriorManager;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ClassFinder extends JPanel {

	/**
     * 
     */
    private static final long serialVersionUID = 7859582262519030245L;
    private JFileChooser chooser = new JFileChooser();
	private JTextField url;
	private JComboBox<String> comboBox;

	public ClassFinder(String title) {

		FileNameExtensionFilter filter = new FileNameExtensionFilter(".jar", "jar");
		chooser.setFileFilter(filter);

		this.add(new JLabel(title));

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createDashedBorder(getForeground()));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		url = new JTextField(40);
		panel2.add(url);

		JButton chooseUrl = new JButton("...");
		chooseUrl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					url.setText(chooser.getSelectedFile().getAbsolutePath());
					fillComboBox();
				}

			}
		});
		panel2.add(chooseUrl);

		panel.add(panel2);

		comboBox = new JComboBox<>();
		panel.add(comboBox);

		this.add(panel);
	}

	public URL getSelectedJarFile() {
		try {
			return new URL("file:///" + url.getText());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getSelectedClassName() {
		return comboBox.getSelectedItem().toString();
	}
	
	public void fillComboBox() {

		comboBox.removeAllItems();

		try {
			
			ArrayList<String> classes = (new WarriorLoader()).getAllClasses(url.getText(), WarriorManager.class.getName());
			for(String warriorManagerClassName : classes)
				comboBox.addItem(warriorManagerClassName);	

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void getSelectedJarFile(String url1) {
		url.setText(url1.substring(6));
		fillComboBox();
	}

	public void getSelectedClassName(String class1) {
		comboBox.setSelectedItem(class1);
	}
}
