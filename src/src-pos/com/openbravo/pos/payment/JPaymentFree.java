


//

//




//




//



package com.openbravo.pos.payment;

import com.openbravo.pos.customers.CustomerInfoExt;
import java.awt.Component;
import com.openbravo.pos.forms.AppLocal;

public class JPaymentFree extends javax.swing.JPanel implements JPaymentInterface {
    
    private double m_dTotal;
    private JPaymentNotifier m_notifier;
    
    /** Creates new form JPaymentFree */
    public JPaymentFree(JPaymentNotifier notifier) {
        m_notifier = notifier;
        initComponents();
    }
    public void activate(CustomerInfoExt customerext, double dTotal, String transID) {
        
        m_dTotal = dTotal;
        
        // m_jTotal.setText(Formats.CURRENCY.formatValue(new Double(m_dTotal)));
        
        m_notifier.setStatus(true, true);
    }
    
    public PaymentInfo executePayment() {
        return new PaymentInfoFree(m_dTotal);
    }
    public Component getComponent() {
        return this;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();

        jLabel1.setText(AppLocal.getIntString("message.paymentfree"));
        add(jLabel1);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    
}
