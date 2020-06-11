package top.naccl.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.naccl.annotation.OnlyAdmin;
import top.naccl.bean.Type;
import top.naccl.service.TypeService;

import javax.validation.Valid;

/**
 * @Description: 分类管理
 * @Author: Naccl
 * @Date: 2020-05-18
 */

@Controller
@RequestMapping("/admin")
@OnlyAdmin
public class TypeController {
	@Autowired
	TypeService typeService;

	/**
	 * 获取分类列表页面
	 */
	@GetMapping("/types")
	public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model){
		model.addAttribute("page",typeService.listType(pageable));
		return "admin/types";
	}

	/**
	 * 跳转添加分类页面
	 */
	@GetMapping("types/input")
	public String input(Model model) {
		model.addAttribute("type", new Type());
		return "admin/types-input";
	}

	/**
	 * 跳转修改分类页面
	 */
	@GetMapping("/types/{id}/input")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("type", typeService.getType(id));
		return "admin/types-input";
	}

	/**
	 * POST提交 添加、修改分类
	 */
	@PostMapping("/types")
	public String post(@Valid Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		Type type1 = typeService.getTypeByName(type.getName());
		if (type1 != null) {//不能添加同名分类，也不能不修改分类名称
			bindingResult.rejectValue("name", "nameError", "分类已存在");
		}

		if (bindingResult.hasErrors()) {
			return "admin/types-input";
		}

		//保存添加、修改分类
		if (type.getId() == null) {//添加
			Type t = typeService.saveType(type);
			if (t == null) {//没保存成功
				redirectAttributes.addFlashAttribute("message", "添加失败");
			} else {//保存成功
				redirectAttributes.addFlashAttribute("message", "添加成功");
			}
		} else {//修改
			Type t = typeService.updateType(type.getId(), type);
			if (t == null) {//没保存成功
				redirectAttributes.addFlashAttribute("message", "修改失败");
			} else {//保存成功
				redirectAttributes.addFlashAttribute("message", "修改成功");
			}
		}


		return "redirect:/admin/types";
	}

	/**
	 * 删除分类
	 */
	@GetMapping("/types/{id}/delete")
	public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		typeService.deleteType(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/types";
	}
}
