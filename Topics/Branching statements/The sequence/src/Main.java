import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int num = scanner.nextInt();
        int counter = 0;

        for (int i = 1; i <= num; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(i + " ");
                counter++;
                if (counter == num) {
                    return;
                }
            }
        }
    }
}