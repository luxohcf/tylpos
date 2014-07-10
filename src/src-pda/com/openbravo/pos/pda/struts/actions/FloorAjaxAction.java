


//

//




//




//



package com.openbravo.pos.pda.struts.actions;

import com.openbravo.pos.pda.bo.RestaurantManager;
import com.openbravo.pos.ticket.Floor;
import com.openbravo.pos.ticket.Place;
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
 * @author Expected hash. user evaluated instead to freemarker.template.SimpleScalar on line 20, column 14 in Templates/Struts/StrutsAction.java.
 */
public class FloorAjaxAction extends org.apache.struts.action.Action {

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

        DynaActionForm aform = (DynaActionForm) form;
        RestaurantManager manager = new RestaurantManager();
        List busyTables = new ArrayList<Floor>();
        List places = new ArrayList<Place>();
        String floorId = (String) aform.get("floorId");
        if (((String) aform.get("floorId")).equals("")) {
            places = manager.findAllPlaces(manager.findTheFirstFloor());
            busyTables = manager.findAllBusyTable(floorId);
        } else {
            places = manager.findAllPlaces(floorId);
            busyTables = manager.findAllBusyTable(floorId);
        }

        request.setAttribute("busy", busyTables);
        request.getSession().setAttribute("floorId", floorId);
        request.getSession().setAttribute("places", places);
        response.setContentType("text/html");
        return mapping.findForward(SUCCESS);

    }
}