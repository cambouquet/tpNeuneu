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
 * une classe comportant une zone graphique dans laquelle on peut dessiner ; le
 * dessin est refait automatiquement par la classe Panel associée ; tous les
 * objets de type ObjetDessinable ajoutés à la liste sont redessinés par un
 * appel à leur méthode dessinerObjet(Graphics g)
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
    private static final long   serialVersionUID     = -4869859338320586441L;
    private JPanel              actionPanel;
    private JPanel              parametresPanel;
    private JPanel              resumePanel;
    private JPanel              resumeContenuPanel;
    private JScrollPane         jsp;
    private JPanel              footerPanel;
    private Saison              saison;
    private JLabel              lDuree               = new JLabel();
    private int                 nombreCommentaires   = 0;
    private int                 nbrParametres        = 0;

    private JFormattedTextField tfVitesse            = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfEnergMaxNeuneus    = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfEnergMaxNourriture = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfEnergReprod        = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfPNourriture        = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfNbrNeuneus         = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfPPizza             = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfPCoca              = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfPBiere             = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfErratiques         = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfVoraces            = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfCannibales         = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfLapins             = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * constructeur
     * @param titre
     *            le nom de l'application
     */
    public ZoneGraphique(String titre, Saison saison)
    {
        // appel au constructeur de base
        super(titre);
        this.saison = saison;
        // ajout d'une taille par défaut
        setSize(800, 800);

        // ajout d'un listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        new JPanel();
        actionPanel = creerActionPanel();
        parametresPanel = creerParametresPanel();
        resumePanel = creerResumePanel();
        footerPanel = creerFooterPanel();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(actionPanel, BorderLayout.SOUTH);
        this.getContentPane().add(parametresPanel, BorderLayout.EAST);
        this.getContentPane().add(resumePanel, BorderLayout.WEST);
        this.getContentPane().add(footerPanel, BorderLayout.NORTH);
        pack();
        setVisible(true);
        this.setResizable(false);
    }

    private JPanel creerActionPanel()
    {
        JPanel panel = new JPanel();
        lDuree = new JLabel("Non commencé");
        lDuree.setFont(new Font("consolas", Font.BOLD, 20));

        panel.add(lDuree);
        return panel;
    }

    private JPanel creerFooterPanel()
    {
        JPanel panel = new JPanel();
        JLabel text = new JLabel("a B & S™ production");
        text.setFont(new Font(null, Font.ITALIC, 12));

        FlowLayout fl = new FlowLayout(FlowLayout.RIGHT);
        panel.setLayout(fl);
        panel.add(text);
        return panel;
    }

    private JPanel creerResumePanel()
    {
        JPanel panel = new JPanel();
        JLabel titreResumePanel = new JLabel("Résumé des événements");

        resumeContenuPanel = new JPanel();
        jsp = new JScrollPane(resumeContenuPanel);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setPreferredSize(new Dimension(300, 400));
        resumeContenuPanel.setLayout(new GridBagLayout());

        panel.setLayout(new BorderLayout());
        panel.add(titreResumePanel, BorderLayout.NORTH);
        panel.add(jsp, BorderLayout.CENTER);
        return panel;
    }

    private JPanel creerParametresPanel()
    {
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());

        // Vitesse
        ajouterParametre(panel, tfVitesse, "Vitesse");
        tfVitesse.setText(String.valueOf((int) (Saison.WAITING_TIME)));

        // quantités d'éléments
        JFormattedTextField[] textFieldsQuantite = {tfNbrNeuneus, tfPNourriture};
        String[] titresQuantite = {"Nombre de neuneus", "% Nourriture"};
        ajouterParametre(panel, textFieldsQuantite, titresQuantite);

        tfNbrNeuneus.setText(String.valueOf((int) (Saison.nombreLofteurs)));
        tfPNourriture.setText(String.valueOf(Saison.quantiteNourriture));

        // energies
        JFormattedTextField[] textFieldsEnergie = {tfEnergMaxNeuneus, tfEnergMaxNourriture, tfEnergReprod};
        String[] titresEnergie = {"Energie max. neuneus", "Energie max. nourriture", "Energie reproduction"};
        ajouterParametre(panel, textFieldsEnergie, titresEnergie);

        tfEnergMaxNeuneus.setText(String.valueOf((int) (Neuneu.ENERGIE_MAX)));
        tfEnergMaxNourriture.setText(String.valueOf((int) (Nourriture.ENERGIE_MAX)));
        tfEnergReprod.setText(String.valueOf((int) (Neuneu.ENERGIE_REPRODUCTION)));

        // proportions de nourriture
        JFormattedTextField[] textFieldsNourriture = {tfPPizza, tfPCoca, tfPBiere};
        String[] titresNourriture = {"% Pizza", "% Coca", "% Biere"};
        ajouterParametre(panel, textFieldsNourriture, titresNourriture);

        tfPPizza.setText(String.valueOf((int) (Saison.proportionPizza * 100)));
        tfPCoca.setText(String.valueOf((int) (Saison.proportionCoca * 100)));
        tfPBiere.setText(String.valueOf((int) (Saison.proportionBiere * 100)));

        // proportions de neuneus
        JFormattedTextField[] textFieldsNeuneus = {tfErratiques, tfVoraces, tfCannibales, tfLapins};
        String[] titresNeuneus = {"% Erratiques", "% Voraces", "% Cannibales", "% Lapins"};
        ajouterParametre(panel, textFieldsNeuneus, titresNeuneus);

        tfErratiques.setText(String.valueOf((int) (Saison.proportionErratique * 100)));
        tfVoraces.setText(String.valueOf((int) (Saison.proportionVorace * 100)));
        tfCannibales.setText(String.valueOf((int) (Saison.proportionCannibale * 100)));
        tfLapins.setText(String.valueOf((int) (Saison.proportionLapin * 100)));

        // Bouton relancer la saison
        JButton bRelancer = new JButton("Relancer");
        bRelancer.addActionListener(new RelancerAction());

        // Ajout des différents éléments au panneau de paramètres

        panel.add(bRelancer, new GridBagConstraints(0, nbrParametres, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 10, 5, 20), 0, 0));
        return panel;
    }

    private void ajouterParametre(JPanel panel, JFormattedTextField[] textFields, String[] titres)
    {
        for (int i = 0; i < textFields.length; i++)
        {
            textFields[i].setColumns(5);
            int insetBas = (i == textFields.length - 1) ? 20 : 5;
            String titre = (titres.length > i) ? titres[i] : "Paramètre";

            panel.add(new JLabel(titre), new GridBagConstraints(0, nbrParametres, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, insetBas, 10), 0, 0));
            panel.add(textFields[i], new GridBagConstraints(1, nbrParametres, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                    GridBagConstraints.NONE, new Insets(5, 10, insetBas, 10), 0, 0));
            nbrParametres++;
        }
    }

    private void ajouterParametre(JPanel panel, JFormattedTextField textField, String titre)
    {
        JFormattedTextField[] textFields = {textField};
        String[] titres = {titre};
        ajouterParametre(panel, textFields, titres);
    }

    public void setLoftPanel(JPanel loftPanel)
    {
        if (loftPanel != null)
        {
            this.getContentPane().remove(loftPanel);
        }

        this.getContentPane().add(loftPanel, BorderLayout.CENTER);
        loftPanel.repaint();
        this.getContentPane().validate();
        this.getContentPane().repaint();
        this.validate();
        this.repaint();
        pack();
    }

    /**
     * largeur de la partie dessinable
     */
    public int getWidth()
    {
        return getContentPane().getWidth();
    }

    /**
     * hauteur de la partie dessinable
     */
    public int getHeight()
    {
        return getContentPane().getHeight();
    }

    public void setTime(String duree)
    {
        this.lDuree.setText(duree);
    }

    public void afficherEvenement(String evenement)
    {
        afficherEvenement(evenement, Color.BLACK);
    }

    public void afficherEvenement(String evenement, Color couleur)
    {
        afficherEvenement(evenement, couleur, false);
    }

    public void afficherEvenement(String evenement, Color couleur, boolean sauterLigne)
    {
        afficherEvenement(evenement, couleur, sauterLigne, 0, true);
    }

    public void afficherEvenement(String duree, String evenement, Color couleur, boolean sauterLigne)
    {
        afficherEvenement(duree, new Color(100, 100, 100), false, 0, false);
        afficherEvenement(evenement, couleur, sauterLigne, 1, true);
    }

    private void
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

    private class RelancerAction implements ActionListener
    {

        private boolean verifierPourcentageNeuneus(JFormattedTextField[] textFields, String messageErreur)
        {
            boolean pourcentageOk = true;
            int total = 0;

            for (int i = 0; i < textFields.length; i++)
            {
                total += new Integer(verifierValeurPourcentage(textFields[i]));
            }

            if (total != 100)
            {
                pourcentageOk = false;
                JOptionPane.showMessageDialog(ZoneGraphique.this, messageErreur, "Pourcentages incorrects",
                        JOptionPane.ERROR_MESSAGE);
            }

            return pourcentageOk;
        }

        private String verifierValeurPourcentage(JFormattedTextField textFields)
        {
            Integer valeur = new Integer(textFields.getText());
            if (valeur > 100)
            {
                textFields.setText("100");
            }

            verifierValeurPositive(textFields);

            return textFields.getText();
        }

        private String verifierValeurPositive(JFormattedTextField textFields)
        {
            Integer valeur = new Integer(textFields.getText());

            if (valeur < 0)
            {
                textFields.setText("0");
            }

            return textFields.getText();
        }

        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            new JPanel();

            // Vérification de la vitesse
            Integer vitesse = new Integer(tfVitesse.getText());
            if (vitesse <= 0)
            {
                tfVitesse.setText("1");
                vitesse = 1;
            }

            Saison.WAITING_TIME = vitesse;

            // Vérification du nombre de neuneus
            Saison.nombreLofteurs = new Integer(verifierValeurPourcentage(tfNbrNeuneus));

            // Vérification des pourcentages des neuneus
            JFormattedTextField[] tfsPNeuneus = {tfErratiques, tfVoraces, tfCannibales, tfLapins};
            boolean pNeuneus = verifierPourcentageNeuneus(tfsPNeuneus,
                    "Le total des pourcentages des proportions de neuneus doit être 100");

            if (pNeuneus)
            {
                Saison.proportionErratique = (new Float(tfErratiques.getText())) / 100;
                Saison.proportionVorace = (new Float(tfVoraces.getText())) / 100;
                Saison.proportionCannibale = (new Float(tfCannibales.getText())) / 100;
                Saison.proportionLapin = (new Float(tfLapins.getText())) / 100;
            }

            // Vérficaiton des pourcentages de nourritures
            Saison.quantiteNourriture = new Integer(verifierValeurPositive(tfPNourriture));

            JFormattedTextField[] tfsPNourriture = {tfPPizza, tfPCoca, tfPBiere};
            boolean pNourriture = verifierPourcentageNeuneus(tfsPNourriture,
                    "Le total des pourcentages des proportions de nourriture doit être 100");

            if (pNourriture)
            {
                Saison.proportionPizza = (new Float(tfPPizza.getText())) / 100;
                Saison.proportionCoca = (new Float(tfPCoca.getText())) / 100;
                Saison.proportionBiere = (new Float(tfPBiere.getText())) / 100;
            }
            
            // Vérification des énergies
            Neuneu.ENERGIE_MAX = new Integer(verifierValeurPourcentage(tfEnergMaxNeuneus));
            Nourriture.ENERGIE_MAX = new Integer(verifierValeurPourcentage(tfEnergMaxNourriture));
            Neuneu.ENERGIE_REPRODUCTION = new Integer(verifierValeurPourcentage(tfEnergReprod));

            resumeContenuPanel.removeAll();
            saison.redemarrerSaison();
        }

    }
}
