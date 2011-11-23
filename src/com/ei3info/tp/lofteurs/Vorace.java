package com.ei3info.tp.lofteurs;

import java.awt.Color;

/**
 * Les Voraces.\n Contient les comportements spécifiques aux Voraces qu'il
 * faut substituer aux comportements par défaut présents dans la superclasse Neuneu.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class Vorace extends Neuneu
{
    /**
     * Le compteur permettant d'attribuer un numéro à chaque neuneu.
     */
    private static int dernierNumero = 1;

    /**
     * Création d'un nouveau vorace.
     * @param loft
     *          Le loft en cours (contient notamment la liste des objets)
     * @param x
     *          Sa position initiale en X
     * @param y
     *          Sa position initiale en Y
     */
    public Vorace(Loft loft, int x, int y)
    {
        super(loft, x, y);
        couleur = Color.CYAN;
        this.numero = dernierNumero;
        dernierNumero++;
    }

    /**
     * Comportement de déplacement spécifique aux Voraces.
     * Déplacement le plus basique des 4 types de neuneu.
     */
    public void seDeplacer()
    {
        // Recherche de la nourriture la plus proche
        Nourriture procheMiam = trouverNourriturePlusProche();
        int mvHandleX = 0;
        int mvHandleY = 0;

        if (procheMiam != null)
        {
            // Pathfinding basique
            mvHandleX = (procheMiam.posX == this.posX) ? 0 : (procheMiam.posX - this.posX)
                    / Math.abs(procheMiam.posX - this.posX);
            mvHandleY = (procheMiam.posY == this.posY) ? 0 : (procheMiam.posY - this.posY)
                    / Math.abs(procheMiam.posY - this.posY);
        }
        // Déplacement
        this.posX = this.posX + mvHandleX;
        this.posY = this.posY + mvHandleY;
    }

    /**
     * Retourne le nom du Vorace.
     */
    @Override
    public String getNom()
    {
        return new String("Vorace " + numero);
    }

    /**
     * Remise à zéro du compteur de numéros.
     */
    public static void resetNumeros()
    {
        dernierNumero = 1;
    }
}
