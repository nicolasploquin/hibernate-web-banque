<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"  %>
<c:import url="/entete.html" />

<h2>Client</h2>
<c:url var="url" value="client">
	<c:param name="action">${(not empty param.action)?param.action:"create"}</c:param>
	<c:param name="idClient">${param.idClient}</c:param>
</c:url>
<form method="post" action="${url}" >
	<label for="nom">Nom</label>
	<input type="text" id="nom" name="nom" value="${param.nom}" placeholder="Nom" required />
	<br />
	<label for="prenom">Prenom</label>
	<input type="text" id="prenom" name="prenom" value="${param.prenom}" placeholder="Prenom" />
	<br />
	<button type="submit">&#xE001; Enregistrer</button>
	<a href="client?action=select-all" class="button">&#xE0A4; Annuler</a>
</form>




<c:import url="/pied.html" />

