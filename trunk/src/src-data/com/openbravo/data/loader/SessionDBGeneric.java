
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
public class SessionDBGeneric implements SessionDB {

    private String name;

    public SessionDBGeneric(String name) {
        this.name = name;
    }

    public String TRUE() {
        return "TRUE";
    }
    public String FALSE() {
        return "FALSE";
    }
    public String INTEGER_NULL() {
        return "CAST(NULL AS INTEGER)";
    }
    public String CHAR_NULL() {
        return "CAST(NULL AS CHAR)";
    }

    public String getName() {
        return name;
    }

    public SentenceFind getSequenceSentence(Session s, String sequence) {
        return new StaticSentence(s, "SELECT NEXTVAL('" + sequence + "')", null, SerializerReadInteger.INSTANCE);
    }
}