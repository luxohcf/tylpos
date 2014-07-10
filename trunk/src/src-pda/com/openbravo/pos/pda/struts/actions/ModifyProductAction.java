


//

//




//




//



package com.openbravo.pos.pda.struts.actions;

import com.openbravo.pos.pda.bo.RestaurantManager;
import com.openbravo.pos.pda.struts.forms.FloorForm;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
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
public class ModifyProductAction extends org.apache.struts.action.Action {

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

        FloorForm floorForm = (FloorForm) form;
        RestaurantManager manager = new RestaurantManager();
        String floorId = (String) floorForm.getFloorId();
        String place = (String) floorForm.getId();
        String str = (String) floorForm.getMode();
        String[] array = floorForm.getParameters();
        List<TicketLineInfo> linesList = new ArrayList<TicketLineInfo>();
        List products = new ArrayList<ProductInfoExt>();
        TicketInfo ticket = manager.findTicket(place);
        linesList = ticket.getM_aLines();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                linesList.get(Integer.valueOf(array[i]) - 0).setMultiply(linesList.get(Integer.valueOf(array[i]) - 0).getMultiply() + 1);    //strange
            }
        }
        manager.updateLineFromTicket(floorForm.getId(), ticket);
        for (Object line : linesList) {
            TicketLineInfo li = (TicketLineInfo) line;
            products.add(manager.findProductById(li.getProductid()));
        }


        request.setAttribute("floorName", manager.findFloorById(manager.findPlaceById(place).getFloor()).getName());
        request.setAttribute("place", place);

        request.setAttribute("placeName", manager.findPlaceNameById(place));
        request.setAttribute("floorId", floorId);
        request.setAttribute("lines", linesList);
        request.setAttribute("products", products);
        request.setAttribute("total", manager.getTotalOfaTicket(place));

        return mapping.findForward(SUCCESS);
    }
}
