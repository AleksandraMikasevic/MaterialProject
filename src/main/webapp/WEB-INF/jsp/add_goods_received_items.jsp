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
        <div class="main-div">
            <div class="panel">
                <h2>Add goods received note</h2>
                <p>Goods received note items</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">${error}
                    </c:if>
                </div>
            </div>
            <form>
                <div class="form-row">
                    <input type="hidden" name="itemNum" id = "itemNum" value ="" class="form-control"><br>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="quantity">Quantity</label>
                        <input type="text" name="quantity" id = "quantity" class="form-control"><br>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="material">Material</label>
                        <select name="material" id ="material" class="form-control">
                            <c:forEach items="${materials}" var="material">
                                <option value="${material.id}"><c:out value="${material.name}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <button type="button" class="btn btn-primary" id ="addItemButton">Save item</button>
                    </div>
                    <div class="form-group col-md-3">
                        <button type="button" id ="deleteItemButton" class="btn btn-primary" disabled="true"><i class="fa fa-remove"></i></button>
                    </div> 
                    <div class="form-group col-md-3">
                        <button type="button" id ="changeItemButton" class="btn btn-primary" disabled="true"><i class="fa fa-cogs"></i></button>
                    </div>
                </div>
            </form>
            <br>
            <div class ="row">
                <div class="tabelaCentar">
                    <table  id ="items1" class="table table-hover table-striped table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Serial number</th>
                                <th scope="col">Item number</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Material</th>
                            </tr>
                        </thead>

                    </table>
                    <form action="/MaterialReception/goods_received_note/add_goods_received_note" method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="sumbit" class="btn btn-primary"><i class="fa fa-check">Save goods received note</i></button>
                    </form>
                </div>
            </div>
        </div>
    </div>


</container>

<style>
    .ui-datepicker {
        background: black;
        color: #EEE;
    }
</style>


<script>
    $(document).ready(function () {
        var t = $("#items1").DataTable({
            dom: "Brtip",
            // for disable multiple column at once
            ajax: {
                "url": "/MaterialReception/goods_received_note/items_json/",
                "type": "GET",
                "datatype": "json",
                dataSrc: ''
            },
            "columns": [
                {"data": "serNum", "name": "serNum", "autoWidth": true},
                {"data": "goodsReceivedNoteItemPK.itemNum", "name": "itemNum", "autoWidth": true},
                {"data": "quantity", "name": "quantity", "autoWidth": true},
                {"data": "material.id", "name": "material", "autoWidth": true}
            ]
        });
        var counter = 1;

        $('#addItemButton').on('click', function () {
            if ($("#itemNum").val() === "") {
                $.ajax({
                    url: "/MaterialReception/goods_received_note/add_item/" + $("#material").val() + "/" + $("#quantity").val(),
                    type: 'GET',
                    "datatype": "json",
                    success: function (data) {
                        t.ajax.reload();
                        document.getElementById("quantity").value = "";
                        document.getElementById("material").value = "1";
                        document.getElementById("itemNum").value = "";
                        $("#deleteItemButton").prop('disabled', true);
                        $("#changeItemButton").prop('disabled', true);
                    },
                    error: function (jqxhr, status, exception) {
                        alert('Exception:', exception.toString());
                    }
                });
            } else {
                $.ajax({
                    url: "/MaterialReception/goods_received_note/update_item/" + $("#itemNum").val() + "/" + $("#material").val() + "/" + $("#quantity").val(),
                    type: 'GET',
                    "datatype": "json",
                    success: function (data) {
                        t.ajax.reload();
                        document.getElementById("quantity").value = "";
                        document.getElementById("material").value = "1";
                        document.getElementById("itemNum").value = "";
                        $("#deleteItemButton").prop('disabled', true);
                        $("#changeItemButton").prop('disabled', true);
                    },
                    error: function (jqxhr, status, exception) {
                        alert('Exception:', exception.toString());
                    }
                });
            }
        });



        $('#items1 tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                $("#deleteItemButton").prop('disabled', true);
                $("#changeItemButton").prop('disabled', true);
            } else {
                t.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                $("#deleteItemButton").prop('disabled', false);
                $("#changeItemButton").prop('disabled', false);
            }
        });

        $('#deleteItemButton').click(function () {

            var selectedRow = t.cell('.selected', 1).data();
            $.ajax({
                url: "/MaterialReception/goods_received_note/remove_item/" + selectedRow,
                type: 'GET',
                success: function (data) {
                    t.ajax.reload();
                    document.getElementById("itemNum").value = "";
                    $("#deleteItemButton").prop('disabled', true);
                    $("#changeItemButton").prop('disabled', true);
                },
                error: function (jqxhr, status, exception) {
                    alert('Exception:', exception.toString());
                }
            });
        });
        $('#changeItemButton').click(function () {
            var selectedRowQ = t.cell('.selected', 2).data();
            var selectedRowM = t.cell('.selected', 3).data();
            var selectedRowN = t.cell('.selected', 1).data();
            document.getElementById("quantity").value = selectedRowQ;
            document.getElementById("material").value = selectedRowM;
            document.getElementById("itemNum").value = selectedRowN;
        });
    });
</script>
