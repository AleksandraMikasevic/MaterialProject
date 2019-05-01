<%-- 
    Document   : index
    Created on : Aug 29, 2018, 2:38:24 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<container>
    <div class="forma">
        <div class="main-div">
            <div class="panel">
            <h2>Log in</h2>
            <p>Insert username and password</p>
                <div class="errorblock">
                    <c:if test="${not empty error}">
                        Your login was unsuccessful.<br/>
                        Caused: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message};
                    </c:if>                </div>
            </div>
            <form action="/MaterialReception/login" name='f' method="post">
                <div class="form-group">
                    <label>Username:</label>
                    <input type ='text' name='username' class="form-control" value=''/>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type ='password' name='password' class="form-control" value=''/>
                </div>
                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                <button type="submit" name ='Submit' class="btn btn-primary"><i class="fa fa-check"></i></button>
            </form>
        </div>
    </div>
</container>
