package top.naccl.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.naccl.bean.DiningCar;
import top.naccl.bean.Food;

import java.util.List;

public interface DiningCarRepository extends JpaRepository<DiningCar, Integer> {

	@Query("select d from diningcar d group by d.user order by d.user.id desc")
	List<DiningCar> findGroupUser();

	@Query("select d.food from diningcar d where d.user.id = ?1 order by d.food.id desc")
	List<Food> findByUserId(Integer id);

	@Query("select d.food from diningcar d where d.user.id = ?1 order by d.food.id desc")
	Page<Food> findByUserId(Integer id, Pageable pageable);

	@Query("select sum(case when d.food.comment>0 then d.food.comment else d.food.price end) from diningcar d where d.user.id=?1")
	int sumPrice(Integer id);

	void deleteByUserIdAndFoodId(Integer userId, Integer foodId);
}
