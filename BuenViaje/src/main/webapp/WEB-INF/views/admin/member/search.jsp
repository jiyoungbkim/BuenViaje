<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3 class="my-5"></h3>

<div class="text-right">
	Total : ${list.size()} 명
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
					<td>${status.index+1}</td>
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
	
<div class="text-center">
	<form action ="search">
		검색 :
		<select name="category">
			<option value="user_id">User ID</option>
			<option value="name">Name</option>
			<option value="email">Email</option>
			
		</select>
		
		<input type="text" name="keyword" required>
		<button type="submit"><i class="fas fa-search"></i></button>
		
	</form>
</div>