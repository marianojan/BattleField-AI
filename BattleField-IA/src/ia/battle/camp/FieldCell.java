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

package ia.battle.camp;

public class FieldCell {
	private int x, y;
	private FieldCellType fieldCellType;
	private SpecialItem specialItem;

	FieldCell(FieldCellType type, int x, int y, SpecialItem specialItem) {
		this.fieldCellType = type;
		this.x = x;
		this.y = y;
		this.specialItem = specialItem;
	}

	public FieldCellType getFieldCellType() {
		return fieldCellType;
	}

	public SpecialItem getSpecialItem() {
		return specialItem;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String toString() {
	    String aux = " ";
	    
		switch (fieldCellType) {
		case NORMAL:
			aux = "N";
			break;
			
		case BLOCKED:
			aux = "B";
			break;
		}

		return aux + " [" + x + ", " + y + "]";
	}

    SpecialItem removeSpecialItem() {
        SpecialItem si = specialItem;
        specialItem = null;
        
        return si;
    }

}
