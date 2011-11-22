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
        Nourriture procheMiam = trouverNourriturePlusProche();
        // Pathfinding basique
        int mvHandleX = (procheMiam.posX == this.posX) ? 0 : (procheMiam.posX - this.posX) / Math.abs(procheMiam.posX - this.posX);
        int mvHandleY = (procheMiam.posY == this.posY) ? 0 : (procheMiam.posY - this.posY) / Math.abs(procheMiam.posY - this.posY);
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
