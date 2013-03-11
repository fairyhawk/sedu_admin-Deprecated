package com.shangde.edu.feed.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;

/**
 * User: Libg Date: 2011-2-24 Time: 17:51:46 Description:CompareBy
 */
public class CompareByProperty {

	/**
	 * List 元素的多个属性进行排序。例如 ListSorter.sort(list, "name", "age")，则先按 name
	 * 属性排序，name 相同的元素按 age 属性排序。
	 * 
	 * @param list
	 *            包含要排序元素的 List
	 * @param properties
	 *            要排序的属性。前面的值优先级高。
	 */
	public static <V> void sortAsc(List<V> list, final String[] properties) {
		Collections.sort(list, new Comparator<V>() {
			public int compare(V o1, V o2) {
				if (o1 == null && o2 == null)
					return 0;
				if (o1 == null)
					return -1;
				if (o2 == null)
					return 1;

				for (String property : properties) {
					Comparator c = new BeanComparator(property);
					int result = c.compare(o1, o2);
					if (result != 0) {
						return result;
					}
				}
				return 0;
			}
		});
	}

	/**
	 * List 元素的多个属性进行排序。例如 ListSorter.sort(list, "name", "age")，则先按 name
	 * 属性排序，name 相同的元素按 age 属性排序。
	 * 
	 * @param list
	 *            包含要排序元素的 List
	 * @param properties
	 *            要排序的属性。前面的值优先级高。
	 */
	public static <V> void sortDesc(List<V> list, final String[] properties) {
		Collections.sort(list, new Comparator<V>() {
			public int compare(V o1, V o2) {
				if (o1 == null && o2 == null)
					return 0;
				if (o1 == null)
					return -1;
				if (o2 == null)
					return 1;

				for (String property : properties) {
					Comparator c = new BeanComparator(property);
					int result = c.compare(o2, o1);
					if (result != 0) {
						return result;
					}
				}
				return 0;
			}
		});
	}

	/**
	 * List 元素的多个属性进行排序。例如 ListSorter.sort(list, "asc:name", "desc:age")，则先按
	 * name 属性排序，name 相同的元素按 age 属性排序。
	 * 
	 * @param list
	 *            包含要排序元素的 List
	 * @param properties
	 *            要排序的属性。前面的值优先级高。
	 */
	public static <V> void sort(List<V> list, final String[] properties) {
		Collections.sort(list, new Comparator<V>() {
			String[] s = null;

			public int compare(V o1, V o2) {
				if (o1 == null && o2 == null)
					return 0;
				if (o1 == null)
					return -1;
				if (o2 == null)
					return 1;

				for (String property : properties) {
					s = property.split(":");
					Comparator c = new BeanComparator(s[1]);
					/**
					 * o1,o2 升序 o2,o1 降序
					 */
					int result = 0;
					if (s[0].equals("asc")) {
						result = c.compare(o1, o2);
					} else if (s[0].equals("desc")) {
						result = c.compare(o2, o1);
					}
					if (result != 0) {
						return result;
					}
				}
				return 0;
			}
		});
	}
}
