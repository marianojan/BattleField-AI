/*
 * Copyright (c) 2012-2013, Ing. Gabriel Barrera <gmbarrera@gmail.com>
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

import ia.battle.camp.BattleField;
import ia.battle.camp.BattleFieldListener;
import ia.battle.camp.WarriorLoader;
import ia.battle.camp.WarriorManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BattleFieldSetup extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 693518024717393345L;
    private JLabel title;
    private JButton startFight;
    private ClassFinder finderWarriorManager1, finderWarriorManager2;
    
    private Frame frame;
    private boolean inFight;

    //TODO: Persistir los jar seleccionados y las clases

    public BattleFieldSetup() {

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

        startFight = new JButton("Fight!");
        startFight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                figthClicked();
            }
        });

        this.add(startFight, BorderLayout.SOUTH);
    }

    private void figthClicked() {

        WarriorLoader warriorLoader = new WarriorLoader();
        WarriorManager wm;
        
        try {

            wm = warriorLoader.createWarriorManager(finderWarriorManager1.getSelectedJarFile(),
                    finderWarriorManager1.getSelectedClassName());
            
            BattleField.getInstance().addWarriorManager(wm);

            wm = warriorLoader.createWarriorManager(finderWarriorManager2.getSelectedJarFile(),
                    finderWarriorManager2.getSelectedClassName());

            BattleField.getInstance().addWarriorManager(wm);

            BattleField.getInstance().addListener(new BattleFieldListener() {

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

                            if (JOptionPane.showConfirmDialog(frame, "Está seguro de finalizar la batalla?",
                                    "Confirme Acción", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                                inFight = false;
                                frame.dispose();
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

                public void turnLapsed(long tick, int turnNumber) {
                    frame.repaint();
                }

                public boolean continueFighting() {
                    return inFight;
                }
                
                public void warriorAttacked() {
                    
                }
            });

            startFight.setEnabled(false);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    BattleField.getInstance().fight();
                    BattleField.getInstance().showResult();

                    startFight.setEnabled(true);
                }
            }).start();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingConsole.run(new BattleFieldSetup(), 600, 400, true, "IA");
    }
}
