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

/*
 * Modified version of BattleFieldSetup, by Mariano Jan (https://github.com/marianojan/)
 * It's an awful copy and paste. Of course it can be done better. But I don't have a lot of
 * time to do it now.
 * 
 * To run this from your own WarriorManager, you should add the next code to a class in your
 * Project:
 * 	
 	public static void main(String[] args) {

		BattleFieldSetupHeadless ventana = BattleFieldSetupHeadless.main(args, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Here you can catch the end of the app, if you need to flush any log cache, or whatever
				//ventana.dispose();
			}
		});
		
		// here you make the references to the WarriorManager-s you want to use. 
		BattleField.getInstance().addWarriorManager(new CondensadorDeFlujo("-a"));
		BattleField.getInstance().addWarriorManager(new CondensadorDeFlujo("-b"));
		
		ventana.figthClicked();
	}
 * 
 */

package ia.battle.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ia.battle.core.BattleField;
import ia.battle.core.BattleFieldListener;
import ia.battle.core.FieldCell;
import ia.battle.core.Warrior;
import ia.battle.core.WarriorManager;
import ia.battle.gui.components.FightButton;
import ia.battle.sound.DefaultSoundPlayer;
import ia.battle.sound.SoundPlayer;

public class BattleFieldSetupHeadless extends JFrame {

	/**
     * 
     */
	private static final long serialVersionUID = 693518024717393345L;
	private JLabel title;
	private FightButton startFight;
	private ClassFinder finderWarriorManager1, finderWarriorManager2;

	private Frame frame;
	private boolean inFight;
	private SoundPlayer soundPlayer;

	private BattleFieldListener battleFieldListener;
	
	public BattleFieldSetupHeadless(final ActionListener onClose) {

		title = new JLabel("IA - Battle Field Agents", JLabel.CENTER);
		title.setBorder(BorderFactory.createEtchedBorder());

		this.add(title, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		finderWarriorManager1 = new ClassFinder("Seleccione el .jar del Warrior Manager 1");
		panel.add(finderWarriorManager1);

		finderWarriorManager2 = new ClassFinder("Seleccione el .jar del Warrior Manager 2");
		panel.add(finderWarriorManager2);

		this.add(panel);

		loadJarSelection();

		startFight = new FightButton(finderWarriorManager1, finderWarriorManager2);
		startFight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				figthClicked();
			}
		});
		startFight.updateEnabledStatus();

		soundPlayer = new DefaultSoundPlayer();
		
		//this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				onClose.actionPerformed(null);
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		battleFieldListener = new BattleFieldListener() {

			@Override
			public void startFight() {

				inFight = true;

				if (frame != null) {
					frame.dispose();
					frame = null;
				}

				frame = new Frame(BattleField.getInstance(), 16, 16);
				frame.setSize(1024, 720);
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);

				frame.addWindowListener(new WindowListener() {

					@Override
					public void windowActivated(WindowEvent arg0) {
					}

					@Override
					public void windowClosed(WindowEvent arg0) {
					}

					@Override
					public void windowClosing(WindowEvent arg0) {

						if (JOptionPane.showConfirmDialog(frame, "Está seguro de finalizar la batalla?", "Confirme Acción",
								JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
							inFight = false;
							frame.dispose();
							onClose.actionPerformed(null);
						}
					}

					@Override
					public void windowDeactivated(WindowEvent arg0) {
					}

					@Override
					public void windowDeiconified(WindowEvent arg0) {
					}

					@Override
					public void windowIconified(WindowEvent arg0) {
					}

					@Override
					public void windowOpened(WindowEvent arg0) {
					}

				});
			}

			public void tickLapsed(long tick) {

			}

			public void turnLapsed(long tick, int turnNumber, Warrior warrior) {
				frame.repaint();
			}

			public boolean continueFighting() {
				return inFight;
			}

			public void warriorAttacked(Warrior attacked, Warrior attacker, int damage) {
				// soundPlayer.playAttack();
			}

			@Override
			public void warriorKilled(Warrior killed) {
				// soundPlayer.playBotKilled();
			}

			@Override
			public void warriorMoved(Warrior warrior, FieldCell from, FieldCell to) {

			}

			@Override
			public void figthFinished(WarriorManager winner) {
				inFight = false;
				if (winner != null) {
					System.out.println("The winner is " + winner.getName());
					JOptionPane.showMessageDialog(null, "The winner is " + winner.getName());
				}
			}

			@Override
			public void worldChanged(FieldCell oldCell, FieldCell newCell) {
				// TODO Auto-generated method stub

			}

			@Override
			public void statsChanged(String managerName1, int warriorsKilled1, String managerName2, int warriorsKilled2) {

				frame.getFieldBoard().setWarriorManagerName1(managerName1);
				frame.getFieldBoard().setWarriorManagerName2(managerName2);
				frame.getFieldBoard().setWarriorsKilled1(warriorsKilled1);
				frame.getFieldBoard().setWarriorsKilled2(warriorsKilled2);

			}

		};
		
		this.add(startFight, BorderLayout.SOUTH);
	}

	public void figthClicked() {

		saveJarSelection();

		//WarriorLoader warriorLoader = new WarriorLoader();
		//WarriorManager wm;

		try {

//			wm = warriorLoader.createWarriorManager(finderWarriorManager1.getSelectedJarFile(), finderWarriorManager1.getSelectedClassName());

			//BattleField.getInstance().addWarriorManager(wm);

			//wm = warriorLoader.createWarriorManager(finderWarriorManager2.getSelectedJarFile(), finderWarriorManager2.getSelectedClassName());

			//BattleField.getInstance().addWarriorManager(wm);

			BattleField.getInstance().addListener(battleFieldListener);

			startFight.setEnabled(false);

			new Thread(new Runnable() {
				@Override
				public void run() {

					BattleField.getInstance().fight();
					BattleField.getInstance().showResult();

					startFight.setEnabled(true);
				}
			}).start();

		} finally {
			
		}
	}

	private void saveJarSelection() {

	}

	private void loadJarSelection() {

	}

	public static BattleFieldSetupHeadless main(String[] args, ActionListener onClose) {
		BattleFieldSetupHeadless dev = new BattleFieldSetupHeadless(onClose);
		SwingConsole.run(dev, 600, 400, true, "IA");
		return dev;
	}
}
