


//

//




//




//



package com.openbravo.pos.payment;

/**
 *
 * @author adrian
 */
public interface MagCardParser {

    public void reset();
    public void append(char c);
    public boolean isComplete();

    public String getHolderName();
    public String getCardNumber();
    public String getExpirationDate();

    public String getTrack1();
    public String getTrack2();
    public String getTrack3();
}
