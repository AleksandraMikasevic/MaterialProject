<%-- 
    Document   : find_employee
    Created on : Nov 22, 2018, 3:09:33 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Logged in employee</h2>
                <p>Informations  about employee</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form modelAttribute="employee">
                <div class="form-group">
                    <form:label path="username">Username</form:label>
                    <form:input disabled="true" path="username" class="form-control" id="username" placeholder="username" value= "${employee.username}"/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="lastName">Last name</form:label>
                        <form:input disabled="true" path="lastName" class="form-control" id="lastName" placeholder="lastName" value= "${employee.lastName}"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="firstName">First name</form:label>
                        <form:input disabled="true" path="firstName" class="form-control" id="firstName" placeholder="firstName" value= "${employee.firstName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="id">ID</form:label>
                    <form:input disabled="true" path="id" class="form-control" id="id" placeholder="id" value= "${employee.id}"/>
                </div>

            </form:form>
        </div>
    </div>
</container>