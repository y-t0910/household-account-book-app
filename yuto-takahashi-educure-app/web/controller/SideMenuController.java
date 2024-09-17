package jp.co.hoge.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SideMenuController {

	@GetMapping("/Side_Menu")
	public String getSideMenu(Model model) {
	    int[] data = {12, 19, 3, 5, 2, 3};
	    model.addAttribute("chartData", data);
	    return "Side_Menu";
	}

}
