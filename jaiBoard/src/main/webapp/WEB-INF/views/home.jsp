<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<%@ include file="include/head.jsp"%>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<!-- Navbar -->
		<%@ include file="include/main_header.jsp"%>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<%@ include file="include/left_column.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0 text-dark">Starter Page</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Starter Page</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-6">
							<div class="card">
                                <div class="card-header">
                                    <h5 class="m-0">Basic CRUD</h5>
                                </div>
                                <div class="card-body">
                                    <h6 class="card-title">Basic type CRUD board</h6>

                                    <p class="card-text">CRUD 기능 (페이징 검색 기능 개발로 정상 작동 불가)</p>
                                    <a href="${path}/article/list" class="btn btn-danger">Go Basic</a>
                                </div>
                            </div>

                            <div class="card card-primary card-outline">
                                <div class="card-header">
                                    <h5 class="m-0">Paging CRUD</h5>
                                </div>
                                <div class="card-body">
                                    <h6 class="card-title">Paging type CRUD board</h6>

                                    <p class="card-text">Paging CRUD 기능 (페이징 검색 기능 개발로 정상 작동 불가)</p>
                                    <a href="${path}/article/paging/list" class="btn btn-danger">Go Paging</a>
                                </div>
                            </div>
						</div>
						<!-- /.col-md-6 -->
						<div class="col-lg-6">
							<div class="card">
								<div class="card-header">
									<h5 class="m-0">Search Paging CRUD</h5>
								</div>
								<div class="card-body">
									<h6 class="card-title">Search Paging type CRUD board</h6>

									<p class="card-text">Paging, Search, 댓글, 계정, 파일 업로드</p>
									<a href="${path}/article/paging/search/list" class="btn btn-primary">Go Search</a>
								</div>
							</div>

							<div class="card card-primary card-outline">
								<div class="card-header">
									<h5 class="m-0">Profile</h5>
								</div>
								<div class="card-body">
									<h6 class="card-title">Profile manage</h6>

									<p class="card-text"> 계정관리 | 작성 글, 댓글</p>
									<a href="${path}/user/profile" class="btn btn-primary">Go Profile</a>
								</div>
							</div>
						</div>
						<!-- /.col-md-6 -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
			<div class="p-3">
				<h5>Title</h5>
				<p>Sidebar content</p>
			</div>
		</aside>
		<!-- /.control-sidebar -->

		<!-- Main Footer -->
		<%@ include file="include/main_footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->
	<%@ include file="include/plugin_js.jsp"%>
</body>
</html>

