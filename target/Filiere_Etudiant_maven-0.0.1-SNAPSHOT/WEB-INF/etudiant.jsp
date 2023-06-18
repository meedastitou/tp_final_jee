<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style>
	<%@include file="layout/style.css" %>
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="templates/header.jsp" %>
	<h1 style="margin-top:40px; text-align: center;"> Gestion des Etudiants </h1>
	<hr />
	
	<div style="width:50%; position:relative; left:25%; margin-top:60px">
		<form action="etudiant/save" method="post">
			<fieldset>
				<legend>Ajouter Etudiant</legend>
					<table border="0" cellpadding="5" cellspacing="10" width="100%">
						<tr>
							<td> ID </td>
							<td>
								<input type="number" 
										<c:if test="${ etudiantBean.etudiant.id > 0}">
										value="${etudiantBean.etudiant.id }"
										</c:if>	
										required
										readonly="true"
										style="background-color:silver; border: 1px solid black;"
								/> 							
							</td>
						</tr>
						<tr>
							<td>Nom</td>
							<td>
								<input type="text"
										name="name"
										value="${ etudiantBean.etudiant.nom}"
										required
										
								/> 
							</td>
						</tr>
						<tr>
							<td>prenom</td>
							<td>
								<input type="text"
										name="prenom"
										value="${ etudiantBean.etudiant.prenom}"
										required
										
								/> 
							</td>
						</tr>
						<tr>
							<td>cne</td>
							<td>
								<input type="number"
										name="cne"
										<c:if test="${ etudiantBean.etudiant.cne > 0}">
										value="${etudiantBean.etudiant.cne }"
										</c:if>	
										required
								/> 
							</td>
						</tr>
						<tr>
							<td>Filiere</td>
							<td>
								<select name="filiere" style="width:100%">
									<option value="0" style="color: #ced4da;">choisir filiere</option>
									<c:forEach var="f" items="${filiereBean.filieres }">
										<option 
												value="${f.idFiliere }"
												<c:if test="${f.idFiliere == etudiantBean.etudiant.filiere.idFiliere }">selected</c:if> >
												${f.code }
											</option>
									</c:forEach>
								</select>
								
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: right;"> 
								<input type="submit" 
									   value="${etudiantBean.label}"/> 
							</td>
						</tr>
					</table>
			</fieldset>
		</form>		
	</div>
	<div style="position:relative; width:60%; left:20%; margin-top:30px">
		<table border="1" width="100%" cellpadding="5" style="text-align: center;">
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Cne</th>
				<th>Filiere</th>
				<th>Modifier</th>
				<th>Supprimer</th>
			</tr>
		
			<c:forEach items="${etudiantBean.etudiants}" var="e">
				<tr>
					<td>${e.id}</td>
					<td>${e.nom}</td>
					<td>${e.prenom}</td>
					<td>${e.cne}</td>
					<td>${e.filiere.code}</td>					
					<td>
						<a href="etudiant/modifyContext?id=${e.id}">
							<img src="img/edit.png" />
						</a>
					</td>
					<td>
						<a href="etudiant/delete?id=${e.id}">
							<img src="img/drop.png" />
						</a>
					</td>
				</tr>
			</c:forEach>
		
			<tfoot>
				<tr>
					<td colspan="7" style="text-align: center;">
					Nombre total des filieres est : ${etudiantBean.etudiants.size()}
					</td>
				</tr>	
			</tfoot>
		</table>
	</div>
</body>
</html>