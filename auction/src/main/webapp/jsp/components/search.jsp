<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 2/25/2015
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="search-bar">
    <form name="search" action="<%=request.getContextPath()%>/prod" method="get">
        <button type="submit">Искать</button>
        <input type="text" name="searchQuery" placeholder="Поиск">
    </form>
</div>
