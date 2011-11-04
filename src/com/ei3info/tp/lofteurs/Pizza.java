/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Camille
 *
 */
public class Pizza extends Nourriture {

	public Pizza(int x, int y) {
		this.posX = x;
		this.posY = y;
		System.out.println("Pizza placée en " + posX + ":" + posY);
	}
	
	@Override
	public void dessinerObjet(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRoundRect(posX * tailleX, posY * tailleY, tailleX - 4, tailleY - 4, tailleX, tailleY);
	}

}
