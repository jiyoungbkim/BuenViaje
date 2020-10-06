<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <ul class="pagination justify-content-center pagination-info" style="margin: 20px 0">
	<c:if test="${ page != 1}">
		<li id="prev" class="page-item"><a class="page-link" href="?page=${ page-1 }">
							<i class="fas fa-arrow-left"></i></a></li>
	</c:if>
		<c:forEach var="cnt" begin="1" end="${ totalPage }">
			<li class="page-item <c:if test="${ page == cnt }">active</c:if>">
				<!-- href 에서 ? 를 지정해주게 되면 현재 페이지에서 query 부분만 바꾸겠다는 뜻 -->
				<!-- view?page=${cnt} 하게 되면 파일 디렉토리를 변경하겠다는 뜻  -->
				<a class="page-link" href="?page=${ cnt }">${ cnt }</a>
			</li>
		</c:forEach>
	<c:if test="${ page != totalPage}">
		<li id="prev" class="page-item"><a class="page-link" href="?page=${ page+1 }">
							<i class="fas fa-arrow-right"></i></a></li>
	</c:if>
</ul> --%>
<ul class="pagination pagination-circle justify-content-center pagination-info"
	style="margin: 20px 0">
	<!--First-->
	<li class="page-item disabled"><a class="page-link">First</a></li>

	<!--Arrow left-->
	<li class="page-item disabled"><a class="page-link"
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
			class="sr-only">Previous</span>
	</a></li>

	<c:if test="${pagination.currentBlock > 1}">
		


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
		
	</c:if>
	<!--Arrow right-->
	<li class="page-item"><a class="page-link" aria-label="Next">
			<span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
	</a></li>

	<!--First-->
	<li class="page-item"><a class="page-link">Last</a></li>
</ul>
