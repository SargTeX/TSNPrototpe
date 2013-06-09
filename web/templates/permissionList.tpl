<a href='PermissionAdd'>Neue Berechtigung erstellen</a>

<table>
	<thead>
		<tr>
			<th>Name</th>
			<th>Beschreibung</th>
			<th>Standardwert</th>
			<th colspan="3">Datentyp</th>
		</tr>
	</thead>
	<tbody>
		{{~it.permissions :permission}}
			<tr>
				<td>{{=permission.name}}</td>
				<td>{{=permission.description}}</td>
				<td>{{=permission.defaultValue}}</td>
				<td>{{=permission.type}}</td>
				<td><a href="PermissionEditController?name={{=permission.name}}">Edit</a></td>
				<td><a href="PermissionDeleteController?name={{=permission.name}}" class="delete">Delete</a></td>
			</tr>
		{{~}}
	</tbody>
</table>