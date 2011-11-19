package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

/**
 * un panneau de dessin pour le loft
 * 
 * @author moreau
 *
 */
class LoftPanel extends JPanel {
	/**
	 * référence sur la liste des objets à dessiner
	 */
	private LinkedList<ObjetDessinable> listeObjets;
	
	/**
	 * constructeur
	 * 
	 * @param listeObjets r�f�rence sur la liste des objets (g�r�e par la ZoneGraphique)
	 */
	public LoftPanel(LinkedList<ObjetDessinable> listeObjets) {
		this.listeObjets = new LinkedList<ObjetDessinable>(listeObjets);
	}
	
	/**
	 * on red�finit la méthode paint() : elle se contente d'appeler les méthodes
	 * dessinerObjet() de la liste d'objets dessinables
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// on redessine tout
		for (ObjetDessinable x : listeObjets) {
			x.dessinerObjet(g);
		}
	}
	
	public void updateListeObjets(LinkedList<ObjetDessinable> listeObjets) {
		this.listeObjets = new LinkedList<ObjetDessinable>(listeObjets);
		this.revalidate();
		this.repaint();
	}

    public void removeObjet(ObjetDessinable objetDetruit)
    {
        this.listeObjets.remove(objetDetruit);
    }
}
