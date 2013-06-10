<a href="LanguageAdd">Neue Sprache erstellen</a>

<table>
	<thead>
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Beschreibung</th>
			<th colspan="3">Aktionen</th>
		</tr>
	</thead>
	<tbody>
		{{~it.languages :language}}
			<tr>
				<td>{{=language.code}}</td>
				<td>{{=language.name}}</td>
				<td>{{=language.description}}</td>
				<td><a href="LanguageItemTranslationListController?languageCode={{=language.code}}">Übersetzungen anzeigen</a></td>
				<td><a href="LanguageEditController?code={{=language.code}}">Edit</a></td>
				<td><a href="LanguageDeleteController?code={{=language.code}}" class="delete">Delete</a></td>
			</tr>
		{{~}}
	</tbody>
</table>