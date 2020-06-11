package top.naccl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.naccl.service.FoodService;

/**
 * @Description: 首页
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Controller
public class IndexController {
	@Autowired
	private FoodService foodService;

	/**
	 * 首页展示 普通、特价、推荐
	 */
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("common",foodService.listFoodByComment(-1,5));//普通
		model.addAttribute("special",foodService.listFoodBySpecial(5));//特价
		model.addAttribute("recommend",foodService.listFoodByComment(0,5));//推荐
		return "index";
	}
}
