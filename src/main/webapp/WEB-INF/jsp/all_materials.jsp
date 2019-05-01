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
                <a href="<c:url value='/material/add_material'/>" class="btn btn-primary" style="font-size:24px;"><i class="fa fa-plus-square-o"></i></a>
            </div>
            <table  id="materials" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Unit</th>
                        <th scope="col">Tax</th>
                        <th scope="col">Price</th>
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
            var tabela = $("#materials").DataTable({
                "destroy": true,
                "processing": true, // for show progress bar
                "filter": true, // this is for disable filter (search box)
                "orderMulti": false, // for disable multiple column at once
                ajax: {
                    "url": "/MaterialReception/material/all_materials_json",
                    "type": "GET",
                    "datatype": "json",
                    dataSrc: ''
                },
                "columns": [
                    {"data": "id", "name": "id", "autoWidth": true},
                    {"data": "name", "name": "name", "autoWidth": true},
                    {"data": "unit", "name": "unit", "autoWidth": true},
                    {"data": "tax", "name": "tax", "autoWidth": true, "render": function (data, type, full, meta) {
                            return data + "%";
                        }},
                    {"data": "price", "name": "price", "autoWidth": true, "render": function (data, type, full, meta) {
                            return data + " din";
                        }},
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-info-circle btn btn-info" href="/MaterialReception/material/find_material/' + full.id + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-cogs btn btn-info" href="/MaterialReception/material/update_material/' + full.id + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-remove btn btn-info" href="/MaterialReception/material/remove_material/' + full.id + '"></a>';
                        }
                    }
                ]
            });
        });
    </script>               
</body>


