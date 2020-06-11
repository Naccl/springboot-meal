package top.naccl.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.naccl.exception.NotFoundException;
import top.naccl.dao.TypeRepository;
import top.naccl.bean.Type;

import java.util.List;

/**
 * @Description: 菜品分类操作实现类
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Service
public class TypeServiceImpl implements TypeService {
	@Autowired
	TypeRepository typeRepository;

	@Override
	public Type getType(Integer id) {
		return typeRepository.getOne(id);
	}

	@Override
	public Type getTypeByName(String name) {
		return typeRepository.findByName(name);
	}

	@Override
	public List<Type> listType() {
		return typeRepository.findAll();
	}

	@Override
	public Page<Type> listType(Pageable pageable) {
		return typeRepository.findAll(pageable);
	}

	@Transactional
	@Override
	public Type saveType(Type type) {
		return typeRepository.save(type);
	}

	@Transactional
	@Override
	public Type updateType(Integer id, Type type) {
		Type t = typeRepository.findById(id).orElse(null);
		if (t == null) {
			throw new NotFoundException("该分类不存在");
		}
		BeanUtils.copyProperties(type, t);
		return typeRepository.save(t);
	}

	@Transactional
	@Override
	public void deleteType(Integer id) {
		typeRepository.deleteById(id);
	}
}
