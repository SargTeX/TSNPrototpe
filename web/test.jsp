<html>
	<head>
		
		<!-- JavaScript includes -->
		<script type="text/javascript" src="js/3rdParty/jquery.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.class.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.ui.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.formLabels.js"></script>
		<link rel="stylesheet" href="css/ui-lightness/jquery.ui.css" />
	</head>
	<body>
		<input id="test" type="button" value="Test" />
		<script type="text/javascript">
			$('#test').click(function() {
				$.fn.formLabels();
			});
		</script>
		<input type="text" name="username" title="Benutzername" />
	</body>
</html>