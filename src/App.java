import prints.Line;
import prints.Test;

public class App {
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        Line line = new Line();
        line.print();
        System.out.println("Hello, World!");
        test.print();
        line.print();
    }
}
