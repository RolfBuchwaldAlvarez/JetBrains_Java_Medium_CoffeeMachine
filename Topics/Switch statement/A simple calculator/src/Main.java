import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // start coding here
        long firstNum = sc.nextLong();
        String operator = sc.next();
        long secondNum = sc.nextLong();
        printAnswer(firstNum, operator, secondNum);
    }

    public static void printAnswer(long firstNum, String operator, long secondNum) {
        switch (operator) {
            case "/":
                if (secondNum == 0) {
                    System.out.println("Division by 0!");
                } else {
                    System.out.println(firstNum / secondNum);
                }
                break;
            case "+":
                System.out.println(firstNum + secondNum);
                break;
            case "*":
                System.out.println(firstNum * secondNum);
                break;
            case "-":
                System.out.println(firstNum - secondNum);
                break;
            default:
                System.out.println("Unknown operator");
                break;

        }
    }
}