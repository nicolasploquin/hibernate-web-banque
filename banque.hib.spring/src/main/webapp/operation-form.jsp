<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"  %>
<c:import url="/entete.html" />
<c:url value="operation" var="url">
	<c:param name="idCompte">${param.idCompte}</c:param>
</c:url>
<form method="post" action="${url}">
<label for="nom">Date</label>
<input type="date" id="date" name="date" placeholder="jj/mm/aaaa" />
<br/>
<label for="libelle">Libellé</label>
<input type="text" id="libelle" name="libelle" placeholder="Libellé" />
<br/>
<label for="montant">Montant</label>
<input type="number" id="montant" name="montant" placeholder="Montant" min="0" step="0.01" />
<br/>
<label for="type">Type</label>
<select id="type" name="type">
	<option value="Credit" selected>Crédit</option>
	<option value="Debit">Débit</option>
</select>
<br/>
<button type="submit" value="create" name="action">Créer</button>
</form>
<c:url var="url" value="client">
	<c:param name="action">select-all</c:param>
</c:url>
<p><a href="${url}">Liste Clients</a></p>

<c:import url="/pied.html" />

