public class Person
{   

    // Приватные поля инкапсуляция
    private String name;
    private int age;
    private String email;


    // Конструктор по умолчанию
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    /*
     * Getter's
     */
    // geter [@standart] для name
    public String getName() {
        return name;
    }
    // geter [@link standart] для age
    public int getAge() {
        return age;
    }
    // geter {@standart} для email
    public String getEmail() {
        return email;
    }


    /*
     * Setter's
     */
    // setter [@standart] для name
    public void setName(String name) {
        this.name = name;
    }
    // setter [@Свой] для age
    public void setAge(int age) {
        if (age >= 18 && age <= 200)
        {
            this.age = age;
        }
        else
        {
            throw new IllegalArgumentException("Некорректный возраст");
        }
    }
    // setter [@standart] для email
    public void setEmail(String email) {
        if (email != null && email.contains("@"))
        {
            this.email = email;
        }
        else
        {
            System.out.println("Электорнная почта не должна быть пустой и должна содержать символ @");
        }
    }

    // Метод info для вывода информации
    public void info()
    {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Почта: " + email);
    }   
}