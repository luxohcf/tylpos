
//    Copyright (C) 2008-2009 Openbravo, S.L.

//

//




//




//



package com.openbravo.pos.payment;

public class PaymentInfoMagcardRefund extends PaymentInfoMagcard {
    
    /** Creates a new instance of PaymentInfoMagcardRefund */
    public PaymentInfoMagcardRefund(String sHolderName, String sCardNumber, String sExpirationDate, String track1, String track2, String track3, String sTransactionID, double dTotal) {
       super(sHolderName, sCardNumber, sExpirationDate, track1, track2, track3, sTransactionID, dTotal);
    }
    
    /** Creates a new instance of PaymentInfoMagcard */
    public PaymentInfoMagcardRefund(String sHolderName, String sCardNumber, String sExpirationDate, String sTransactionID, double dTotal) {
        super(sHolderName, sCardNumber, sExpirationDate, sTransactionID, dTotal);
    }
    
    @Override
    public PaymentInfo copyPayment(){
        PaymentInfoMagcard p = new PaymentInfoMagcardRefund(m_sHolderName, m_sCardNumber, m_sExpirationDate, track1, track2, track3, m_sTransactionID, m_dTotal);
        p.m_sAuthorization = m_sAuthorization;
        p.m_sErrorMessage = m_sErrorMessage;
        return p;
    }    
    
    @Override
    public String getName() {
        return "magcardrefund";
    }    
}
