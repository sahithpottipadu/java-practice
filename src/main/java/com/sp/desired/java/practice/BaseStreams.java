package com.sp.desired.java.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.sp.desired.java.entities.Employee;

/**
 * @author sahith
 * While Stream is a stream of objects. 
 * BaseStream is a stream for primitive int, long, double datatypes.  
 *
 */
public class BaseStreams 
{
	static Employee[] emps;
	
	static Map<Integer, Employee> empMap;
	
	static List<Employee> employees;
	
	static void creating()
	{
		int xx = employees.stream().mapToInt(Employee::getId).sum();
		System.out.println(xx);
		
		IntStream s1 = IntStream.of(1,2,3);
		
		IntStream s2 = IntStream.range(1, 11);
	}
	
	static void operation()
	{
		// sum, avg, range
		int a = employees.stream().mapToInt(Employee::getId).sum(); // or average or range
		
		// reduce. We are mapping each Employee to an ID and then reducing the overall to a sum
		int sum = employees.stream().map(Employee::getId).reduce(0, Integer::sum); // this is equivalent to IntStream::sum.
		
	}
	
	public static Employee getEmployeeById(Integer id)
	{
		return empMap.getOrDefault(1, null);
	}
	
	public static void main(String[] args)
	{
		emps = new Employee[] 
				{
					new Employee(1, "f1", "l1", 100),
					new Employee(1, "f2", "l2", 200),
					new Employee(1, "f3", "l3", 300)
				};
		empMap.put(emps[0].getId(), emps[0]);
		empMap.put(emps[1].getId(), emps[1]);
		empMap.put(emps[2].getId(), emps[2]);
		employees = Arrays.asList(emps);
		creating();
	}

}
