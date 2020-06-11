package top.naccl.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description: 食品实体类
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Entity(name = "food")
@Table
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//设置MySQL的自增策略
	private Integer id;

	@Column(nullable = false)
	@NotBlank(message = "名称不能为空")
	private String name;

	private String feature;//特色
	private String material;//材料

	@Column(nullable = false)
	@NotNull(message = "价格不能为空")
	private Integer price;//价格

	@ManyToOne
	private Type type;//1家常 2凉菜 3主食 4饮品

	private String picture;//图片路径 默认NULL
	private Integer hits;//点餐次数 默认为0

	private Integer comment;//整数代表特价菜的价格，0代表厨师推荐，-1表示为正常菜品

	@OneToMany(mappedBy = "food")//一个食品对应多个点餐车，Food作为被维护端，通过Diningcar的food建立关联
	private List<DiningCar> diningCars;
}
