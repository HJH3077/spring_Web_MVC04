package com.ict.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.VO.VO;
import com.ict.service.MyProcess;

@Controller
public class MyController {
	@Autowired
	private MyProcess myProcess;

	public void setMyProcess(MyProcess myProcess) {
		this.myProcess = myProcess;
	}

	// 파라미터 값 받기 : 1. JSP 처럼 받기(request로 받기)
	/*
	 * @RequestMapping("grades.do") public ModelAndView
	 * GradesCommand(HttpServletRequest request) { ModelAndView mv = new
	 * ModelAndView("result"); String name = request.getParameter("name"); int kor =
	 * Integer.parseInt(request.getParameter("kor")); int eng =
	 * Integer.parseInt(request.getParameter("eng")); int math =
	 * Integer.parseInt(request.getParameter("math"));
	 * 
	 * // 총점 int sum = myProcess.getSum(kor, eng, math);
	 * 
	 * // 평균 double avg = myProcess.getAvg(sum);
	 * 
	 * // 학점 String hak = myProcess.getHak(avg);
	 * 
	 * mv.addObject("name", name); mv.addObject("sum", sum); mv.addObject("avg",
	 * avg); mv.addObject("hak", hak); return mv; }
	 */

	/*
	 * // 파라미터 값 받기 : 2. vo를 사용하여 파라미터 값 받기 (권장) // 자동으로 vo에 들어온다. 단, 파라미터의 이름과 vo의
	 * 이름이 같아야 한다.
	 * 
	 * @RequestMapping("grades.do") public ModelAndView GradesCommand(VO vo) {
	 * ModelAndView mv = new ModelAndView("result");
	 * 
	 * int sum = myProcess.getSum(vo.getKor(), vo.getEng(), vo.getMath());
	 * 
	 * double avg = myProcess.getAvg(sum);
	 * 
	 * String hak = myProcess.getHak(avg);
	 * 
	 * 
	 * mv.addObject("name", vo.getName()); mv.addObject("sum", sum);
	 * mv.addObject("avg", avg); mv.addObject("hak", hak);
	 * 
	 * 
	 * // vo에 다 담기 vo.setSum(String.valueOf(sum)); // vo에 String으로 저장해서 변환한 것, int면
	 * 그냥 넣으면 됨 vo.setAvg(String.valueOf(avg)); vo.setHak(hak);
	 * 
	 * mv.addObject("vo", vo); // vo에 결과를 저장할 수 있도록 만들자 return mv; }
	 */

	/*
	 * // 파라미터 값 받기 : 3. Model 사용 // 들어온 파라미터는 vo에 담고 리턴할 때 mvo라는 이름으로 데이터를 가지고
	 * 리턴한다.
	 * 
	 * @RequestMapping("grades.do") public ModelAndView
	 * GradesCommand(@ModelAttribute("mvo")VO vo) { ModelAndView mv = new
	 * ModelAndView("result");
	 * 
	 * int sum = myProcess.getSum(vo.getKor(), vo.getEng(), vo.getMath());
	 * 
	 * double avg = myProcess.getAvg(sum);
	 * 
	 * String hak = myProcess.getHak(avg);
	 * 
	 * 
	 * mv.addObject("name", vo.getName()); mv.addObject("sum", sum);
	 * mv.addObject("avg", avg); mv.addObject("hak", hak);
	 * 
	 * 
	 * // vo에 다 담기 vo.setSum(String.valueOf(sum)); // vo에 String으로 저장해서 변환한 것, int면
	 * 그냥 넣으면 됨 vo.setAvg(String.valueOf(avg)); vo.setHak(hak);
	 * 
	 * // Model은 나갈 때 쓰는 것, model에 vo를 다 집어넣고 보내버림 // mv.addObject("vo", vo); // 이걸
	 * 안 붙여도됨 return mv; }
	 */

	// 파라미터 값 받기 : 4. 파라미터를 어노테이션으로 받기(@RequestParam("cPage")String cPage)
	@RequestMapping("grades.do")
	// VO가 아닌 cPage는 따로 request로 받아야 하므로 이런식으로 작성가능
	public ModelAndView GradesCommand(VO vo, @RequestParam("cPage")String cPage) {
		ModelAndView mv = new ModelAndView("result");

		int sum = myProcess.getSum(vo.getKor(), vo.getEng(), vo.getMath());

		double avg = myProcess.getAvg(sum);

		String hak = myProcess.getHak(avg);

		vo.setSum(String.valueOf(sum));
		vo.setAvg(String.valueOf(avg));
		vo.setHak(hak);

		mv.addObject("vo", vo); 
		return mv;
	}
}
