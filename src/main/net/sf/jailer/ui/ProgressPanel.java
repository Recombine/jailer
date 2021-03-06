/*
 * Copyright 2007 - 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.jailer.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.sf.jailer.database.DMLTransformer;

/**
 * Progress panel.
 * 
 * @author Ralf Wisser
 */
public class ProgressPanel extends javax.swing.JPanel {
	private Font font = new JLabel("normal").getFont();
	private Font nonbold = new Font(font.getName(), font.getStyle() & ~Font.BOLD, font.getSize());
	private final ProgressTable progressTable;
	
    /** Creates new form ProgressPanel 
     * @param progressTable */
    public ProgressPanel(ProgressTable progressTable) {
    	this.progressTable = progressTable;
    	initComponents();
    	jLabel1.setForeground(jLabel1.getBackground());
        progressTableHolder.setViewportView(progressTable);
        stepLabel.setFont(nonbold);
        exportedRowsLabel.setFont(nonbold);
        collectedRowsLabel.setFont(nonbold);
        elapsedTimeLabel.setFont(nonbold);
        progressTableHolder.setColumnHeaderView(null);
    }
    
    public void updateRowsPerTable(Map<String, Long> rowsPerTable) {
    	rowsPerTablePanel.removeAll();
    	allMouseListener.clear();
    	int y = 0;
    	
    	GridBagConstraints gridBagConstraints;
    	for (String tableName: rowsPerTable.keySet()) {
    		Color bgColor;
    		if (y % 2 == 0) {
    			bgColor = new java.awt.Color(240, 255, 255);
    		} else {
    			bgColor = Color.WHITE;
            }
    		JLabel l = createLabel(y, tableName, bgColor);
    		l.setText(" " + tableName + " ");
            l.setOpaque(true);
            l.setFont(nonbold);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = y;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
//            gridBagConstraints.insets = new Insets(2, 0, 2, 0);
            rowsPerTablePanel.add(l, gridBagConstraints);

            l = new JLabel("" + rowsPerTable.get(tableName) + "  ");
            if (y % 2 == 0) {
    			l.setBackground(new java.awt.Color(240, 255, 255));
    		} else {
    			l.setBackground(Color.WHITE);
            }
            l.setOpaque(true);
            l.setFont(nonbold);
            l.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = y;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
//            gridBagConstraints.insets = new Insets(2, 0, 2, 0);
            rowsPerTablePanel.add(l, gridBagConstraints);
            
            ++y;
    	}
    	JLabel l = new JLabel("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = y;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        rowsPerTablePanel.add(l, gridBagConstraints);
    }

    private int currentlySelectedRow = -1;
    private final Color BGCOLOR_OF_SELECTED_ROW = Color.CYAN;
    private List<MouseListener> allMouseListener = new ArrayList<MouseListener>();
    
    private JLabel createLabel(final int y, final String tableName, Color bgColor) {
		final JLabel label = new JLabel();
		label.setBackground(bgColor);
		MouseListener l;
		label.addMouseListener(l = new MouseListener() {
			Color bgColor;
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (bgColor != null) {
					label.setBackground(bgColor);
				}
				if (currentlySelectedRow == y) {
					currentlySelectedRow = -1;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				for (MouseListener l: allMouseListener) {
					if (l != this) {
						l.mouseExited(e);
					}
				}
				if (bgColor == null) {
					bgColor = label.getBackground();
				}
				label.setBackground(BGCOLOR_OF_SELECTED_ROW);
				currentlySelectedRow = y;
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				progressTable.selectAllCells(tableName);
			}
		});
		if (y == currentlySelectedRow) {
			l.mouseEntered(null);
		}
		allMouseListener.add(l);
		return label;
	}

	/** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        stepLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        collectedRowsLabel = new javax.swing.JLabel();
        exportedRowsLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rowsPerTablePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        elapsedTimeLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        progressTableHolder = new javax.swing.JScrollPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel3.setText(" Stage ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel4.setText(" Collected Rows  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        stepLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(stepLabel, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel5.setText(" Exported Rows  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        collectedRowsLabel.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(collectedRowsLabel, gridBagConstraints);

        exportedRowsLabel.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(exportedRowsLabel, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Rows per Table"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setViewportBorder(null);

        rowsPerTablePanel.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(rowsPerTablePanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel5, gridBagConstraints);

        jLabel1.setForeground(new java.awt.Color(230, 230, 230));
        jLabel1.setText("                                     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel6.setText(" Elapsed Time ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        elapsedTimeLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(elapsedTimeLabel, gridBagConstraints);

        jPanel3.add(jPanel2);

        jSplitPane1.setLeftComponent(jPanel3);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        panel3.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText(" Day ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel6.add(jLabel2, gridBagConstraints);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(" Progress ");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel6.add(jLabel7, gridBagConstraints);

        panel3.add(jPanel6, java.awt.BorderLayout.PAGE_START);
        panel3.add(progressTableHolder, java.awt.BorderLayout.CENTER);

        jPanel4.add(panel3);

        jSplitPane1.setRightComponent(jPanel4);

        add(jSplitPane1);
    }// </editor-fold>//GEN-END:initComponents

    public void confirm() {
	    String message;
		message = "Successfully finished.";
	    if (DMLTransformer.numberOfExportedLOBs > 0) {
			message += "\n" + DMLTransformer.numberOfExportedLOBs + " CLOBs/BLOBs exported.\n\n" +
			           "Note that the CLOBs/BLOBs can only\n" +
					   "be imported with the 'Import SQL Data' Tool";
		}
	    JOptionPane.showMessageDialog(this, message, "Finished", JOptionPane.INFORMATION_MESSAGE);
    }
	
	// 
	// obsolete
	// new StatisticDialog(this, message);
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel collectedRowsLabel;
    public javax.swing.JLabel elapsedTimeLabel;
    public javax.swing.JLabel exportedRowsLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel panel3;
    private javax.swing.JScrollPane progressTableHolder;
    private javax.swing.JPanel rowsPerTablePanel;
    public javax.swing.JLabel stepLabel;
    // End of variables declaration//GEN-END:variables

	private static final long serialVersionUID = -2750282839722695036L;
}
