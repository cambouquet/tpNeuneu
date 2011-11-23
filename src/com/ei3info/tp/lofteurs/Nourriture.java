/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Les Nourritures. /n
 * Superclasse de tous les types de nourriture, elle contient toutes les propriétés et méthodes basiques nécessaires au bon fonctionnement.
 * La spécialisation des nourritures permettra la modification de certains paramètres.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public abstract class Nourriture extends ObjetPositionnable
{
    /**
     * Le niveau d'énergie maximum contenu dans une nourriture
     */
    public static int ENERGIE_MAX = 5;
    /**
     * La couleur par défaut
     */
    protected Color couleur = Color.black;
    /**
     * Le niveau d'énergie minimum d'une nourriture
     */
    protected int energieMin= 1;
    /**
     * Le niveau d'énergie initiale de la nourriture.
     */
    protected int energieInitiale;    
    /**
     * Le niveau d'énergie actuel de la nourriture.
     */
    protected int energie;    

    /**
     * Création d'une nouvelle nourriture.
     * @param x
     *          Sa position initiale en X.
     * @param y
     *          Sa position initiale en Y.
     */
    protected Nourriture(int x, int y)
    {
        super(x, y);
        this.energieInitiale = (int) Math.rint(Math.random()*(ENERGIE_MAX - energieMin) + energieMin);
        this.energie = energieInitiale;
    }

    @Override
    /**
     * Dessin d'un objet à l'écran.
     */
    public void dessinerObjet(Graphics g)
    {
        g.setColor(couleur);
        g.fillOval(posX * tailleX, posY * tailleY, tailleX - 4, tailleY - 4);
        g.drawString(String.valueOf(energie), (posX + 1) * tailleX - 15, posY * tailleY - 5);
    }
    
    /**
     * Consommation d'un élément de nourriture.
     * @param quantite
     *          La quantité d'énergie consommée.
     */
    protected void consommer(int quantite)
    {
        this.energie = this.energie - quantite;
    }

    /**
     * Permet de retourner le niveau d'énergie actuel.
     * @return
     *          Le niveau d'énergie actuel.
     */
    public int getEnergie()
    {
        return energie;
    }
    
    /**
     * Retourne la description de la consommation (inutilisé).
     * @return
     */
    public abstract String getDescriptionConsommation();
}
