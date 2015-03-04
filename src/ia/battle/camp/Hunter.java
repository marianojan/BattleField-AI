/*
 * Copyright (c) 2012-2015, Ing. Gabriel Barrera <gmbarrera@gmail.com>
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

import ia.battle.camp.actions.Action;
import ia.exceptions.RuleException;

public class Hunter {
	private String name;
	private int strength, speed, range;
	private FieldCell position;
	
	Hunter(String name, int strength, int speed, int range) {
		this.name = name;
		this.strength = strength;
		this.speed = speed;
		this.range = range;
	}
	
	String getName() {
		return name;
	}

	int getStrength() {
		return strength;
	}

	int getSpeed() {
		return speed;
	}

	int getRange() {
		return range;
	}
	
	public FieldCell getPosition() {
		return position;
	}

	void setPosition(FieldCell position) {
		this.position = position;
	}
	
	public Action playTurn(long tick, int actionNumber) {
		
		try {
			for(Warrior warrior : BattleField.getInstance().getWarriors() ){
				
				
				
			}
			
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
