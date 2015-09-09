package ia.battle.test;

import ia.battle.camp.BattleField;
import ia.battle.camp.FieldCell;
import ia.exceptions.OutOfMapException;

public class TestFieldCell {

	public static void main(String[] args) {
		
		try {
			BattleField.getInstance().initCells();
			FieldCell f1 = BattleField.getInstance().getFieldCell(1, 1);
			FieldCell f2 = BattleField.getInstance().getFieldCell(1, 1);
			
			if (f1.equals(f2))
				System.out.println("Igual");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
