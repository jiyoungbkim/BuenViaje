<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<div class="container">

<h3 class="my-5">My Information</h3>

	<form:form commandName="member" enctype="multipart/form-data">
		<form:hidden path="userId" />
		<form:hidden path="name" />
		<div>
			<label>User ID</label>
			<p><img src="avata/${USER.userId}"
				class="rounded-circle avata-sm">
				${member.userId}</p>
		</div>    
		
		<div>
			<label for="name">Name</label>
			<p>${member.name}</p>
		</div>
		<div>
			<label>Avatar</label>
			<p><input type="file" name="avata"/></p>
		</div>
		<div>
			<label for="name">User Level</label>
			<p>${member.userLevel}</p>
		</div>
		
		<div>
			<label for="name">Nick Name</label>
			<form:input path="nickName" class="form-control" />
		</div>
		
		<div>
			<label for="name">Phone Number</label>
			<form:input path="phoneNumber" class="form-control" />
		</div>
		
		<div>
			<label for="name">Email</label>
			<form:input path="email" class="form-control" />
		</div>
		
		<div>
			<label for="name">Address</label>
			<form:input path="address" class="form-control" />
		</div>
		
		<div>
			<label for="name">Password</label>
			<form:password path="password" class="form-control"/>
			<form:errors cssClass="error"/>
		</div>
		
		<div class="row mt-3">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				<button type="submit" class="btn btn-default">Submit</button>
				<a href="view" class="btn btn-default">Back</a>
			</div>		
		</div>
	</form:form>
</div>