<%-- 
    Document   : add_supplier
    Created on : Nov 22, 2018, 5:08:34 PM
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
                <h2>Insert supplier</h2>
                <p>Insert information about supplier</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/MaterialReception/supplier/add_supplier" method="post" modelAttribute="supplier">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="id">ID</form:label>
                        <form:input path="id" class="form-control" id="id" placeholder="ID"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="place">Place</form:label>
                        <form:input path="place" class="form-control" id="place" placeholder="Place"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="name">Name</form:label>
                    <form:input path="name" class="form-control" id="name" placeholder="Name"/>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i></button>
                    </div>
                    <div class="form-group col-md-6">
                        <a href="<c:url value='/suppliers/all_suppliers'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
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
                id : {
                    validators: {
                        notEmpty: {
                            message: 'Unesite PIB dobavljaca'
                        }
                    }
                },
                place: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite sediste dobavljaca'
                        }
                    }
                },
                name: {
                    validators: {
                        notEmpty: {
                            message: 'Unesite naziv dobavljaca'
                        }
                    }
                }
            }
        });
    });

</script>
</body>

