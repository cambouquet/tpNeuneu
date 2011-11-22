package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public abstract class Neuneu extends ObjetPositionnable
{
    public static int ENERGIE_MAX          = 40;
    public static int ENERGIE_REPRODUCTION = 10;

    protected Loft             loft;
    protected int              energie;
    protected Color            couleur              = Color.CYAN;
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
                            miam.consommer(Neuneu.ENERGIE_MAX - this.energie);
                            this.energie = Neuneu.ENERGIE_MAX - 1; // On s'assure qu'un Neuneu ne reste pas trop bloqué
                        } else
                        {
                            this.energie = this.energie + miam.getEnergie();
                            miam.consommer(miam.getEnergie());
                        }
                        if (miam.getEnergie() <= 0)
                        {
                            loft.detruireObjet(miam);
                        }
                        loft.afficherEvenementDuree(getNom() + " : \"" + miam.getDescriptionConsommation() + "\"");
                    }
                }
            }
        }
    }

    protected void seReproduire()
    {   
    if (this instanceof Lapin) {
        if (this.getRCounter() > 0) {
            this.setRCounter(this.getRCounter() - 1);
        }
        int[] nextN = trouverNeuneuPlusProche();
        double distN = Math.sqrt(Math.pow((nextN[0] - this.posX), 2) + Math.pow((nextN[1] - this.posY), 2));

        if ((distN == 0) && (this.getRCounter() == 0))
        {
            this.setRCounter(5);
            Neuneu neuneucree = null;

            // On parcourt la liste des ObjetDessinable pour déterminer lequel
            // est sur la même case
            LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
            for (ObjetDessinable obj : localListeObjet)
            {
                ObjetPositionnable objPos = (ObjetPositionnable) obj;
                double dist = Math.sqrt(Math.pow(objPos.posX - this.posX, 2)
                        + Math.pow(objPos.posY - this.posY, 2));
                if (dist == 0 && !obj.equals(this))
                {

                    // On vérifie que l'objet sur la même case est bien du type
                    // Neuneu
                    if (obj instanceof Neuneu)
                    {
                        Neuneu neuneu = (Neuneu) obj;
                        // Création d'un neuneu
                        neuneucree = loft.creerNouveauNeuneu();
                        if (neuneucree != null)
                        {
                            System.out.println(this.getNom() + " et " + neuneu.getNom() + " ont donné naissance à " + neuneucree.getNom());
                            loft.afficherEvenementDuree(this.getNom() + " et " + neuneu.getNom() + " ont donné naissance à " + neuneucree.getNom(), Color.PINK, false);
                            loft.addBebeNeuneu(neuneucree);
                        }

                        // Modification des niveaux d'énergie et destruction(s)
                        // éventuelle(s)
                        this.energie -= ENERGIE_REPRODUCTION;
                        neuneu.setEnergie(neuneu.getEnergie() - ENERGIE_REPRODUCTION);

                        if (this.energie <= 0)
                        {
                            this.mourir();
                        }
                        if (neuneu.getEnergie() <= 0)
                        {
                            neuneu.mourir();
                        }
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
                    distMin = dist;
                    procheMiam[0] = miam.posX;
                    procheMiam[1] = miam.posY;
                }
            }
        }

        return procheMiam;
    }

    public int[] trouverNeuneuPlusProche()
    {
        double distMin = 2 * loft.getTailleLoft();
        int[] procheNeuneu = new int[2];
        procheNeuneu[0] = 0;
        procheNeuneu[1] = 0;

        // On parcourt la liste des ObjetDessinable
        LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
        for (ObjetDessinable obj : localListeObjet)
        {        
            // On vérifie avant tout que l'on teste la Nourriture
            if (obj instanceof Neuneu)
            {
                Neuneu neuneu = (Neuneu) obj;
                //On vérifie que l'objet ne soit pas l'objet appelant la fonction
                if (!neuneu.equals(this)){
                    double dist = Math.sqrt(Math.pow((neuneu.posX - this.posX), 2)
                            + Math.pow((neuneu.posY - this.posY), 2));
                    if (dist < distMin)
                    {
                        distMin = dist;
                        procheNeuneu[0] = neuneu.posX;
                        procheNeuneu[1] = neuneu.posY;
                    }
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
        g.drawString(String.valueOf(energie), (posX + 1) * tailleX - 15, posY * tailleY - 5);
    }

    public int getEnergie()
    {
        return this.energie;
    }
    
    private int getRCounter()
    {
        return 0;
    }
    
    private void setRCounter(int i)
    {
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
