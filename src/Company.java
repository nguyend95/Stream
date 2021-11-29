import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Company {
    private String city;
    private String name;
    private List<Company> subsidiaries = new ArrayList<>();
    private Company parent;
    private long revenue;
    private List<Employee> employees = new ArrayList<>();

    public Company(String city, String name, long revenue) {
        this.city = city;
        this.name = name;
        this.revenue = revenue;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public List<Company> getSubsidiaries() {
        return subsidiaries;
    }

    public Company getParent() {
        return parent;
    }

    public long getRevenue() {
        return revenue;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getEmployeesInSubsidiaries(){
        List<Employee> employeeList = new ArrayList<>();
        if (subsidiaries.isEmpty()){
            return this.employees;
        }

        for (Company c : this.subsidiaries) {
            employeeList.addAll(c.getEmployeesInSubsidiaries());
        }

        return Stream.of(employeeList, employees)
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    public void setSubsidiaries(List<Company> subsidiaries) {
        this.subsidiaries = subsidiaries;
    }

    public void setParent(Company parent) {
        this.parent = parent;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    @Override
    public String toString() {
        return "Company{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", revenue=" + revenue +
                "\nemployees=\n" + employees +
                '}';
    }
}
