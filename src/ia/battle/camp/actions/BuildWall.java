package ia.battle.camp.actions;

import ia.battle.camp.FieldCell;

public final class BuildWall extends Action {
	private FieldCell cellToBuild;
	
	public BuildWall(FieldCell cellToBuild) {
		this.cellToBuild = cellToBuild;
	}
	
	public FieldCell getCellToBuild() {
		return cellToBuild;
	}

}
