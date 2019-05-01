<%-- 
    Document   : find_supplier
    Created on : Nov 22, 2018, 5:09:18 PM
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
                <h2>Supplier</h2>
                <p>Informations about supplier</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form modelAttribute="supplier">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="id">ID</form:label>
                        <form:input disabled="true" path="id" class="form-control" id="id" placeholder="ID"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="place">Place</form:label>
                        <form:input disabled="true" path="place" class="form-control" id="place" placeholder="Place"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="name">Name</form:label>
                    <form:input disabled="true" path="name" class="form-control" id="name" placeholder="Name"/>
                </div>
                <div class="form-group">
                    <a href="<c:url value='/supplier/all_suppliers'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                </div>
            </form:form>
        </div>
    </div>
</container>