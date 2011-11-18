package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public abstract class Neuneu extends ObjetPositionnable
{

    protected Loft             loft;
    protected int              energie;
    protected int              energieMax;
    protected Color            couleur              = Color.CYAN;
    protected static final int ENERGIE_REPRODUCTION = 5;

    protected Neuneu(int x, int y)
    {
        super(x, y);
    }

    protected Neuneu(Loft loft, int x, int y)
    {
        this(x, y);
        this.loft = loft;
    }

    protected void seDeplacer()
    {

    }

    protected void manger()
    {
        int[] nextF = trouverNourriturePlusProche();
        double distF = Math.sqrt(Math.pow(nextF[0], 2) + Math.pow(nextF[1], 2));

        if (distF == 0)
        {

            // On parcourt la liste des ObjetDessinable pour déterminer lequel
            // est sur la même case que le cannibale
            LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
            for (ObjetDessinable obj : localListeObjet)
            {
                ObjetPositionnable objPos = (ObjetPositionnable) obj;
                double dist = Math.sqrt(Math.pow(objPos.posX, 2)
                        + Math.pow(objPos.posY, 2));
                if (dist == 0)
                {

                    // On vérifie que l'objet sur la même case est bien du type
                    // Nourriture
                    if (obj instanceof Nourriture)
                    {
                        Nourriture miam = (Nourriture) obj;
                        int enerT = (this.energieMax - this.energie)
                                - miam.getEnergie();
                        if (enerT <= 0)
                        {
                            this.energie = this.energieMax;
                            miam.consommer(miam.getEnergie() + this.energie
                                    - this.energieMax);
                        } else
                        {
                            this.energie = this.energie + miam.getEnergie();
                            miam.consommer(miam.getEnergie());
                        }
                    }
                }
            }
        }
    }

    protected void seReproduire()
    {
        int[] nextN = trouverNeuneuPlusProche();
        double distN = Math.sqrt(Math.pow(nextN[0], 2) + Math.pow(nextN[1], 2));

        if (distN == 0)
        {

            // On parcourt la liste des ObjetDessinable pour déterminer lequel
            // est sur la même case que le cannibale
            LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
            for (ObjetDessinable obj : localListeObjet)
            {
                ObjetPositionnable objPos = (ObjetPositionnable) obj;
                double dist = Math.sqrt(Math.pow(objPos.posX, 2)
                        + Math.pow(objPos.posY, 2));
                if (dist == 0)
                {

                    // On vérifie que l'objet sur la même case est bien du type
                    // Neuneu
                    if (obj instanceof Neuneu)
                    {
                        Neuneu neuneu = (Neuneu) obj;
                        // Création d'un neuneu

                        // Vérification des niveaux d'énergie et destruction(s)
                        // éventuelle(s)
                        int enerX = this.energie - ENERGIE_REPRODUCTION;
                        int enerY = neuneu.getEnergie() - ENERGIE_REPRODUCTION;

                        if (enerX <= 0)
                        {
                            this.mourir();
                        }
                        if (enerY <= 0)
                        {
                            neuneu.mourir();
                        }
                    }
                }
            }
        }
    }

    protected boolean mourir()
    {
        boolean mort = false;
        if (energie <= 0)
        {
            mort = true;
            loft.detruireObjet(this);
        }
        return mort;
    }

    public int[] trouverNourriturePlusProche()
    {
        throw new UnsupportedOperationException("Pas encore fait");
    }

    public int[] trouverNeuneuPlusProche()
    {
        throw new UnsupportedOperationException("Pas encore fait");
    }

    @Override
    public void dessinerObjet(Graphics g)
    {
        g.setColor(couleur);
        g.fillRect(posX * tailleX, posY * tailleY, tailleX - 4, tailleY - 4);
    }

    public int getEnergie()
    {
        return this.energie;
    }

    public void setEnergie(int energie)
    {
        int energieReelle = (energie > this.energieMax) ? this.energieMax
                : energie;
        energieReelle = (energieReelle < 0) ? 0 : energieReelle;

        this.energie = energieReelle;
    }
}
