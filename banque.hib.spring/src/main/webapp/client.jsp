<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"  %>
<c:import url="/entete.html" />

<h2>${requestScope.client.prenom} ${requestScope.client.nom}</h2> 
<div class="liste comptes">
<table><tbody>
<c:forEach var="compte" items="${requestScope.client.comptes}">
<c:url var="url" value="compte">
	<c:param name="action">select</c:param>
	<c:param name="idCompte">${compte.idCompte}</c:param>
</c:url>
<tr><td>${compte.numero}</td><td><fmt:formatNumber value="${compte.solde}" type="currency" currencySymbol="€" /></td><td><a href="${url}"><img src="img/info_26.png" alt="détails" /></a></td></tr>
</c:forEach>
</tbody></table>
</div>
<c:url var="urlVirement" value="client">
	<c:param name="action">virement-form</c:param>
	<c:param name="idClient">${requestScope.client.idClient}</c:param>
</c:url>
<p><a href="${urlVirement}">Effectuer un virement</a></p>

<c:import url="/pied.html" />
