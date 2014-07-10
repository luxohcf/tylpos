
//    Copyright (C) 2009 Openbravo, S.L.

//

//




//




//



package com.openbravo.pos.scale;

public class ScaleFake implements Scale {
    
    /** Creates a new instance of ScaleFake */
    public ScaleFake() {
    }
    
    public Double readWeight() throws ScaleException {
        return new Double(Math.random() * 2.0);
    }
    
}
