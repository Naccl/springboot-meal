package top.naccl.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.naccl.annotation.OnlyAdmin;
import top.naccl.bean.Food;
import top.naccl.service.FoodService;
import top.naccl.service.TypeService;

import javax.validation.Valid;

/**
 * @Description: 菜品管理
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Controller
@RequestMapping("/admin")
@OnlyAdmin
public class FoodController {
	@Autowired
	private FoodService foodService;
	@Autowired
	private TypeService typeService;

	/**
	 * 菜品管理页面
	 */
	@GetMapping("/foods")
	public String foods(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("page", foodService.listFood(pageable));
		return "admin/foods";
	}

	/**
	 * 按分类查询菜品
	 */
	@PostMapping("/foods/search")
	public String search(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
	                     @RequestParam Integer typeId, Model model) {
		if (typeId != null && typeId != 0) {
			model.addAttribute("page", foodService.listFood(pageable, typeId));
		} else {
			model.addAttribute("page", foodService.listFood(pageable));
		}
		return "admin/foods :: foodList";
	}

	/**
	 * 跳转菜品添加页面
	 */
	@GetMapping("/foods/input")
	public String input(Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("food", new Food());
		return "admin/foods-input";
	}

	/**
	 * 跳转菜品修改页面
	 */
	@GetMapping("/foods/{id}/input")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("food", foodService.getFood(id));
		return "admin/foods-input";
	}

	/**
	 * POST提交 添加、修改菜品
	 */
	@PostMapping("/foods")
	public String post(@Valid Food food, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if (food.getType().getId() == null) {
			bindingResult.rejectValue("name", "nameError", "分类不能为空");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("types", typeService.listType());
			return "admin/foods-input";
		}

		Food food1 = foodService.getFoodByName(food.getName());
		if (food.getId() == null) {//添加
			if (food1 != null) {//不能添加已存在的菜品名称
				bindingResult.rejectValue("name", "nameError", "菜品已存在");
				model.addAttribute("types", typeService.listType());
				return "admin/foods-input";
			}

			Food f = foodService.saveFood(food);
			if (f == null) {//没保存成功
				redirectAttributes.addFlashAttribute("message", "添加失败");
			} else {//保存成功
				redirectAttributes.addFlashAttribute("message", "添加成功");
			}
		} else {//修改
			if (food1 != null && food1.getId() != food.getId()) {//不能修改成其它已存在的菜品名称
				bindingResult.rejectValue("name", "nameError", "菜品已存在");
				model.addAttribute("types", typeService.listType());
				return "admin/foods-input";
			}

			Food f = foodService.updateFood(food.getId(), food);
			if (f == null) {//没保存成功
				redirectAttributes.addFlashAttribute("message", "修改失败");
			} else {//保存成功
				redirectAttributes.addFlashAttribute("message", "修改成功");
			}
		}
		return "redirect:/admin/foods";
	}


	/**
	 * 删除菜品
	 */
	@GetMapping("/foods/{id}/delete")
	public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		foodService.deleteFood(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/foods";
	}
}
