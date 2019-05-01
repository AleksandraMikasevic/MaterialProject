<%-- 
    Document   : remove_material
    Created on : Aug 29, 2018, 9:04:34 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Delete material</h2>
                <p>Information about material</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                </div>
            </div>
            <form:form action="/MaterialReception/material/remove_material/${material.id}" method="post" modelAttribute="material">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="id">ID</form:label>
                        <form:input disabled="true" path="id" class="form-control" id="id" placeholder="ID"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="name">Name</form:label>
                        <form:input disabled="true" path="name" class="form-control" id="name" placeholder="name"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="unit">Unit</form:label>
                    <form:input disabled="true" path="unit" class="form-control" id="unit" placeholder="unit"/>
                </div>
                <div class="form-group">
                    <form:label path="tax">Tax</form:label>
                    <form:input disabled="true" path="tax" class="form-control" id="tax" placeholder="tax"/>
                </div>
                <div class="form-group">
                    <form:label path="price">Price</form:label>
                    <form:input disabled="true" path="price" class="form-control" id="price" placeholder="price"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:input disabled="true" path="description" class="form-control" id="description" placeholder="description" value= "${material.description}"/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-remove"></i></button>
                    </div>
                    <div class="form-group col-md-6">
                        <a href="/MaterialReception/material/all_materials" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</container>
