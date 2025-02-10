<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
	new Vue({
        el:".container",
        data:{
           search_empno:"",
           isExist:false,
           dto:"",
        },
        methods:{
           clickSearchBtn(){
              console.log("일단 여기 들어옴");
              fetch("${pageContext.request.contextPath }/ceo_eugene/member-info.jsp?empno="+this.search_empno)
              .then(res => res.json())
              .then(data=>{
                 console.log(data);
                 this.isExist = data.isExist;
                 this.dto = data.dto;
                 console.log(data.isExist);
              })
              .catch((err)=>{
                 console.log(err);
              })
              
              console.log("fetch함수 끝")
           }
        }
     });
	</script>
</body>
</html>