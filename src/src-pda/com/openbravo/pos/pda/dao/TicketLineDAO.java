


//

//




//




//



package com.openbravo.pos.pda.dao;

import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jaroslawwozniak
 */
public class TicketLineDAO extends BaseJdbcDAO {

    public List<TicketLineInfo> findLinesByTicket(String ticketId) {
        TicketDAO ticketDao = new TicketDAO();
        TicketInfo ticket = ticketDao.getTicket(ticketId);

        return ticket.getM_aLines();

    }

    @Override
    protected Object map2VO(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
