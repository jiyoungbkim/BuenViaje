<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">

<h3 class="my-5">Member List</h3>


	
<div class="my-3 text-right">

	(총 :${pagination.total}건)
</div>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>ID</th>
				<th>Name</th>
				<th>PhoneNumber</th>
				<th>Email</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}" varStatus="status">
				<fmt:formatDate var="regDate" value="${item.regDate}" pattern="yyyy-MM-dd"/>
				<tr>
					<%-- <td>${total - item.seq + 1}</td> --%>
					<td>${pagination.total - (pagination.start +
						status.index)+1}</td>
					<td>
						<%-- <a href="view/${item.userId}?page=${page}">${item.userId}</a> --%>
						<a href="view/${item.userId}?page=${pagination.page}">${item.userId}</a>
						<c:if test="${today==regDate}">
							<span class="badge badge-pill badge-danger">
								<i class="fas fa-tag"></i> New
							</span>
						</c:if>
					</td>
					<td>${item.name}</td>
					<td>${item.phoneNumber}</td>
					<td>${item.email}</td>
					<td>${regDate}</td>
				</tr>	
			</c:forEach>			
		</tbody>
	</table>
	
<jsp:include page="/WEB-INF/views/common/pagination.jsp"/>	

	<%-- <div class="text-center">
		<form action ="search">
			<button type="button"
				class="btn btn-default btn-sm"
				data-toggle="dropdown">
				Dropdown
			</button>
			
			<div class="dropdown-menu">
				<a class="dropdown-item" href="#">사원 ID</a>
				<a class="dropdown-item" href="#">이름</a>
				<a class="dropdown-item" href="#">Email</a>
			</div>
			
		</div>
	<input type="text" class="form-control" placeholder="Search">
	</div>
		 --%>

	<div class="row">
	<form action ="search" class="form-inline mx-auto">
		
		<select name="category" class="btn btn-primary dropdown-toggle btn-sm">
			<option value="user_id">User ID</option>
			<option value="name">Name</option>
			<option value="email">Email</option>
			
		</select>
		
		<!-- <input type="text" name="keyword" required>
		<button type="submit"><i class="fas fa-search"></i></button> -->
		
		<input class="form-control" type="text" placeholder="Search" aria-label="Search" name="keyword" required>
        <button class="btn btn-mdb-color btn-rounded btn-sm my-0 ml-sm-2" type="submit">Search</button>
		
	</form>
	</div>
</div>