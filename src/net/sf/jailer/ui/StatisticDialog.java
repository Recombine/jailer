/*
 * StatisticDialog.java
 *
 * Created on 28. April 2008, 10:20
 */

package net.sf.jailer.ui;

import java.awt.Dimension;

import javax.swing.JLabel;


/**
 * Shows export statistic after exportation.
 * 
 * @author Ralf Wisser
 */
public class StatisticDialog extends javax.swing.JDialog {
    
	private int nextY = 0;
	
    /** Creates new form StatisticDialog */
    public StatisticDialog(java.awt.Frame parent, String content) {
        super(parent, true);
        initComponents();
        for (String line: ("\n" + content).split("\n")) {
        	addLine(line);
        }
        Dimension ps = contentPane.getPreferredSize();
        if (ps == null) {
        	ps = new Dimension(100, 100);
        }
        int w = 250, h = Math.min(Math.max(70 + ps.height, 100), 300);
        setSize(w, h);
        setLocation(Math.max(0, parent.getX() + parent.getWidth() / 2 - w / 2),
                    Math.max(0, parent.getY() + parent.getHeight() / 2 - h / 2));
        invalidate();
        setVisible(true);
    }

    /**
     * Adds line to content pane.
     * 
     * @param line the line
     */
    private void addLine(String line) {
    	String splits[] = line.split(": *");

    	JLabel label1 = new JLabel();
    	label1.setFont(new java.awt.Font("Dialog", 0, 12));
	    label1.setText("  " + splits[0] + (splits.length > 1? ":     " : " "));
	    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = nextY;
	    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        if (splits.length == 1) {
	    	gridBagConstraints.gridwidth = 2;
	    }
	    contentPane.add(label1, gridBagConstraints);
	
	    if (splits.length > 1) {
	    	JLabel label2 = new JLabel();
	    	label2.setText(splits[1]);
		    gridBagConstraints = new java.awt.GridBagConstraints();
		    gridBagConstraints.gridx = 1;
		    gridBagConstraints.gridy = nextY;
		    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		    gridBagConstraints.weightx = 1.0;
		    contentPane.add(label2, gridBagConstraints);
	    }
	    
	    nextY++;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Erzeugter Quelltext ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS));

        setTitle("Export Statistic");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(null);
        contentPane.setLayout(new java.awt.GridBagLayout());

        contentPane.setMinimumSize(new java.awt.Dimension(300, 300));
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel1.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 256;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        contentPane.add(jLabel1, gridBagConstraints);

        jScrollPane1.setViewportView(contentPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jButton1, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    // Variablendeklaration - nicht modifizieren//GEN-BEGIN:variables
    private javax.swing.JPanel contentPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // Ende der Variablendeklaration//GEN-END:variables
    
}
