package top.naccl.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.naccl.exception.NotFoundException;
import top.naccl.dao.FoodRepository;
import top.naccl.bean.Food;
import top.naccl.bean.Type;
import top.naccl.util.MyBeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 菜品管理操作实现类
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food getFood(Integer id) {
		return foodRepository.getOne(id);
	}

	@Override
	public Food getFoodByName(String name) {
		return foodRepository.findByName(name);
	}

	@Transactional
	@Override
	public int updateViews(Integer id) {
		return foodRepository.updateViews(id);
	}

	@Override
	public Page<Food> listFood(Pageable pageable) {
		return foodRepository.findAll(pageable);
	}

	@Override
	public Page<Food> listFood(Pageable pageable, Integer typeId) {
		return foodRepository.findAll(new Specification<Food>() {
			@Override
			public Predicate toPredicate(Root<Food> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				//查找分类
				predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), typeId));
				//拼接SQL
				criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}

	@Override
	public Page<Food> listFood(Pageable pageable, String name, Integer typeId) {
		return foodRepository.findAll(new Specification<Food>() {
			@Override
			public Predicate toPredicate(Root<Food> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				//模糊查找名称
				predicates.add(criteriaBuilder.like(root.<String>get("name"), "%" + name + "%"));
				//查找分类
				if (typeId != null && typeId != 0) {
					predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), typeId));
				}
				//拼接SQL
				criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}

	@Override
	public Page<Food> listFoodBySpecial(Integer size) {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(0, size, sort);
		return foodRepository.findByCommentGreaterThan(0, pageable);
	}

	@Override
	public Page<Food> listFoodByComment(Integer comment, Integer size) {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(0, size, sort);
		return foodRepository.findByCommentIs(comment, pageable);
	}

	@Transactional
	@Override
	public Food saveFood(Food food) {
		food.setHits(0);
		return foodRepository.save(food);
	}

	@Transactional
	@Override
	public Food updateFood(Integer id, Food food) {
		Food f = foodRepository.findById(id).orElse(null);
		if (f == null) {
			throw new NotFoundException("该菜品不存在");
		}
		BeanUtils.copyProperties(food, f, MyBeanUtils.getNullPropertyNames(food));
		return foodRepository.save(f);
	}

	@Transactional
	@Override
	public void deleteFood(Integer id) {
		foodRepository.deleteById(id);
	}

}
