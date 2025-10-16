package OOP.OOPAbstractAndInkepsulation;

class Person {
    
    private String name;    // Приватное поле. Модификатор доступа prtvate скрывает поле и к нему нельзя обратиться напрямую
    
    public String getName() { // Метод доступа (называется такой метод - геттер)
        return name;        // Возвращает поле name 
    }


    public void setName(String name) { // Метод установки
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person();

        person.setName("null");

        System.out.println(person.getName());
    }    
}
