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
	
		<h1>eZoo <small>Assign Animal Feeding</small></h1>
		<hr class="paw-primary">
		
		<form action="assignFeeding" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <label for="id" class="col-sm-4 control-label">Feeding Schedule ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="fid" name="fid" placeholder="Feeding Schedule ID" required="required"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="food" class="col-sm-4 control-label">Animal ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="aid" name="aid" placeholder="Animal ID" required="required"/>
		    </div>
		  </div>
		  
		  
		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">Animal Name</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="aname" name="aname" placeholder="Animal Name" />
		    </div>
		  </div>
		 

			<div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <input type="submit" class="btn btn-primary" name="assign" value="Assign">
		      
		    </div>
		    <div class="col-sm-offset-4 col-sm-1">
		      <input type="submit" class="btn btn-primary" style="margin-left: -400px;" name="unassign" value="Unassign">
		      
		    </div>
		   
		  </div>

		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />