
//    Copyright (C) 2008-2009 Openbravo, S.L.

//

//




//




//



package com.openbravo.data.model;

/**
 *
 * @author adrian
 */
public class Column {
    
    private String name;
    
    public Column(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public boolean isPK() {
        return false;
    }
}
