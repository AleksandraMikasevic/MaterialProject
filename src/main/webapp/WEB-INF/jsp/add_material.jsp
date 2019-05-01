<%-- 
    Document   : add_material
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Add material</h2>
                <p>Please insert information about material</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/MaterialReception/material/add_material" method="post" modelAttribute="material">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="id">ID</form:label>
                        <form:input path="id" class="form-control" id="id" placeholder="ID"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="name">Name</form:label>
                        <form:input path="name" class="form-control" id="name" placeholder="Name"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="unitEn">Unit</form:label>
                    <form:select path="unitEn" class="form-control">
                        <form:options items="${units}" />
                    </form:select>
                </div>
                <div class="form-group">
                    <form:label path="tax">Tax</form:label>
                    <form:input path="tax" class="form-control" id="tax" placeholder="Tax"/>
                </div>
                <div class="form-group">
                    <form:label path="price">Price</form:label>
                    <form:input path="price" class="form-control" id="price" placeholder="Price"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" class="form-control" id="description" placeholder="Description"/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i></button>
                    </div>
                    <div class="form-group col-md-6">
                        <a href="<c:url value='/material/all_materials'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</container>
<script>
    $(document).ready(function () {
        $('form').bootstrapValidator({
            fields: {
                id: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite sifru materijala'
                        }
                    }
                },
                name: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite naziv materijala'
                        }
                    }
                },
                tax: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite pdv'
                        }
                    }
                },
                price: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite cenu'
                        }
                    }
                }
            }
        });
    });

</script>
</body>
