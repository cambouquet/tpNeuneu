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
    private LoftPanel                   loftPanel;
    private static final int            WAITING_TIME = 250;
    private boolean                     finSaison    = false;

    public Loft(int tailleLoft, ZoneGraphique zone)
    {
        this.tailleLoft = tailleLoft;
        this.zone = zone;
        this.listeObjets = new LinkedList<ObjetDessinable>();
        this.listeObjetsDetruits = new LinkedList<ObjetDessinable>();
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
            this.add(new Pizza((int) (Math.random() * (tailleLoft - 1)),
                    (int) (Math.random() * (tailleLoft - 1))));
        }
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
        afficherEvenement("Nous sommes heureux de vous présenter la saison 1 de Secrets Neuneus !\n", Color.RED);
        
        int heures = 0;
        while (nombreNeuneusRestants() > 0 && !finSaison)
        {
            afficherEvenement("\nJour " + heures / 24 + " : " + heures % 24 + "h\n", new Color(100, 100, 100));
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
                        afficherEvenement(neuneu.getNom() + " est mort...", Color.RED);
                    }
                }
            }
            for (ObjetDessinable objetDetruit : listeObjetsDetruits)
            {
                listeObjets.remove(objetDetruit);
            }
            listeObjetsDetruits.clear();

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
        afficherEvenement("durée : " +  heures / 24 + " jours et " + heures % 24 + "h\n", Color.RED);
        afficherEvenement("Bientôt la saison 2 : plus d'action, de suspens et d'émotion !!!\n", Color.RED);
    }
    
    public void afficherEvenement(String evenement)
    {
        zone.afficherEvenement(evenement);
    }

    public void afficherEvenement(String evenement, Color couleur)
    {
        zone.afficherEvenement(evenement, couleur);
    }
}
