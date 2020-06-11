package top.naccl.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.naccl.exception.NotFoundException;
import top.naccl.dao.UserRepository;
import top.naccl.bean.User;

/**
 * @Description: 用户管理操作实现类
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUser(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User checkUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public Page<User> listUser(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Transactional
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public User updateUser(Integer id, User user) {
		User u = userRepository.findById(id).orElse(null);
		if (u == null) {
			throw new NotFoundException("该用户不存在");
		}
		BeanUtils.copyProperties(user, u);
		return userRepository.save(u);
	}

	@Transactional
	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
}
