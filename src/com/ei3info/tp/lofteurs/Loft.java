/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Dimension;
import java.util.LinkedList;

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
    private static final int            WAITING_TIME = 50;
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
        
        System.out.println("Bienvenue à tous !");
        System.out
        .println("Nous sommes heureux de vous présenter la saison 1 de Secrets Neuneus !\n");
        
        int heures = 0;
        while (nombreNeuneusRestants() > 0 && !finSaison)
        {
            System.out.println("\n>>> Jour " + heures / 24 + " : " + heures % 24 + "h\n");
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
                        System.err.println(neuneu.getNom() + " est mort...");
                    }
                    try
                    {
                        Thread.sleep(WAITING_TIME);
                    } catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            for (ObjetDessinable objetDetruit : listeObjetsDetruits)
            {
                listeObjets.remove(objetDetruit);
            }
            listeObjetsDetruits.clear();
            
            heures++;
        }
        
        System.out.println("Fin de la saison 1 !");
        System.out.println("durée : " +  heures / 24 + " jours et " + heures % 24 + "h\n");
        System.out
        .println("Bientôt la saison 2 : plus d'action, de suspens et d'émotion !!!");
    }
}
