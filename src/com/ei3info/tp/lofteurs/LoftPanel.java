package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

/**
 * un panneau de dessin pour le loft
 * @author moreau
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
class LoftPanel extends JPanel
{
    /**
     * Generated default serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * référence sur la liste des objets à dessiner
     */
    private LinkedList<ObjetDessinable> listeObjets;

    /**
     * référence sur la liste des objets détruits pendant la réactualisation
     */
    private LinkedList<ObjetDessinable> listeObjetsDetruits;

    /**
     * référence sur la liste des objets détruits pendant la réactualisation
     */
    private LinkedList<ObjetDessinable> listeObjetsCrees;

    /**
     * constructeur
     * @param listeObjets
     *            référence sur la liste des objets (gérée par la ZoneGraphique)
     */
    public LoftPanel(LinkedList<ObjetDessinable> listeObjets)
    {
        this.listeObjets = new LinkedList<ObjetDessinable>(listeObjets);
        this.listeObjetsDetruits = new LinkedList<ObjetDessinable>();
        this.listeObjetsCrees = new LinkedList<ObjetDessinable>();
    }

    /**
     * on redéfinit la méthode paint() : elle se contente d'appeler les méthodes
     * dessinerObjet() de la liste d'objets dessinables
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponents(g);

        // On efface ce qu'il y avait
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Saison.tailleLoft * ObjetPositionnable.tailleX, Saison.tailleLoft * ObjetPositionnable.tailleX);
        
        // on redessine tout
        for (ObjetDessinable x : listeObjets)
        {
            // Sauf si l'objet est dans la liste des objets détruits
            if (!listeObjetsDetruits.contains(x))
            {
                x.dessinerObjet(g);
            }
        }
        
        // On met à jour la liste des objets en enlevant les objets détruits
        for (ObjetDessinable obj : listeObjetsDetruits)
        {
            listeObjets.remove(obj);
        }
        listeObjetsDetruits.clear();

        // On met à jour la liste des objets en ajoutant les objets créés
        for (ObjetDessinable obj : listeObjetsCrees)
        {
            obj.dessinerObjet(g);
            listeObjets.add(obj);
        }
        listeObjetsCrees.clear();
    }

    /**
     * Mets à jour la liste d'objet. Ne devrait pas être utilisée quand la saison a commencé.
     * @param listeObjets
     *          La nouvelle liste d'objets
     */
    public void updateListeObjets(LinkedList<ObjetDessinable> listeObjets)
    {
        this.listeObjets = new LinkedList<ObjetDessinable>(listeObjets);
        this.revalidate();
        this.repaint();
    }

    /**
     * Ajoute un objet à la liste des objets détruits qui permettra de mettre à jour la liste des objets acutels à la fin du repaint().
     * @param objetDetruit
     *              L'objet détruit.
     */
    public void removeObjet(ObjetDessinable objetDetruit)
    {
        this.listeObjetsDetruits.add(objetDetruit);
    }

    /**
     * Nettoyer le loft : efface la liste des objets à dessiner.
     */
    public void nettoyer()
    {
        listeObjets.clear();
        listeObjetsDetruits.clear();
        listeObjetsCrees.clear();
    }

    /**
     * Ajoute un objet à la liste des objets créés qui permettra de mettre à jour la liste des objets acutels à la fin du repaint().
     * @param neuneucree
     *          L'objet créé.
     */
    public void addBebeNeuneu(Neuneu neuneucree)
    {
        this.listeObjetsCrees.add(neuneucree);
    }
}
