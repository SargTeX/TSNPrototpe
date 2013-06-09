<form controller="Usergroup{{?it.id}}Edit{{??}}Add{{?}}">
	<input type="text" name="name" label="Name" value="{{=it.name}}" />
	<input type="text" name="description" label="Beschreibung" value="{{=it.description}}" />
	{{?it.id}}<input type="hidden" name="id" value="{{=it.id}}" />{{?}}
	<input type="submit" label="Abschicken" />
</form>