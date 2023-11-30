package com.sp.desired.java.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
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
	
	public static String getEmployeeNameById(Integer id)
	{
		return empMap.get(id).getfName();
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
	
	static void advancedCollect()
	{
		List<Employee> employees = Arrays.asList(emps);
		
		// joining
		Integer[] empIds = {1,2,3};
		String names = Stream.of(empIds).map(Streams::getEmployeeNameById)
										.collect(Collectors.joining(", "))
										.toString();
		
		//grouping & mapping
		
		Map<String, List<Employee>> ss1 = employees.stream().collect(Collectors.groupingBy(Employee::getfName));
				
		Map<String, List<Double>> ss2 = employees.stream()
										.collect(Collectors.groupingBy(
																		Employee::getfName, 
																		Collectors.mapping
																		(
																			e -> e.getSalary(), Collectors.toList()
																		)
																	));
		
		//summarizingDouble,Int,Long
		DoubleSummaryStatistics stats = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(stats.getAverage());
		
		//partitionBy
		List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
		Map<Boolean, List<Integer>> s3 = intList.stream().collect(Collectors.partitioningBy(i -> i%2 == 0));
		
		//parallelstreams
		employees.stream().parallel().forEach(e -> System.out.println(e.getId()));
		
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
					new Employee(2, "f1", "l2", 100),
					new Employee(3, "f3", "l3", 300)
				};
		empMap = new HashMap<Integer, Employee>();
		empMap.put(emps[0].getId(), emps[0]);
		empMap.put(emps[1].getId(), emps[1]);
		empMap.put(emps[2].getId(), emps[2]);
		advancedCollect();
		createStreamFromArray();
	}

}
