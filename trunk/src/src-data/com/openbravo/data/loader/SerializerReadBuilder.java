


//

//




//




//



package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;

/**
 *
 * @author  adrian
 */
public class SerializerReadBuilder  implements SerializerRead {
    
    private SerializableBuilder m_sb;
    
    /** Creates a new instance of SerializerReadBuilder */
    public SerializerReadBuilder(SerializableBuilder sb) {
        m_sb = sb;
    }
    
    public Object readValues(DataRead dr) throws BasicException {
        SerializableRead sr = m_sb.createNew();
        sr.readValues(dr);
        return sr;
    }
    
}
