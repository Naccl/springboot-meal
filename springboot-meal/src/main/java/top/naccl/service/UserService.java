package top.naccl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.naccl.bean.User;


public interface UserService {
	User getUser(Integer id);

	User getUserByUsername(String name);

	User checkUser(String username, String password);

	Page<User> listUser(Pageable pageable);

	User saveUser(User user);

	User updateUser(Integer id, User user);

	void deleteUser(Integer id);
}
