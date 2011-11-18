package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Neuneu extends ObjetPositionnable {

	protected Loft loft;
	protected int energie;
	protected Color couleur = Color.CYAN;

	protected Neuneu(int x, int y) {
		super(x, y);
	}
	
	protected Neuneu(Loft loft, int x, int y) {
		this(x, y);
		this.loft = loft;
	}
	
	public int[] trouverNourriturePlusProche()
	{
		return null;
	}

	public int[] trouverNeuneuPlusProche()
	{
		return null;
	}

	@Override
	public void dessinerObjet(Graphics g) {
		g.setColor(couleur);
		g.fillRect(posX * tailleX, posY * tailleY, tailleX - 4, tailleY - 4);
	}
}
