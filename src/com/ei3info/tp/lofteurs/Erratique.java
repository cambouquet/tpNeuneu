package com.ei3info.tp.lofteurs;

import java.awt.Color;

public class Erratique extends Neuneu
{
    private static int dernierNumero = 1;
    
    public Erratique(Loft loft, int x, int y)
    {
        super(loft, x, y);
        couleur = Color.BLUE;
        this.numero = dernierNumero;
        dernierNumero ++;
    }

    protected void seDeplacer()
    {
        // Vérification bordure en X
        if (this.posX == 0 || this.posX == loft.getTailleLoft())
        {
            // Décision sur la direction
            double mvHandle = Math.random();
            if (mvHandle < 0.5)
            {
                mvHandle = -1;
            } else
            {
                mvHandle = 1;
            }

            // Déplacement effectif
            this.posY = this.posY + (int) mvHandle;
        }

        // Vérification bordure en Y
        if (this.posY == 0 || this.posY == loft.getTailleLoft())
        {
            // Décision sur la direction
            double mvHandle = Math.random();
            if (mvHandle < 0.5)
            {
                mvHandle = -1;
            } else
            {
                mvHandle = 1;
            }
            // Déplacement effectif
            this.posX = this.posX + (int) mvHandle;
        }

        // Déplacement dans le cas général

        double mvHandleX = Math.random();
        if (mvHandleX < 0.33)
        {
            mvHandleX = -1;
        } else
        {
            if (mvHandleX < 0.66)
            {
                mvHandleX = 0;
            } else
            {
                mvHandleX = 1;
            }
        }
        double mvHandleY = Math.random();
        if (mvHandleY < 0.33)
        {
            mvHandleY = -1;
        } else
        {
            if (mvHandleY < 0.66)
            {
                mvHandleY = 0;
            } else
            {
                mvHandleY = 1;
            }
        }
        this.posX = this.posX + (int) mvHandleX;
        this.posY = this.posY + (int) mvHandleY;
    }

    @Override
    public String getNom()
    {
        return new String("Erratique " + numero);
    }

    public static void resetNumeros()
    {
        dernierNumero = 1;
    }
}
