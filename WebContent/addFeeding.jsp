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
	
		<h1>eZoo <small>Add Feeding</small></h1>
		<hr class="paw-primary">
		
		<form action="addFeeding" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <label for="id" class="col-sm-4 control-label">ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="id" name="id" placeholder="ID" required="required"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="food" class="col-sm-4 control-label">Food</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="food" name="food" placeholder="Food" required="required"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">Notes</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="notes" name="notes" placeholder="Notes" required="required"/>
		    </div>
		  </div>
		 
		  <div class="form-group">
		    <label for="ftime" class="col-sm-4 control-label">Feeding Time</label>
		    <div class="col-sm-4">
		    
		    		 <input type="text" id=""
					name="hour" size="15" maxlength="2" style="text-align: right;">
					
					: <input type="text" id="" name="minutes" size="15" maxlength="2"
					style="text-align: right;">
						
					<select required="required" name="am_pm">
					<option value="AM">AM</option>
					<option value="PM">PM</option>
				</select>
				
				</div>
			</div>

			<div class="form-group">
				<label for="rtime" class="col-sm-4 control-label">Recurrence
					Time</label>
				<div class="col-sm-4">

					<input type="text" id="" name="rhour" size="15" maxlength="2"
						style="text-align: right;"> : <input type="text" id=""
						name="rminutes" size="15" maxlength="2" style="text-align: right;">

					<select required="required" name="ram_pm">
						<option value="AM">AM</option>
						<option value="PM">PM</option>
					</select>

				</div>
			</div>

			<div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <input type="submit" class="btn btn-primary" name="add" value="Add">
		      
		    </div>
		    <div class="col-sm-offset-4 col-sm-1">
		      <input type="submit" class="btn btn-primary" style="margin-left: -400px;" name="update" value="Update">
		      
		    </div>
		   
		  </div>

		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />