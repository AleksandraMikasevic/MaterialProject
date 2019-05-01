
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
                <h2>Update goods received note</h2>
                <p>Basic information about goods received note</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/MaterialReception/goods_received_note/change_goods_received_note_info" method="POST" modelAttribute="grcn">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="id">ID</form:label>
                        <form:input disabled="true" path="id" class="form-control" id="id" placeholder="ID"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="date">Date</form:label>
                        <form:input path="date" class="form-control" id="datepicker" placeholder="date" value="${grcn.date}"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="employee.id">Employee</form:label>
                        <form:select path="employee.id" class="form-control">
                            <form:options items="${employees}" itemLabel="fullName" itemValue="id" />
                        </form:select>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="weightCertificate.id">Weight certificate</form:label>
                        <form:select path="weightCertificate.id" class="form-control">
                            <form:options items="${certificates}" itemLabel="id" itemValue="id" />
                        </form:select>
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
                        <a href="<c:url value='/goods_received_note/all_goods_received_notes'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
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
    $(document).ready(function () {
        $('form').bootstrapValidator({
            fields: {
                datum: {
                    validators: {
                        notEmpty: {
                            message: 'Izaberite datum prijemnice.'
                        }
                    }
                }
            }
        });
    });
</script>

