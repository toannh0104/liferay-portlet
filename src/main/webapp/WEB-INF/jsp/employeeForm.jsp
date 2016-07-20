<%@include file="init.jsp"%>
<portlet:actionURL var="addEmployeeURL">
	<portlet:param name="action" value="save"/>
</portlet:actionURL>
<portlet:renderURL var="backToList" />

<p>
<liferay-ui:header title="Back to Employee List" backURL="${backToList}" backLabel="Back"></liferay-ui:header>
</p>
<form:form id="employeeForm" commandName="employee" method="post" action="${addEmployeeURL}" cssClass="form-horizontal">
	<c:if test="${not empty message }">
		${message}
	</c:if>
	<div id="rootwizard">
		<ul>
			<li><a href="#tab1" data-toggle="tab"><spring:message code="label.tab1.title"/> </a></li>
			<li><a href="#tab2" data-toggle="tab"><spring:message code="label.tab2.title"/></a></li>
		</ul>
		<div class="tab-content">
			<!-- Tab 1 : Basic Information -->
			<div class="tab-pane" id="tab1">
				<div class="control-group">
					<label class="control-label" for="firstname"><spring:message
							code="label.firstname" /><span class="required">*</span>
					</label>
					<div class="controls">
						<form:input path="firstName" cssClass="required alpha"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="name">
						<spring:message code="label.lastname"/><span class="required">*</span>
					</label>
					<div class="controls">
						<form:input path="lastName" cssClass="required alpha"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="email">
						<spring:message code="label.email"/><span class="required">*</span>
					</label>
					<div class="controls">
						<form:input path="email" cssClass="required email"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="address"><spring:message code="label.address"/> </label>
					<div class="controls">
						<form:input path="address"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="street"><spring:message code="label.street"/> </label>
					<div class="controls">
						<form:input path="street"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="city"><spring:message code="label.city"/> </label>
					<div class="controls">
						<form:input path="city"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="state"><spring:message code="label.state"/> </label>
					<div class="controls">
						<form:input path="state"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="postCode"><spring:message code="label.postCode"/> </label>
					<div class="controls">
						<form:input path="postCode"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="telephone"><spring:message code="label.telephone"/> </label>
					<div class="controls">
						<form:input path="telephone"/>
					</div>
				</div>
			</div>
			<!-- Tab 2 : DepartmentDao Detail -->
			<div class="tab-pane" id="tab2">
				<div class="control-group">
					<label class="control-label"><spring:message code="label.category"/> </label>
					<div class="controls">
						<select id="department-category">
							<option value="">--Select--</option>
							<c:forEach items="${departmentCategory}" var="departmentCategory" varStatus="status">
								<option value="${fn:replace(departmentCategory.category,' ','-')}"
									<c:if test="${employee.department.departmentCategory.id == departmentCategory.id }">
										selected="selected"
									</c:if>
								>${departmentCategory.category}</option>
							</c:forEach>
						</select>
						
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><spring:message code="label.department"/> </label>
					<div class="controls">
						<form:select path="department.id" id="department">
							<form:option value="">--Select--</form:option>
							<c:forEach items="${departments}" var="department" varStatus="status">
								<c:choose>
									<c:when test="${not empty employee.department and department.departmentCategory.id eq employee.department.departmentCategory.id}">
										<form:option cssClass="${fn:replace(department.departmentCategory.category,' ','-')}" value="${department.id}">${department.departmentName}</form:option>
									</c:when>
									<c:otherwise>
										<form:option cssClass="${fn:replace(department.departmentCategory.category,' ','-')}" value="${department.id}" cssStyle="display:none;">${department.departmentName}</form:option>
									</c:otherwise>
								</c:choose>								
							</c:forEach>
						</form:select>
					</div>
				</div>
			</div>
			<div class="well well-large">
				<form:hidden path="id"/>
	  			<a href="javascript:void(0);" class="btn button-previous" ><i class="icon-arrow-left"></i> <spring:message code="label.previous" /></a>
	  			<a href="javascript:void(0);" class="btn button-next"><spring:message code="label.next"/> <i class="icon-arrow-right"></i></a>
			  	<button type="submit" class="btn btn-success button-submit">
						<spring:message code="label.submit" />
						<i class="icon-angle-right"></i>
				</button>
			</div>
		</div>
	</div>
</form:form>
<script>
$(document).ready(function() {
	jQuery("#department-category").change(function() {
		var category = jQuery.trim(jQuery(this).val());
		$("#department option").not(':first').hide();
		$("#department option:first-child").attr("selected", "selected");
		if(category != "") {
			$("#department option[class='"+category+"']").show();
		}
	});
});
</script>