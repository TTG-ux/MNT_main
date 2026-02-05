package StreamAPI.Level4.TaskAlfa;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeePass {
    
    public static Map<String, List<Employee>> groupAndSortByDepartment(List<Employee> employees) {
        return employees.stream()
                        .collect(Collectors.groupingBy(Employee::department,
                                                       Collectors.collectingAndThen(Collectors.toList(), list -> {
                                                        // Сортируем список на месте по убыванию ЗП
                                                        list.sort(Comparator.comparingInt(Employee::salary).reversed());
                                                        return list;
                                                       }
                                                    )
                        ));
    }

    public static void main(String[] args) {
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 100000),
            new Employee("Bob", "HR", 80000),
            new Employee("Charlie", "IT", 120000),
            new Employee("Diana", "Finance", 90000),
            new Employee("Eve", "HR", 85000)
        );

        Map<String, List<Employee>> result = groupAndSortByDepartment(employees);

        result.forEach((dept, emps) -> {
                        out.println("\nотдел: " + dept);
                        emps.forEach(emp ->
                            out.println(" " + emp.name() + " - " + emp.salary())
                        );
        });
    }
}

// Record для сотрудника
record Employee(String name, String department, int salary){

}