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

import java.util.Random;

class TerrainGenerator {
	private int width;
	private int height;

	public TerrainGenerator(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int[][] getMaze() {
		int x, y;
		int[][] maze = new int[width][height];
		Random rnd = new Random();
		int basePoints = rnd.nextInt(30) + 10;

		for (int i = 0; i < basePoints; i++) {
			x = rnd.nextInt(width);
			y = rnd.nextInt(height);

			maze[x][y] = 2;
			
			if (x > 0 && y < height - 1)
				maze[x - 1][y + 1] = 1;
			
			if (x > 0 && y > 0)
				maze[x - 1][y - 1] = 1;
			
			if (x > 0)
				maze[x - 1][y] = 1;
			
			if (x < width - 1)
				maze[x + 1][y] = 1;
			
			if (x < width - 1 && y < height - 1)
				maze[x + 1][y + 1] = 1;
			
			if (x < width - 1 && y > 0)
				maze[x + 1][y - 1] = 1;
			
			if (y < height - 1)
				maze[x][y + 1] = 1;
			
			if (y > 0)
				maze[x][y - 1] = 1;
		}

		return maze;
	}

	public static void main(String[] args) {
		TerrainGenerator tg = new TerrainGenerator(60, 40);

		int[][] m = tg.getMaze();
		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 60; j++) {
				System.out.print(m[j][i]);
			}
			System.out.println();
		}

	}

}
