Hello World! Login here!

<form controller="UserLogin">
	<input type="text" name="username" label="Benutzername" value="{{=it.username || ''}}" />
	<input type="password" name="password" label="Passwort" />
	<input type="submit" label="Abschicken" />
</form>