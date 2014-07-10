

<%@page contentType="text/javascript" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>

<span>
    <% boolean rowodd = false; %>
    <c:forEach var="product" items="${auxiliars}" varStatus="nr">
        <tr id="${nr.count - 1}" class="<%= rowodd ? "odd" : "even" %>">
            <td class="name" style="background-color:#ffb7b3;">* ${product.name}</td>
            <td class="normal" style="background-color:#ffb7b3;"><fmt:formatNumber type="currency" value="${product.priceSell + product.priceSell * rates[nr.count - 1]}" maxFractionDigits="2" minFractionDigits="2"/></td>
            <td class="normal" style="background-color:#ffb7b3;"></td>
            <td style="background-color:#ffb7b3;"><input value="Add" type="submit" class="floor" onclick="ajaxAddProduct('<%=request.getSession().getAttribute("place")%>', ${nr.count - 1}, '${product.name}', '${product.id}', 1);"/></td>
        </tr>
    </c:forEach>
</span>

