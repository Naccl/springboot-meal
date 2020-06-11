package top.naccl.controller.user;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.naccl.annotation.OnlyUser;
import top.naccl.bean.DiningCar;
import top.naccl.bean.User;
import top.naccl.service.DiningCarService;
import top.naccl.service.FoodService;
import top.naccl.service.TypeService;

import javax.servlet.http.HttpSession;

/**
 * @Description: 用户点餐页
 * @Author: Naccl
 * @Date: 2020-05-18
 */

@Controller
@RequestMapping("/user")
@OnlyUser
public class UserIndexController {
	@Autowired
	FoodService foodService;
	@Autowired
	TypeService typeService;
	@Autowired
	DiningCarService diningCarService;

	/**
	 * 查看菜品列表
	 */
	@GetMapping("/index")
	public String index(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
	                    Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("foods", diningCarService.getUserFoods(user.getId()));
		model.addAttribute("types", typeService.listType());
		model.addAttribute("page", foodService.listFood(pageable));
		return "user/index";
	}

	/**
	 * 查询菜品
	 */
	@PostMapping("/index/search")
	public String search(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
	                     @RequestParam Integer typeId, @RequestParam String name,
	                     Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("foods", diningCarService.getUserFoods(user.getId()));
		if (name != null && !"".equals(name)) {
			model.addAttribute("page", foodService.listFood(pageable, name, typeId));
			return "user/index :: foodList";
		}
		if (typeId != null && typeId != 0) {
			model.addAttribute("page", foodService.listFood(pageable, typeId));
		} else {
			model.addAttribute("page", foodService.listFood(pageable));
		}
		return "user/index :: foodList";
	}

	/**
	 * 添加菜品到点餐车
	 */
	@PostMapping("/add")
	@ResponseBody
	public JSONObject addFoodToCar(@RequestParam Integer id, HttpSession session) {
		JSONObject result = new JSONObject();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			result.put("success", false);
			result.put("message", "登录已失效，请重新登录！");
		} else {
			DiningCar diningCar = new DiningCar();
			diningCar.setUser(user);
			diningCar.setFood(foodService.getFood(id));
			foodService.updateViews(id);
			DiningCar d = diningCarService.saveDiningCar(diningCar);
			if (d == null) {
				result.put("success", false);
				result.put("message", "加入点餐车失败！");
			} else {
				result.put("success", true);
				result.put("message", "加入点餐车成功！");
			}
		}
		return result;
	}
}
