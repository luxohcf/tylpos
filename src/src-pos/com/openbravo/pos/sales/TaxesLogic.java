
//    Copyright (C) 2008-2009 Openbravo, S.L.

//

//




//




//



package com.openbravo.pos.sales;

import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import com.openbravo.pos.ticket.TicketTaxInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 */
public class TaxesLogic {
    
    private List<TaxInfo> taxlist;
    
    private Map<String, TaxesLogicElement> taxtrees;
    
    public TaxesLogic(List<TaxInfo> taxlist) {
        this.taxlist = taxlist;
      
        taxtrees = new HashMap<String, TaxesLogicElement>();
                
        // Order the taxlist by Application Order...
        List<TaxInfo> taxlistordered = new ArrayList<TaxInfo>();
        taxlistordered.addAll(taxlist);
        Collections.sort(taxlistordered, new Comparator<TaxInfo>() {
            public int compare(TaxInfo o1, TaxInfo o2) {
                if (o1.getApplicationOrder() < o2.getApplicationOrder()) {
                    return -1;
                } else if (o1.getApplicationOrder() == o2.getApplicationOrder()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        
        // Generate the taxtrees
        HashMap<String, TaxesLogicElement> taxorphans = new HashMap<String, TaxesLogicElement>();
        
        for (TaxInfo t : taxlistordered) {
                       
            TaxesLogicElement te = new TaxesLogicElement(t);
            
            // get the parent
            TaxesLogicElement teparent = taxtrees.get(t.getParentID());
            if (teparent == null) {
                // orphan node
                teparent = taxorphans.get(t.getParentID());
                if (teparent == null) {
                    teparent = new TaxesLogicElement(null);
                    taxorphans.put(t.getParentID(), teparent);
                } 
            } 
            
            teparent.getSons().add(te);

            // Does it have orphans ?
            teparent = taxorphans.get(t.getId());
            if (teparent != null) {
                // get all the sons
                te.getSons().addAll(teparent.getSons());
                // remove the orphans
                taxorphans.remove(t.getId());
            }          
            
            // Add it to the tree...
            taxtrees.put(t.getId(), te);
        }
    }
    
    public void calculateTaxes(TicketInfo ticket) throws TaxesException {
  
        List<TicketTaxInfo> tickettaxes = new ArrayList<TicketTaxInfo>(); 
        
        for (TicketLineInfo line: ticket.getLines()) {
            tickettaxes = sumLineTaxes(tickettaxes, calculateTaxes(line));
        }
        
        ticket.setTaxes(tickettaxes);
    }
    
    public List<TicketTaxInfo> calculateTaxes(TicketLineInfo line) throws TaxesException {
        
        TaxesLogicElement taxesapplied = getTaxesApplied(line.getTaxInfo());
        return calculateLineTaxes(line.getSubValue(), taxesapplied);
    }
    
    private List<TicketTaxInfo> calculateLineTaxes(double base, TaxesLogicElement taxesapplied) {
 
        List<TicketTaxInfo> linetaxes = new ArrayList<TicketTaxInfo>();
        
        if (taxesapplied.getSons().isEmpty()) {           
            TicketTaxInfo tickettax = new TicketTaxInfo(taxesapplied.getTax());
            tickettax.add(base);
            linetaxes.add(tickettax);
        } else {
            double acum = base;
            
            for (TaxesLogicElement te : taxesapplied.getSons()) {
                
                List<TicketTaxInfo> sublinetaxes = calculateLineTaxes(
                        te.getTax().isCascade() ? acum : base, 
                        te);
                linetaxes.addAll(sublinetaxes);
                acum += sumTaxes(sublinetaxes);
            }
        }
        
        return linetaxes;       
    }
    
    private TaxesLogicElement getTaxesApplied(TaxInfo t) throws TaxesException {
        
        if (t == null) {
            throw new TaxesException(new java.lang.NullPointerException());
        }
        
        return taxtrees.get(t.getId());
    }
        
    private double sumTaxes(List<TicketTaxInfo> linetaxes) {
        
        double taxtotal = 0.0;
        
        for (TicketTaxInfo tickettax : linetaxes) {
            taxtotal += tickettax.getTax();
            
        }
        return  taxtotal;
    }
    
    private List<TicketTaxInfo> sumLineTaxes(List<TicketTaxInfo> list1, List<TicketTaxInfo> list2) {
     
        for (TicketTaxInfo tickettax : list2) {
            TicketTaxInfo i = searchTicketTax(list1, tickettax.getTaxInfo().getId());
            if (i == null) {
                list1.add(tickettax);
            } else {
                i.add(tickettax.getSubTotal());
            }
        }
        return list1;
    }
    
    private TicketTaxInfo searchTicketTax(List<TicketTaxInfo> l, String id) {
        
        for (TicketTaxInfo tickettax : l) {
            if (id.equals(tickettax.getTaxInfo().getId())) {
                return tickettax;
            }
        }    
        return null;
    }
    
    public double getTaxRate(String tcid, Date date) {
        return getTaxRate(tcid, date, null);
    }
    
    public double getTaxRate(TaxCategoryInfo tc, Date date) {
        return getTaxRate(tc, date, null);
    }
    
    public double getTaxRate(TaxCategoryInfo tc, Date date, CustomerInfoExt customer) {
        
        if (tc == null) {
            return 0.0;
        } else {
            return getTaxRate(tc.getID(), date, customer);
        }
    }
    
    public double getTaxRate(String tcid, Date date, CustomerInfoExt customer) {
        
        if (tcid == null) {
            return 0.0;
        } else {
            TaxInfo tax = getTaxInfo(tcid, date, customer);
            if (tax == null) {
                return 0.0;
            } else {
                return tax.getRate();
            }            
        }
    }
    
    public TaxInfo getTaxInfo(String tcid, Date date) {
        return getTaxInfo(tcid, date, null);
    }
    
    public TaxInfo getTaxInfo(TaxCategoryInfo tc, Date date) {
        return getTaxInfo(tc.getID(), date, null);
    }
    
    public TaxInfo getTaxInfo(TaxCategoryInfo tc, Date date, CustomerInfoExt customer) {
        return getTaxInfo(tc.getID(), date, customer);
    }    
    
    public TaxInfo getTaxInfo(String tcid, Date date, CustomerInfoExt customer) {
        
        TaxInfo candidatetax = null;
        TaxInfo defaulttax = null;
        
        for (TaxInfo tax : taxlist) {
            if (tax.getParentID() == null && tax.getTaxCategoryID().equals(tcid) && tax.getValidFrom().compareTo(date) <= 0) {


                if (candidatetax == null || tax.getValidFrom().compareTo(candidatetax.getValidFrom()) > 0) {
                    // is valid date
                    if ((customer == null || customer.getTaxCustCategoryID() == null) && tax.getTaxCustCategoryID() == null) {
                        candidatetax = tax;
                    } else if (customer != null && customer.getTaxCustCategoryID() != null && customer.getTaxCustCategoryID().equals(tax.getTaxCustCategoryID())) {
                        candidatetax = tax;
                    }
                }
                
                if (tax.getTaxCustCategoryID() == null) {
                    if (defaulttax == null || tax.getValidFrom().compareTo(defaulttax.getValidFrom()) > 0) {
                        defaulttax = tax;
                    }
                }
            }
        }

        return candidatetax == null ? defaulttax : candidatetax;
    }
}
