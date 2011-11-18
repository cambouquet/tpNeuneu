package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Neuneu extends ObjetPositionnable {

	protected Loft loft;
	protected int energie;
	protected int energieMax;
	protected Color couleur = Color.CYAN;

	protected Neuneu(int x, int y) {
		super(x, y);
	}
	
	protected Neuneu(Loft loft, int x, int y) {
		this(x, y);
		this.loft = loft;
	}
	
	protected void seDeplacer()
	{
		
	}
	protected void manger()
	{
		
	}
	
	protected void seReproduire()
	{
		
	}
	
	protected boolean mourir()
	{
		throw new UnsupportedOperationException("Pas encore fait");
	}
	
	public int[] trouverNourriturePlusProche()
	{
	    throw new UnsupportedOperationException("Pas encore fait");
	}

	public int[] trouverNeuneuPlusProche()
	{
	    throw new UnsupportedOperationException("Pas encore fait");
	}

	@Override
	public void dessinerObjet(Graphics g) {
		g.setColor(couleur);
		g.fillRect(posX * tailleX, posY * tailleY, tailleX - 4, tailleY - 4);
	}

	public int getEnergie() 
	{
		return this.energie;
	}

	public void setEnergie(int energie) 
	{
		int energieReelle = (energie > this.energieMax) ? this.energieMax : energie;
		energieReelle = (energieReelle < 0) ? 0 : energieReelle;
		
		this.energie = energieReelle;
	}
}
