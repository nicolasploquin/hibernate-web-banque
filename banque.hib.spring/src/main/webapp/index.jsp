<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"  %>
<c:import url="/entete.html" />

<a href="client?action=select-all">Liste des clients </a> - 
<a href="client-form.jsp">Créer un client</a>

<c:url var="url" value="client">
	<c:param name="action">select-critere</c:param>
</c:url>
<form method="post" action="${url}" >
	<input type="search" id="critere" name="critere" placeholder="Rechercher" />
	<button type="submit">Rechercher</button>
</form>


<%-- Affichage de la liste des clients --%>
<c:if test="${not empty requestScope.clients}">
<h2>Liste des clients</h2>
<c:forEach var="client" items="${requestScope.clients}">
<c:url var="urlDetail" value="client">
	<c:param name="action">select</c:param>
	<c:param name="idClient">${client.idClient}</c:param>
</c:url>
<c:url var="urlModifier" value="client-form.jsp">
	<c:param name="action">update</c:param>
	<c:param name="idClient">${client.idClient}</c:param>
	<c:param name="nom">${client.nom}</c:param>
	<c:param name="prenom">${client.prenom}</c:param>
</c:url>
<p>${client.prenom} ${client.nom} <a href="${urlDetail}" class="button">&#xE0F4;</a><a href="${urlModifier}" class="button">&#xE088;</a></p>
</c:forEach>
</c:if><!-- liste clients -->

<c:import url="/pied.html" />

