<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container">

<h3 class="my-5">My Information</h3>

	<div class="row">
		<div class="col-md-2">User ID</div>
		<div class="col-md-10">
			<img src="avata/${USER.userId}"
				class="rounded-circle avata-md"><br>
			${USER.userId}		
		</div>	
	</div>

	<div class="row">
		<div class="col-md-2">Name</div>
		<div class="col-md-10">${USER.name}</div>
	</div>
	
	<div class="row">
		<div class="col-md-2">Email</div>
		<div class="col-md-10">${USER.email}</div>
	</div>
	
	<div class="row">
		<div class="col-md-2">Phone Number</div>
		<div class="col-md-10">${USER.phoneNumber}</div>
	</div>
	
	<div class="row">
		<div class="col-md-2">Nick Name</div>
		<div class="col-md-10">${USER.nickName}</div>
	</div>
	
	<div class="row">
		<div class="col-md-2">Address</div>
		<div class="col-md-10">${USER.address}</div>
	</div>

	<div class="row mt-3">
		<div class="col-md-2"></div>
		<div class="col-md-10">
			<a href="edit" class="btn btn-default btn-sm">Edit</a>
		</div>	
	</div>

</div>   