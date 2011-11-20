package com.ei3info.tp.lofteurs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * une classe comportant une zone graphique dans laquelle on peut dessiner ;
 * le dessin est refait automatiquement par la classe Panel associée ; tous
 * les objets de type ObjetDessinable ajoutés à la liste sont redessinés par 
 * un appel à leur méthode dessinerObjet(Graphics g)
 * 
 * @see ObjectDessinable,LoftPanel
 * @author moreau
 *
 */
public class ZoneGraphique extends JFrame {

	private JPanel loftPanel;
	private JPanel actionPanel;
	private Saison saison;
	
	/**
	 * constructeur
	 *
	 * @param titre le nom de l'application
	 */
	public ZoneGraphique(String titre, Saison saison)  {
		// appel au constructeur de base
		super(titre);
		this.saison = saison;
		// ajout d'une taille par défaut
		setSize(600,600);
		
		// ajout d'un listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0) ;
			}
	    	} ) ;

		loftPanel = new JPanel();
		actionPanel = creerActionPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(actionPanel, BorderLayout.NORTH);
		
		setVisible(true);
	}
	
	private JPanel creerActionPanel()
    {
        JPanel panel = new JPanel();
        JButton bRelancer = new JButton("Relancer");
        bRelancer.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                loftPanel = new JPanel();
//                ZoneGraphique.this.getContentPane().validate();
//                ZoneGraphique.this.getContentPane().repaint();
                saison.redemarrerSaison();
            }
            
        });
        panel.add(bRelancer);
        
        return panel;
    }

    public void setLoftPanel(JPanel loftPanel)
	{
        this.getContentPane().remove(loftPanel);
		this.loftPanel = loftPanel;
		this.getContentPane().add(loftPanel, BorderLayout.CENTER);
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
