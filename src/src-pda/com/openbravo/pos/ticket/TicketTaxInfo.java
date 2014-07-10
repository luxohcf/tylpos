


//

//




//




//



package com.openbravo.pos.ticket;

public class TicketTaxInfo {

    private TaxInfo tax;
    private double subtotal;
    private double taxtotal;

    /** Creates a new instance of TicketTaxInfo */
    public TicketTaxInfo(TaxInfo tax) {
        this.tax = tax;

        subtotal = 0.0;
        taxtotal = 0.0;
    }

    public TaxInfo getTaxInfo() {
        return tax;
    }

    public void add(double dValue) {
        subtotal += dValue;
        taxtotal = subtotal * tax.getRate();
    }

    public double getSubTotal() {
        return subtotal;
    }

    public double getTax() {
        return taxtotal;
    }

    public double getTotal() {
        return subtotal + taxtotal;
    }
}
