<a href="LanguageItemAdd">Item hinzufügen</a>
<a href="LanguageItemTranslateController?languageCode={{=it.languageCode}}">Item übersetzen</a>

<table>
	<thead>
		<tr>
			<th>Name</th>
			<th>Übersetzung</th>
		</tr>
	</thead>
	<tbody>
		{{~it.items :item}}
			<row>
				<item>{{=item.name}}</item>
				<item class="editable">{{=item.translation}}</item>
			</row>
		{{~}}
	</tbody>
</table>