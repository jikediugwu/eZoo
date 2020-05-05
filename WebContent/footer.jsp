<!-- Footer -->
<footer class="text-center">

	<div class="footer-below">
		<div style="color:blue; float:left;">
			<ul class="footer-links">
				<li class="footer-links-li"><a href="#">Contact Us</a></li>
				<li class="footer-links-li"><a href="#">Careers</a></li>
				<li class="footer-links-li"><a href="#">Terms & Conditions</a></li>
				<li class="footer-links-li"><a href="#">FAQs</a></li>
				<li class="footer-links-li"><a href="#">Locations</a></li>
				
				<li class="footer-links-li"><a href="#">Cookie policy</a></li>
				<li class="footer-links-li"><a href="#">Press</a></li>
				<li class="footer-links-li"><a href="#">Accessibility</a></li>
				<li class="footer-links-li"><a href="#">Privacy policy</a></li>
				<li class="footer-links-li"><a href="#">Sitemap</a></li>
			</ul>

		</div>

		<div style="color: blue; margin-right: 100px; float: right;">
			<div style="margin-bottom: 15px; color: #ffffff;">Follow us</div>
			<a href="#" class="fa fa-facebook"></a> <a href="#"
				class="fa fa-twitter"></a> <a href="#" class="fa fa-linkedin"></a> <a
				href="#" class="fa fa-youtube"></a> <a href="#"
				class="fa fa-instagram"></a>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-12" style="margin-top: 100px;">Copyright &copy; EZOO 2020</div>
			</div>
		</div>
	</div>
</footer>
	
<!-- jQuery -->
<script>
	$(document).ready(function() {
		$(".delbtn").click(function() {

			var $row = $(this).closest("tr");
			var $text = $row.find(".fscheduleid_td").text();
			$(".hclassform").attr("value", $text);

		});
	});
</script>

<script src="resources/libraries/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="resources/libraries/js/bootstrap.min.js"></script>

<!-- Plugin JavaScript -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="resources/libraries/js/classie.js"></script>
<script src="resources/libraries/js/cbpAnimatedHeader.js"></script>

<!-- Contact Form JavaScript -->
<script src="resources/libraries/js/jqBootstrapValidation.js"></script>

<!-- Custom Theme JavaScript -->
<script src="resources/libraries/js/freelancer.js"></script>

<!--     jQuery DataTables -->
<script type="text/javascript"
	src="https://cdn.datatables.net/t/bs/dt-1.10.11/datatables.js"></script>

<!--     Custom Javascript -->
<script src="resources/scripts/custom.js"></script>

</body>
</html>