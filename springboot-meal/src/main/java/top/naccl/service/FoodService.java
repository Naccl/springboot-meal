package top.naccl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.naccl.bean.Food;

public interface FoodService {
	Food getFood(Integer id);

	Food getFoodByName(String name);

	int updateViews(Integer id);

	Page<Food> listFood(Pageable pageable);

	Page<Food> listFood(Pageable pageable, Integer typeId);

	Page<Food> listFood(Pageable pageable, String name, Integer typeId);

	Page<Food> listFoodBySpecial(Integer size);

	Page<Food> listFoodByComment(Integer comment, Integer size);

	Food saveFood(Food food);

	Food updateFood(Integer id, Food food);

	void deleteFood(Integer id);
}
