public class HomeWorkFive {
    public static void main(String[] args) {

        Employee[] employeesArray = new Employee[5];

        employeesArray[0] = new Employee("Иван Иванов", 32, "Boss", "Ivan@boss.com",
                "(0) 344-23-11", 400000);
        employeesArray[1] = new Employee("Сергей Сергеев", 41, "Engineer", "Sergey@engineer.com",
                "(0) 344-23-12", 20000);
        employeesArray[2] = new Employee("Мария Иванова", 29, "Secretary", "Mariya@secretary.com",
                "(0) 344-23-13", 1000);
        employeesArray[3] = new Employee("Татьяна Ларина", 45, "Accountant", "Tatyana@accountant.com",
                "(0) 344-23-14", 301000);
        employeesArray[4] = new Employee("Василий Покрышкин", 52, "Driver", "none",
                "(8)456 344-23-16", 10000);

        for (Employee employeeForPrint : employeesArray) {
            if (employeeForPrint.age > 40) {
                Employee.employeeInfo(employeeForPrint);
            }
        }
    }

    public static class Employee {

        private String fullName;
        private int age;
        private String post;
        private String email;
        private String phone;
        private int salary;

        public Employee(String fullName, int age, String post, String email, String phone, int salary) {
            this.fullName = fullName;
            this.age = age;
            this.post = post;
            this.email = email;
            this.phone = phone;
            this.salary = salary;
        }

        static void employeeInfo(Employee employee) {
            System.out.println("Name: " + employee.fullName + "\n" + "Age: " + employee.age + "\n" + "Post: " + employee.post +
                    "\n" + "Email: " + employee.email + "\n" + "Phone: " + employee.phone + "\n" + "Salary: " + employee.salary + "\n");

        }
    }

}
