package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.util.LinkedList;

public class Cannibale extends Neuneu
{
    private static int dernierNumero = 1;
    
    public Cannibale(Loft loft, int x, int y)
    {
        super(loft, x, y);
        couleur = Color.RED;
        this.numero = dernierNumero;
        dernierNumero ++;
    }

    public void seDeplacer()
    {
        // Recherche de la nourriture la plus proche
        int[] nextF = new int[2];
        nextF = trouverNourriturePlusProche();
        // Recherche du neuneu le plus proche
        int[] nextN = new int[2];
        nextN = trouverNeuneuPlusProche();
        // Détermination de la source la plus proche
        double distN = Math.sqrt(Math.pow((nextN[0] - this.posX), 2) + Math.pow((nextN[1] - this.posY), 2));
        double distF = Math.sqrt(Math.pow((nextF[0] - this.posX), 2) + Math.pow((nextF[1] - this.posY), 2));

        if (distF < distN)
        {
            nextN[0] = 0; // Il fallait bien mettre quelque chose.
        } else
        {
            nextF = nextN;
        }
        // Pathfinding basique
        int mvHandleX = (nextN[0] == this.posX) ? 0 : (nextN[0] - this.posX) / Math.abs((nextN[0] - this.posX));
        int mvHandleY = (nextN[1] == this.posY) ? 0 : (nextN[1] - this.posY) / Math.abs((nextN[1] - this.posY));
        // Déplacement
        this.posX = this.posX + mvHandleX;
        this.posY = this.posY + mvHandleY;
    }

    public void manger()
    {
        // Vérification de la présence d'une source de nourriture sur la case
        int[] nextN = trouverNeuneuPlusProche();
        int[] nextF = trouverNourriturePlusProche();
        double distN = Math.sqrt(Math.pow((nextN[0] - this.posX), 2) + Math.pow((nextN[1] - this.posY), 2));
        double distF = Math.sqrt(Math.pow((nextF[0] - this.posX), 2) + Math.pow((nextF[1] - this.posY), 2));

        if (distN * distF == 0)
        {
            // On parcourt la liste des ObjetDessinable pour déterminer lequel
            // est sur la même case que le cannibale
            LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
            for (ObjetDessinable obj : localListeObjet)
            {
                ObjetPositionnable objPos = (ObjetPositionnable) obj;
                double dist = Math.sqrt(Math.pow(objPos.posX - this.posX, 2)
                        + Math.pow(objPos.posY - this.posY, 2));

                if (dist == 0)
                {
                    // Différenciation Neuneu/Nourriture: cas Neuneu
                    if (obj instanceof Neuneu)
                    {
                        Neuneu neuneu = (Neuneu) obj;
                        int enerT = (this.ENERGIE_MAX - this.energie)
                                - neuneu.getEnergie();
                        if (enerT <= 0)
                        {
                            this.energie = this.ENERGIE_MAX;
                        } else
                        {
                            this.energie = this.energie + neuneu.getEnergie();
                        }
                        // Mort du neuneu qui s'est fait bouffer
                        neuneu.mourir();
                    }

                    // Différenciation Neuneu/Nourriture: cas Nourriture
                    if (obj instanceof Nourriture)
                    {
                        Nourriture miam = (Nourriture) obj;
                        int enerT = (this.ENERGIE_MAX - this.energie)
                                - miam.getEnergie();
                        if (enerT <= 0)
                        {
                            this.energie = this.ENERGIE_MAX;
                            miam.consommer(miam.getEnergie() + this.energie
                                    - this.ENERGIE_MAX);
                        } else
                        {
                            this.energie = this.energie + miam.getEnergie();
                            miam.consommer(miam.getEnergie());
                            loft.detruireObjet(miam);
                        }
                    }
                }
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
