package register;

public class Employee extends User {
    /**
     * Task 3: Create an Employee class. Employees are users, who has a position and get salary.
     * Hint: To derive a class use this syntax: public class {derived class name} extends {base class name}
     * Create a main method and create some Employee object there.
     */

    private String position;
    private int salary;

    public Employee(String name, int birthYear, String address,
                    EyeColor eyeColor, String position, int salary) {
        super(name, birthYear, address, eyeColor);
        this.position = position;
        this.salary = salary;
    }

    public Employee() {
        super("Kate Doe", 2000, "Fő utca 3.", EyeColor.GREEN);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
