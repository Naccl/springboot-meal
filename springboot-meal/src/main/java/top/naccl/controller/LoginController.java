package top.naccl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.naccl.bean.User;
import top.naccl.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @Description: 登录
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	/**
	 * 登录页
	 */
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	/**
	 * 验证登录
	 */
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
		User user = userService.checkUser(username, password);
		if (user != null) {
			user.setPassword(null);
			session.setAttribute("user", user);
			redirectAttributes.addFlashAttribute("message", "登录成功");
			if (user.getIdent() == 0) {
				return "redirect:/user/index";
			} else if (user.getIdent() == 1) {
				return "redirect:/admin/foods";
			}
			return "redirect:/";
		} else {
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
			return "redirect:/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		session.removeAttribute("user");
		redirectAttributes.addFlashAttribute("message", "已退出");
		return "redirect:/";
	}
}
