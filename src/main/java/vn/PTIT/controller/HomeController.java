package vn.PTIT.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.PTIT.beans.Search;
import vn.PTIT.beans.TimeUtils;
import vn.PTIT.entities.Phim;
import vn.PTIT.repositories.PhimRepository;
import vn.PTIT.services.PhimService;
import vn.PTIT.services.UserService;

@Controller
public class HomeController {

	@Autowired
	PhimRepository phimRepository;
	@Autowired
	PhimService phimService;
	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/index", "/home" }, method = { RequestMethod.GET })
	public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimle", phimService.search1(2));
		model.addAttribute("phimbo", phimService.search1(1));

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("cartoon", phimService.cartoonMovie());

		Search search = new Search();
		model.addAttribute("search", search);

		return "index";
	}

	@RequestMapping(value = { "/user/manage" }, method = { RequestMethod.GET })
	public String quanly(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		Search search = new Search();
		model.addAttribute("search", search);
		model.addAttribute("userDis", userService.loadUserByUsername(request.getRemoteUser()));
		
		List<Phim> phim = phimRepository.findAll();
		Collections.sort(phim, new Comparator<Phim>() {

			@Override
			public int compare(Phim o1, Phim o2) {
				return o2.getLuot_xem()-o1.getLuot_xem();
			}
		});
		model.addAttribute("listMovieRP", phim);
		// tr??? v??? t??n view.
		return "admin/manage";

	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
	public String login(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		Search search = new Search();
		model.addAttribute("search", search);
		// tr??? v??? t??n view.
		return "login";

	}

	@RequestMapping(value = { "/imdb" }, method = { RequestMethod.GET })
	public String imdb(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		// tr??? v??? t??n view.
		return "imdb";

	}

	@RequestMapping(value = { "/movies/searchName" }, method = { RequestMethod.POST })
	public String search1(@ModelAttribute("search") Search search, final ModelMap model,
			final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		String nameMovie = search.getName();
		model.addAttribute("movie", phimService.searchName(nameMovie));
		return "tim_phim";
	}

	@RequestMapping(value = { "/filter_movie/phim_hoat_hinh" }, method = { RequestMethod.GET })
	public String locphim1(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);

		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim ho???t h??nh", pageNumber));
		model.addAttribute("theloai", "Phim ho???t h??nh");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";
	}

	@RequestMapping(value = { "/filter_movie/phim_hanh_dong" }, method = { RequestMethod.GET })
	public String locphim2(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim h??nh ?????ng", pageNumber));
		model.addAttribute("theloai", "Phim h??nh ?????ng");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_hai_huoc" }, method = { RequestMethod.GET })
	public String locphim3(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim h??i h?????c", pageNumber));
		model.addAttribute("theloai", "Phim h??i h?????c");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_co_trang" }, method = { RequestMethod.GET })
	public String locphim5(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		Search search = new Search();
		model.addAttribute("search", search);

		model.addAttribute("movie", phimService.searchTheLoai("Phim c??? trang", pageNumber));
		model.addAttribute("theloai", "Phim c??? trang");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_tai_lieu" }, method = { RequestMethod.GET })
	public String locphim6(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim t??i li???u", pageNumber));
		model.addAttribute("theloai", "Phim t??i li???u");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_hoihop_gaycan" }, method = { RequestMethod.GET })
	public String locphim7(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		Search search = new Search();
		model.addAttribute("search", search);

		model.addAttribute("movie", phimService.searchTheLoai("Phim h???i h???p - gay c???n", pageNumber));
		model.addAttribute("theloai", "Phim h???i h???p - gay c???n");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_hinh_su" }, method = { RequestMethod.GET })
	public String locphim8(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim h??nh s???", pageNumber));
		model.addAttribute("theloai", "Phim h??nh s???");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_bian_sieunhien" }, method = { RequestMethod.GET })
	public String locphim9(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim b?? ???n - si??u nhi??n", pageNumber));
		model.addAttribute("theloai", "Phim b?? ???n - si??u nhi??n");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_gia_dinh" }, method = { RequestMethod.GET })
	public String locphim10(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim gia ????nh", pageNumber));
		model.addAttribute("theloai", "Phim gia ????nh");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_tam_ly" }, method = { RequestMethod.GET })
	public String locphim11(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim t??m l??", pageNumber));
		model.addAttribute("theloai", "Phim t??m l??");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_kinh_di" }, method = { RequestMethod.GET })
	public String locphim12(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim kinh d???", pageNumber));
		model.addAttribute("theloai", "Phim kinh d???");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_chien_tranh" }, method = { RequestMethod.GET })
	public String locphim13(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim chi???n tranh", pageNumber));
		model.addAttribute("theloai", "Phim chi???n tranh");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_tinhcam_langman" }, method = { RequestMethod.GET })
	public String locphim14(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim t??nh c???m - l??ng m???n", pageNumber));
		model.addAttribute("theloai", "Phim t??nh c???m - l??ng m???n");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_vo_thuat" }, method = { RequestMethod.GET })
	public String locphim15(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim v?? thu???t", pageNumber));
		model.addAttribute("theloai", "Phim v?? thu???t");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_than_thoai" }, method = { RequestMethod.GET })
	public String locphim16(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim th???n tho???i", pageNumber));
		model.addAttribute("theloai", "Phim th???n tho???i");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_thethao_amnhac" }, method = { RequestMethod.GET })
	public String locphim17(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim th??? thao - ??m nh???c", pageNumber));
		model.addAttribute("theloai", "Phim th??? thao - ??m nh???c");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_vien_tuong" }, method = { RequestMethod.GET })
	public String locphim18(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim vi???n t?????ng", pageNumber));
		model.addAttribute("theloai", "Phim vi???n t?????ng");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_phieu_luu" }, method = { RequestMethod.GET })
	public String locphim19(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim phi??u l??u", pageNumber));
		model.addAttribute("theloai", "Phim phi??u l??u");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/phim_chinh_kich" }, method = { RequestMethod.GET })
	public String locphim20(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		int pageNumber = Integer.parseInt(request.getParameter("page"));

		model.addAttribute("movie", phimService.searchTheLoai("Phim ch??nh k???ch", pageNumber));
		model.addAttribute("theloai", "Phim ch??nh k???ch");
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/{nam}" }, method = { RequestMethod.GET })
	public String locphim_theonam1(@PathVariable int nam, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		model.addAttribute("movie", phimService.searchNam(nam, pageNumber));

		if (nam == TimeUtils.filterTime()) {
			model.addAttribute("theloai", "N??m " + TimeUtils.filterTime());
			model.addAttribute("namdep", TimeUtils.filterTime());
		}
		else if (nam == TimeUtils.filterTime1()) {
			model.addAttribute("theloai", "N??m " + TimeUtils.filterTime1());
			model.addAttribute("namdep", TimeUtils.filterTime1());
		} else if (nam == TimeUtils.filterTime2()) {
			model.addAttribute("theloai", "N??m " + TimeUtils.filterTime2());
			model.addAttribute("namdep", TimeUtils.filterTime2());
		}
		else if (nam == TimeUtils.filterTime3()) {
			model.addAttribute("theloai", "N??m " + TimeUtils.filterTime3());
			model.addAttribute("namdep", TimeUtils.filterTime3());
		}
		else if (nam == TimeUtils.filterTime4()) {
			model.addAttribute("theloai", "N??m " + TimeUtils.filterTime4());
			model.addAttribute("namdep", TimeUtils.filterTime4());
		}
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}

	@RequestMapping(value = { "/filter_movie/truoc_{nam}" }, method = { RequestMethod.GET })
	public String locphim_theonam2(@PathVariable int nam, final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) {
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());

		model.addAttribute("phimlehot", phimService.hotOddMovie());
		model.addAttribute("phimserieshot", phimService.hotSeriesMovie());

		model.addAttribute("imdbMovie", phimService.imdbMovie(2));

		Search search = new Search();
		model.addAttribute("search", search);
		
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		model.addAttribute("movie", phimService.searchTruocNam(nam, pageNumber));

		model.addAttribute("theloai", "Tr?????c n??m " + TimeUtils.filterTime4());
		model.addAttribute("truocnam", "Tr?????c n??m " + + TimeUtils.filterTime4());
		// tr??? v??? t??n view.
		return "loc_phim_theloai";

	}
	
	@RequestMapping(value = { "/error-403" }, method = { RequestMethod.GET })
	public String error403(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response) {

		// must have
		model.addAttribute("imdb", TimeUtils.editTime());
		model.addAttribute("filterTime", TimeUtils.filterTime());
		model.addAttribute("filterTime1", TimeUtils.filterTime1());
		model.addAttribute("filterTime2", TimeUtils.filterTime2());
		model.addAttribute("filterTime3", TimeUtils.filterTime3());
		model.addAttribute("filterTime4", TimeUtils.filterTime4());
		
		Search search = new Search();
		model.addAttribute("search", search);

		return "error_403";
	}

}
