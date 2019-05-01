
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Add goods received note</h2>
                <p>Basic information about goods received note</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/MaterialReception/goods_received_note/add_goods_received_note_info" method="POST" modelAttribute="grcn">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="id">ID</form:label>
                        <form:input disabled="true" path="id" class="form-control" id="id" placeholder="id"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="date">Date</form:label>
                        <form:input path="date" class="form-control" id="datepicker" placeholder="date"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="employee.id">Employee</form:label>
                        <form:select path="employee.id" class="form-control">
                            <form:options items="${employees}" itemLabel="firstName" itemValue="id" />
                        </form:select>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="weightCertificate.id">Weight certificate</form:label>
                        <form:input path="weightCertificate.id" class="form-control" readonly="true" id="weightCertificate" placeholder="Weight certificate" />
                    </div>
                </div>
                <div class="form-group col-md-12">
                <form:label path="supplier.id">Supplier</form:label>
                    <form:select path="supplier.id" class="form-control">
                        <form:options items="${suppliers}" itemLabel="name" itemValue="id" />
                    </form:select>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-check"></i></button>
                    </div>
                    <div class="form-group col-md-6">
                        <a href="<c:url value='/weight_certificate/all_weight_certificate'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</container>
<script>
    $(document).ready(function () {
        $("#datepicker").datepicker({dateFormat: 'yy-mm-dd'});
    });
</script>

