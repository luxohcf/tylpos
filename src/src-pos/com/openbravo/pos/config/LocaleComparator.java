


//

//




//




//



package com.openbravo.pos.config;

import java.util.Comparator;
import java.util.Locale;

/**
 *
 * 
 */
public class LocaleComparator implements Comparator<Locale> {
    
    /** Creates a new instance of LocaleComparator */
    public LocaleComparator() {
    }
    
    public int compare(Locale o1, Locale o2) {
        return o1.getDisplayName().compareTo(o2.getDisplayName());
    }
}
