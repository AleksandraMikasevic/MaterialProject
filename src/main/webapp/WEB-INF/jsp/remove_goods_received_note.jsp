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
                <h2>Delete goods received note</h2>
                <p>Information about goods received note</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}</c:if>
                    </div>
                </div>
            <form:form action="/MaterialReception/goods_received_note/remove_goods_received_note/${grcn.id}" method="post" modelAttribute="grcn">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <form:label path="id">ID</form:label>
                        <form:input disabled="true" path="id" class="form-control" id="id" placeholder="id"/>
                    </div>
                    <div class="form-group col-md-6">
                        <form:label path="date">Date</form:label>
                        <form:input disabled="true" path="date" class="form-control" id="date" placeholder="Date"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:label path="sum">Sum</form:label>
                    <form:input disabled="true" path="sum" class="form-control" id="sum" placeholder="sum"/>
                </div>
                <div class="form-group">
                    <form:label path="employee">Employee</form:label>
                    <form:input disabled="true" path="employee" class="form-control" id="employee" placeholder="Employee"/>
                </div>
                <div class="form-group">
                    <form:label path="weightCertificate">Weight certificate</form:label>
                    <form:input disabled="true" path="weightCertificate" class="form-control" id="weightCertificate" placeholder="weightCertificate"/>
                    <label>Goods received note items</label>
                    <br>
                    <table  id="stavke" class="table table-hover table-striped table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Serial number</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Material</th>
                                <th scope="col">Price</th>
                                <th scope="col">Sum</th>
                                <th scope="col">Tax</th>
                                <th scope="col">Sum with tax</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${grcn.goodsReceivedNoteItems}" var="item">
                                <tr>
                                    <th scope="row">${item.serNum}</th>
                                    <td>${item.quantity}</td>
                                    <td>${item.material.name}</td>
                                    <td>${item.material.price}</td>
                                    <td>${item.material.price*item.quantity}</td>
                                    <td>${item.material.tax}%</td>
                                    <td>${item.material.price*item.quantity*(item.material.tax+100)/100}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <button type="submit" class="btn btn-primary"><i class="fa fa-remove"></i></button>
                        </div>
                        <div class="form-group col-md-6">
                            <a href="<c:url value='/goods_received_note/all_goods_received_notes'/>" class="btn btn-primary"><i class="fa fa-reply"></i></a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
</container>

