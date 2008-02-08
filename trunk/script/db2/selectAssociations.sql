SELECT TABNAME, REFTABNAME, ''B'', ''n:1'', ''A.'' || rtrim(ltrim(FK_COLNAMES)) || ''='' || ''B.'' || rtrim(ltrim(PK_COLNAMES))
FROM syscat.REFERENCES 
WHERE TABSCHEMA=UPPER(''{0}'') AND NOT TABNAME IN (''JL_DEPENDENCY'', ''JL_ENTITY'', ''JL_GRAPH'', ''JL_SET'') ORDER BY TABNAME
