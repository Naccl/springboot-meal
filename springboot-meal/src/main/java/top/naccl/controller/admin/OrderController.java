package top.naccl.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.naccl.annotation.OnlyAdmin;
import top.naccl.service.DiningCarService;


/**
 * @Description: 查看用户点餐情况
 * @Author: Naccl
 * @Date: 2020-05-18
 */

@Controller
@RequestMapping("/admin")
@OnlyAdmin
public class OrderController {
	@Autowired
	DiningCarService diningCarService;

	/**
	 * 查看用户点餐情况
	 */
	@GetMapping("/orders")
	public String orders(Model model) {
		model.addAttribute("orderMap", diningCarService.getOrders());
		return "admin/orders";
	}
}
