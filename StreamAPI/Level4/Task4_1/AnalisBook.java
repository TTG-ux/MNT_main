package StreamAPI.Level4.Task4_1;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnalisBook {
    
    public static void main(String[] args) {
        
        List<Book> books = List.of(
            new Book("Java Programming", 250),
            new Book("Spring Boot Guide", 320),
            new Book("Effective Java", 150),
            new Book("Clean Code", 400),
            new Book("Algorithms", 180)
        );

        // Фильтруем, сортируем, извлекаем названия с помощью StreamAPI
        List<String> Title = books.stream()
                                  .filter(b -> b.getPage() >= 200)  // Книги от 200 стр
                                  .sorted((b1, b2) -> b2.getPage() - b1.getPage())  // сорт по убыванию
                                  .limit(3)      // первые 3
                                  .map(Book::getTitle)   // только названия
                                  .collect(Collectors.toList());
        
        out.println(Title);
    }
}

class Book {

    private final String title;
    private final int pages;

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public int getPage() {
        return pages;
    }

    @Override
    public String toString() {
        return title + " (" + pages + " стр.)";
    }
}