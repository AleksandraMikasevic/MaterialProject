<%-- 
    Document   : add_material
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<container>
    <div class="forma">
        <div class="main-div" style="max-width: 50%">
            <div class="panel">
                <h2>Weight certificate</h2>
                <p>Informations about weight certificate</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form modelAttribute="weightCertificate">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="id">ID</form:label>
                        <form:input disabled="true" path="id" class="form-control" id="id" placeholder="id"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="date">Date</form:label>
                        <form:input disabled="true" path="date" class="form-control" id="date" placeholder="date"/>
                    </div>
                    <div class="form-group col-md-12">
                        <form:label path="sum">Sum</form:label>
                        <form:input disabled="true" path="sum" class="form-control" id="sum" placeholder="sum"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="employee">Employee</form:label>
                    <form:input disabled="true" path="employee" class="form-control" id="employee" placeholder="employee"/>
                </div>
                <label>Weight certificate items</label>
                <br>
                <table  id="stavke" class="table table-hover table-striped table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Serial number</th>
                            <th scope="col">Gross weight</th>
                            <th scope="col">Net weight</th>
                            <th scope="col">Registration number</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${weightCertificate.weightCertificateItems}" var="item">
                            <tr>
                                <th scope="row">${item.serNum}</th>
                                <td>${item.grossWeight}</td>
                                <td>${item.netWeight}</td>
                                <td>${item.registrationNumber}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br>
                <div class="form-row">
                    <div class="form-group col-md-6">
                    <a href="<c:url value='/weight_certificate/all_weight_certificate'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                    </div>
                    <div class="form-group col-md-6">
                        <c:if test="${not empty num}">
                        <a href="<c:url value='/goods_received_note/find_goods_received_note/${num}'/>" class="btn btn-primary">Goods received note</a>
                    </c:if>
                    <c:if test="${empty num}">
                        <a href="<c:url value='/goods_received_note/add_goods_received_note/${weightCertificate.id}'/>" class="btn btn-primary">Goods received note</a>
                    </c:if>
                    </div>
                </div>
            </form:form>   
        </div>
    </div>
</container>
