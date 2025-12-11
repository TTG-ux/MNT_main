package Immutable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class Record {
    
    // Все поля final и private
    private final long id;
    private final String name;
    private final List<String> tokens;  // Потенциально опасное изменяемое поле
    
    
    public Record(long id, String name, List<String> tokens) {
        this.id = id;
        this.name = name;
        // Защитная копия для изменяемой коллекции
        // Создаем новый ArrayList, копируя все элементы из переданного списка.
        // Это необходимо, потому что:
        // 1. Если сохранить ссылку на оригинальный список (this.tokens = tokens),
        //    внешний код сможет изменить наш объект, модифицируя оригинальную коллекцию.
        // 2. Создание копии "отвязывает" наш объект от внешнего состояния.
        // 3. Проверка на null защищает от NullPointerException.
        // 4. Подобный подход называется "defensive copying" (защитное копирование) и
        //    является стандартной практикой при создании неизменяемых объектов.
        this.tokens = tokens != null ? new ArrayList<>(tokens) : new ArrayList<>(); 
    }

    
    // Геттеры без сетеров
    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    // Безопасный геттер с защитной копией для изменяемой коллекции
    public List<String> getTokens() {
        return new ArrayList<>(tokens);
    }    

    /**
		 * Безопасный метод добавления токена, возвращающий новый экземпляр объекта.
		 * 
		 * Этот метод демонстрирует важнейший принцип работы с неизменяемыми объектами:
		 * вместо модификации существующего объекта, мы создаём новый объект с обновлёнными данными.
		 * 
		 * Процесс выполняется в несколько шагов:
		 * 1. Создаём копию существующей коллекции токенов (защитное копирование)
		 * 2. Вносим изменения в эту копию (добавляем новый токен)
		 * 3. Создаём новый экземпляр класса Record с этой модифицированной коллекцией
		 * 4. Возвращаем новый объект, оставляя исходный неизменным
		 * 
		 * Подобный подход используется во многих стандартных неизменяемых классах Java:
		 * - String.concat() возвращает новую строку вместо изменения текущей
		 * - BigDecimal.add() создаёт новый объект с результатом операции
		 * - Stream API и функциональные методы в коллекциях
		 * 
		 * Преимущества:
		 * - Атомарность операции - клиентский код видит объект либо в исходном, 
		 *   либо в полностью обновлённом состоянии
		 * - Потокобезопасность без явной синхронизации
		 * - Предотвращение побочных эффектов в разных частях программы
		 * 
		 * @param token токен для добавления в коллекцию
		 * @return новый объект Record с добавленным токеном
		 */
    public Record addToken(String token) {
        List<String> newToken = new ArrayList<>(this.tokens);

        newToken.add(token);
        
        return new Record(this.id, this.name, newToken);
    }


    // Правильная реализация equals для неизменяемого класса
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (getClass() != o.getClass())
            return false;

        Record record = (Record) o;

        if (id != record.id)
            return false;
        if (!Objects.equals(name, record.name)) return false;

        return Objects.equals(tokens, record.tokens);
    }

    // Правильная реализация hashcode для неизменяемого класса
    @Override
    public int hashCode() {
        return Objects.hash(id, name, tokens);
    }
    
    /**
     * Полезная реализация toString.
     */
    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tokens=" + tokens +
                '}';
    }
}