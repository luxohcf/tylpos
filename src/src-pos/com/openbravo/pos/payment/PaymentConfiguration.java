


//

//




//




//



package com.openbravo.pos.payment;

import com.openbravo.pos.forms.AppConfig;
import javax.swing.JPanel;

/**
 *
 * @author Mikel Irurita
 */
public interface PaymentConfiguration {
    
    public JPanel getComponent();
    public void loadProperties(AppConfig config);
    public void saveProperties(AppConfig config);
    
}
