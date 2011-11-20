package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public abstract class Neuneu extends ObjetPositionnable
{

    protected Loft             loft;
    protected int              energie;
    protected static final int ENERGIE_MAX          = 20;
    protected Color            couleur              = Color.CYAN;
    protected static final int ENERGIE_REPRODUCTION = 5;
    protected int numero;

    protected Neuneu(int x, int y)
    {
        super(x, y);
    }

    protected Neuneu(Loft loft, int x, int y)
    {
        this(x, y);
        this.loft = loft;
        this.energie = ENERGIE_MAX;
    }

    protected abstract void seDeplacer();

    protected void manger()
    {
        int[] nextF = trouverNourriturePlusProche();
        if (nextF[0] == posX && nextF[1] == posY)
        {
            System.out.println(getNom() + " a trouvé de la nourriture.");
            // On parcourt la liste des ObjetDessinable pour déterminer lequel
            // est sur la même case que le cannibale
            LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
            for (ObjetDessinable obj : localListeObjet)
            {
                // On vérifie que l'objet sur la même case est bien du type
                // Nourriture
                if (obj instanceof Nourriture)
                {
                    Nourriture miam = (Nourriture) obj;
                    if (miam.posX == this.posX && miam.posY == this.posY)
                    {
                        int enerT = (Neuneu.ENERGIE_MAX - this.energie)
                                - miam.getEnergie();
                        if (enerT <= 0)
                        {
                            miam.consommer(- enerT);
                            this.energie = Neuneu.ENERGIE_MAX;
                        } else
                        {
                            this.energie = this.energie + miam.getEnergie();
                            miam.consommer(miam.getEnergie());
                        }

                        System.out.println(getNom() + " a bien mangé");
                    }
                }
            }
        }
        // double distF = Math.sqrt(Math.pow(nextF[0] - this.posX, 2) +
        // Math.pow(nextF[1] - this.posY, 2));
        //
        // if (distF == 0)
        // {
        //
        // // On parcourt la liste des ObjetDessinable pour déterminer lequel
        // // est sur la même case que le cannibale
        // LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
        // for (ObjetDessinable obj : localListeObjet)
        // {
        // ObjetPositionnable objPos = (ObjetPositionnable) obj;
        // double dist = Math.sqrt(Math.pow(objPos.posX - this.posX, 2)
        // + Math.pow(objPos.posY - this.posY, 2));
        // if (dist == 0)
        // {
        //
        // // On vérifie que l'objet sur la même case est bien du type
        // // Nourriture
        // if (obj instanceof Nourriture)
        // {
        // Nourriture miam = (Nourriture) obj;
        // int enerT = (Neuneu.ENERGIE_MAX - this.energie)
        // - miam.getEnergie();
        // if (enerT <= 0)
        // {
        // this.energie = Neuneu.ENERGIE_MAX;
        // miam.consommer(miam.getEnergie() + this.energie
        // - Neuneu.ENERGIE_MAX);
        // } else
        // {
        // this.energie = this.energie + miam.getEnergie();
        // miam.consommer(miam.getEnergie());
        // }
        // }
        // }
        // }
        // }
    }

    protected void seReproduire()
    {
        int[] nextN = trouverNeuneuPlusProche();
        double distN = Math.sqrt(Math.pow(nextN[0], 2) + Math.pow(nextN[1], 2));

        if (distN == 0)
        {

            // On parcourt la liste des ObjetDessinable pour déterminer lequel
            // est sur la même case
            LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
            for (ObjetDessinable obj : localListeObjet)
            {
                ObjetPositionnable objPos = (ObjetPositionnable) obj;
                double dist = Math.sqrt(Math.pow(objPos.posX - this.posX, 2)
                        + Math.pow(objPos.posY - this.posY, 2));
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
        double distMin = 2 * Math.pow(loft.getTailleLoft(), 2);
        int[] procheMiam = new int[2];

        // On parcourt la liste des ObjetDessinable
        LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
        for (ObjetDessinable obj : localListeObjet)
        {

            // On vérifie avant tout que l'on teste la Nourriture
            if (obj instanceof Nourriture)
            {
                Nourriture miam = (Nourriture) obj;
                double dist = Math.sqrt(Math.pow((miam.posX - this.posX), 2)
                        + Math.pow((miam.posY - this.posY), 2));
                if (dist <= distMin)
                {
                    procheMiam[0] = miam.posX;
                    procheMiam[1] = miam.posY;
                }
            }
        }

        return procheMiam;
    }

    public int[] trouverNeuneuPlusProche()
    {
        int distMin = 2 * loft.getTailleLoft();
        int[] procheNeuneu = new int[2];

        // On parcourt la liste des ObjetDessinable
        LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
        for (ObjetDessinable obj : localListeObjet)
        {

            // On vérifie avant tout que l'on teste la Nourriture
            if (obj instanceof Neuneu)
            {
                Neuneu neuneu = (Neuneu) obj;
                double dist = Math.sqrt(Math.pow((neuneu.posX - this.posX), 2)
                        + Math.pow((neuneu.posY - this.posY), 2));
                if (dist <= distMin)
                {
                    procheNeuneu[0] = neuneu.posX;
                    procheNeuneu[1] = neuneu.posY;
                }
            }
        }

        return procheNeuneu;
    }

    @Override
    public void dessinerObjet(Graphics g)
    {
        g.setColor(couleur);
        g.fillRect(posX * tailleX, posY * tailleY, tailleX - 4, tailleY - 4);
        g.drawString(String.valueOf(numero), posX * tailleX - 5, posY * tailleY - 5);
    }

    public int getEnergie()
    {
        return this.energie;
    }

    public void setEnergie(int energie)
    {
        int energieReelle = (energie > this.ENERGIE_MAX) ? this.ENERGIE_MAX
                : energie;
        energieReelle = (energieReelle < 0) ? 0 : energieReelle;

        this.energie = energieReelle;
    }

    public abstract String getNom();
}
