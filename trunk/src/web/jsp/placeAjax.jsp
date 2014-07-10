

<%@page contentType="text/javascript" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <span>
<input type="text" id="input${lineNo}" size="3" onchange="getIndexBackByEditing('${lineNo}', '${place}');" value="<fmt:formatNumber type="number" value="${line.multiply}" maxFractionDigits="2" minFractionDigits="0"/>" /> <fmt:formatNumber type="currency" value="${line.value}" maxFractionDigits="2" minFractionDigits="2"/>

 </span>
 <span>
     Total:  <fmt:formatNumber type="currency" value="${total}" maxFractionDigits="2" minFractionDigits="2" />
 </span>
