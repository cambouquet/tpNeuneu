/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.JLabel;

/**
 * @author Camille
 */
public class Loft extends Thread
{

    private int                         tailleLoft;
    private ZoneGraphique               zone;
    private LinkedList<ObjetDessinable> listeObjets;
    private LinkedList<ObjetDessinable> listeObjetsDetruits;
    private LinkedList<ObjetDessinable> listeObjetsCrees;
    private LoftPanel                   loftPanel;
    private static final int            WAITING_TIME = 200;
    private boolean                     finSaison    = false;
    private int                         heures;

    public Loft(int tailleLoft, ZoneGraphique zone)
    {
        this.tailleLoft = tailleLoft;
        this.zone = zone;
        this.listeObjets = new LinkedList<ObjetDessinable>();
        this.listeObjetsDetruits = new LinkedList<ObjetDessinable>();
        this.listeObjetsCrees= new LinkedList<ObjetDessinable>();
        this.loftPanel = new LoftPanel(listeObjets);
        loftPanel.setPreferredSize(new Dimension(tailleLoft
                * ObjetPositionnable.tailleX, tailleLoft
                * ObjetPositionnable.tailleY));
        loftPanel.setSize(tailleLoft * ObjetPositionnable.tailleX, tailleLoft
                * ObjetPositionnable.tailleY);
        this.zone.setLoftPanel(loftPanel);
    }

    public void remplissageAleatoire(float f)
    {
        int nbrCaseARemplir = (int) (f * tailleLoft);

        for (int i = 0; i < nbrCaseARemplir; i++)
        {
            double x = Math.random();
            if (x < Saison.proportionPizza)
            {
                this.add(new Pizza((int) (Math.random() * (tailleLoft - 1)),
                        (int) (Math.random() * (tailleLoft - 1))));
            } else
            {
                x -= Saison.proportionPizza;
                if (x < Saison.proportionCoca)
                {
                    this.add(new Coca((int) (Math.random() * (tailleLoft - 1)),
                            (int) (Math.random() * (tailleLoft - 1))));
                } else
                {
                    x -= Saison.proportionCoca;
                    if (x < Saison.proportionBiere)
                    {
                        this.add(new Biere(
                                (int) (Math.random() * (tailleLoft - 1)),
                                (int) (Math.random() * (tailleLoft - 1))));
                    }
                }
            }
        }
    }

    public Neuneu creerNouveauNeuneu()
    {
        double x = Math.random();

        Neuneu neuneu = null;
        if (x < Saison.proportionVorace)
        {
            neuneu = new Vorace(this, (int) (Math.random() * (tailleLoft - 1)),
                    (int) (Math.random() * (tailleLoft - 1)));
        } else
        {
            x -= Saison.proportionVorace;
            if (x < Saison.proportionErratique)
            {
                neuneu = new Erratique(this, (int) (Math.random() * (tailleLoft - 1)),
                        (int) (Math.random() * (tailleLoft - 1)));
            } else
            {
                x -= Saison.proportionErratique;
                if (x < Saison.proportionCannibale)
                {
                    neuneu = new Cannibale(this, (int) (Math.random() * (tailleLoft - 1)),
                            (int) (Math.random() * (tailleLoft - 1)));
                } else
                {
                    x -= Saison.proportionCannibale;
                    if (x < Saison.proportionLapin)
                    {
                        neuneu = new Lapin(this, (int) (Math.random() * (tailleLoft - 1)),
                                (int) (Math.random() * (tailleLoft - 1)));
                    }
                }
            }
        }
        return neuneu;
    }

    public void add(ObjetDessinable objet)
    {
        listeObjets.add(objet);
        loftPanel.updateListeObjets(listeObjets);
    }

    private int nombreNeuneusRestants()
    {
        int nbrNeuneus = 0;
        for (ObjetDessinable objet : listeObjets)
        {
            if (objet instanceof Neuneu)
            {
                nbrNeuneus++;
            }
        }
        return nbrNeuneus;
    }

    public int getTailleLoft()
    {
        return this.tailleLoft;
    }

    public void detruireObjet(ObjetDessinable objetADetruire)
    {
        listeObjetsDetruits.add(objetADetruire);
        loftPanel.removeObjet(objetADetruire);
        loftPanel.repaint();
    }

    public LinkedList<ObjetDessinable> getListeObjets()
    {
        return this.listeObjets;
    }

    public void arreter()
    {
        finSaison = true;
        loftPanel.nettoyer();
    }

    @Override
    public void run()
    {
        afficherEvenement("Bienvenue à tous !", Color.RED);
        afficherEvenement(
                "Nous sommes heureux de vous présenter la saison 1 de Secrets Neuneus !\n",
                Color.RED, true);

        heures = 0;
        while (nombreNeuneusRestants() > 0 && !finSaison)
        {
            zone.setTime(heures);
            long debut = System.currentTimeMillis();
            for (ObjetDessinable objet : listeObjets)
            {
                if (!listeObjetsDetruits.contains(objet)
                        && objet instanceof Neuneu)
                {
                    Neuneu neuneu = (Neuneu) objet;

                    neuneu.setEnergie(neuneu.getEnergie() - 1);
                    boolean mort = neuneu.mourir();
                    loftPanel.repaint();
                    if (!mort)
                    {
                        neuneu.seDeplacer();
                        loftPanel.repaint();
                        neuneu.manger();
                        loftPanel.repaint();
                        neuneu.seReproduire();
                        loftPanel.repaint();
                        neuneu.mourir();
                        loftPanel.repaint();
                    } else
                    {
                        afficherEvenementDuree(
                                neuneu.getNom() + " est mort...", Color.RED,
                                false);
                    }
                }
            }
            for (ObjetDessinable objetDetruit : listeObjetsDetruits)
            {
                listeObjets.remove(objetDetruit);
            }
            listeObjetsDetruits.clear();
            for (ObjetDessinable objetCree : listeObjetsCrees)
            {
                listeObjets.add(objetCree);
            }
            listeObjetsCrees.clear();
            
            long duree = System.currentTimeMillis() - debut;
            duree = (WAITING_TIME - duree < 0) ? 0 : WAITING_TIME - duree;
            try
            {
                Loft.sleep(duree);
            } catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            heures++;
        }

        afficherEvenement("Fin de la saison 1 !", Color.RED);
        afficherEvenement("durée : " + heures / 24 + " jours et " + heures % 24
                + "h\n", Color.RED);
        afficherEvenement(
                "Bientôt la saison 2 : plus d'action, de suspens et d'émotion !!!\n",
                Color.RED);
    }

    public String getDuree()
    {
        return new String("Jour " + heures / 24 + " : " + heures % 24 + "h");
    }

    public void afficherEvenementDuree(String evenement)
    {
        zone.afficherEvenement(getDuree(), evenement, Color.BLACK, false);
    }

    public void afficherEvenementDuree(String evenement, Color couleur,
            boolean sauterLigne)
    {
        zone.afficherEvenement(getDuree(), evenement, couleur, sauterLigne);
    }

    public void afficherEvenement(String evenement)
    {
        zone.afficherEvenement(evenement);
    }

    public void afficherEvenement(String evenement, Color couleur)
    {
        zone.afficherEvenement(evenement, couleur);
    }

    public void afficherEvenement(String evenement, Color couleur,
            boolean sauterLigne)
    {
        zone.afficherEvenement(evenement, couleur, sauterLigne);
    }

    public void addBebeNeuneu(Neuneu neuneucree)
    {
        this.listeObjetsCrees.add(neuneucree);
        this.loftPanel.addBebeNeuneu(neuneucree);
    }
}
