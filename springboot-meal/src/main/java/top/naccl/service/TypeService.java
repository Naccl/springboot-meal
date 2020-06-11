package top.naccl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.naccl.bean.Type;

import java.util.List;

public interface TypeService {
	Type getType(Integer id);

	Type getTypeByName(String name);

	List<Type> listType();

	Page<Type> listType(Pageable pageable);

	Type saveType(Type type);

	Type updateType(Integer id, Type type);

	void deleteType(Integer id);
}
