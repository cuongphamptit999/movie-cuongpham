<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- sử dụng taglibs của spring để bind-data từ end-point trả về. -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- tích hợp jstl vào jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta name="description"
	content="Xem phim mới miễn phí nhanh chất lượng cao. Xem Phim online Việt Sub, Thuyết minh, lồng tiếng chất lượng HD. Xem phim nhanh online chất lượng cao" />
<meta name="keywords"
	content="HTML, CSS, JavaScript, Java, Spring, MySql, Maven" />
<meta name="author" content="CuongPham">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- FAVICONS -->
<%@ include file="/WEB-INF/views/layout/favicons.jsp"%>
<!-- -------- -->

<!-- CSS & JAVA_SCRIPT -->
<%@ include file="/WEB-INF/views/layout/includer.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/xem_phim.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/down_movie.css">
<!-- ----------------- -->

<title>Movie Project</title>
</head>

<body>
	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v5.0"></script>

	<!-- HEADER -->
	<%@ include file="/WEB-INF/views/layout/header.jsp"%>
	<!-- ------ -->

	<!-- MAIN -->
	<div id="main" class="container">
		<h1 class="my-5"></h1>
		<div class="col-md-9 phim">
			<div class="chi-tiet">
				<div class="row">
					<div class="col-md-3 poster">
						<img
							src="https://drive.google.com/uc?export=view&id=${see_movie.posterVideoPhims.get(0).id_google_drive }"
							alt="movie">
						<div class="xem-trailer">
							<a href="/movie_details/watch_series_movie/${see_movie.seo }">XEM
								PHIM</a>
						</div>
					</div>
					<div class="col-md-9 noi-dung">
						<h4>XEM PHIM ${see_movie.ten_phim_tiengviet }</h4>
						<p class="en">${see_movie.ten_phim_tienganh }</p>
						<p class="content-movie scrollbar" id="style-1">
							${see_movie.noi_dung_phim }</p>
					</div>
				</div>
				<h4 class="down">DOWNLOAD MOVIE</h4>
				<div id="download-link-list">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Tập phim</th>
								<th>Part</th>
								<th>Link download</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="nFilters" value="${1 }" scope="request" />
							<c:forEach var="videoAttachment"
								items="${see_movie.posterVideoPhims }" varStatus="loop">
								<c:if test="${loop.index > 0}">
									<tr>
										<td>${nFilters }</td>
										<td>Full</td>
										<td><a
											href="https://drive.google.com/uc?export=download&id=${videoAttachment.id_google_drive }"
											download>Download Tập ${nFilters }</a></td>
									</tr>
									<c:set var="nFilters" value="${nFilters + 1}" scope="request" />
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>


			</div>

			<div class="binhluan">
				<h4>BÌNH LUẬN</h4>
				<div style="background-color: rgba(65, 63, 63, 0.678);">
					<div class="fb-comments"
						data-href="http://128.0.0.1:${see_movie.id }"
						data-width="100%" data-numposts="5"></div>
				</div>
			</div>
		</div>
		
		<div class="col-md-3 danhmuc">
			<h1 class="my-2"></h1>
			<h5 class="tieude font-weight-bold">PHIM LẺ HOT TRONG TUẦN</h5>
			<ul class="list-unstyled list-phim scrollbar" id="style-1">
				<c:forEach var="phimlehot" items="${phimlehot }" varStatus="loop">
					<c:if test="${loop.index <9 }">
						<li>
							<div class="row">
								<div class="col-md-4">
									<a href="/odd_movie_details/${phimlehot.seo }"><img
										src="https://drive.google.com/uc?export=view&id=${phimlehot.posterVideoPhims.get(0).id_google_drive }"
										alt="movie"></a>
								</div>
								<div class="col-md-8 list-phim-infor">
									<a href="/odd_movie_details/${phimlehot.seo }"> <span
										class="list-phim-infor-vn">${phimlehot.ten_phim_tiengviet }</span>
										<br> <span class="list-phim-infor-en">${phimlehot.ten_phim_tienganh }</span>
									</a> <br> <span class="list-phim-infor-view">Lượt xem:
										${phimlehot.luot_xem }</span>
								</div>

							</div>
						</li>
					</c:if>
				</c:forEach>
			</ul>

			<h1 class="my-5"></h1>
			<h5 class="tieude font-weight-bold">PHIM BỘ HOT TRONG TUẦN</h5>
			<ul class="list-unstyled list-phim scrollbar" id="style-1">
				<c:forEach var="phimserieshot" items="${phimserieshot }"
					varStatus="loop">
					<c:if test="${loop.index <9 }">
						<li>
							<div class="row">
								<div class="col-md-4">
									<a href="/series_movie_details/${phimserieshot.seo }"><img
										src="https://drive.google.com/uc?export=view&id=${phimserieshot.posterVideoPhims.get(0).id_google_drive }"
										alt="movie"></a>
								</div>
								<div class="col-md-8 list-phim-infor">
									<a href="/series_movie_details/${phimserieshot.seo }"> <span
										class="list-phim-infor-vn">${phimserieshot.ten_phim_tiengviet }</span>
										<br> <span class="list-phim-infor-en">${phimserieshot.ten_phim_tienganh }</span>
									</a> <br> <span class="list-phim-infor-view">Lượt xem:
										${phimserieshot.luot_xem }</span>
								</div>

							</div>
						</li>
					</c:if>
				</c:forEach>
			</ul>

		</div>
		
		<div class="clear-with-height"></div>
		<h1 class=my-5></h1>
	</div>

	<!--  FOOTER -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!-- ------- -->
</body>

</html>
