package com.ei3info.tp.lofteurs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * La fenetre graphique de l'application. Elle contient le panneau du loft, un
 * panneau pour modifier les paramètres et relancer la saison et un panneau avec
 * les événements.
 * @see ObjectDessinable,LoftPanel
 * @author moreau
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class ZoneGraphique extends JFrame
{

    /**
     * Generated serial UID
     */
    private static final long serialVersionUID = -4869859338320586441L;

    /**
     * Panneau contenant les différents paramètres configurables et le bouton
     * pour relancer une nouvelle saison.
     */
    private ParametresPanel   parametresPanel;

    /**
     * Panneau contenant le résumé des différents événements ayant eu lieu dans
     * le loft.
     */
    private ResumePanel       resumePanel;

    /**
     * La saison actuellement en cours ou à redémarrer.
     */
    private Saison            saison;

    /**
     * La durée écoulée depuis le début de la saison.
     */
    private JLabel            lDuree           = new JLabel();

    /**
     * constructeur
     * @param titre
     *            le nom de l'application
     * @param saison
     *            La saison actuelle
     */
    public ZoneGraphique(String titre, Saison saison)
    {
        // appel au constructeur de base
        super(titre);
        this.saison = saison;
        // ajout d'une taille par défaut
        setSize(800, 800);

        // Ajout des différents panneaux
        parametresPanel = new ParametresPanel(this);
        resumePanel = new ResumePanel();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(creerDureePanel(), BorderLayout.SOUTH);
        this.getContentPane().add(parametresPanel, BorderLayout.EAST);
        this.getContentPane().add(resumePanel, BorderLayout.WEST);
        this.getContentPane().add(creerNomPanel(), BorderLayout.NORTH);

        // Affichage
        pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        setVisible(true);
    }

    /**
     * Création du panneau affichant la durée.
     * @return Le panneau affichant la durée.
     */
    private JPanel creerDureePanel()
    {
        JPanel panel = new JPanel();
        lDuree = new JLabel("Non commencé");
        lDuree.setFont(new Font("consolas", Font.BOLD, 20));

        panel.add(lDuree);
        return panel;
    }

    /**
     * Création du panneau contenant les infos de production.
     * @return Le panneau contenant les infos de production.
     */
    private JPanel creerNomPanel()
    {
        JPanel panel = new JPanel();
        JLabel text = new JLabel("a B & S™ production");
        text.setFont(new Font(null, Font.ITALIC, 12));

        FlowLayout fl = new FlowLayout(FlowLayout.RIGHT);
        panel.setLayout(fl);
        panel.add(text);
        return panel;
    }

    /**
     * Remplace le panneau contenant l'affichage du loft par le nouveau fourni.
     * @param loftPanel
     *            Le panneau contenant l'affichage du loft.
     */
    public void setLoftPanel(JPanel loftPanel)
    {
        if (loftPanel != null)
        {
            this.getContentPane().remove(loftPanel);
        }

        this.getContentPane().add(loftPanel, BorderLayout.CENTER);
        pack();
    }

    /**
     * Modifie la durée à afficher.
     * @param duree
     *            La durée à afficher.
     */
    public void setDuree(String duree)
    {
        this.lDuree.setText(duree);
    }

    /**
     * Affiche un nouvel événement avec la couleur noire par défaut sans sauter
     * de lignes.
     * @param evenement
     *            L'événement à afficher.
     */
    public void afficherEvenement(String evenement)
    {
        afficherEvenement(evenement, Color.BLACK);
    }

    /**
     * Affiche un nouvel événement avec la couleur spécifiée sans sauter de
     * lignes.
     * @param evenement
     *            L'événement à afficher.
     * @param couleur
     */
    public void afficherEvenement(String evenement, Color couleur)
    {
        afficherEvenement(evenement, couleur, false);
    }

    /**
     * Affiche un nouvel événement avec la couleur spécifiée en sautant ou non
     * une ligne après.
     * @param evenement
     *            L'événement à afficher.
     * @param couleur
     *            La couleur du texte à afficher.
     * @param sauterLigne
     *            true - Une ligne est sautée après le texte\nfalse Aucune ligne
     *            n'est sautée après le texte.
     */
    public void afficherEvenement(String evenement, Color couleur, boolean sauterLigne)
    {
        resumePanel.afficherEvenement(evenement, couleur, sauterLigne, 0, true);
    }

    /**
     * Affiche un nouvel événement avec la couleur spécifiée en sautant ou non
     * une ligne après, tout en indiquant avant le moment auquel il a eu lieu.
     * @param duree
     *            La duree à afficher.
     * @param evenement
     *            L'événement à afficher.
     * @param couleur
     *            La couleur du texte à afficher.
     * @param sauterLigne
     *            true - Une ligne est sautée après le texte\nfalse Aucune ligne
     *            n'est sautée après le texte.
     */
    public void afficherEvenement(String duree, String evenement, Color couleur, boolean sauterLigne)
    {
        resumePanel.afficherEvenement(duree, new Color(100, 100, 100), false, 0, false);
        resumePanel.afficherEvenement(evenement, couleur, sauterLigne, 1, true);
    }

    /**
     * Lancer une nouvelle saison.
     */
    public void relancerSaison()
    {
        resumePanel.clear();
        saison.redemarrerSaison();
    }
}
