import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        List<String> names = Stream.of("Jeff", "Bill", "Mark", "Elon", "Vojta", "Petr")
                .collect(Collectors.toList());
        List<String> surnames = Stream.of("Gates", "Boz", "Diviner", "Sheet", "Doew")
                .collect(Collectors.toList());
        List<String> companyName = Stream.of("Keri", "Popoba", "Fomi", "Rerend")
                .collect(Collectors.toList());
        List<String> cities = Stream.of("Prague", "Berlin", "London", "New York", "Hanoi", "Nupaky", "Abuja")
                .collect(Collectors.toList());

        List<Employee> employees = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        Random rand = new Random();

//        vytvoreni 4 company
        for (int i = 0; i < 4; i++){
            companies.add(new Company(
                    cities.get(rand.nextInt(cities.size())),
                    companyName.get(rand.nextInt(companyName.size())),
                    rand.nextLong()));
        }

//        vazba mezi ne
        companies.get(1).setSubsidiaries(
                Stream.of(companies.get(2), companies.get(3)).collect(Collectors.toList()));
        companies.get(2).setParent(companies.get(1));
        companies.get(3).setParent(companies.get(1));

//        vytvoreni employee
        for (int i = 0; i < 12; i++){
            Employee employee = new Employee(String.valueOf(i),
                    cities.get(rand.nextInt(cities.size())),
                    names.get(rand.nextInt(names.size())) + " " + surnames.get(rand.nextInt(surnames.size())),
                    rand.nextBoolean(),
                    rand.nextInt());

            employees.add(employee);

            if (i % 3 == 0){
                companies.get(1).addEmployee(employee);
                continue;
            }

            if (i % 5 == 0){
                companies.get(0).addEmployee(employee);
                continue;
            }

            if (i % 4 == 0){
                companies.get(2).addEmployee(employee);
                continue;
            }

            companies.get(3).addEmployee(employee);
        }

//        companies.forEach(System.out::println);

//        employees.forEach(System.out::println);

//        System.out.println("Employees with salary greater than 300k");
//        employees.stream()
//                .filter(e -> e.getSalary() > 300_000)
//                .collect(Collectors.toList())
//                .forEach(System.out::println);

        System.out.println("Number of employee with salary greater than 300k");
        System.out.println(
                employees.stream()
                .filter(e -> e.getSalary() > 300_000)
                .count());

        System.out.println("Get employee in subsidiaries as well");
        System.out.println(companies.get(1).getEmployeesInSubsidiaries().stream().count());
//        companies.get(1).getEmployeesInSubsidiaries().forEach(System.out::println);

        System.out.println("Sort employees by city then salary");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getCity)
                .thenComparing(Comparator.comparing(Employee::getSalary).reversed()))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("Employee with the least salary");
        System.out.println(employees.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new));

        System.out.println("allMatch, anyMatch, and noneMatch");
        System.out.println(employees.stream()
                .anyMatch(employee -> employee.getSalary() > 200_000));

        System.out.println("IntStream, LongStream and DoubleStream");
        System.out.println(employees.stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(NoSuchElementException::new));

        System.out.println("Avg salary");
        System.out.printf("%.2f%n", employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElseThrow(NoSuchElementException::new));

        System.out.println("employee's names group by city");
        employees.stream()
                .collect(groupingBy(Employee::getCity,
                        Collectors.mapping(Employee::getFullName, toList())))
                .forEach((city, employee) ->
                        employee.forEach(e ->
                                System.out.println(city + " | " + e)));

//        employeeGroupByCity.forEach(
//                (city, employee) -> employee.forEach(e ->
//                        System.out.println(city + " | " + e.getFullName()))
//        );
    }
}
