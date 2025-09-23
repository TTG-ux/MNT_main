public class Main
{
    // Реализация класса Person
    public static void main(String[] args)
    {
        // Создал объект person_0 для админа 
        Person person_0 = new Person("Adm", 30, "Adm@gmail.ru");
        
        Person person_1 = new Person();
        person_1.setName("Nuck");
        person_1.setAge(1);
        person_1.setEmail("Nick@mail.ru");

        // Вывод информации об Админе в консоль
        System.out.println("i о админе");
        person_0.info();
        System.out.println();


        // Вывод информации об пользователе в консоль
        System.out.println("i о пользователей");
        System.out.println(
            "Имя: "     +   person_1.getName() + "\n" +
            "Возраст: " +   person_1.getAge() + "\n" +
            "Почта: "   +   person_1.getEmail() + "\n");
    }
}
