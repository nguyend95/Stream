import java.util.Objects;

public class Employee {
    private String personalId;
    private String city;
    private String fullName;
    private boolean married;
    private int salary;

    public Employee(String personalId, String city, String fullName, boolean married, int salary) {
        this.personalId = personalId;
        this.city = city;
        this.fullName = fullName;
        this.married = married;
        this.salary = salary;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isMarried() {
        return married;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "personalId='" + personalId + '\'' +
                ", city='" + city + '\'' +
                ", fullName='" + fullName + '\'' +
                ", married=" + married +
                ", salary=" + salary + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return married == employee.married && salary == employee.salary && Objects.equals(personalId, employee.personalId) && Objects.equals(city, employee.city) && Objects.equals(fullName, employee.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalId, city, fullName, married, salary);
    }
}
