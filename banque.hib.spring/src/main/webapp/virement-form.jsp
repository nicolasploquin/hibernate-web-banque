<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"  %>
<c:import url="/entete.html" />
<c:url value="client" var="url">
	<c:param name="idClient">${param.idClient}</c:param>
</c:url>
<form method="post" action="${url}">
<label for="idCompteDebit">À débiter</label>
<select id="idCompteDebit" name="idCompteDebit" >
	<option value="" selected>(à débiter)</option>
<c:forEach var="compte" items="${requestScope.client.comptes}">
	<option value="${compte.idCompte}">${compte.numero} - ${compte.solde}</option>
</c:forEach>
</select>
<br/>
<label for="libelleDebit">Libellé Débit</label>
<input type="text" id="libelleDebit" name="libelleDebit" placeholder="Libellé Débit" />
<br/>
<br/>
<label for="idCompteCredit">À créditer</label>
<select id="idCompteCredit" name="idCompteCredit">
	<option value="" selected>(à créditer)</option>
<c:forEach var="compte" items="${requestScope.client.comptes}">
	<option value="${compte.idCompte}">${compte.numero} - ${compte.solde}</option>
</c:forEach>
</select>
<br/>
<label for="libelleCredit">Libellé Crédit</label>
<input type="text" id="libelleCredit" name="libelleCredit" placeholder="Libellé Crédit" />
<br/>
<br/>
<label for="montant">Montant</label>
<input type="number" id="montant" name="montant" placeholder="Montant" min="0" step="0.01" />
<br/>
<button type="submit" value="virement" name="action">Valider</button>
</form>
<c:url var="url" value="client?action=select-all" />
<p><a href="${url}">Liste Clients</a></p>

<c:import url="/pied.html" />
