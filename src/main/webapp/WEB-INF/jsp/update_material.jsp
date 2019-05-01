<%-- 
    Document   : update_material
    Created on : Aug 29, 2018, 9:04:55 PM
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
                <h2>Update material</h2>
                <p>Fill in new informations about material</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/MaterialReception/material/update_material/${material.id}" method="post" modelAttribute="material">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="id">ID</form:label>
                        <form:input disabled="true" path="id" class="form-control" id="id" placeholder="id" value= "${material.id}"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="name">Name</form:label>
                        <form:input path="name" class="form-control" id="name" placeholder="name" value= "${material.name}"/>
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
                    <form:input path="tax" class="form-control" id="tax" placeholder="tax" value= "${material.tax}"/>
                </div>
                <div class="form-group">
                    <form:label path="price">Price</form:label>
                    <form:input path="price" class="form-control" id="price" placeholder="Price" value= "${material.price}"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Description</form:label>
                    <form:input path="description" class="form-control" id="description" placeholder="description" value= "${material.description}"/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i></button>                    </div>
                    <div class="form-group col-md-6">
                        <a href="/MaterialReception/material/all_materials" class="btn btn-primary"><i class="fa fa-reply"></i></a>
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
                sifraMaterijala: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite sifru materijala'
                        }
                    }
                },
                nazivMaterijala: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite naziv materijala'
                        }
                    }
                },
                pdv: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite pdv'
                        }
                    }
                },
                cena: {
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
