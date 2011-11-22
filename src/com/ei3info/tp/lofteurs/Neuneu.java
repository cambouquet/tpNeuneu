package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public abstract class Neuneu extends ObjetPositionnable
{
    public static int ENERGIE_MAX          = 40;
    public static int ENERGIE_REPRODUCTION = 10;

    protected Loft    loft;
    protected int     energie;
    protected Color   couleur              = Color.CYAN;
    protected int     numero;

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
        Nourriture procheMiam = trouverNourriturePlusProche();

        // S'il reste au moins 1 nourriture
        if (procheMiam != null)
        {
            if (procheMiam.posX == posX && procheMiam.posY == posY)
            {
                int enerT = (Neuneu.ENERGIE_MAX - this.energie) - procheMiam.getEnergie();
                if (enerT <= 0)
                {
                    procheMiam.consommer(Neuneu.ENERGIE_MAX - this.energie);
                    // On s'assure qu'un Neuneu ne reste pas trop bloqué
                    this.energie = Neuneu.ENERGIE_MAX - 1;
                } else
                {
                    this.energie = this.energie + procheMiam.getEnergie();
                    procheMiam.consommer(procheMiam.getEnergie());
                }
                if (procheMiam.getEnergie() <= 0)
                {
                    loft.detruireObjet(procheMiam);
                }
                loft.afficherEvenementDuree(getNom() + " : \"" + procheMiam.getDescriptionConsommation() + "\"");
            }
        }
    }

    protected void seReproduire()
    {
        if (this instanceof Lapin)
        {
            if (this.getRCounter() > 0)
            {
                this.setRCounter(this.getRCounter() - 1);
            }
            Neuneu procheNeuneu = trouverNeuneuPlusProche();

            if (procheNeuneu != null)
            {
                if ((procheNeuneu.posX == this.posX && procheNeuneu.posY == this.posY) && (this.getRCounter() == 0))
                {
                    this.setRCounter(5);
                    Neuneu neuneucree = null;

                    // Création d'un neuneu
                    neuneucree = loft.creerNouveauNeuneu();
                    if (neuneucree != null)
                    {
                        System.out.println(this.getNom() + " et " + procheNeuneu.getNom() + " ont donné naissance à "
                                + neuneucree.getNom());

                        loft.afficherEvenementDuree(this.getNom() + " et " + procheNeuneu.getNom()
                                + " ont donné naissance à " + neuneucree.getNom(), Color.PINK, false);
                        loft.addBebeNeuneu(neuneucree);
                    }

                    // Modification des niveaux d'énergie et
                    // destruction(s)
                    // éventuelle(s)
                    this.energie = this.energie - ENERGIE_REPRODUCTION;
                    procheNeuneu.setEnergie(procheNeuneu.getEnergie() - ENERGIE_REPRODUCTION);

                    this.mourir();
                    procheNeuneu.mourir();
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

    public Nourriture trouverNourriturePlusProche()
    {
        double distMin = 2 * Math.pow(loft.getTailleLoft(), 2);
        Nourriture procheMiam = null;

        // On parcourt la liste des ObjetDessinable
        LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
        for (ObjetDessinable obj : localListeObjet)
        {
            // On vérifie avant tout que l'on teste la Nourriture
            if (obj instanceof Nourriture)
            {
                Nourriture miam = (Nourriture) obj;
                double dist = Math.sqrt(Math.pow((miam.posX - this.posX), 2) + Math.pow((miam.posY - this.posY), 2));
                if (dist <= distMin)
                {
                    distMin = dist;
                    procheMiam = miam;
                }
            }
        }

        return procheMiam;
    }

    public Neuneu trouverNeuneuPlusProche()
    {
        double distMin = 2 * loft.getTailleLoft();
        Neuneu procheNeuneu = null;

        // On parcourt la liste des ObjetDessinable
        LinkedList<ObjetDessinable> localListeObjet = loft.getListeObjets();
        for (ObjetDessinable obj : localListeObjet)
        {
            // On vérifie avant tout que l'on teste la Nourriture
            if (obj instanceof Neuneu)
            {
                Neuneu neuneu = (Neuneu) obj;
                // On vérifie que l'objet ne soit pas l'objet appelant la
                // fonction
                if (!neuneu.equals(this))
                {
                    double dist = Math.sqrt(Math.pow((neuneu.posX - this.posX), 2)
                            + Math.pow((neuneu.posY - this.posY), 2));
                    if (dist < distMin)
                    {
                        distMin = dist;
                        procheNeuneu = neuneu;
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
        int energieReelle = (energie > this.ENERGIE_MAX) ? this.ENERGIE_MAX : energie;
        energieReelle = (energieReelle < 0) ? 0 : energieReelle;

        this.energie = energieReelle;
    }

    public abstract String getNom();
}
