package com.ei3info.tp.lofteurs;

import java.awt.Color;

/**
 * Les Lapins.\n Contient les comportements spécifiques aux Lapins qu'il
 * faut substituer aux comportements par défaut présents dans la superclasse Neuneu.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class Lapin extends Neuneu
{
    /**
     * Le compteur permettant d'attribuer un numéro à chaque neuneu.
     */
    private static int         dernierNumero       = 1;

    /**
     * Un paramètre permettant de manipuler l'attrait d'un lapin à la nourriture
     * plutôt que les autres neuneus.
     */
    protected static final int FAIM_LAPIN          = 0;
    
    /**
     * Un timer empêchant la reproduction à chaque tour (il faut 1 ou 2 tours entre deux reproductions par le même lapin).
     */
    public int                 reproductionCounter = 0;

    /**
     * Création d'un nouveau lapin.
     * @param loft
     *          Le loft en cours (contient notamment la liste des objets)
     * @param x
     *          Sa position initiale en X
     * @param y
     *          Sa position initiale en Y
     */
    public Lapin(Loft loft, int x, int y)
    {
        super(loft, x, y);
        couleur = Color.PINK;
        this.numero = dernierNumero;
        dernierNumero++;
    }

    /**
     * Comportement de déplacement spécifique aux Lapins.
     * Le principe du lapin est qu'il cherche à se reproduire avant de chercher
     * à manger. S'il n'a pas suffisamment d'énergie pour se déplacer et se
     * reproduire, il cherchera d'abord à manger. (cf paramètre FAIM_LAPIN)
     */
    public void seDeplacer()
    {
        // Coordonnées vers lesquelles on veut se déplacer
        int[] nextD = new int[2];
        

        // Recherche de la nourriture la plus proche
        Nourriture procheMiam = trouverNourriturePlusProche();
        // Recherche du neuneu le plus proche
        int[] nextN = new int[2];
        Neuneu procheNeuneu = trouverNeuneuPlusProche();
        
        if (procheMiam != null && procheNeuneu != null)
        {
            // Détermination de la source la plus proche
            int distF = Math.abs(procheMiam.posX - this.posX) + Math.abs(procheMiam.posY - this.posY);
            int distN = Math.abs(procheNeuneu.posX - this.posX) + Math.abs(procheNeuneu.posY - this.posY);

            // Si la nourriture est plus proche et qu'il a faim
            if (distF + FAIM_LAPIN < distN)
            {
                nextD[0] = procheMiam.posX;
                nextD[1] = procheMiam.posY;
            } else
            {
                nextD[0] = procheNeuneu.posX;
                nextD[1] = procheNeuneu.posY;
            }
        } else if (procheNeuneu != null)
        {
            nextD[0] = procheNeuneu.posX;
            nextD[1] = procheNeuneu.posY;

        } else if (procheMiam != null)
        {
            nextD[0] = procheMiam.posX;
            nextD[1] = procheMiam.posY;
        }
        
        int mvHandleX = 0;
        int mvHandleY = 0;
        
        // Si il reste au moins 1 autre neuneu ou une autre nourriture
        if (nextD != null)
        {
            // Pathfinding basique
            mvHandleX = (nextD[0] == this.posX) ? 0 : (nextD[0] - this.posX) / Math.abs((nextD[0] - this.posX));
            mvHandleY = (nextD[1] == this.posY) ? 0 : (nextD[1] - this.posY) / Math.abs((nextD[1] - this.posY));
        }
        
        // Déplacement
        this.posX = this.posX + mvHandleX;
        this.posY = this.posY + mvHandleY;

    }

    @Override
    /**
     * Retourne le nom du Lapin.
     */
    public String getNom()
    {
        return new String("Lapin " + numero);
    }

    /**
     * Retourne le compteur de reproduction.
     */
    public int getRCounter()
    {
        return this.reproductionCounter;
    }

    /**
     * Setter du compteur de reproduction.
     * @param i
     *          Le paramètre permettant de set le compteur.
     */
    private void setRCounter(int i)
    {
        this.reproductionCounter = i;
    }

    /**
     * Remise à zéro du compteur de numéros.
     */
    public static void resetNumeros()
    {
        dernierNumero = 1;
    }
}
