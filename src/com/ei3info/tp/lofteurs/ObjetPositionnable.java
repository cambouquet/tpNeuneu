/**
 * 
 */
package com.ei3info.tp.lofteurs;


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
	 * Ordonnée en nombre de cases, à partir de 0.
	 */
	protected int posY;
	
	/**
	 * Largeur d'une case en pixel.
	 */
	public static final int tailleX = 20;

	/**
	 * Hauteur d'une case en pixel.
	 */
	public static final int tailleY = 20;
	
	/**
	 * Créé un nouvel objet positionnable aux coordonnées indiquées.
	 * @param x
	 *         L'abscisse de l'objet.
	 * @param y
	 *         L'ordonnée de l'objet.
	 */
	protected ObjetPositionnable(int x, int y)
	{
		this.posX = x;
		this.posY = y;
	}
	
	/**
	 * Retourne l'abscisse de l'objet.
	 * @return
	 *         L'abscisse de l'objet.
	 */
	public int getX()
	{
		return this.posX;
		
	}
	
	/**
	 * Retourne l'ordonnée de l'objet.
	 * @return
	 *         L'ordonnée de l'objet.
	 */
	public int getY()
	{
		return this.posY;
	}
}
