package top.naccl.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Description: 点餐车实体类
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@Entity(name = "diningcar")
@Table
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiningCar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//设置MySQL的自增策略
	private Integer id;

	@ManyToOne
	private User user;//点餐车所属用户

	@ManyToOne
	private Food food;//点餐车中的食品，按照项目设计文档，逻辑是否有问题？
}
