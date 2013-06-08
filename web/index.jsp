<html>
	<head>
		
		<!-- JavaScript includes -->
		<script type="text/javascript" src="js/3rdParty/doT.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.class.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.ui.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.formLabels.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.form.js"></script>
		<script type="text/javascript" src="js/framework.js"></script>
		<script type="text/javascript" src="js/ui/theme.jqueryui.js"></script>
		<link rel="stylesheet" href="css/ui-lightness/jquery.ui.css" />
	</head>
	<body>
		<script type="text/javascript">
			$(document).ready(function() {
				var request = new ControllerRequest('Home');
				request.execute();
			});
		</script>
		
		<div id="header"></div>
		<div id="content"></div>
		<div id="footer"></div>
	</body>
</html>