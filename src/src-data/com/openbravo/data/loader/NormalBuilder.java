


//

//




//




//



package com.openbravo.data.loader;

import java.util.*;
import com.openbravo.basic.BasicException;

public class NormalBuilder implements ISQLBuilderStatic {
    
    private String m_sSentence;
    
    /** Creates a new instance of NormalBuilder */
    public NormalBuilder(String sSentence) {
        m_sSentence = sSentence;
    }
    
    public String getSQL(SerializerWrite sw, Object params) throws BasicException {
        
        NormalParameter mydw = new NormalParameter(m_sSentence);
        if (sw != null) {
            sw.writeValues(mydw, params);
        }
        return mydw.getSentence();
    }
    
    private static class NormalParameter implements DataWrite {
    
        private String m_sSentence;
        private ArrayList m_aParams; // of String
        
        public NormalParameter(String sSentence) {
            m_sSentence = sSentence;
            m_aParams = new ArrayList();
        }
        
        public void setDouble(int paramIndex, Double dValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(dValue));
        }
        
        public void setBoolean(int paramIndex, Boolean bValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(bValue));
        }       
        public void setInt(int paramIndex, Integer iValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(iValue));
        }
        
        public void setString(int paramIndex, String sValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(sValue));
        }
        
        public void setTimestamp(int paramIndex, java.util.Date dValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(dValue));
        }
//        public void setBinaryStream(int paramIndex, java.io.InputStream in, int length) throws DataException{
//            throw new DataException("Param type not allowed");      
//        }
        public void setBytes(int paramIndex, byte[] value) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.noparamtype"));
        }
        public void setObject(int paramIndex, Object value) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, DataWriteUtils.getSQLValue(value));
        }
        
        private void ensurePlace(int i) {
            m_aParams.ensureCapacity(i);
            while (i >= m_aParams.size()){
                m_aParams.add(null);
            }
        }
        
        public String getSentence() {
            
            StringBuffer sNewSentence = new StringBuffer();
            int iCount = 0;
            int iPos;
            int iLast = 0;
            while ((iPos = m_sSentence.indexOf('?', iLast)) > 0) {
                sNewSentence.append(m_sSentence.substring(iLast, iPos));
                if (iCount < m_aParams.size() && m_aParams.get(iCount) != null) {
                    // el valor que viene
                    sNewSentence.append(m_aParams.get(iCount));
                } else {
                    // nulo
                    sNewSentence.append(DataWriteUtils.getSQLValue((Object) null));
                }
                iCount++;
                iLast = iPos + 1;
            }
            sNewSentence.append(m_sSentence.substring(iLast));
            
            return sNewSentence.toString(); // sustituida
        }                
    }    
}
