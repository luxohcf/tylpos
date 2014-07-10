
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
public class SessionDBOracle implements SessionDB {

    public String TRUE() {
        return "1";
    }
    public String FALSE() {
        return "0";
    }
    public String INTEGER_NULL() {
        return "CAST(NULL AS INTEGER)";
    }
    public String CHAR_NULL() {
        return "CAST(NULL AS CHAR)";
    }

    public String getName() {
        return "Oracle";
    }

    public SentenceFind getSequenceSentence(Session s, String sequence) {
        return new StaticSentence(s, "SELECT " + sequence + ".NEXTVAL FROM DUAL", null, SerializerReadInteger.INSTANCE);
    }
}