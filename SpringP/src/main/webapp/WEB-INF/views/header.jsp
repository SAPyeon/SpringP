<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row header">
	<div class="col-xs-12">
		<nav class="navbar navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Navbar</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="/board/list">주가리스트</a></li>
						<li class="nav-item"><a class="nav-link" href="/community/list">커뮤니티</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								Dropdown </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="#">Action</a></li>
								<li><a class="dropdown-item" href="#">Another action</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li><a class="dropdown-item" href="#">Something else
										here</a></li>
							</ul></li>
						<li class="nav-item"><a class="nav-link disabled">Disabled</a>
						</li>
					</ul>
					<form class="d-flex" action="/board/searchList" method="get"
						id="searchForm">
						<input class="form-control me-2" type="search"
							placeholder="Search" aria-label="Search" id="searchInput"
							name="search">
						<button class="btn btn-outline-success" type="submit"
							id="goToSearchPage">Search</button>
					</form>
				</div>
			</div>
		</nav>
	</div>
	<div style="text-align: right">
		<c:choose>
			<c:when test="${loginName==null}">
				<a href="/member/login">로그인하기</a>
			</c:when>
			<c:otherwise>
				<label>Welcome! ${loginName}님</label>
				<a href="/member/mypage">마이페이지</a>
				<a href="/member/logout">로그아웃하기</a>
			</c:otherwise>
		</c:choose>
		<input type="hidden" value="${loginId}" id="loginId">
	</div>
	<div></div>
</div>