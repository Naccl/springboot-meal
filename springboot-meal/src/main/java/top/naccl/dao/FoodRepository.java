package top.naccl.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import top.naccl.bean.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>, JpaSpecificationExecutor<Food> {

	Food findByName(String name);

	Page<Food> findByCommentGreaterThan(Integer comment, Pageable pageable);

	Page<Food> findByCommentIs(Integer comment, Pageable pageable);

	@Transactional
	@Modifying
	@Query("update food f set f.hits = f.hits+1 where f.id = ?1")
	int updateViews(Integer id);
}
