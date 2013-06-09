<form controller="Permission{{=it.action.ucfirst()}}">
	<input type="text" name="name" label="Name" value="{{=it.name||''}}"{{?it.action == 'name'}} disabled="disabled"{{?}} />
	<input type="text" name="description" label="Beschreibung" value="{{=it.description||''}}" />
	<input type="text" name="defaultValue" label="Standardwert" value="{{=it.defaultValue||''}}" />
	<input type="text" name="type" label="Datentyp" value="{{=it.type||''}}" />
	<input type="submit" label="Abschicken" />
</form>