<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<jsp:include page="header.jsp"></jsp:include>

<body>

	<jsp:include page="theme-loader.jsp"></jsp:include>

	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbar-main-menu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<h3>Cadastro de Usuário</h3>
										

										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													
													<div class="card-block">

														<form class="form-material" method="post" action="<%=request.getContextPath()%>/ServletUserController" id="formUser">
														
														<input type="hidden" name="action" id="action" value="">
														
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLogin.id }">
																<span class="form-bar"></span>
																<label class="float-label">Id:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="name" id="name" class="form-control" required="required" value="${modelLogin.name }">
																<span class="form-bar"></span>
																<label class="float-label">Nome:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${modelLogin.email }">
																<span class="form-bar"></span>
																<label class="float-label">E-mail:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login" class="form-control" required="required" value="${modelLogin.login }">
																<span class="form-bar"></span>
																<label class="float-label">Login:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="password" name="password" id="password" class="form-control" required="required" autocomplete="off" value="${modelLogin.password }">
																<span class="form-bar"></span>
																<label class="float-label">Senha:</label>
															</div>
															
															<button type="button" class="btn btn-primary waves-effect waves-light" onclick="cleanForm();">Novo</button>
															<button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
															<button type="button" class="btn btn-danger waves-effect waves-light" onclick="deletar();">Excluir</button>
															
												

														</form>
													
													</div>
												</div>
											</div>
										</div>
										<span>${msg}</span>

									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="js-file.jsp"></jsp:include>
	
	<script type="text/javascript">
		function cleanForm() {
			let elements = document.getElementById("formUser").elements;
			
			for (let i = 0; i < elements.length; i++) {
				elements[i].value = "";
			}
		}
		
		function deletar() {
			
			if (confirm("Deseja realmente excluir usuário?")){
				document.getElementById("formUser").method = "get"; 
				document.getElementById("action").value = "deletar"; 
				document.getElementById("formUser").submit();
					
			}
			
		}
		
		function deleteAjax() {
			
			if (confirm("Deseja realmente excluir usuário?")){
				
				let urlAction = document.getElementById("formUser").action;
				let idUser = document.getElementById("id").value; 
				
				$.ajax({
					method: "get", 
					url: urlAction, 
					data: "id=" + idUser + "&action=deletarAjax", 
					success: function (response) {
						alert(response);
					}
					
				}).fail(function(xhr, status, errorThrow) {
					alert("Falha ao deletar usuário! Erro: " + xhr.responseText);
				});
				
			}
		}
	</script>
	
</body>

</html>