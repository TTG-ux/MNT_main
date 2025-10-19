package Abstract;

class Main {

    public static void main(String[] args) {
        
        Shape shape1 = new Rectangle(10, 15, 200, 150);
        Shape shape2 = new Circle(50, 60, 200);

        printShape(shape1);
        printShape(shape2);
    }

    static void printShape(Shape shape) {
        shape.print();
    }
}





abstract class Shape {

    int x;  // x - координата точки
    int y;  // y - координата точки

    Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void print() {
        System.out.println("Геометрическая фигура");
    }
}

// Производный класс прямоугольника
class Rectangle extends Shape {

    int width;
    int hight;

    // Конструктор с обращением к конструктору класса Shape
    Rectangle(int x, int y, int width, int hight) {

        super(x, y);

        this.width = width;
        this.hight = hight;
    }

    @Override
    void print() {
        System.out.printf("Прямоугольник. Верхний левый угол: (%d. %d); Ширина: %d; Длинна: %d\n", x, y, width, hight);
    }
}

// Производный класс круга
class Circle extends Shape {
    int radius;

    // Конструктор с обращением к конструктору класса Shape
    Circle(int x, int y, int radius) {
        
        super(x, y);

        this.radius = radius;
    }

    @Override
    void print() {
        System.out.printf("Круг. Центр: (%d, %d); Радиус: %d\n", x, y, radius);
    }
}
