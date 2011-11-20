package com.ei3info.tp.lofteurs;

import java.awt.Color;

public class Vorace extends Neuneu
{
    private static int dernierNumero = 1;
    
    public Vorace(Loft loft, int x, int y)
    {
        super(loft, x, y);
        couleur = Color.CYAN;
        this.numero = dernierNumero;
        dernierNumero ++;
    }

    public void seDeplacer()
    {
        // Recherche de la nourriture la plus proche
        int[] nextF = new int[2];
        nextF = trouverNourriturePlusProche();
        // Pathfinding basique
        int mvHandleX = (nextF[0] == this.posX) ? 0 : (nextF[0] - this.posX) / Math.abs(nextF[0] - this.posX);
        int mvHandleY = (nextF[1] == this.posY) ? 0 : (nextF[1] - this.posY) / Math.abs(nextF[1] - this.posY);
        // Déplacement
        this.posX = this.posX + mvHandleX;
        this.posY = this.posY + mvHandleY;
    }
    
    @Override
    public String getNom()
    {
        return new String("Vorace " + numero);
    }

    public static void resetNumeros()
    {
        dernierNumero = 1;
    }
}
