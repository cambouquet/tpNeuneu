/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Panneau affichant les commentaires sur la saison en cours.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class ResumePanel extends JPanel
{
    /**
     * Generated default serial UID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Le nombre de commentaires actuellement affichés.
     */
    private int         nombreCommentaires = 0;
    
    /**
     * Le panneau qui contient effectivement les commentaires.
     */
    private JPanel      resumeContenuPanel;
    
    /**
     * Le scroll pane qui contient le panneau avec les commentaires.
     */
    private JScrollPane jsp;

    /**
     * Constructeur.
     */
    public ResumePanel()
    {
        creerResumePanel();
    }
    
    /**
     * Créé le panneau.
     */
    private void creerResumePanel()
    {
        JLabel titreResumePanel = new JLabel("Résumé des événements");

        resumeContenuPanel = new JPanel();
        jsp = new JScrollPane(resumeContenuPanel);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setPreferredSize(new Dimension(350, 400));
        resumeContenuPanel.setLayout(new GridBagLayout());

        this.setLayout(new BorderLayout());
        this.add(titreResumePanel, BorderLayout.NORTH);
        this.add(jsp, BorderLayout.CENTER);
    }

    /**
     * Affiche un nouvelle événement à l'écran.
     * 
     * @param evenement
     *          Le texte à afficher.
     * @param couleur
     *          La couleur du texte à afficher.
     * @param sauterLigne
     *          Indique si l'on doit sauter une ligne après le texte.
     * @param positionX
     *          Donne la position à afficher dans la ligne.
     * @param finLigne
     *          Indique si le message est le dernier messsage de la ligne.
     */
    public void
            afficherEvenement(String evenement, Color couleur, boolean sauterLigne, int positionX, boolean finLigne)
    {
        JLabel lEvenement = new JLabel(evenement);
        lEvenement.setForeground(couleur);

        // Saut de ligne
        int verticalInset = (sauterLigne) ? 15 : 2;
        Insets ligneInsets = new Insets(2, 2, verticalInset, 5);
        
        // Fin de la ligne
        int longueur = (finLigne) ? GridBagConstraints.REMAINDER : 1;

        resumeContenuPanel.add(lEvenement, new GridBagConstraints(positionX, nombreCommentaires, longueur, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL, ligneInsets, 0, 0));
        System.out.println(evenement);
        if (finLigne)
        {
            nombreCommentaires++;
        }

        jsp.validate();
        jsp.repaint();
        jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
    }

    /**
     * Efface tous les commentaires.
     */
    public void clear()
    {
        resumeContenuPanel.removeAll();
    }
}
