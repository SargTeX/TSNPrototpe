<div class="formFieldLabel">
	<label for="{{=it.permission.name}}">{{=it.permission.name}}</label>
</div>
<div class="formField">
	{{=PermissionUtil.getSetTemplate(it.permission, it.permissionValues[it.permission.name].value)}}
</div>