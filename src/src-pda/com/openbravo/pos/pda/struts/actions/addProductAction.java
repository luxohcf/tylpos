


//

//




//




//



package com.openbravo.pos.pda.struts.actions;

import com.openbravo.pos.pda.bo.RestaurantManager;
import com.openbravo.pos.pda.struts.forms.AddedProductForm;
import com.openbravo.pos.ticket.ProductInfo;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author jaroslawwozniak
 */
public class addProductAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        AddedProductForm aForm = (AddedProductForm) form;
        RestaurantManager bo = new RestaurantManager();
        String place = aForm.getPlace();
        String productId = aForm.getProductId();

        bo.addLineToTicket(place, productId);
        List auxiliars = new ArrayList<ProductInfo>();
        auxiliars = bo.findAuxiliars(productId);
        request.setAttribute("place", place);
        request.setAttribute("auxiliars", auxiliars);
        request.setAttribute("rates", bo.findAllTaxRatesByCategory(auxiliars));

        return mapping.findForward(SUCCESS);
    }
}
