


//

//




//




//



package com.openbravo.pos.payment;

/**
 *
 * @author adrian
 */
public class MagCardParserIntelligent implements MagCardParser {

    private String m_sHolderName;
    private String m_sCardNumber;
    private String m_sExpirationDate;

    private StringBuffer m_sField;

    private static final int READING_HOLDER = 0;
    private static final int READING_NUMBER = 1;
    private static final int READING_DATE = 2;
    private static final int READING_FINISHED = 3;
    private int m_iAutomState;

    /** Creates a new instance of BasicMagCardReader */
    public MagCardParserIntelligent() {
        reset();
    }

    @Override
    public void reset() {
        m_sHolderName = null;
        m_sCardNumber = null;
        m_sExpirationDate = null;
        m_sField = new StringBuffer();
        m_iAutomState = READING_HOLDER;
    }

    @Override
    public void append(char c) {

        switch (m_iAutomState) {
            case READING_HOLDER:
            case READING_FINISHED:
                if (c == 0x0009) {
                    m_sHolderName = m_sField.toString();
                    m_sField = new StringBuffer();
                    m_iAutomState = READING_NUMBER;
                } else if (c == 0x000A) {
                    m_sHolderName = null;
                    m_sCardNumber = null;
                    m_sExpirationDate = null;
                    m_sField = new StringBuffer();
                    m_iAutomState = READING_HOLDER;
                } else {
                    m_sField.append(c);
                    m_iAutomState = READING_HOLDER;
                }
                break;
            case READING_NUMBER:
                if (c == 0x0009) {
                    m_sCardNumber = m_sField.toString();
                    m_sField = new StringBuffer();
                    m_iAutomState = READING_DATE;
                } else if (c == 0x000A) {
                    m_sHolderName = null;
                    m_sCardNumber = null;
                    m_sExpirationDate = null;
                    m_sField = new StringBuffer();
                    m_iAutomState = READING_HOLDER;
                } else {
                    m_sField.append(c);
                }
                break;
            case READING_DATE:
                if (c == 0x0009) {
                    m_sHolderName = m_sCardNumber;
                    m_sCardNumber = m_sExpirationDate;
                    m_sExpirationDate = null;
                    m_sField = new StringBuffer();
                } else if (c == 0x000A) {
                    m_sExpirationDate = m_sField.toString();
                    m_sField = new StringBuffer();
                    m_iAutomState = READING_FINISHED;
                } else {
                    m_sField.append(c);
                }
                break;
        }
    }

    @Override
    public boolean isComplete() {
        return m_iAutomState == READING_FINISHED;
    }

    @Override
    public String getHolderName() {
        return m_sHolderName;
    }
    @Override
    public String getCardNumber() {
        return m_sCardNumber;
    }
    @Override
    public String getExpirationDate() {
        return m_sExpirationDate;
    }
    @Override
    public String getTrack1() {
        return null;
    }
    @Override
    public String getTrack2() {
        return null;
    }
    @Override
    public String getTrack3() {
        return null;
    }
}
