<%-- 
    Document   : view
    Created on : 7 nov. 2018, 13:25:16
    Author     : pedago
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page de gestion des codes de promotion</title>
    </head>
    <body>
         <h1>Edition des taux de remise</h1>
        
        <form method='GET'>
                    Code : <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}+" title="Une lettre en MAJUSCULES"><br/>
		    Taux : <input name="taux" type="number" step="0.01" min="0.0" max="99.99" size="5"><br/>
			<input type="hidden" name="action" value="ADD">
			<input type="submit" value="Ajouter">
	</form>
         
        <div>
            <h4>${confirmationAction}</h4>
        </div>
        
        <div>
            <p></p>
            <table border="1">
                <tr><th>Code</th><th>Taux</th><th>Action</th></tr>
                
                <c:forEach var="ligne" items="${fullTable}">
                    <tr>
                        <td>${ligne.code}</td>
                        <td><fmt:formatNumber type="number" pattern="00.00" value="${ligne.taux}" /> %</td>
                        <td>
                            <c:choose>
                                <c:when test="${usedCodes.contains(ligne.code)}">
                                    Utilisé
                                </c:when>
                                <c:otherwise>
                                    <a href="?action=DELETE&code=${ligne.code}">Supprimer</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>                
            </table>
        </div>
    </body>
</html>
