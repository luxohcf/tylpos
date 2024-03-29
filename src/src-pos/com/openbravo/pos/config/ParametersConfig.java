
//    Copyright (C) 2009 Openbravo, S.L.

//

//




//




//



package com.openbravo.pos.config;

import com.openbravo.data.user.DirtyManager;
import com.openbravo.pos.util.StringParser;
import java.awt.Component;

/**
 *
 * @author adrian
 */
public interface ParametersConfig {

    public Component getComponent();
    
    public void addDirtyManager(DirtyManager dirty);

    public void setParameters(StringParser p);
    public String getParameters();

}
