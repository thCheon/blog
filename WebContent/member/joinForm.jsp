<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Cos Blog</title>
  <!-- Bootstrap core CSS -->
  <link href="<%=request.getContextPath() %>/css/bootstrap.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link href="<%=request.getContextPath() %>/css/blog-home.css" rel="stylesheet">
  <!-- Bootstrap core JavaScript -->
  <script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
  <script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
  
  <script>
	  function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/Blog/popup/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
		}
	
		function jusoCallBack(roadFullAddr){
				// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
				document.form.roadFullAddr.value = roadFullAddr;
		}
	</script>
</head>
<body>
   <!-- Nav Include -->
   <jsp:include page="/include/header.jsp"/>
  
   <!-- Page Content -->
	<div class="container">
	  <div class="row">
	    <!-- Blog Entries Column -->
	    <div class="col-md-8">
		    <div class="content-section">
			    <form method="POST" name="form" action="<%= request.getContextPath()%>/member?cmd=member_join">
				    <fieldset class="form-group">
				       <legend class="border-bottom mb-4">join -- <%= request.getContextPath()%></legend>
				       <div class="from-group">
					       <label class="form-control-label">ID</label>
					       <input class="form-control form-control-lg" type="text" name="id" maxlength="20" required autofocus>
				       </div>
				       <div class="from-group">
					       <label class="form-control-label">PassWord</label>
					       <input class="form-control form-control-lg" type="password" id="pw" name="password" maxlength="20" required>
				       </div>
				       <div class="from-group">
					       <label class="form-control-label">Confirm_PassWord</label>
					       <input class="form-control form-control-lg" type="password" id="pwcheck" name="confirm" maxlength="20" required>
				       </div>
				       <div class="from-group">
					       <label class="form-control-label">UserName</label>
					       <input class="form-control form-control-lg" type="text" name="username" maxlength="20" required>
				       </div>
				       <div class="from-group">
				       		<div id="list"></div>
								 	<div id="callBackDiv">
					       		<label class="form-control-label">Address</label>
					       		<button class="btn btn-outline-info float-right" type="button" onClick="goPopup();">Search Korean Address</button>
					       		<input class="form-control form-control-lg" type="text" name="roadFullAddr" maxlength="100" required readonly>
					       	</div>
				       </div>
				       <div class="from-group">
						       <label class="form-control-label">E-Mail</label>
						       <input class="form-control form-control-lg" type="text" name="email" maxlength="20" required>
				       </div>
				       <div class="border-top pt-3">
				       	<small class="text-muted">
				       		Already Have An Account? <a class="ml-2" href="<%=request.getContextPath()%>/member/loginForm.jsp">Login</a>
				       	</small>
				       	<p id="pwsame" style="color:red;"></p>
				       </div>
				       <div class="form-group">
				       	<button class="btn btn-outline-info" type="submit">Sign in</button>
				       </div>
				    </fieldset>
			    </form>
		    </div>
	    </div>
	
	    <!-- SideBar Include -->
	      <jsp:include page="/include/aside.jsp"/>
	
	  </div>
	  <!-- /.row -->
	
	</div>
	<!-- /.container -->
	
	<!-- Footer -->
	<footer class="py-5 bg-dark">
	  <div class="container">
	    <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
	  </div>
	  <!-- /.container -->
	</footer>

</body>

</html>