package com.ei3info.tp.lofteurs;

import java.awt.Color;

public class Lapin extends Neuneu
{
    private static int         dernierNumero       = 1;

    protected static final int FAIM_LAPIN          = 0;
    public int                 reproductionCounter = 0;

    public Lapin(Loft loft, int x, int y)
    {
        super(loft, x, y);
        couleur = Color.PINK;
        this.numero = dernierNumero;
        dernierNumero++;
    }

    /**
     * Le principe du lapin est qu'il cherche à se reproduire avant de chercher
     * à manger. S'il n'a pas suffisamment d'énergie pour se déplacer et se
     * reproduire, il cherchera d'abord à manger.
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
    public String getNom()
    {
        return new String("Lapin " + numero);
    }

    public int getRCounter()
    {
        return this.reproductionCounter;
    }

    private void setRCounter(int i)
    {
        this.reproductionCounter = i;
    }

    public static void resetNumeros()
    {
        dernierNumero = 1;
    }
}
