<html>
	<head>
		
		<!-- JavaScript includes -->
		<script type="text/javascript" src="js/3rdParty/doT.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.class.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.ui.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.formLabels.js"></script>
		<script type="text/javascript" src="js/framework.js"></script>
		<script type="text/javascript" src="js/ui/theme.jqueryui.js"></script>
		<link rel="stylesheet" href="css/ui-lightness/jquery.ui.css" />
	</head>
	<body>
		<input id="test" type="button" value="Test" />
		<script type="text/javascript">
			$('#test').click(function() {
				$.fn.formLabels();
				var request = new ControllerRequest('UserLogin');
				request.execute();
			});
		</script>
		<input type="text" name="username" title="Benutzername" />
		
		<div id="content"></div>
	</body>
</html>