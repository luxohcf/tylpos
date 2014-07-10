


//

//




//




//



package com.openbravo.pos.ticket;

import java.io.Serializable;

/**
 *
 * @author jaroslawwozniak
 */
public class TaxInfo implements Serializable {

    private static final long serialVersionUID = -2705212098856473043L;
    private String id;
    private String name;
    private String taxcategoryid;
    private String taxcustcategoryid;
    private String parentid;
    private double rate;
    private boolean cascade;
    private Integer order;

    public boolean isCascade() {
        return cascade;
    }

    public void setCascade(boolean cascade) {
        this.cascade = cascade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getParentID() {
        return parentid;
    }

    public void setParentID(String parentid) {
        this.parentid = parentid;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTaxcategoryid() {
        return taxcategoryid;
    }

    public void setTaxcategoryid(String taxcategoryid) {
        this.taxcategoryid = taxcategoryid;
    }

    public String getTaxcustcategoryid() {
        return taxcustcategoryid;
    }

    public void setTaxcustcategoryid(String taxcustcategoryid) {
        this.taxcustcategoryid = taxcustcategoryid;
    }

    public Integer getApplicationOrder() {
        return order == null ? Integer.MAX_VALUE : order.intValue();
    }
}
