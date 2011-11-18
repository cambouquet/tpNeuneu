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
	public static final int tailleX = 30;

	/**
	 * Hauteur d'une case en pixel.
	 */
	public static final int tailleY = 30;
	
	protected ObjetPositionnable(int x, int y)
	{
		this.posX = x;
		this.posY = y;
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
