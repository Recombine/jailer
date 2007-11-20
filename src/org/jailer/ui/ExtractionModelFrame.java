/*
 * ExtractionModelFrame.java
 *
 * Created on 18. November 2007, 16:26
 */

package org.jailer.ui;

import java.io.File;

import org.jailer.extractionmodel.ExtractionModel;

/**
 * Main frame of Restriction-Model-Editor.
 * 
 * @author Wisser
 */
public class ExtractionModelFrame extends javax.swing.JFrame {
    
    /**
     *  Creates new form ExtractionModelFrame.
     *  
     *  @param extractionModelFile file containing the model
     */
    public ExtractionModelFrame(String extractionModelFile) {
        initComponents();
        editorPanel.add(new ExtractionModelEditor(extractionModelFile), "editor");
        pack();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        editorPanel = new javax.swing.JPanel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extraction Model Editor");
        editorPanel.setLayout(new java.awt.CardLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(editorPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExtractionModelFrame extractionModelFrame = new ExtractionModelFrame("extractionmodel/scott-without-subordinates.csv");
                extractionModelFrame.setLocation(40, 40);
                extractionModelFrame.setSize(960, 660);
                extractionModelFrame.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel editorPanel;
    // End of variables declaration//GEN-END:variables
    
}