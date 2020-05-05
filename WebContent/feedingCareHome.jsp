	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">

	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
 
		<h1>eZoo <small>Feeding Care</small></h1>
		<hr class="paw-primary">


		<table
			class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Schedule ID</th>
					<th class="text-center">Food</th>
					<th class="text-center">Notes</th>
					<th class="text-center">Feeding Time</th>
					<th class="text-center">Recurrence Time</th>
					<th class="text-center">Delete</th>

				</tr>
			</thead>

			<tbody>
				<c:forEach var="f" items="${feeding}">
					<tr>
						<td class="fscheduleid_td"><c:out value="${f.scheduleID}" />
							<form action="feedingCare" method="post" id="hform">
								<input type="hidden" name="del" value="" class="hclassform">
							</form></td>

						<td><c:out value="${f.food}" /></td>
						<td><c:out value="${f.notes}" /></td>
						<td><c:out value="${f.feedingTime}" /></td>
						<td><c:out value="${f.recurrence}" /></td>
						<td><input type="submit" value="delete" form="hform"
							class="delbtn"></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

		<h2>
			eZoo <small>Assigned Animal Feeding</small>
		</h2>
		<hr class="paw-primary">
		<table
			class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Animal ID</th>
					<th class="text-center">Animal name</th>
					<th class="text-center">Feeding ID</th>
					<th class="text-center">Food</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="l" items="${list}">
					<tr>
						<td><c:out value="${l.animalID}" /></td>
						<td><c:out value="${l.animalName}" /></td>
						<td><c:out value="${l.scheduleID}" /></td>
						<td><c:out value="${l.food}" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
