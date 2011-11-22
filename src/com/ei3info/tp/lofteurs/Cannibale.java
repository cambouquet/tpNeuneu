package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.util.LinkedList;

public class Cannibale extends Neuneu
{
    private static int         dernierNumero  = 1;
    protected static final int FAIM_CANNIBALE = 0;

    public Cannibale(Loft loft, int x, int y)
    {
        super(loft, x, y);
        couleur = Color.RED;
        this.numero = dernierNumero;
        dernierNumero++;
    }

    public void seDeplacer()
    {
        // Coordonnées vers lesquelles on veut se déplacer
        int[] nextD = new int[2];
        // Recherche de la nourriture la plus proche
        Nourriture procheMiam = trouverNourriturePlusProche();

        // Recherche du neuneu le plus proche
        Neuneu procheNeuneu = trouverNeuneuPlusProche();

        if (procheMiam != null && procheNeuneu != null)
        {
            // Détermination de la source la plus proche
            int distF = Math.abs(procheMiam.posX - this.posX) + Math.abs(procheMiam.posY - this.posY);
            int distN = Math.abs(procheNeuneu.posX - this.posX) + Math.abs(procheNeuneu.posY - this.posY);

            // Si la nourriture est plus proche et qu'il a faim
            if (distF + FAIM_CANNIBALE < distN)
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

    public void manger()
    {
        // Vérification de la présence d'une source de nourriture sur la case
        Neuneu procheNeuneu = trouverNeuneuPlusProche();
        Nourriture procheMiam = trouverNourriturePlusProche();

        // On mange le neuneu s'il est sur la même case
        if (procheNeuneu != null && procheNeuneu.posX == this.posX && procheNeuneu.posY == this.posY)
        {
            // Le cannibale mange l'autre neuneu, même s'il n'a plus faim !
            // Il ne peut pas s'en empêcher, il adore ça
            int enerT = (Neuneu.ENERGIE_MAX - this.energie) - procheNeuneu.getEnergie();
            if (enerT <= 0)
            {
                this.energie = Neuneu.ENERGIE_MAX;
            } else
            {
                this.energie = this.energie + procheNeuneu.getEnergie();
            }
            // Mort du neuneu qui s'est fait bouffer
            procheNeuneu.setEnergie(0);
            procheNeuneu.mourir();
            loft.afficherEvenementDuree("Oh non ! " + getNom() + " a dévoré " + procheNeuneu.getNom() + " !!!", Color.RED, false);
        }

     // On mange la nourriture si elle est sur la même case
        if (procheMiam != null && procheMiam.posX == this.posX && procheMiam.posY == this.posY)
        {
            int enerT = (Neuneu.ENERGIE_MAX - this.energie) - procheMiam.getEnergie();
            if (enerT <= 0)
            {
                this.energie = Neuneu.ENERGIE_MAX - 1;
                procheMiam.consommer(procheMiam.getEnergie() + this.energie - Neuneu.ENERGIE_MAX);
            } else
            {
                this.energie = this.energie + procheMiam.getEnergie();
                procheMiam.consommer(procheMiam.getEnergie());
                loft.detruireObjet(procheMiam);
            }
        }
    }

    // Fin de manger()

    @Override
    public String getNom()
    {
        return new String("Cannibale " + numero);
    }

    public static void resetNumeros()
    {
        dernierNumero = 1;
    }
}
