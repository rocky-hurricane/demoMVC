<%--
  Created by IntelliJ IDEA.
  User: rocky
  Date: 17/4/14
  Time: 上午1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Input Jsp</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#lastName").change(function () {
                var val = $(this).val();
                val = $.trim(val);
                $(this).val(val);
                var url = "${pageContext.request.contextPath }/ajaxValidateLastName";
                var args = {"lastName":val,"date":new Date()};  //new Date() prevent from lazy load.

                //若修改的 lastName 和之前的 lastName 一致, 则不发送 Ajax 请求, 直接 alert:lastName 可用!
                var _oldLastName = $("#_oldLastName").val();
                _oldLastName = $.trim(_oldLastName);
                if(_oldLastName != null && _oldLastName != "" && _oldLastName == val){
                    alert("lastName is available!");
                    return;
                }

                $.post(url, args, function(data){
                    if(data == "0"){
                        alert("lastName is available!");
                    }else if(data == "1"){
                        alert("lastName is ocuppied!");
                    }else{
                        alert("inter problem. ");
                    }
                });
            });
        })

    </script>
</head>
<body>

<c:set value="${pageContext.request.contextPath }/emp" var="url"></c:set>
<c:if test="${employee.id != null }">
    <c:set value="${pageContext.request.contextPath }/emp/${employee.id}" var="url"></c:set>
</c:if>


<!-- modelAttribute is equal to the key value in the map -->
    <form:form action="${url}" method="POST" modelAttribute="employee">

        <!-- when the hidden value needed to be displayed later(回显), use form:hidden(must relate to modelAttribute)
            in other cases, use input hidden -->
        <c:if test="${employee.id != null }">
            <input type="hidden" id="_oldLastName" value="${employee.lastName }"/>
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT"/>
        </c:if>

        LastName: <form:input path="lastName" id="lastName"/>
                  <form:errors path="lastName"></form:errors>
        <br>
        Email: <form:input path="email"/>
               <form:errors path="email"></form:errors>
        <br>
        Birth: <form:input path="birth"/>
               <form:errors path="birth"></form:errors>
        <br>
        Departments:
        <form:select path="department.id" items="${departments}"
            itemLabel="departmentName" itemValue="id"></form:select>
        <br>
        <input type="submit" value="Submit"/>
    </form:form>


</body>
</html>