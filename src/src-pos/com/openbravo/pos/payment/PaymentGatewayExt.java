
//    Copyright (C) 2008-2009 Openbravo, S.L.

//

//




//




//



package com.openbravo.pos.payment;

public class PaymentGatewayExt implements PaymentGateway {
    
    /** Creates a new instance of PaymentGatewayExt */
    public PaymentGatewayExt() {
    }
  
    public void execute(PaymentInfoMagcard payinfo) {
        payinfo.paymentOK("OK", payinfo.getTransactionID() , "");
    }
}
