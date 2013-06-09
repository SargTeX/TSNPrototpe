<html>
	<head>
		
		<!-- JavaScript includes -->
		<script type="text/javascript" src="js/3rdParty/jquery.js"></script>
		<script type="text/javascript" src="js/3rdParty/jquery.class.js"></script>
		<script type="text/javascript" src="js/framework.js"></script>
		
		<link rel="stylesheet" href="css/3rdParty/ui-lightness/jquery.ui.css" />
		<link rel="stylesheet" href="css/3rdParty/superfish.css" />
		<link rel="stylesheet" href="css/basic.css" />
		
		<script type="text/javascript">
			core.load([
				'util/String',
				'3rdParty/doT',
				'3rdParty/jquery.ui',
				'3rdParty/jquery.formLabels',
				'3rdParty/jquery.form',
				'3rdParty/jquery.superfish',
				'util/Gui',
				'util/jquery',
				'util/Request'
			]);
			core.loadTheme('ui/theme.jqueryui', function() {return new JQueryUiTheme();});
		</script>
	</head>
	<body>
		<script type="text/javascript">
			$(document).ready(function() {
				var request = new ControllerRequest('Home');
				request.execute();
			});
		</script>
		
		<p><div id="header"></div></p>
		<p><div id="content"></div></p>
		<p><div id="footer"></div></p>
	</body>
</html>