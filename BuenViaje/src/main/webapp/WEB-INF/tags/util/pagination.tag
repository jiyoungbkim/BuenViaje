<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<ul class="pagination justify-content-center pagination-info"
	style="margin: 20px 0">
	<c:if test="${pagination.currentBlock > 1}">
		<li id="prev" class="page-item">
			<a class="page-link" href="?page=1">처음</a>
		</li>
	

		<li class="page-item">
			<a class="page-link" href="?page=${pagination.prevBlockPage}">
			<i class="fas fa-arrow-left"></i></a>
		</li>
	</c:if>

	<c:forEach var="cnt" 
		begin="${pagination.startPage}"
		end="${pagination.endPage}">
		<li class="page-item 
			<c:if test="${pagination.page==cnt}">active</c:if>">
			<a class="page-link" href="?page=${cnt}">${cnt}</a>
		</li>
	</c:forEach>
	
	<c:if test="${pagination.currentBlock < pagination.totalBlock}">
		<li class="page-item">
			<a class="page-link" href="?page=${pagination.nextBlockPage}">
			<i class="fas fa-arrow-right"></i></a>
		</li>
		<li class="page-item">
			<a class="page-link" href="?page=${pagination.totalPage}">마지막</a>
		</li>
	</c:if>
</ul>