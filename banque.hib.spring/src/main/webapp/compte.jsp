<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"  %>
<c:import url="/entete.html" />

<!-- Affichage d'un client -->
<c:if test="${not empty requestScope.compte}">
<h2>Compte n°${requestScope.compte.numero}</h2>
<p>${requestScope.compte.client.prenom} ${requestScope.compte.client.nom}</p>

<div class="liste operations">
<table><tbody>
<c:forEach var="operation" items="${requestScope.compte.operations}">
<tr><td><fmt:formatDate value="${operation.date}" pattern="dd/MM/yyyy" /></td><td>${operation.libelle}</td><td><fmt:formatNumber value="${operation.montant}" type="currency" currencySymbol="€" /></td><td><small>${operation["class"].simpleName}</small></td></tr>
</c:forEach>
</tbody></table>
</div>
<p><a href="operation-form.jsp?idCompte=${requestScope.compte.idCompte}" >Ajouter une opération</a></p>

</c:if><!-- compte -->



<c:import url="/pied.html" />
