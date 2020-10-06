<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
$(function() {
	$('.delete-btn').click(function(e){
		e.preventDefault(); //디폴트로 이동하는 것 막음
		var result = confirm('${member.userId} 회원을 삭제할까요?');
		if(result) {
			location = $(this).attr("href");
		}
	})	
});

</script>

<div class="container">
<h3 class="my-5">Member Information</h3>

<div class="row">
	<div class="col-md-2">user ID</div>
	<div class="col-md-10">${member.userId}</div>
</div>

	<div class="row">
		<div class="col-md-2">Name</div>
		<div class="col-md-10">${member.name}</div>
	</div>
	<div class="row">
		<div class="col-md-2">user Level</div>
		<div class="col-md-10">${member.userLevel}</div>
	</div>
	<div class="row">
		<div class="col-md-2">Nickname</div>
		<div class="col-md-10">${member.nickName}</div>
	</div>
	<div class="row">
		<div class="col-md-2">Phone number</div>
		<div class="col-md-10">${member.phoneNumber}</div>
	</div>
	<div class="row">
		<div class="col-md-2">Email</div>
		<div class="col-md-10">${member.email}</div>
	</div>
	<div class="row">
		<div class="col-md-2">Address</div>
		<div class="col-md-10">${member.address}</div>
	</div>
	<div class="row">
		<div class="col-md-2">Create Date</div>
		<div class="col-md-10">${member.regDate}</div>
	</div>
	<div class="row">
		<div class="col-md-2">Update Date</div>
		<div class="col-md-10">${member.updateDate}</div>
	</div>

<div class="row mt-3">
	<div class="col-md-2"></div>
	<div class="col-md-20">
	<a href="../edit/${member.userId}?page=${param.page}"
		class="btn btn-default btn-sm">Edit</a> <a
		href="../delete/${member.userId}?page=${param.page}"
		class="btn btn-default btn-sm">Delete</a> <a
		href="../list?page=${param.page}" class="btn btn-default btn-sm">Back</a>
	</div>
</div>
</div>
