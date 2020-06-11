package top.naccl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.naccl.service.FoodService;

/**
 * @Description: 菜品详情
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Controller
public class DetailController {
	@Autowired
	FoodService foodService;

	/**
	 * 菜品详情页
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable Integer id, Model model) {
		model.addAttribute("food", foodService.getFood(id));
		return "detail";
	}
}
