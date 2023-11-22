package com.sp.desired.java.practice;

import java.util.Arrays;import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sp.desired.java.entities.Employee;

/**
 * @author sahith
 * 
 * Streams is not a Data Structure which means it does not store data and never modifies 
 * the underlying data structure. It is just a way to perform some operations on data from a data structure
 * in efficient and fast way.
 * 
 * There are 2 types of operations that can be performed
 * Intermediate - all intermediate operations are lazy
 * 	map - used only to transform
 * 	flatMap - transform and flat 
 * 	peek
 * 	sorted
 * 	
 * Terminate
 * 	forEach
 * 	collect
 * 	findFirst - shot circuit terminal operation
 * 	allMatch
 * 	noneMatch
 *  anyMatch
 *  min
 *  max
 *  distinct
 * 	
 */
public class Streams 
{
	static Employee[] emps;
	
	static Map<Integer, Employee> empMap;
	
	public static void createStreamFromArray()
	{
		Stream<Employee> empStream = Arrays.stream(emps);
		empStream.forEach(emp -> System.out.println(emp.getfName()));
		// or
		//Stream.of(emps[0], emps[1], emps[2]); // this behind the scenes uses Arrays.stream();
		// or 
		// use streambuilder
	}
	
	public static Employee getEmployeeById(Integer id)
	{
		return empMap.getOrDefault(1, null);
	}
	
	public static void basicOperation()
	{
		Integer[] empIds = {1,2,3};
		//map -> map each element in the stream to a specific type. in this case each id is mapped to Employee
		List<Employee> l1 = Stream.of(empIds)
							.map(Streams::getEmployeeById)
							.collect(Collectors.toList());
		l1.forEach(l -> System.out.println(l.getfName()));
		
		//map+filter
		List<Employee> l2 = Stream.of(empIds)
							.map(Streams::getEmployeeById)
							.filter(e -> e != null)
							.filter(e -> e.getSalary() > 100)
							.collect(Collectors.toList());
		l2.forEach(l -> System.out.println(l.getfName()));
		
		// findFirst
		Optional<Employee> employee = Stream.of(empIds)
									.map(Streams::getEmployeeById)
									.filter(e -> e != null)
									.filter(e -> e.getSalary() > 100)
									.findFirst();
		System.out.println(employee.isPresent() ? employee.get().getlName() : "No one found");
		
		// toArray
		Employee[] ee = Stream.of(empIds)
		.map(Streams::getEmployeeById)
		.toArray(Employee[]::new);
		
		//flatmap
		List<List<String>> l3 = Arrays.asList
								(
										Arrays.asList("Jeff", "Bezos"), 
			      						Arrays.asList("Bill", "Gates"), 
			      						Arrays.asList("Mark", "Zuckerberg")
			      				);
		l3.stream().flatMap(x -> Stream.of(x.get(0)+x.get(1))).forEach(System.out::println);
		
		//peek
		List<Employee> l4 = Stream.of(empIds)
							.map(Streams::getEmployeeById)
							.collect(Collectors.toList());
		l4.stream().peek(e -> e.setSalary(e.getSalary()*2)).forEach(System.out::println);
		
		// sorted
		l4.stream().sorted((e1, e2) -> e1.getfName().compareTo(e2.getfName())).forEach(System.out::println);
		
		// min
		l4.stream().min(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
		
		//max
		l4.stream().max((e1, e2) -> e2.getId() - e1.getId()).orElseThrow(NoSuchElementException::new);
		
		// distinct
		l4.stream().distinct().forEach(System.out::println);;
		
		//allMatch, anyMatch, noneMatch -- shor
		List<Integer> l5 = Arrays.asList(2, 4, 5, 6, 8);
		l5.stream().allMatch(e -> e%2==0); // check if all are divisible by 2
		l5.stream().anyMatch(i -> i%2==0); // check if element is divisible by 2
		l5.stream().noneMatch(i -> i%3==0);
		
	}	
	
	public static void main(String[] args) 
	{
		List<List<String>> l3 = Arrays.asList
				(
						Arrays.asList("Jeff", "Bezos"), 
  						Arrays.asList("Bill", "Gates"), 
  						Arrays.asList("Mark", "Zuckerberg")
  				);
l3.stream().flatMap(x -> Stream.of(x.get(0)+x.get(1))).forEach(System.out::println);
		emps = new Employee[] 
				{
					new Employee(1, "f1", "l1", 100),
					new Employee(1, "f2", "l2", 200),
					new Employee(1, "f3", "l3", 300)
				};
		empMap.put(emps[0].getId(), emps[0]);
		empMap.put(emps[1].getId(), emps[1]);
		empMap.put(emps[2].getId(), emps[2]);
		createStreamFromArray();
	}

}
