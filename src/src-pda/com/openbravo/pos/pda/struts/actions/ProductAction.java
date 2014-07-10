


//

//




//




//



package com.openbravo.pos.pda.struts.actions;

import com.openbravo.pos.pda.bo.RestaurantManager;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfo;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.DynaActionForm;

/**
 *
 * @author jaroslawwozniak
 */
public class ProductAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";

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

        DynaActionForm inputFormPlace = (DynaActionForm) form;
        RestaurantManager manager = new RestaurantManager();
        List<CategoryInfo> categories = new ArrayList<CategoryInfo>();
        categories = manager.findAllCategories();
        List products = new ArrayList<ProductInfo>();
        products = manager.findProductsByCategory(categories.get(0).getId());
        List taxRates = new ArrayList<String>();
        taxRates = manager.findAllTaxRatesByCategory(products);
        List subcategories = new ArrayList<CategoryInfo>();
        subcategories = manager.findAllSubcategories(categories.get(0).getId());

        request.setAttribute("products", products);
        request.setAttribute("rates", taxRates);
        request.getSession().setAttribute("place", (String) inputFormPlace.get("place"));
        request.setAttribute("placeName", manager.findPlaceNameById((String) inputFormPlace.get("place")));
        request.setAttribute("categories", categories);
        request.setAttribute("subcategories", subcategories);
        request.getSession().setAttribute("floorId", (String) inputFormPlace.get("floorId"));

        return mapping.findForward(SUCCESS);
    }
}