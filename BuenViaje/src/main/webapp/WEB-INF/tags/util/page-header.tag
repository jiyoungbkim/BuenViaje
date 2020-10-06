<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="icon" required="true" type="String" %>
<%@ attribute name="title" required="true" type="String" %>


<h3 class="mt-5 mb-4">

	<c:if test="${not empty icon}">
		<i class="fas fa-${icon}"></i>		
	</c:if>
	${title}				
</h3>
