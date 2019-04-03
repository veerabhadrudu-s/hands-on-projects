/**
 * 
 */
package com.handson.miscellaneous;

import java.util.HashMap;
import java.util.Map;

/**
 * @author veera
 *
 */
public class Employee {

	private String name;

	public Employee(String name) {
		super();
		this.name = name;
	}

	public static void main(String[] args) {
		Employee employee = new Employee("Name");
		Employee employee1 = new Employee("Name1");
		Employee employee2 = new Employee("Name New");
		Map<Employee, Employee> map = new HashMap<>();
		map.put(employee, employee);
		map.put(employee1, employee1);
		map.put(employee2, employee2);
		System.out.println(map.size());
		System.out.println(map.get(new Employee("Name")));

	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + "]";
	}
	
	

}
