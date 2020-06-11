package top.naccl.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.naccl.annotation.OnlyUser;
import top.naccl.bean.User;
import top.naccl.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Description: 修改用户资料
 * @Author: Naccl
 * @Date: 2020-05-18
 */

@Controller
@RequestMapping("/user")
@OnlyUser
public class UserUpdateController {
	@Autowired
	UserService userService;

	@GetMapping("/input")
	public String input(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "user/user-input";
	}

	@PostMapping("/input")
	public String post(@Valid User user, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "user/user-input";
		}

		User u = (User) session.getAttribute("user");
		User user1 = userService.getUserByUsername(user.getUsername());
		if (user1 != null && user1.getId() != u.getId()) {//存在其它同名用户
			bindingResult.rejectValue("username", "nameError", "用户已存在");
			return "user/user-input";
		}

		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setTelephone(user.getTelephone());
		u.setAddress(user.getAddress());
		User user2 = userService.updateUser(u.getId(), u);
		u.setPassword(null);
		if (user2 == null) {//没保存成功
			redirectAttributes.addFlashAttribute("message", "修改失败");
		} else {//保存成功
			redirectAttributes.addFlashAttribute("message", "修改成功");
		}
		return "redirect:/user/index";
	}
}
