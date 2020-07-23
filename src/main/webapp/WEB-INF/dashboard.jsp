<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="listComputers"> <spring:message code="Title"/> </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle"> <c:out value="${countComputers}"/>
                Computers list
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="listComputers" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="http://localhost:8080/computer__database/addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="listComputers" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name <a href="http://localhost:8080/computer__database/listComputers?orderBy=true">Click OrderBy</a>
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                	 <c:forEach var="computer"  items="${ListComputers}">
                  <tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${computer.id}">
                        </td>
                        <td>
                            <a href="http://localhost:8080/computer__database/editComputer?id=${computer.id}" onclick=""><c:out value="${computer.name}"></c:out></a>
                        </td>
                        <td><c:out value="${computer.introduced}"></c:out></td>
                        <td><c:out value="${computer.discontinued}"></c:out></td>
                        <td><c:out value="${computer.company_id}"></c:out></td>

                   </tr>
		          
				</c:forEach>
                    
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                 <li>
                      <a href="ListComputers?page=${page}&lenPage=${lenPage}" aria-label="Previous">
					  <span aria-hidden="true">&laquo;</span>
                      </a>
                 </li>
                      <c:forEach var="i" begin="1" end="5">                
              	 <li>
              			<a name="${page+i}" id="page" href="listComputers?page=${page+i}&lenPage=${lenPage}"><c:out value="${page+i}"></c:out></a>
              	 </li>
     		 		</c:forEach>
                 <li>
                    <a href="listComputers?page=${page+5}&lenPage=${lenPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>                  	
                </a>
            </li>
         
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default"><a href = "listComputers?lenPage=${lenPage=10}">10</a></button>
            <button type="button" class="btn btn-default"><a href = "listComputers?lenPage=${lenPage=50}">50</a></button>
            <button type="button" class="btn btn-default"><a href = "listComputers?lenPage=${lenPage=100}">100</a></button>
        </div>

    </footer>
    
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>