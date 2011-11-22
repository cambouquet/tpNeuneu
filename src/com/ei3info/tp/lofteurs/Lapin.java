package com.ei3info.tp.lofteurs;

import java.awt.Color;

public class Lapin extends Neuneu
{
    private static int dernierNumero = 1;
    
    protected static final int FAIM_LAPIN = 0;
    public int reproductionCounter = 0;

    public Lapin(Loft loft, int x, int y)
    {
        super(loft, x, y);
        couleur = Color.PINK;
        this.numero = dernierNumero;
        dernierNumero ++;
    }

    public void seDeplacer()
    {
        // Le principe du lapin est qu'il cherche à se reproduire avant de
        // chercher à manger.
        // S'il n'a pas suffisamment d'énergie pour se déplacer et se
        // reproduire, il cherchera d'abord à manger.

     // Recherche de la nourriture la plus proche
        int[] nextF = new int[2];
        nextF = trouverNourriturePlusProche();
        // Recherche du neuneu le plus proche
        int[] nextN = new int[2];
        nextN = trouverNeuneuPlusProche();
        // Détermination de la source la plus proche
        int distF = Math.abs(nextF[0] - this.posX) + Math.abs(nextF[1] - this.posY);
        int distN = Math.abs(nextN[0] - this.posX) + Math.abs(nextN[1] - this.posY);
        if (distF + FAIM_LAPIN < distN) {
            nextN[0] = nextF[0];
            nextN[1] = nextF[1];
        }
        // Pathfinding basique
        int mvHandleX = (nextN[0] == this.posX) ? 0 : (nextN[0] - this.posX) / Math.abs((nextN[0] - this.posX));
        int mvHandleY = (nextN[1] == this.posY) ? 0 : (nextN[1] - this.posY) / Math.abs((nextN[1] - this.posY));
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
