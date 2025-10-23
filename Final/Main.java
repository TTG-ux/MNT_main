package Final;

public class Main {
    
    public static void main(String[] args) {
        
        User user = new User("Ivan", "Ivan@mail.com", 34);

        // Можно читать данные
        System.out.println(user.getUsername());

        // // Но нельзя изменить
        // user.username = "Петр"; 
    }
}

public final class User {

    private final String username;
    private final String email;
    private final int age;

    public User(String username, String email, int age) {
        // Присваивание значения только один раз при создании
        this.username = username;
        this.email = email;
        this.age = age;
    }

    // Только методы для чтения данных
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}