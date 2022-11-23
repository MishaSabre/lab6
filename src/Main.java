import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Transformator transformator = new Transformator();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение в инфиксной форме");
        String param = scan.nextLine();
        System.out.println(transformator.change(param));

    }
}
