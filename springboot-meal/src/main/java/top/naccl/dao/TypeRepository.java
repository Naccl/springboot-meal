package top.naccl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.naccl.bean.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
	Type findByName(String name);
}
