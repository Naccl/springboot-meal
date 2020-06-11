package top.naccl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.naccl.bean.DiningCar;
import top.naccl.bean.Food;
import top.naccl.bean.User;

import java.util.List;
import java.util.Map;

public interface DiningCarService {

	List<Food> getUserFoods(Integer id);

	Page<Food> getUserFoods(Integer id, Pageable pageable);

	Map<User, List<Food>> getOrders();

	DiningCar saveDiningCar(DiningCar diningCar);

	void deleteDiningCarByUserIdAndFoodId(Integer userId, Integer foodId);
}
