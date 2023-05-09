package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Flag;

import jakarta.servlet.http.HttpSession;

@Controller
public class NationalFlagController {
	//変数session
	@Autowired
	HttpSession session;
	Flag FlagRepository;
	int count;
	String comment;
	int rightAns;
	int answer;
	
	
	//初期表示・もう一度ボタン
	@GetMapping("/")
	public String index() {
		//セッション内クリア
		session.invalidate();  
		return "opening";
	}
	
	@GetMapping("/game")
	public ModelAndView game(ModelAndView mv) {
		//ランダムにIDを決める
		int id = new java.util.Random().nextInt(2);
		Flag flag = FlagRepository.findById(id);
		//変数answerにデータベースからanswerを格納したい
		answer = flag.getAnswer();
		
		mv.setViewName("game");
		mv.addObject("flag", flag);
		return mv;
	}
	
	
	//回答後
	
	@GetMapping("/answer/{number}")
	public ModelAndView answer(@PathVariable(name = "number")int number,ModelAndView mv) {
		count = (Integer)session.getAttribute("count");
		count++;
		answer = (Integer)session.getAttribute("answer");
			
		if(count >= 10) {
			mv.setViewName("end");
			mv.addObject("rightAns",rightAns);
			return mv;
		}else {
			if(number == answer) {
				comment = "正解です！";
				rightAns = (Integer)session.getAttribute("rightAns");
				rightAns++;
				session.setAttribute("rightAns", rightAns);
				session.setAttribute("count", count);
				
				mv.setViewName("game");
				mv.addObject("comment", comment);
				return mv;
				
			}else {
				comment = "ざんねん！！不正解　　こたえは " + answer  + " でした";
				mv.setViewName("game");
				mv.addObject("comment", comment);
				return mv;
			}
		}
	}
}