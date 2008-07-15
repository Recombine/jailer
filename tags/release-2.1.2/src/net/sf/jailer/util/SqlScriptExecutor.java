package net.sf.jailer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.sql.SQLException;
import java.util.zip.GZIPInputStream;

import net.sf.jailer.database.StatementExecutor;

import org.apache.log4j.Logger;


/**
 * Reads in and executes SQL-scripts.
 * 
 * @author Wisser
 */
public class SqlScriptExecutor {

	/**
	 * Comment prefix for multi-line comments.
	 */
	public static final String UNFINISHED_MULTILINE_COMMENT = "--+";

	/**
	 * Comment prefix for last line of a multi-line comment.
	 */
	public static final String FINISHED_MULTILINE_COMMENT = "--.";
	
	/**
     * The logger.
     */
    private static final Logger _log = Logger.getLogger(SqlScriptExecutor.class);

    /**
     * Reads in and executes a SQL-script.
     * 
     * @param scriptFileName the name of the script-file
     * @param statementExecutor for execution of statements
     */
    public static void executeScript(String scriptFileName, StatementExecutor statementExecutor) throws IOException, SQLException {
        _log.info("reading file '" + scriptFileName + "'");
    	BufferedReader reader;
    	if (scriptFileName.toLowerCase().endsWith(".gz") || scriptFileName.toLowerCase().endsWith(".zip")) {
    		reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(scriptFileName))));
    	} else {
    		reader = new BufferedReader(new FileReader(scriptFileName));
    	}
        String line = null;
        StringBuffer currentStatement = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0 || line.startsWith("--")) {
            	if (line.startsWith(UNFINISHED_MULTILINE_COMMENT)) {
            		String cmd = line.substring(UNFINISHED_MULTILINE_COMMENT.length());
            		if (cmd.startsWith("CLOB")) {
            			importCLob(cmd.substring(4).trim(), reader, statementExecutor);
            		}
            		if (cmd.startsWith("BLOB")) {
            			importBLob(cmd.substring(4).trim(), reader, statementExecutor);
            		}
            	}
                continue;
            }
            if (line.endsWith(";")) {
            	String stmt = currentStatement + line.substring(0, line.length() - 1);
                try {
                	statementExecutor.execute(stmt);
                } catch (SQLException e) {
                	// drops may fail
                	if (!stmt.trim().toLowerCase().startsWith("drop")) {
                		throw e;
                	}
                }
                currentStatement.setLength(0);
            } else {
                currentStatement.append(line + " ");
            }
        }
        reader.close();
        _log.info("successfully read file '" + scriptFileName + "'");
    }

    /**
     * Imports clob from sql-script.
     * 
     * @param clobLocator locates the clob
     * @param reader for reading content
     */
	private static void importCLob(final String clobLocator, final BufferedReader reader, StatementExecutor statementExecutor) throws IOException, SQLException {
		int c1 = clobLocator.indexOf(',');
		int c2 = clobLocator.indexOf(',', c1 + 1);
		String table = clobLocator.substring(0, c1).trim();
		String column = clobLocator.substring(c1 + 1, c2).trim();
		String where = clobLocator.substring(c2 + 1).trim();
		String line;
		File lobFile = new File("lob." + System.currentTimeMillis());
		Writer out = new FileWriter(lobFile);
		while ((line = reader.readLine()) != null) {
		    line = line.trim();
			if (line.startsWith(UNFINISHED_MULTILINE_COMMENT)) {
				String content = line.substring(UNFINISHED_MULTILINE_COMMENT.length());
				int l = content.length();
				boolean inEscape = false;
				for (int i = 0; i < l; ++i) {
					char c = content.charAt(i);
					if (c == '\\') {
						if (inEscape) {
							inEscape = false;
						} else {
							inEscape = true;
							continue;
						}
					} else {
						if (inEscape) {
							if (c == 'n') {
								c = '\n';
							}
							inEscape = false;
						}
					}
					out.write(c);
				}
			} else {
				break;
			}
		}
		out.close();
		statementExecutor.insertClob(table, column, where, lobFile);
		lobFile.delete();
	}
    
    /**
     * Imports blob from sql-script.
     * 
     * @param clobLocator locates the clob
     * @param reader for reading content
     */
	private static void importBLob(final String clobLocator, final BufferedReader reader, StatementExecutor statementExecutor) throws IOException, SQLException {
		int c1 = clobLocator.indexOf(',');
		int c2 = clobLocator.indexOf(',', c1 + 1);
		String table = clobLocator.substring(0, c1).trim();
		String column = clobLocator.substring(c1 + 1, c2).trim();
		String where = clobLocator.substring(c2 + 1).trim();
		String line;
		File lobFile = new File("lob." + System.currentTimeMillis());
		OutputStream out = new FileOutputStream(lobFile);
		while ((line = reader.readLine()) != null) {
		    line = line.trim();
			if (line.startsWith(UNFINISHED_MULTILINE_COMMENT)) {
				String content = line.substring(UNFINISHED_MULTILINE_COMMENT.length());
				out.write(Base64.decode(content));
			} else {
				break;
			}
		}
		out.close();
		statementExecutor.insertBlob(table, column, where, lobFile);
		lobFile.delete();
	}

}