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
import ia.battle.camp.ConfigurationManager;
import ia.battle.camp.FieldCellType;
import ia.exceptions.OutOfMapException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FieldBoard extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -1351031450255485371L;
    private static final Image grass = new ImageIcon(FieldBoard.class.getResource("grass.jpg")).getImage();
    private static final Image rocks = new ImageIcon(FieldBoard.class.getResource("wall.png")).getImage();
    private static final Image warrior1 = new ImageIcon(FieldBoard.class.getResource("warrior1.png")).getImage();
    private static final Image warrior2 = new ImageIcon(FieldBoard.class.getResource("warrior2.png")).getImage();
    private static final Image fogOfWar = new ImageIcon(FieldBoard.class.getResource("fogOfWar.png")).getImage();
    private static final Image HPorange = new ImageIcon(FieldBoard.class.getResource("HPorange.png")).getImage();
    private static final Image HPviolet = new ImageIcon(FieldBoard.class.getResource("HPviolet.png")).getImage();
    private static final Image HPempty = new ImageIcon(FieldBoard.class.getResource("HPempty.png")).getImage();
    private static final Image box = new ImageIcon(FieldBoard.class.getResource("box.png")).getImage();

    private static final Integer cellWidth = 16;
    private static final Integer cellHeight = 16;

    private BattleField battleField;
    private int offset_x, offset_y;

    public FieldBoard(BattleField battleField, int offset_x, int offset_y) {

        this.battleField = battleField;
        this.offset_x = offset_x;
        this.offset_y = offset_y;

    }

    public void paint(Graphics g) {
        int i, j;
        super.paint(g);

        int width = ConfigurationManager.getInstance().getMapWidth();
        int height = ConfigurationManager.getInstance().getMapHeight();

        int vision1 = 1;
        int vision2 = 1;

        int health1 = 100;
        int actualHealth1 = 100;

        int health2 = 100;
        int actualHealth2 = 100;

        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

        String nombreW1 = "1";
        String nombreW2 = "2";

        try {

            x1 = battleField.getWarriors().get(0).getPosition().getX();
            y1 = battleField.getWarriors().get(0).getPosition().getY();

            x2 = battleField.getWarriors().get(1).getPosition().getX();
            y2 = battleField.getWarriors().get(1).getPosition().getY();

            nombreW1 = battleField.getWarriors().get(0).getName();
            nombreW2 = battleField.getWarriors().get(1).getName();

            vision1 = battleField.getWarriors().get(0).getRange();
            vision2 = battleField.getWarriors().get(1).getRange();

            health1 = battleField.getWarriors().get(0).getInitialHealth();
            health2 = battleField.getWarriors().get(1).getInitialHealth();

            actualHealth1 = battleField.getWarriors().get(0).getHealth();
            actualHealth2 = battleField.getWarriors().get(1).getHealth();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Font ft = new Font("Times New Roman", Font.PLAIN, 12);
        g.setColor(Color.RED);
        g.setFont(ft);

        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {

                g.drawImage(grass, (i) * cellWidth + offset_x, (j) * cellHeight + offset_y, cellWidth, cellHeight, this);

                try {
                    if (battleField.getFieldCell(i, j).getFieldCellType() == FieldCellType.BLOCKED) {
                        g.drawImage(rocks, i * cellWidth + offset_x, j * cellHeight + offset_y, cellWidth, cellHeight,
                                this);
                    } else {
                        
                        if (battleField.getFieldCell(i, j).hasSpecialItem())
                            g.drawImage(box, i * cellWidth + offset_x, j * cellHeight + offset_y, cellWidth,
                                    cellHeight, this);
                    }
                } catch (OutOfMapException e) {
                    e.printStackTrace();
                }
            }
        }

        Font f = new Font("Times New Roman", Font.PLAIN, 20);
        g.setFont(f);

        // Draw the first warrior
        if (actualHealth1 > 0) {
            g.drawImage(warrior1, x1 * cellWidth + offset_x, y1 * cellHeight + offset_y, cellWidth, cellHeight, this);

            g.setColor(Color.MAGENTA);
            g.drawString(nombreW1, (x1 * cellWidth) - (45 - 7) + offset_x, (y1 * cellHeight) - 25 + offset_y);
            g.drawImage(HPempty, (x1 * cellWidth) - (45 - 7) + offset_x, (y1 * cellHeight) - 25 + offset_y, 90, 5, this);
            g.drawImage(HPviolet, (x1 * cellWidth) - (45 - 7) + offset_x, (y1 * cellHeight) - 25 + offset_y,
                    actualHealth1 * 90 / health1, 5, this);

        }

        // Draw the second warrior
        if (actualHealth2 > 0) {
            g.drawImage(warrior2, x2 * cellWidth + offset_x, y2 * cellHeight + offset_y, cellWidth, cellHeight, this);

            g.setColor(Color.ORANGE);
            g.drawString(nombreW2, (x2 * cellWidth) - (45 - 7) + offset_x, (y2 * cellHeight) - 30 + offset_y);
            g.drawImage(HPempty, (x2 * cellWidth) - (45 - 7) + offset_x, (y2 * cellHeight) - 30 + offset_y, 90, 5, this);
            g.drawImage(HPorange, (x2 * cellWidth) - (45 - 7) + offset_x, (y2 * cellHeight) - 30 + offset_y,
                    actualHealth2 * 90 / health2, 5, this);
        }

        for (i = 0; i < width; i++) {

            for (j = 0; j < height; j++) {

                if (!(((Math.pow(i - x1, 2)) + (Math.pow(j - y1, 2)) <= Math.pow(vision1, 2) && (actualHealth1 > 0))
                        || ((Math.pow(i - x2, 2)) + (Math.pow(j - y2, 2)) <= Math.pow(vision2, 2) && (actualHealth2 > 0))))

                    g.drawImage(fogOfWar, (i) * cellWidth + offset_x, (j) * cellHeight + offset_y, cellWidth,
                            cellHeight, this);

            }
        }

        g.setColor(Color.BLACK);
        
        
        
        g.drawString(battleField.getTick() + "/10000", +ConfigurationManager.getInstance().getMapHeight() * cellHeight
                - 100 + offset_x, +offset_y);

    }
}
