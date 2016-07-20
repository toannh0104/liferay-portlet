<%@include file="init.jsp"%>

<portlet:renderURL var="addEmployeeUrl">
	<portlet:param name="render" value="employeeForm"/>
</portlet:renderURL>
<portlet:renderURL var="viewEmployeeUrl">
	<portlet:param name="render" value="viewEmployee"/>
</portlet:renderURL>

<div class="row-fluid">
<div class="alert alert-success fade in"
	<c:if test="${empty success }"> 
		style="display: none;"
	</c:if>
	>
		<i class="icon-remove close" data-dismiss="alert"></i>
	<strong>Success!</strong> <span class="message">${success}</span>		
</div>
<div class="alert alert-danger fade in"
	<c:if test="${empty error }"> 
		style="display: none;"
	</c:if>
	>
		<i class="icon-remove close" data-dismiss="alert"></i>
	<strong>Error!</strong><span class="message">${error}</span>
</div>
<p class="pull-right">
<a href="${addEmployeeUrl}" class="btn btn-primary">
	<i class="icon-plus"></i> <spring:message code="label.newEmployee" />
</a>
</p>
<p class="pull-left">
	<label>Search: <input type="text" id="search-input"></label>
</p>
<table id="employee-datatable" class="table table-striped table-bordered">
	<thead>
		<tr>
			<th><spring:message code="label.firstname" /></th>
			<th><spring:message code="label.lastname" /></th>
			<th><spring:message code="label.email" /></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>
<script>
jQuery(document).ready(function() {
	var timerActive = false;
	var searchTimer = null;
	jQuery("#search-input").keyup(function(e) {
		var element = this;		
		if(timerActive){
			if(searchTimer != null){
				searchTimer.stop();
				searchTimer.clearTimer();
			}
			timerActive = !timerActive;
		}
		
		if(isValidKey(e) && !timerActive){
			// If not active, active timer
			timerActive = !timerActive;
			searchTimer = $.timer(function(){
				searchEmployee(element);
			});
			searchTimer.once(1000);
		}
	});
	
	function searchEmployee(element) {
		jQuery.ajax({
			type : "POST",
			url : "<portlet:resourceURL id='searchEmployee' />",
			data : "quertString=" + jQuery(element).val(),
			dataType : "json",
			success : function(response) {
				var dataTable = jQuery('#employee-datatable').dataTable();
				dataTable.fnClearTable();
				var jsonArr = [];
				jQuery(response.employeeList).each(function(indx, element) {
					jsonArr.push({
						firstName : element.firstName,
						lastName : element.lastName,
						email : element.email,
						id : element.id,
					});
				});
				dataTable.fnAddData(jsonArr);
			},
			error : function(e) {
				alert('Ajax Error: ' + e);
			}
		});
	}
	
 	function isValidKey(e) {
		var key = e.charCode || e.keyCode || 0;
		// Alphabetics: 65 to 90
		// Numbers: 48 to 57, number-pad: 96 to 105
		// backspace: 8, delete: 46, semicolon: 186, equal-sign: 187, comma: 188,
		// dash: 189, period: 190, forward slash: 191, grave accent: 192
		// open-bracket: 219, back-slash: 220, close-bracket: 221, single-quote: 222
		return ((key >= 65 && key <= 90) || (key >= 96 && key <= 105)
				|| (key >= 48 && key <= 57) || key == 8 || key == 46
				|| (key >= 186 && key <= 192) || (key >= 219 && key <= 222));
	}
	
 	jQuery('#employee-datatable').dataTable({
		 	bAutoWidth : true,
			bProcessing : true,
			bFilter : false,
			sPaginationType : "full_numbers",
			sAjaxSource : "<portlet:resourceURL id='getAllEmployee'/>",
			"aoColumns" : [
				{
				 	"mData" : "firstName",
				 	"mRender" : function(data, type, full) {
						return "<a href='${addEmployeeUrl}&employeeId="+full.id+"'>" +data+ " </a>";
					}
				},
				{
					"mData" : "lastName"
				},
				{
					"mData" : "email"
				},
				{
					"mData" : "id",
					"bSortable" : false,
					"bSearchable" : false,
					"mRender" : function(data, type, full) {
						return "<a href='${addEmployeeUrl}&employeeId="+data+"' class='bs-tooltip' data-placement='top' data-original-title='Edit' title='Edit'><i class='icon-pencil'></i></a>"
						+"&nbsp;<a href='#' id='rowId_"+data+"' onclick='deleteEmployee(this)' class='bs-tooltip' data-placement='top' data-original-title='Delete' title='Delete'><i class='icon-trash'></i></a>";
					}
				}]
	 });
});

// Delete Employee From database and data table also
function deleteEmployee(element) {
	var empId = jQuery(element).attr('id').substring($(element).attr('id').indexOf('_') + 1);
	var tr = jQuery(element).parents('tr')[0];
	var dataTable = jQuery('#employee-datatable').dataTable();
	var confirmDelete = confirm("Are you sure you want delete Employee?");
	if(confirmDelete){
		jQuery.ajax({
			type : "POST",
			url : "<portlet:resourceURL id='deleteEmployee'/>",
			data : "employeeId="+empId,
			dataType : "json",
			success : function(response) {
				jQuery(".alert").find(".message").text(response.actionMessage);
				if (response.status == "success") {
					dataTable.fnDeleteRow(tr);
					jQuery(".alert-success").show();
				}
				if (response.status == "error") {
					jQuery(".alert-danger").show();
					return false;
				}
			},
			error : function(e) {
				alert('Error while deleting employee : ' + e);
			}
		});
	}
}
</script>