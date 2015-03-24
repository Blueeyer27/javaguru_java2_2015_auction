<%@ page import="lv.javaguru.java2.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 2/17/2015
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Declaring JSP variable--%>
<% String resPath = request.getContextPath()+"/staticRes";%>
<%-- Added by Vlad --%>

<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="<%=resPath%>/css/genStyles.css"/>
    <link type="text/css" rel="stylesheet" href="<%=resPath%>/css/itemStyles.css"/>
</head>
<body>
<div class="container">
    <%@ include file="components/header.jsp" %> <!-- Header -->
    <%@ include file="components/top-banner.jsp" %> <!-- Header -->
    <%@ include file="components/navigation.jsp" %> <!-- Navigation -->
    <%@ include file="components/search.jsp" %> <!-- Search -->
    <%
        // Getting model and splitting it into meaningfull components
        Map<String, Object> model = (Map<String, Object>)request.getAttribute("model");
        List<Product> products = (List<Product>)model.get("searchResult");
        Integer resultCount = (Integer)model.get("resultCount");

        List<String> categoryNames = (List<String>)model.get("categoryNames");
        List<Long> productsInCategory = (List<Long>)model.get("productsInCategory");
    %>

    <div  class="content request-wrap">
        <div class="category-list" style="float: left;">
            <h1>Категории</h1>
            <ul>

                <% for(int i = 0; i < categoryNames.size(); i++) { %>
                    <li><%=categoryNames.get(i)%><span> (<%=productsInCategory.get(i)%>)</span></li>
                <%}%>

            </ul>
        </div><!--End of category-list -->
        <div class="req-container">

            <p> Найдено <strong><%=resultCount%></strong>
                результатов по запросу
                <strong>"<%=request.getParameter("searchQuery")%>"</strong>
            </p>
            <div class="merch-list">

                <% for (Product product : products) { %>
                    <div class="record">
                        <img src="<%=resPath%>/images/cat.jpg"/>
                        <span><%=product.getName()%></span>
                        <p>
                            <%=product.getDescription()%>
                        </p>
                    </div>
                <%}%>
            </div> <!-- End of merch-list-->
        </div><!-- End of req-container -->
    </div> <!-- End of content -->
</div> <!-- End of container -->
<%@ include file="components/footer.jsp" %> <!-- footer -->
</body>
</html>
