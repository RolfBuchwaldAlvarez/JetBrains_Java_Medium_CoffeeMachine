import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int numberOfDirection = scanner.nextInt();

        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int zero = 0;

        switch (numberOfDirection) {
            case zero:
                System.out.println("do not move");
                break;
            case one:
                System.out.println("move up");
                break;
            case two:
                System.out.println("move down");
                break;
            case three:
                System.out.println("move left");
                break;
            case four:
                System.out.println("move right");
                break;
            default:
                System.out.println("error!");
                break;
        }
    }
}