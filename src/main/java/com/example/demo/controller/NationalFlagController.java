package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.History;
import com.example.demo.entity.Flag;
import com.example.demo.repository.FlagRepository;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class NationalFlagController {
	
	@Autowired
	HttpSession session;
	FlagRepository flagRepository;
	Flag flag;
	//初期表示・もう一度ボタン
	@GetMapping("/")
	public String index() {
		//セッション内クリア
		session.invalidate();  
		return "opening";
	}
	
	
	@GetMapping("/game")
	public ModelAndView game(ModelAndView mv) {
		@SuppressWarnings("unchecked")
		List<History> histories = (List<History>)session.getAttribute("histories");
		if (histories == null) {
			histories = new ArrayList<>();
			session.setAttribute("histories", histories);
		}
		
		if(histories.size() >= 2) {
			System.out.println(histories.size());
			mv.setViewName("end");
			mv.addObject("histories", histories);
			return mv;
		}else {
			//ランダムにIDを決める
			int id = new java.util.Random().nextInt(2);
			id++;
			//IDでDBから問題取り出し
			flag = flagRepository.findById(id).get();
			session.setAttribute("flag", flag);
			
			mv.setViewName("game");
			mv.addObject("flag", flag);
			return mv;
		}
	}
	
	
	//回答後
	
	@PostMapping("/answer")
	public ModelAndView answer(int number,ModelAndView mv) {
		flag = (Flag)session.getAttribute("flag");
		String comment;
	
		@SuppressWarnings("unchecked")
		List<History> histories = (List<History>)session.getAttribute("histories");
			
		if(number == flag.getAnswer()) {
			histories.add(new History(histories.size() + 1,"正解",flag.getTitle()));
			comment = "正解です！";
			session.setAttribute("comment", comment);
			System.out.println(histories.size());
			mv.setViewName("game3");
			mv.addObject("comment", comment);
			return mv;
			
		}else {
			histories.add(new History(histories.size() + 1,"不正解",flag.getTitle()));
			comment ="ざんねん！！不正解　　こたえは　" + flag.getTitle()  + "　の　" + flag.getAnswer() + "　でした";
			session.setAttribute("comment", comment);
			System.out.println(histories.size());
			mv.setViewName("game3");
			mv.addObject("comment", comment);
			return mv;
		}
	}
}