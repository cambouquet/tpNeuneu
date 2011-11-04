/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Graphics;

/**
 * @author Camille
 *
 */
public class Pizza extends Nourriture {

	public Pizza(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	@Override
	public void dessinerObjet(Graphics g)
	{
		g.drawRoundRect(posX * tailleX, posY * tailleY, tailleX - 4, tailleY - 4, tailleX, tailleY);
		System.out.println("Pizza en : " + posX + ": " + posY);
	}

}
