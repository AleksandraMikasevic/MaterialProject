<%-- 
    Document   : all_suppliers
    Created on : Nov 22, 2018, 5:08:22 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<body>
    <div class="container-fluid">
        <br>
        <div class="row">
            <div class="col-xl-4 col-lg-8 col-md-12 col-sm-12 col-12">
                <a href="<c:url value='/supplier/add_supplier'/>" class="btn btn-primary" style="font-size:24px;"><i class="fa fa-plus-square-o"></i></a>
            </div>
            <table  id="suppliers" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Place</th>
                        <th scope="col">Name</th>
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
            var table = $("#suppliers").DataTable({
                "destroy": true,
                "processing": true, // for show progress bar
                "filter": true, // this is for disable filter (search box)
                "orderMulti": false, // for disable multiple column at once
                ajax: {
                    "url": "/MaterialReception/supplier/all_suppliers_json",
                    "type": "GET",
                    "datatype": "json",
                    dataSrc: ''
                },
                "columns": [
                    {"data": "id", "name": "id", "autoWidth": true},
                    {"data": "place", "name": "place", "autoWidth": true},
                    {"data": "name", "name": "name", "autoWidth": true},
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-info-circle btn btn-info" href="/MaterialReception/supplier/find_supplier/' + full.id + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-cogs btn btn-info" href="/MaterialReception/supplier/update_supplier/' + full.id + '"></a>';
                        }
                    },
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-remove btn btn-info" href="/MaterialReception/supplier/remove_supplier/' + full.id + '"></a>';
                        }
                    }
                ]
            });
        });
    </script>               
</body>

