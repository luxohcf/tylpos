
//    Copyright (C) 2008-2009 Openbravo, S.L.

//

//




//




//



package com.openbravo.data.loader;

/**
 *
 * 
 */
public class SessionDBMySQL implements SessionDB {

    public String TRUE() {
        return "TRUE";
    }
    public String FALSE() {
        return "FALSE";
    }
    public String INTEGER_NULL() {
        return "CAST(NULL AS UNSIGNED INTEGER)";
    }
    public String CHAR_NULL() {
        return "CAST(NULL AS CHAR)";
    }

    public String getName() {
        return "MySQL";
    }

    public SentenceFind getSequenceSentence(Session s, String sequence) {
        return new SequenceForMySQL(s, sequence);
    }
}
