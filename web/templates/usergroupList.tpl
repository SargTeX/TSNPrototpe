<a href='UsergroupAdd'>Neue Benutzergruppe erstellen</a>


<table>
	<thead>
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Beschreibung</th>
			<th colspan="3">Anzahl User</th>
		</tr>
	</thead>
	<tbody>
		{{~it.usergroups :group:value}}
			<tr>
				<td>{{=group.id}}</td>
				<td>{{=group.name}}</td>
				<td>{{=group.description}}</td>
				<td>{{=group.userCount}}</td>
				<td><a href='UsergroupEditController?id={{=group.id}}'>Edit</a></td>
				<td><a href='UsergroupDeleteController?id={{=group.id}}'>Delete</a></td>
			</tr>
		{{~}}
	</tbody>
</table>