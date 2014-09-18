package ia.battle.gui.components;

import ia.battle.gui.ClassFinder;
import ia.battle.gui.ClassFinder.ClassFinderObserver;

import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

public class FightButton extends JButton implements ClassFinderObserver {

	private static final long serialVersionUID = 4015918365089316502L;
	private List<ClassFinder> classFinders;

	public FightButton(ClassFinder... classFinders) {
		super("Fight!");
		this.classFinders = Arrays.asList(classFinders);
		for (ClassFinder classFinder: classFinders)
			classFinder.addSelectionChangeObserver(this);
		setEnabled(false);
	}

	public void updateEnabledStatus() {
		Boolean newStatus = true;
		for (ClassFinder classFinder : classFinders) {
			if (!(classFinder.selectedJarExists() && classFinder
					.hasSelectedClass()))
				newStatus = false;
		}
		setEnabled(newStatus);
	}

	@Override
	public void fileSelectionChange(String filename) {
		updateEnabledStatus();
	}

	@Override
	public void classSelectionChange(String classname) {
		updateEnabledStatus();
	}

}
