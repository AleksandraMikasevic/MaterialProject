<%-- 
    Document   : remove_supplier
    Created on : Nov 22, 2018, 5:08:46 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Delete supplier</h2>
                <p>Informations about supplier</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                </div>
            </div>
            <form:form action="/MaterialReception/supplier/remove_supplier/${supplier.id}" method="post" modelAttribute="supplier">
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
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-remove"></i></button>
                    </div>
                    <div class="form-group col-md-6">
                        <a href="/MaterialReception/supplier/all_suppliers" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</container>
