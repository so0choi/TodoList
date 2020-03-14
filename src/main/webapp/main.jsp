<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%@ page import="todo.dto.TodoDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To Do Web</title>
    <link rel="stylesheet" href="main_css.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
    <h2 id="title">나의 해야할 일들</h2>
    <button type="button" id="add_list" onclick="location.href='TodoFormServlet'">새로운 TODO 등록</button>

    <div id="lists">
        <div class="outside">
			<div class="section">TODO </div>
            <table id="TODO">
	          	<%
	           		List<TodoDto> todo = new ArrayList<>();
	           		todo = (List<TodoDto>)request.getAttribute("todo");
	           	%>
	           	<c:forEach items="${todo }" var="item">
	           	<tr id="list${item.id }"><td>
		           	<span class="up">${item.title }</span><br>
		           	<span class="sub">등록날짜 : ${item.regDate }, ${item.name }, 
		           	우선순위 ${item.sequence }<button type="button" id="btn${item.id }" 
		            onclick="changeType(${item.id})" >→</button></span>
	           	</td></tr>
	           	</c:forEach>
             </table>
         </div>
       
        <div class="outside">
            <div class="section">DOING</diV>
            <table id="DOING">
           		<%
           		List<TodoDto> doing = new ArrayList<>();
           		doing = (List<TodoDto>)request.getAttribute("doing");
           		%>
           		<c:forEach items="${doing }" var="item">
	           	<tr id="list${item.id }"><td><span class="up">${item.title }</span><br>
	           	<span class="sub">등록날짜 : ${item.regDate }, ${item.name }, 
	           	우선순위 ${item.sequence }<button type="button" id="btn${item.id }"
	            onclick="changeType(${item.id})" >→</button></span>
	           	</td></tr>
           		</c:forEach>
           </table>
        </div>
        
        <div class="outside">
	    	<div class="section">DONE</div>
	   		<table id="DONE">
	            <%
	           	List<TodoDto> done = new ArrayList<>();
	           	done = (List<TodoDto>)request.getAttribute("done");
	           	%>
		        <c:forEach items="${done }" var="item">
		        <tr id="list${item.id }"><td>
		        <span class="up">${item.title }</span><br>
		        <span class="sub">등록날짜 : ${item.regDate }, ${item.name }, 
				우선순위 ${item.sequence }</span>
		        </td></tr>
		        </c:forEach>
	        </table>
        </div>
       </div> 
   
   
	
    
</body>
<script type="text/javascript">
function changeType(tid) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET',"/TodoList/type?id="+encodeURI(tid),true);
    xhr.addEventListener("load",function(){
    	
        var tr = document.getElementById('list'+tid);
        var p_tbl = tr.parentNode.parentNode;
      
        if(p_tbl.id=="TODO") 
        	var n_tbl = document.getElementById("DOING");
        else 
        	var n_tbl = document.getElementById("DONE");
        
        if(n_tbl.id=="DONE"){
        	var btn = document.getElementById("btn"+tid)
            btn.parentNode.removeChild(btn);
        }
        
        n_tbl.appendChild(tr);
    })
    xhr.send();
    
}

	</script>
</html>