<form controller="Language{{=it.action.ucfirst()}}">
	<input name="code" label="Code" value="{{=it.code||''}}"{{?it.action == "edit"}} disabled="disabled"{{?}} />
	<input name="name" label="Name" value="{{=it.name||''}}" />
	<input name="description" label="Beschreibung" value="{{=it.description||''}}" />
	<input type="submit" label="Abschicken" />
</form>