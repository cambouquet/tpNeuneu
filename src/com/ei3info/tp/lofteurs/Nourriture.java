/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Camille
 */
public abstract class Nourriture extends ObjetPositionnable
{
    public static int ENERGIE_MAX = 5;
    protected Color couleur = Color.black;
    protected int energieMin= 1;
    protected int energieInitiale;    
    protected int energie;    

    protected Nourriture(int x, int y)
    {
        super(x, y);
        this.energieInitiale = (int) Math.rint(Math.random()*(ENERGIE_MAX - energieMin) + energieMin);
        this.energie = energieInitiale;
    }

    @Override
    public void dessinerObjet(Graphics g)
    {
        g.setColor(couleur);
        g.fillOval(posX * tailleX, posY * tailleY, tailleX - 4, tailleY - 4);
        g.drawString(String.valueOf(energie), (posX + 1) * tailleX - 15, posY * tailleY - 5);
    }
    
    /**
     * 
     * @param quantite
     */
    protected void consommer(int quantite)
    {
        this.energie = this.energie - quantite;
    }

    public int getEnergie()
    {
        return energie;
    }
    
    public abstract String getDescriptionConsommation();
}
