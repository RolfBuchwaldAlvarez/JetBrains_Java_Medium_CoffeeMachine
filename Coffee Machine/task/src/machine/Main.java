package machine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachineResources coffeeMachineResources = new CoffeeMachineResources(400, 540, 120, 9, 550);
        CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeMachineResources);
        String userInput = "";
        while (coffeeMachine.getCoffeeMachineState() != CoffeeMachineState.EXIT) {
            coffeeMachine.analyzingUserInput(userInput);
            if (coffeeMachine.getCoffeeMachineState() != CoffeeMachineState.EXIT) {
                userInput = scanner.nextLine();
            }
        }
    }

}
