<%@include file="init.jsp"%>
<portlet:renderURL var="backToList" />
<p>
<liferay-ui:header title="Back to Employee List" backURL="${backToList}" backLabel="Back"></liferay-ui:header>
</p>
<c:choose>
	<c:when test="${empty employee}">
		<spring:message code="employee.not.found" />
	</c:when>
	<c:otherwise>
		<div class="form-horizontal">
			<!--Basic Information -->
			<fieldset>
				<legend><spring:message code="label.tab1.title"/></legend>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.firstname"/></strong></span>
					<span class="span10">${employee.firstName}</span>
				</div>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.lastname"/></strong></span>
					<span class="span10">${employee.lastName}</span>
				</div>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.email"/></strong></span>
					<span class="span10">${employee.email}</span>
				</div>	
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.address"/></strong></span>
					<span class="span10">${employee.address}</span>
				</div>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.street"/></strong></span>
					<span class="span10">${employee.street}</span>
				</div>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.city"/></strong></span>
					<span class="span10">${employee.city}</span>
				</div>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.state"/></strong></span>
					<span class="span10">${employee.state}</span>
				</div>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.postCode"/></strong></span>
					<span class="span10">${employee.postCode}</span>
				</div>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.telephone"/></strong></span>
					<span class="span10">${employee.telephone}</span>
				</div>
			</fieldset>
			<!-- DepartmentDao Detail -->
			<fieldset>
				<legend><spring:message code="label.tab2.title"/></legend>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.category"/></strong></span>
					<span class="span10">${employee.department.departmentCategory.category}</span>
				</div>
				<div class="span12">
					<span class="span2"><strong><spring:message code="label.department"/></strong></span>
					<span class="span10">${employee.department.departmentName}</span>
				</div>
			</fieldset>
		</div>
	</c:otherwise>
</c:choose>