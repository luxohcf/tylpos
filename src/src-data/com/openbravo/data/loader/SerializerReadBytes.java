


//

//




//




//



package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;

/**
 *
 * @author Adrian
 */
public class SerializerReadBytes implements SerializerRead {
    
    public static final SerializerRead INSTANCE = new SerializerReadBytes();
    
    /** Creates a new instance of SerializerReadBytes */
    private SerializerReadBytes() {
    }
    
    public Object readValues(DataRead dr) throws BasicException {
        return Datas.BYTES.getValue(dr,1);
    }       
}
