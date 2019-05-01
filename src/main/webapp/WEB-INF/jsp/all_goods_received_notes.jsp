<%-- 
    Document   : all_materials
    Created on : Aug 29, 2018, 8:36:14 PM
    Author     : User
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
    <div class="container-fluid">
        <br>

        <div class="row">
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <br>
            </div>
            <table  id="grns" class="table table-hover table-striped table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Number</th>
                        <th scope="col">Date of reception</th>
                        <th scope="col">Entry date</th>
                        <th scope="col">Sum</th>
                        <th scope="col">Tax sum</th>
                        <th scope="col">Sum with taxes</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
            </table>

        </div>
    </div>
    <script>
        $(document).ready(function () {
            var table = $("#grns").DataTable({
                "destroy": true,
                "processing": true, // for show progress bar
                "filter": true, // this is for disable filter (search box)
                "orderMulti": false, // for disable multiple column at once
                ajax: {
                    "url": "/MaterialReception/goods_received_note/json",
                    "type": "GET",
                    "datatype": "json",
                    dataSrc: ''
                },
                "columns": [
                    {"data": "id", "name": "ID", "autoWidth": true},
                    {"data": "date", "name": "Date", "autoWidth": true,
                        "render": function (data, type, row) {
                            var date = new Date(data);
                            var day = date.getDate();
                            var month = date.getMonth() + 1;
                            var year = date.getFullYear();
                            return day + "." + month + "." + year;
                        }},
                    {"data": "entryDate", "name": "Entry date", "autoWidth": true,
                        "render": function (data, type, row) {
                            var date = new Date(data);
                            var day = date.getDate();
                            var month = date.getMonth() + 1;
                            var year = date.getFullYear();
                            return day + "." + month + "." + year;
                        }},
                    {"data": "sum", "name": "Sum", "autoWidth": true},
                    {"data": "taxSum", "name": "Tax sum", "autoWidth": true},
                    {"data": "sumWithTax", "name": "Sum with taxes", "autoWidth": true},
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-info-circle btn btn-info" href="/MaterialReception/goods_received_note/find_goods_received_note/' + full.id + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-cogs btn btn-info" href="/MaterialReception/goods_received_note/update_goods_received_note/' + full.id + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-remove btn btn-info" href="/MaterialReception/goods_received_note/remove_goods_received_note/' + full.id + '"></a>';
                        }
                    }
                ]
            });
        });
    </script> 
</body>
