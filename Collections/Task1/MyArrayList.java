public class MyArrayList {
    // Внутренний массив для хранения элементов
    private int[] elements;
    
    // Текущее количество элементов в списке
    private int size;
    
    // Константа для начальной ёмкости
    private static final int DEFAULT_CAPACITY = 10;
    
    // Константа для коэффициента роста (1.5)
    private static final double GROWTH_FACTOR = 1.5;

    // Конструктор по умолчанию
    public MyArrayList() {
        elements = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    // Добавляет элемент в конец списка
    public void add(int value) {
        // Проверяем, нужно ли расширить массив
        if (size == elements.length) {
            grow();
        }
        elements[size++] = value;
    }

    // Удаляет элемент по индексу
    public void remove(int index) {
        checkIndex(index);
        
        // Сдвигаем элементы влево, начиная с удаляемого
        for (int i = index; i < size - 1; i++)
            elements[i] = elements[i + 1];
        
        size--;
    }

    // Возвращает элемент по индексу
    public int get(int index) {
        checkIndex(index);
        return elements[index];
    }

    // Проверяет наличие элемента в списке
    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == value)
                return true;
        }
        return false;
    }

    // Возвращает текущее количество элементов
    public int size() {
        return size;
    }

    /**
     * Вспомогательный метод для проверки корректности индекса
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size
            );
        }
    }

    // Вспомогательный метод для расширения массива
    private void grow() {
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        // Гарантируем как минимум +1 элемент при очень маленьких массивах
        if (newCapacity <= elements.length) {
            newCapacity = elements.length + 1;
        }
        
        int[] newArray = new int[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    // Возвращает строковое представление списка
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}