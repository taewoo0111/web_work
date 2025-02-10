<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container flex-fill"
		style="width: 600px; height: 600px; margin-top: 50px;">
		<div class="d-inline-block"
			style="padding: 10px 20px; cursor: pointer; font-weight: bold; background-color: #f1f1f1; border: 1px solid #ddd;"
			id="ceoTab" onclick="switchTab('ceo')">관리자 로그인</div>
		<div class="d-inline-block"
			style="padding: 10px 20px; cursor: pointer; font-weight: bold; background-color: #f1f1f1; border: 1px solid #ddd;"
			id="adminTab" onclick="switchTab('admin')">점장 로그인</div>
		<div class="d-inline-block"
			style="padding: 10px 20px; cursor: pointer; font-weight: bold; background-color: #f1f1f1; border: 1px solid #ddd;"
			id="staffTab" onclick="switchTab('staff')">사원 로그인</div>

		<div id="ceoContent" class="tab-content"
			style="padding: 20px; background-color: #fff; border-top: 1px solid #ddd; display: none;">
			<h3>관리자 로그인</h3>
			<form action="/user/loginCeo.jsp"
				style="display: flex; flex-direction: column; gap: 15px;">
				<label for="comid">회사코드:</label> <input type="text" id="comid"
					name="comid" style="padding: 10px; width: 100%;"><br>
				<label for="empno">사원번호:</label> <input type="text" id="empno"
					name="empno" style="padding: 10px; width: 100%;"><br>
				<label for="ename">이름:</label> <input type="text" id="ename"
					name="ename" style="padding: 10px; width: 100%;"><br>
				<label for="epwd">비밀번호:</label> <input type="password" id="epwd"
					name="epwd" style="padding: 10px; width: 100%;"><br>
				<button type="submit" style="padding: 10px;">로그인</button>
			</form>
		</div>

		<div id="adminContent" class="tab-content"
			style="padding: 20px; background-color: #fff; border-top: 1px solid #ddd; display: none;">
			<h3>점장 로그인</h3>
			<form action="/user/loginAdmin.jsp"
				style="display: flex; flex-direction: column; gap: 15px;">
				<label for="comid">회사코드:</label> <input type="text" id="comid"
					name="comid" style="padding: 10px; width: 100%;"><br>
				<label for="storenum">매장번호:</label> <input type="text" id="storenum"
					name="storenum" style="padding: 10px; width: 100%;"><br>
				<label for="empno">사원번호:</label> <input type="text" id="empno"
					name="empno" style="padding: 10px; width: 100%;"><br>
				<label for="ename">이름:</label> <input type="text" id="ename"
					name="ename" style="padding: 10px; width: 100%;"><br>
				<label for="epwd">비밀번호:</label> <input type="password" id="epwd"
					name="epwd" style="padding: 10px; width: 100%;"><br>
				<button type="submit" style="padding: 10px;">로그인</button>
			</form>
		</div>

		<div id="staffContent" class="tab-content"
			style="padding: 20px; background-color: #fff; border-top: 1px solid #ddd; display: none;">
			<h3>사원 로그인</h3>
			<form action="/user/loginStaff.jsp"
				style="display: flex; flex-direction: column; gap: 15px;">
				<label for="comid">회사코드:</label> <input type="text" id="comid"
					name="comid" style="padding: 10px; width: 100%;"><br>
				<label for="storenum">매장번호:</label> <input type="text" id="storenum"
					name="storenum" style="padding: 10px; width: 100%;"><br>
				<label for="empno">사원번호:</label> <input type="text" id="empno"
					name="empno" style="padding: 10px; width: 100%;"><br>
				<label for="ename">이름:</label> <input type="text" id="ename"
					name="ename" style="padding: 10px; width: 100%;"><br>
				<label for="epwd">비밀번호:</label> <input type="password" id="epwd"
					name="epwd" style="padding: 10px; width: 100%;"><br>
				<button type="submit" style="padding: 10px;">로그인</button>
			</form>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        function switchTab(tab) {
            // Hide all content
            document.getElementById('ceoContent').style.display = 'none';
            document.getElementById('adminContent').style.display = 'none';
            document.getElementById('staffContent').style.display = 'none';

            // Remove active class from both tabs
            document.getElementById('ceoTab').classList.remove('active-tab');
            document.getElementById('adminTab').classList.remove('active-tab');
            document.getElementById('staffTab').classList.remove('active-tab');

            // Show the clicked tab's content
            if (tab === 'ceo') {
                document.getElementById('ceoContent').style.display = 'block';
                document.getElementById('ceoTab').classList.add('active-tab');
            } else if (tab === 'admin') {
                document.getElementById('staffContent').style.display = 'block';
                document.getElementById('staffTab').classList.add('active-tab');
            } else if (tab === 'staff') {
                document.getElementById('staffContent').style.display = 'block';
                document.getElementById('staffTab').classList.add('active-tab');
            }
        }

        // Initialize with "점장 회원가입" tab active
        switchTab('ceo');
    </script>

</body>
</html>