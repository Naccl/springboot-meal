package top.naccl.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 食品分类实体类
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Entity(name = "foodtype")
@Table
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//设置MySQL的自增策略
	private Integer id;

	@Column(nullable = false)
	@NotBlank(message = "分类名称不能为空")
	private String name;

	@OneToMany(mappedBy = "type")//一个分类对应多个食品，Foodtype作为被维护端，通过Food的type建立关联
	private List<Food> foods = new ArrayList<>();
}
