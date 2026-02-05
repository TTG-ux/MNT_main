package StreamAPI.Level4.Task4_2;

import static java.lang.System.out;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortedObjects {
    
    public static void main(String[] args) {
        
        List<Person> people = List.of(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 25),
            new Person("David", 30),
            new Person("Eve", 35)
        );

        // Сортируем по возрасту с помощью StreamAPI
        out.println("--Сортировка по возрасту--\n");

        people.stream()
              .sorted(Comparator.comparing(Person::getAge))
              .forEach(out::println);

        // Сортировка по имени с помощью StreamAPI
        out.println("\n--Сортировка по имени--\n");

        people.stream()
              .sorted(Comparator.comparing(Person::getName))
              .forEach(out::println);

        // Группируем по возрасту с помощью StreanAPI
        out.println("\n--Группировка по возрасту--\n");

        Map<Integer, List<Person>> sortedByAge = people.stream()
                                                       .collect(Collectors.groupingBy(Person::getAge));
        
        sortedByAge.forEach((age, persons) ->
                            out.println(age + " лет: " + persons)
        );

        // Группируем по категориям возраста
        out.println("\n--Группировка по категории возраста--\n");

        Map<String, List<Person>> sortedByCategoryAge = people.stream()
            .collect(Collectors.groupingBy(p -> {
            if (p.getAge() < 20)
                return "Молодеж";
            if (p.getAge() < 30)
                return "Взрослые";
            
            return "Старшее поколение";
        }));

        sortedByCategoryAge.forEach((cat, persons) ->
            out.println(cat + ": " + persons)
        );
    }
}

class Person {

    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}