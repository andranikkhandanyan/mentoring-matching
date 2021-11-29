package mentoringmatching.app.domain;

import akhandanyan.csv.CsvProperty;

public class Employee {
    @CsvProperty("Name")
    private String name;
    @CsvProperty("Email")
    private String email;
    @CsvProperty("Division")
    private Division division;
    @CsvProperty("Age")
    private Integer age;
    @CsvProperty("Timezone")
    private Integer timezoneOffset;

    public Employee(String name, String email, Division division, int age, int timezoneOffset) {
        this.name = name;
        this.email = email;
        this.division = division;
        this.age = age;
        this.timezoneOffset = timezoneOffset;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
