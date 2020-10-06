<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<div class="container">
<div class="my-5 mx-5">Edit

<form:form commandName="member">
	<form:hidden path="userId"/>
	<form:hidden path="name"/>

		<div>
			<label>사용자 ID</label>
			<p>${member.userId}</p>
		</div>
		<div>
			<label for="name">Name</label>
			<p>${member.name}</p>
		</div>
		<label for="userLevel">Level</label>
		<form:select path="userLevel" items="${userLevels}" />
		
		<div class="md-form">
			<label for="nickName">Nickname</label>
			<form:input path="nickName" class="form-control"/>
		</div>
		
		<div class="md-form">
			<label for="phoneNumber">Phone Number</label>
			<form:input path="phoneNumber" class="form-control"/>
			<form:errors path="phoneNumber" element="div" cssClass="error" />
		</div>
		<div class="md-form">
			<label for="email">Email</label>
			<form:input path="email" class="form-control"/>
			<form:errors path="email" element="div" cssClass="error" />
		</div>
		<div class="md-form">
			<label for="address">Address</label>
			<form:input path="address" class="form-control"/>
		</div>

		<div class="md-form">
			<label for="password">Admin Password</label>
			<form:password path="password" class="form-control"/>
			<form:errors path="password" cssClass="error" />
			<form:errors cssClass="error" />
		</div>

		<div class="row mt-3">
			<div class="col-md-2"></div>
			<div class="col-md-20">
				<button type="submit" class="btn btn-default">Submit</button>
				<a href="../view/${member.userId}?page=${param.page}"
						class="btn btn-default">Back</a>
				
			</div>
		</div>
		</form:form>
</div>
</div>