package top.naccl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.naccl.bean.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);
}
