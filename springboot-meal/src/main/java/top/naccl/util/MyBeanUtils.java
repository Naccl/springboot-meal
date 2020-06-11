package top.naccl.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: BeanUtils.copyProperties 排除属性
 * @Author: Naccl
 * @Date: 2020-05-17
 */
public class MyBeanUtils {
	private static List<String> getNullPropertyNamesList(Object source) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
		List<String> nullPropertyNames = new ArrayList<>();
		for (PropertyDescriptor pd : pds) {
			String propertyName = pd.getName();
			if (beanWrapper.getPropertyValue(propertyName) == null) {
				nullPropertyNames.add(propertyName);
			}
		}
		return nullPropertyNames;
	}

	public static String[] getNullPropertyNames(Object source) {
		List<String> nullPropertyNames = getNullPropertyNamesList(source);
		return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
	}

	public static String[] getNullAndIgnorePropertyNames(Object source, String[] propertyNames) {
		List<String> nullPropertyNames = getNullPropertyNamesList(source);
		for (String p : propertyNames) {
			nullPropertyNames.add(p);
		}
		return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
	}


}
