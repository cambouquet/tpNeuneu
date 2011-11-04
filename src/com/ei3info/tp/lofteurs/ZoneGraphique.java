package com.ei3info.tp.lofteurs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * une classe comportant une zone graphique dans laquelle on peut dessiner ;
 * le dessin est refait automatiquement par la classe Panel associ�e ; tous
 * les objets de type ObjetDessinable ajout�s � la liste sont redessin�s par 
 * un appel � leur m�thode dessinerObjet(Graphics g)
 * 
 * @see ObjectDessinable,LoftPanel
 * @author moreau
 *
 */
public class ZoneGraphique extends JFrame {

	private JPanel loftPanel;
	
	/**
	 * constructeur
	 *
	 * @param titre le nom de l'application
	 */
	public ZoneGraphique(String titre)  {
		// appel au constructeur de base
		super(titre);
		
		// ajout d'une taille par d�faut
		setSize(600,600);
		
		// ajout d'un listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0) ;
			}
	    	} ) ;

		loftPanel = new JPanel();
		
		setVisible(true);
	}
	
	public void setLoftPanel(JPanel loftPanel)
	{
		this.loftPanel = loftPanel;
		this.remove(loftPanel);
		this.add(loftPanel);
		loftPanel.repaint();
		pack();
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
	
	/**
	 * largeur de la partie dessinable
	 */
	public int getWidth() {
		return getContentPane().getWidth();
	}
	
	/**
	 * hauteur de la partie dessinable
	 */
	public int getHeight() {
		return getContentPane().getHeight();
	}

}
