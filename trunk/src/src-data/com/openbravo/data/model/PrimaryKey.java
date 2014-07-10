
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
public class PrimaryKey extends Column {
    
    public PrimaryKey(String name) {
        super(name);
    }
    
    public boolean isPK() {
        return true;
    }
}
