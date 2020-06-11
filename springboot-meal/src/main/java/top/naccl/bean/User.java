package top.naccl.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description: 用户实体类
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Entity(name = "user")
@Table
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//设置MySQL的自增策略
	private Integer id;

	@Column(nullable = false)
	@NotBlank(message = "用户名不能为空")
	private String username;

	@Column(nullable = false)
	@NotBlank(message = "密码不能为空")
	private String password;

	@Column(nullable = false)
//	@NotNull(message = "身份不能为空") 不判断，后台自动加
	private Integer ident;//0普通用户 1管理员

	@Column(nullable = false)
	@NotBlank(message = "电话不能为空")
	private String telephone;

	@Column(nullable = false)
	@NotBlank(message = "地址不能为空")
	private String address;

	@OneToMany(mappedBy = "user")//一个用户对应多个点餐车，User作为被维护端，通过Diningcar的user建立关联
	private List<DiningCar> diningCars;
}
