

<%@page contentType="text/javascript" pageEncoding="UTF-8"
        import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>d">
<link rel=StyleSheet href="../layout.css" type="text/css" media=screen>
<span>
    <% boolean rowodd = false; %>
    <c:forEach var="category" items="${subcategories}" varStatus="nr">
        <% rowodd = !rowodd; %>
        <tr class="<%= rowodd ? "odd" : "even" %>">
            <td class="category" colspan="4" onclick="retrieveURLforCategories('productAjaxAction.do?categoryId=${category.id}&mode=1', '${category.id}');update();">${category.name}</td>
        </tr>
        <tr>
            <td colspan="4"><div id="${category.id}"></div></td>
        </tr>
    </c:forEach>
    <% ArrayList products = (ArrayList) request.getSession().getAttribute("products");%>
    <c:forEach var="product" items="${products}" varStatus="nr">
        <% rowodd = !rowodd; %>
        <tr id="${nr.count - 1}" class="<%= rowodd ? "odd" : "even" %>">
            <td class="name">${product.name}</td>
            <td class="normal"><fmt:formatNumber type="currency" value="${product.priceSell + product.priceSell * rates[nr.count - 1]}" maxFractionDigits="2" minFractionDigits="2"/></td>
            <td class="normal"></td>
            <td><input value="Add" type="submit" class="floor" onclick="ajaxAddProduct('<%=request.getSession().getAttribute("place")%>', ${nr.count - 1}, '${product.name}', '${product.id}', 0);"/></td>
        </tr>
    </c:forEach>
</span>
