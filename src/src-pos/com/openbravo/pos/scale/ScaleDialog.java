


//

//




//




//



package com.openbravo.pos.scale;

import com.openbravo.beans.JNumberDialog;
import com.openbravo.pos.forms.AppLocal;
import java.awt.Component;
import javax.swing.ImageIcon;

/**
 *
 * @author adrian
 */
public class ScaleDialog implements Scale {

    private Component parent;

    public ScaleDialog(Component parent) {
        this.parent = parent;
    }

    public Double readWeight() throws ScaleException {
        
        // Set title for grams Kilos, ounzes, pounds, ...
        return JNumberDialog.showEditNumber(parent, AppLocal.getIntString("label.scale"), AppLocal.getIntString("label.scaleinput"), new ImageIcon(ScaleDialog.class.getResource("/com/openbravo/images/ark2.png")));
    }
}
