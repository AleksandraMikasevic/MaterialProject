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
            <table  id="certificates" class="table table-hover table-striped table-bordered">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Date</th>
                        <th scope="col">Sum</th>
                        <th scope="col">Show</th>
                    </tr>
                </thead>
            </table>

        </div>
    </div>
    <script>
        $(document).ready(function () {
            var table = $("#certificates").DataTable({
                "destroy": true,
                "processing": true, // for show progress bar
                "filter": true, // this is for disable filter (search box)
                "orderMulti": false, // for disable multiple column at once
                ajax: {
                    "url": "/MaterialReception/weight_certificate/json",
                    "type": "GET",
                    "datatype": "json",
                    dataSrc: ''
                },
                "columns": [
                    {"data": "id", "name": "id", "autoWidth": true},
                    {"data": "date", "name": "date", "autoWidth": true,
                        "render": function (data, type, row) {
                            var date = new Date(data);
                            var day = date.getDate();
                            var month = date.getMonth() + 1;
                            var year = date.getFullYear();
                            return day + "." + month + "." + year;
                        }},
                    {"data": "sum", "name": "Sum", "autoWidth": true},
                    {
                        "render": function (data, type, full, meta) {
                            return '<a class="fa fa-info-circle btn btn-info" href="/MaterialReception/weight_certificate/find_weight_certificate/' + full.id + '"></a>';
                        }
                    }
                ]
            });
        });
    </script> 
</body>
