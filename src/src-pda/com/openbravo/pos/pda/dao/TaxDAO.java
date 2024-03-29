


//

//




//




//



package com.openbravo.pos.pda.dao;

import com.openbravo.pos.ticket.TaxInfo;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaroslawwozniak
 */
public class TaxDAO extends BaseJdbcDAO implements Serializable {

    public TaxInfo getTax(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlStr = "Select * from TAXES where category = ?";
        List<TaxInfo> list = new ArrayList<TaxInfo>();
        try {
            //get connection
            con = getConnection();
            //prepare statement
            ps = con.prepareStatement(sqlStr);

            ps.setString(1, id);
            //execute
            rs = ps.executeQuery();
            //transform to VO
            list = transformSet(rs);


        } catch (Exception ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return list.get(0);
    }

     public List<TaxInfo> getTaxList() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlStr = "SELECT ID, NAME, CATEGORY, CUSTCATEGORY, PARENTID, RATE, RATECASCADE, RATEORDER FROM TAXES ORDER BY NAME";
        List<TaxInfo> list = new ArrayList<TaxInfo>();
        try {
            //get connection
            con = getConnection();
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            //transform to VO
            list = transformSet(rs);


        } catch (Exception ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return list;
    }


    @Override
    protected TaxInfo map2VO(ResultSet rs) throws SQLException {
        TaxInfo tax = new TaxInfo();
        tax.setId(rs.getString("id"));
        tax.setName(rs.getString("name"));
        tax.setTaxcategoryid(rs.getString("category"));
        tax.setTaxcustcategoryid(rs.getString("custcategory"));
        tax.setParentID(rs.getString("parentid"));
        tax.setRate(rs.getDouble("rate"));
        tax.setCascade(rs.getBoolean("ratecascade"));
        tax.setOrder(rs.getInt("rateorder"));

        return tax;
    }
}
