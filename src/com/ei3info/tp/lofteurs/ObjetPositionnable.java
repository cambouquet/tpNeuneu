/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Graphics;

/**
 * @author Camille
 *
 */
public abstract class ObjetPositionnable implements ObjetDessinable {

	/**
	 * Abscisse en nombre de cases, à partir de 0.
	 */
	protected int posX;

	/**
	 * Ordonnees en nombre de cases, à partir de 0.
	 */
	protected int posY;
	
	/**
	 * Largeur d'une case en pixel.
	 */
	protected int tailleX;

	/**
	 * Hauteur d'une case en pixel.
	 */
	protected int tailleY;
	
	@Override
	public void dessinerObjet(Graphics g) {
		// TODO Auto-generated method stub

	}
	
	public int getX()
	{
		return this.posX;
	}
	
	public int getY()
	{
		return this.posY;
	}

}
