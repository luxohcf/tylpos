
//    Copyright (C) 2008-2009 Openbravo, S.L.

//

//




//




//



package com.openbravo.pos.inventory;

import com.openbravo.data.loader.IKeyed;

/**
 *
 * @author  adrianromero
 */
public class AttributeInfo implements IKeyed {

    private String id;
    private String name;

    /** Creates new CategoryInfo */
    public AttributeInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Object getKey() {
        return id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
