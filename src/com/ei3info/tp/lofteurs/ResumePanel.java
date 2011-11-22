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
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class ResumePanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int         nombreCommentaires = 0;
    private JPanel      resumeContenuPanel;
    private JScrollPane jsp;

    public ResumePanel()
    {
        creerResumePanel();
    }
    
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

    public void
            afficherEvenement(String evenement, Color couleur, boolean sauterLigne, int positionX, boolean finLigne)
    {
        JLabel lEvenement = new JLabel(evenement);
        lEvenement.setForeground(couleur);

        int verticalInset = (sauterLigne) ? 15 : 2;
        Insets ligneInsets = new Insets(2, 2, verticalInset, 5);
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

    public void clear()
    {
        resumeContenuPanel.removeAll();
    }
}
