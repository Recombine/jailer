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
package net.sf.jailer.extractionmodel;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jailer.datamodel.AggregationSchema;
import net.sf.jailer.datamodel.Association;
import net.sf.jailer.datamodel.Column;
import net.sf.jailer.datamodel.DataModel;
import net.sf.jailer.datamodel.Table;
import net.sf.jailer.restrictionmodel.RestrictionModel;
import net.sf.jailer.util.CsvFile;
import net.sf.jailer.util.SqlUtil;

import org.apache.log4j.Logger;

/**
 * Extraction-model, defines the subject and the {@link RestrictionModel}
 * of an extraction.
 * 
 * @author Ralf Wisser
 */
public class ExtractionModel {

    /**
     * The extraction-tasks.
     */
    private List<ExtractionTask> tasks = new ArrayList<ExtractionTask>();
    
    /**
     * The logger.
     */
    private static final Logger _log = Logger.getLogger(ExtractionModel.class);
 
    /**
     * One extraction-task.
     */
    public class ExtractionTask {
        
        /**
         * The table to read from.
         */
        public final Table subject;
        
        /**
         * The SQL-condition.
         */
        public String condition;
        
        /**
         * A limit for the number of subject-entities. (0 for unlimited)
         */
        public final long limit;
        
        /**
         * The restricted data-model to be used for extraction.
         */
        public final DataModel dataModel;
        
        /**
         * Constructor.
         */
        public ExtractionTask(Table subject, String condition, long limit, DataModel dataModel) {
            this.subject = subject;
            this.condition = condition;
            this.limit = limit;
            this.dataModel = dataModel;
        }
    }
	
    /**
     * Constructor for empty restriction models.
     * 
     * @param dataModel the data model to restrict
     */
    public ExtractionModel(DataModel dataModel) throws Exception {
    	Table subject = dataModel.getTables().iterator().hasNext()? dataModel.getTables().iterator().next() : null;
        String condition = "";
        dataModel.setRestrictionModel(new RestrictionModel(dataModel));
        tasks.add(new ExtractionTask(subject, condition, -1, dataModel));
    }

    /**
     * Constructor.
     * 
     * @param the name of the model-file
     */
    public ExtractionModel(String fileName, Map<String, String> sourceSchemaMapping) throws Exception {
        readModel(fileName, sourceSchemaMapping);
    }
    
    /**
     * Gets the extraction-tasks.
     * 
     * @return the extraction-tasks
     */
    public List<ExtractionTask> getTasks() {
        return tasks;
    }
    
    /**
     * Reads in model.
     * 
     * @param fileName the models file name
     */
    private void readModel(String fileName, Map<String, String> sourceSchemaMapping) throws Exception {
        List<CsvFile.Line> csv = new CsvFile(new File(fileName)).getLines();
        for (CsvFile.Line line: csv) {
            String location = line.location;
            DataModel dataModel = new DataModel(sourceSchemaMapping);
            Table subject = dataModel.getTable(SqlUtil.mappedSchema(sourceSchemaMapping, line.cells.get(0)));
            if (subject == null) {
            	_log.warn(location + ": unknown table " + line.cells.get(0));
            }
            String condition = line.cells.get(1);
            if ("".equals(condition)) {
            	condition = "1=1";
                // throw new RuntimeException(location + ": no condition");
            }
            long limit = 0;
            if (!"".equals(line.cells.get(2))) {
                try {
                    limit = Long.parseLong(line.cells.get(2));
                } catch (NumberFormatException e) {
                    throw new RuntimeException(location, e);
                }
            }
            boolean embedded = false;
            for (int i = 3; line.cells.get(i).length() > 0; ++i) {
                if (dataModel.getRestrictionModel() == null) {
                    dataModel.setRestrictionModel(new RestrictionModel(dataModel));
                }
                try {
                	if (line.cells.get(i).equals(RestrictionModel.EMBEDDED)) {
                		embedded = true;
                	}
                    dataModel.getRestrictionModel().addRestrictionDefinition(line.cells.get(i), fileName);
                } catch (Exception e) {
                    throw new RuntimeException(location, e);
                }
            }
            tasks.add(new ExtractionTask(subject, condition, limit, dataModel));
            
            // read xml mapping
            List<CsvFile.Line> xmlMapping = new CsvFile(new File(fileName), "xml-mapping").getLines();
            for (CsvFile.Line xmLine: xmlMapping) {
                location = line.location;
				String name = xmLine.cells.get(0);
				String tag = xmLine.cells.get(1);
				AggregationSchema aggregationSchema = AggregationSchema.valueOf(xmLine.cells.get(2));
                Association association = dataModel.namedAssociations.get(name);
                if (association == null) {
                	_log.warn("unknown association '" + name + "'");
                } else {
                	if (aggregationSchema != null) {
                		association.setAggregationSchema(aggregationSchema);
                	}
                	association.setAggregationTagName(tag);
                }
            }

            // read export modus
            List<CsvFile.Line> exportModusFile = new CsvFile(new File(fileName), "export modus").getLines();
            Iterator<CsvFile.Line> i = exportModusFile.iterator();
            if (i.hasNext()) {
            	dataModel.setExportModus(i.next().cells.get(0));
            }
            
            // read column mapping
            List<CsvFile.Line> columnMappingFile = new CsvFile(new File(fileName), "xml column mapping").getLines();
            for (CsvFile.Line xmLine: columnMappingFile) {
            	String name = xmLine.cells.get(0);
				String mapping = xmLine.cells.get(1);
				Table table = dataModel.getTable(SqlUtil.mappedSchema(sourceSchemaMapping, name));
				if (table == null) {
					_log.warn("unknown table" + name);
				} else {
					table.setXmlTemplate(mapping);
				}
            }
            
            // read filters
            List<CsvFile.Line> filtersFile = new CsvFile(new File(fileName), "filters").getLines();
            for (CsvFile.Line xmLine: filtersFile) {
            	String name = xmLine.cells.get(0);
				String column = xmLine.cells.get(1);
				String filter = xmLine.cells.get(2);
				Table table = dataModel.getTable(SqlUtil.mappedSchema(sourceSchemaMapping, name));
				if (table == null) {
					_log.warn("unknown table" + name);
				} else {
					Column col = null;
					for (Column c: table.getColumns()) {
						if (c.name.equals(column)) {
							col = c;
							break;
						}
					}
					if (col == null) {
						_log.warn("unknown table" + name + "." + column);
					} else {
						col.setFilterExpression(filter);
					}
				}
            }
            
            // read xml settings
            List<CsvFile.Line> xmlSettingsFile = new CsvFile(new File(fileName), "xml settings").getLines();
            i = xmlSettingsFile.iterator();
            if (i.hasNext()) {
            	List<String> cells = i.next().cells;
    			dataModel.getXmlSettings().datePattern = cells.get(0);
    			dataModel.getXmlSettings().timestampPattern = cells.get(1);
    			dataModel.getXmlSettings().rootTag = cells.get(2);
            }
            
            if (embedded) {
            	break;
            }
        }
    }
    
}