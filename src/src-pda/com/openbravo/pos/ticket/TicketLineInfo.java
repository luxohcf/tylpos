


//

//




//




//



package com.openbravo.pos.ticket;

import com.openbravo.pos.pda.util.FormatUtils;
import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author jaroslawwozniak
 */
public class TicketLineInfo implements Serializable {

    private static final long serialVersionUID = 6608012948284450199L;
    private String m_sTicket;
    private int m_iLine;
    private TaxInfo tax;
    private double multiply;
    private double price;
    private String productid;
    private Properties attributes;
    private transient ProductInfo product;

    public TicketLineInfo() {
    }

    public TicketLineInfo(ProductInfo product, double price, TaxInfo tax) {
        this.product = product;
        productid = product.getId();
        this.multiply = 1.0;
        this.price = price;
        this.tax = tax;

        m_sTicket = null;
        m_iLine = -1;
        attributes = new Properties();
        setProperties();
    }

    public void setProperties() {
        attributes.setProperty("product.name", product.getName());
        attributes.setProperty("product.com", product.isCom() ? "true" : "false");
        attributes.setProperty("product.taxcategoryid", product.getTaxcat());
        if (product.getCategoryId() != null) {
             attributes.setProperty("product.categoryid", product.getCategoryId());
        }
    }

    public Properties getAttributes() {
        return attributes;
    }

    public void setAttributes(Properties attributes) {
        this.attributes = attributes;
    }

    public int getM_iLine() {
        return m_iLine;
    }

    public void setM_iLine(int m_iLine) {
        this.m_iLine = m_iLine;
    }

    public String getM_sTicket() {
        return m_sTicket;
    }

    public void setM_sTicket(String m_sTicket) {
        this.m_sTicket = m_sTicket;
    }

    public double getMultiply() {
        return multiply;
    }

    public void setMultiply(double multiply) {
        this.multiply = multiply;
    }

    public double getPrice() {
        try {
            return price +(price * getTax().getRate());
        } catch (NullPointerException ex){
            return price;
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public TaxInfo getTax() {
        return tax;
    }

    public void setTax(TaxInfo tax) {
        this.tax = tax;
    }

    public double getSubValue() {
        return price * multiply;
    }


    public void setTicket(String ticket, int size) {
        m_sTicket = ticket;
        m_iLine = size;
    }

    public double getValue() {
        return getPrice() * multiply;
    }

    public String getProductTaxCategoryID() {
        return (attributes.getProperty("product.taxcategoryid"));
    }

    public String printPrice() {
        return FormatUtils.formatCurrency(price);
    }

    public String printMultiply() {
        return FormatUtils.formatDouble(multiply);
    }
}
