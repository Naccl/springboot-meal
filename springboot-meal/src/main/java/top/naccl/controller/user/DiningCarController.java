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
import top.naccl.bean.User;
import top.naccl.service.DiningCarService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description: 用户点餐车
 * @Author: Naccl
 * @Date: 2020-05-18
 */

@Controller
@RequestMapping("/user")
@OnlyUser
public class DiningCarController {
	@Autowired
	DiningCarService diningCarService;

	/**
	 * 查看点餐车，接收GET和POST(分页load方法需要POST)
	 */
	@RequestMapping("/diningcar")
	public String DiningCar(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
	                        Model model, HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("page", diningCarService.getUserFoods(user.getId(), pageable));
		if ("POST".equals(request.getMethod())) {
			return "user/diningcar :: foodList";
		}
		return "user/diningcar";
	}

	/**
	 * 从点餐车删除菜品
	 */
	@PostMapping("/del")
	@ResponseBody
	public JSONObject addFoodToCar(@RequestParam Integer id, HttpSession session) {
		JSONObject result = new JSONObject();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			result.put("success", false);
			result.put("message", "登录已失效，请重新登录！");
		} else {
			diningCarService.deleteDiningCarByUserIdAndFoodId(user.getId(), id);
			result.put("success", true);
			result.put("message", "移出点餐车成功！");
		}
		return result;
	}
}
