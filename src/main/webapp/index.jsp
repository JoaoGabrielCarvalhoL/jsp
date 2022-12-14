<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Curso JSP</title>

<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 35%;
	right: 35%
}

h1 {
	position: absolute;
	top: 30%;
	left: 35%;
}

h5 {
	position: absolute;
	top: 60%;
	left: 35%;
}
</style>

</head>
<body>

	<h1>Bem vindo!</h1>

	<form action="<%=request.getContextPath()%>/ServletLogin" method="post"
		class="row g-3 needs-validation" novalidate>
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">

		<div class="col-md-6">
			<label class="form-label">Login</label> <input type="text"
				class="form-control" name="Email" required="required">
				<div class="valid-feedback">
		
				</div>
				<div class="invalid-feedback">
					Campo obrigatório.
				</div>
		</div>

		<div class="col-md-6">
			<label class="form-label">Senha</label> <input name="Senha"
				type="password" class="form-control" required="required">
				<div class="valid-feedback">
					
				</div>
				<div class="invalid-feedback">
					Campo obrigatório.
				</div>
		</div>

		<input type="submit" value="Acessar" class="btn btn-primary">

	</form>
	<h5>${msg}</h5> 
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
</body>
</html>