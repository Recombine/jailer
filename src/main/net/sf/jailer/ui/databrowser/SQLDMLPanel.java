/*
 * Copyright 2007 the original author or authors.
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
package net.sf.jailer.ui.databrowser;

import java.awt.Color;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import net.sf.jailer.CommandLineParser;
import net.sf.jailer.database.DMLTransformer;
import net.sf.jailer.database.Session;
import net.sf.jailer.ui.UIUtil;
import net.sf.jailer.util.SqlScriptExecutor;

/**
 * Editor for SQL/DML statements.
 * 
 * @author Ralf Wisser
 */
public class SQLDMLPanel extends javax.swing.JPanel {
	
	private static final long serialVersionUID = 1747749941444843829L;

	private final Session session;
	
	/**
	 * To be done after execution of the script.
	 */
	private final Runnable afterExecution;
	
	/** Creates new form SQLPanel 
	 * @param sql */
    public SQLDMLPanel(String sql, Session session, Runnable afterExecution) {
    	this.session = session;
    	this.afterExecution = afterExecution;
        initComponents();
//        SyntaxSupport instance = SyntaxSupport.getInstance();
//        instance.highlightCurrent(false);
//		instance.addSupport(SyntaxSupport.SQL_LEXER, sqlTextArea3);
        statusLabel.setText("");
        sqlTextArea.setContentType("text/sql");
        sqlTextArea.setText(sql);
        sqlTextArea.select(0, 0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        clipboardSingleLineButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        clipboardButton = new javax.swing.JButton();
        singleLineCheckBox = new javax.swing.JCheckBox();
        executeButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sqlTextArea = new javax.swing.JEditorPane();
        jPanel3 = new javax.swing.JPanel();

        clipboardSingleLineButton.setText(" Copy as Single Line ");
        clipboardSingleLineButton.setToolTipText(" Copy the SQL statement as a single line to the clipboard");
        clipboardSingleLineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipboardSingleLineButtonActionPerformed(evt);
            }
        });

        setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        saveButton.setText(" Save ");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(saveButton, gridBagConstraints);

        clipboardButton.setText(" Copy to Clipboard ");
        clipboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipboardButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(clipboardButton, gridBagConstraints);

        singleLineCheckBox.setText("single line");
        singleLineCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                singleLineCheckBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        jPanel2.add(singleLineCheckBox, gridBagConstraints);

        executeButton.setText(" Execute ");
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(executeButton, gridBagConstraints);

        statusLabel.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanel2.add(statusLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setViewportView(sqlTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String fn = UIUtil.choseFile(null, ".", "Save SQL Query", "", this, false, false);
        if (fn != null) {
            try {
                PrintWriter out = new PrintWriter(new FileWriter(fn));
                out.print(sqlTextArea.getText());
                out.close();
            } catch (Exception e) {
                UIUtil.showException(this, "Error saving query", e);
            }
        }
}//GEN-LAST:event_saveButtonActionPerformed

    private void clipboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipboardButtonActionPerformed
        sqlTextArea.selectAll();
        sqlTextArea.copy();
        sqlTextArea.select(0, 0);
}//GEN-LAST:event_clipboardButtonActionPerformed

    private void clipboardSingleLineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipboardSingleLineButtonActionPerformed
    	String orig = sqlTextArea.getText();
    	sqlTextArea.setText(orig.replaceAll(" *(\n|\r)+ *", " "));
    	sqlTextArea.selectAll();
        sqlTextArea.copy();
    	sqlTextArea.setText(orig);
        sqlTextArea.select(0, 0);
    }//GEN-LAST:event_clipboardSingleLineButtonActionPerformed

    private String lastMultiLineSQL = "";
    
    private void singleLineCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_singleLineCheckBoxItemStateChanged
        if (singleLineCheckBox.isSelected()) {
    		lastMultiLineSQL = sqlTextArea.getText();
    		String EOL = "EOL498503458430EOL";
        	sqlTextArea.setText(lastMultiLineSQL.replaceAll(";(\n|\r)", EOL).replaceAll(" *(\n|\r)+ *", " ").replaceAll(EOL + " *", ";\n"));
    	} else {
    		sqlTextArea.setText(lastMultiLineSQL);
    	}
        sqlTextArea.setCaretPosition(0);
    }//GEN-LAST:event_singleLineCheckBoxItemStateChanged

    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
    	if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Execute Statements?", "Execute", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
    		String sqlFile;
    		try {
	    		String sqlFileBase = "temp_" + System.currentTimeMillis();
	    		for (int i = 0; ; ++i) {
	    			sqlFile = sqlFileBase + i + ".sql";
	    			if (!CommandLineParser.getInstance().newFile(sqlFile).exists()) {
	    				break;
	    			}
	    		}
	    		PrintWriter out = new PrintWriter(CommandLineParser.getInstance().newFile(sqlFile));
	    		out.println(sqlTextArea.getText());
	    		out.println(";");
	    		out.close();
    		} catch (Exception e) {
    			UIUtil.showException(this, "Error", e);
    			return;
    		}
    		List<String> args = new ArrayList<String>();
			args.add("import");
			args.add(sqlFile);
			args.addAll(session.getCliArguments());
			args.add("-transactional");
			if (UIUtil.runJailer(SwingUtilities.getWindowAncestor(this), args, false, true, false, true, null, session.getPassword(), null, null, false, false, true)) {
				statusLabel.setText("executed " + SqlScriptExecutor.getLastStatementCount() + " statements");
				statusLabel.setForeground(new Color(0, 100, 0));
				afterExecution.run();
				JOptionPane.showMessageDialog(this, "Successfully executed " + SqlScriptExecutor.getLastStatementCount() + " statements", "SQL/DML", JOptionPane.INFORMATION_MESSAGE);
		 
			} else {
				statusLabel.setText("Error, rolled back");
				statusLabel.setForeground(new Color(115, 0, 0));
			}
			CommandLineParser.getInstance().newFile(sqlFile).delete();
    	}
    }//GEN-LAST:event_executeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clipboardButton;
    private javax.swing.JButton clipboardSingleLineButton;
    private javax.swing.JButton executeButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton saveButton;
    private javax.swing.JCheckBox singleLineCheckBox;
    private javax.swing.JEditorPane sqlTextArea;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}
