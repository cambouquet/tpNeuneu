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
public abstract class Nourriture extends ObjetPositionnable {
	protected Nourriture(int x, int y) {
		super(x, y);
	}

	protected Color couleur = Color.black;

	@Override
	public void dessinerObjet(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(posX * tailleX, posY * tailleY, tailleX - 4,
				tailleY - 4);
	}
}
