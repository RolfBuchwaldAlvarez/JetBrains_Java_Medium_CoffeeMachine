package machine;

import java.util.Objects;
import java.util.Scanner;

public class CoffeeMachine {

    private CoffeeMachineResources coffeeMachineResources;

    private CoffeeMachineState coffeeMachineState;

    private boolean isUserInputNeeded = false;

    public CoffeeMachine(CoffeeMachineResources coffeeMachineResources) {
        this.coffeeMachineResources = coffeeMachineResources;
        this.coffeeMachineState = CoffeeMachineState.START;
    }

    public CoffeeMachineResources getCoffeeMachineResources() {
        return coffeeMachineResources;
    }

    public void setCoffeeMachineResources(CoffeeMachineResources coffeeMachineResources) {
        this.coffeeMachineResources = coffeeMachineResources;
    }

    public CoffeeMachineState getCoffeeMachineState() {
        return coffeeMachineState;
    }

    public void setCoffeeMachineState(CoffeeMachineState coffeeMachineState) {
        this.coffeeMachineState = coffeeMachineState;
    }

    public boolean isUserInputNeeded() {
        return isUserInputNeeded;
    }

    public void setUserInputNeeded(boolean userInputNeeded) {
        isUserInputNeeded = userInputNeeded;
    }

    public void analyzingUserInput(String userInput) {
        if ("".equals(userInput)) {
            this.coffeeMachineState = CoffeeMachineState.CHOOSE_ACTION;
        } else if ("buy".equals(userInput)) {
            this.coffeeMachineState = CoffeeMachineState.CHOOSE_COFFEE;
        } else if ("fill".equals(userInput)) {
            this.coffeeMachineState = CoffeeMachineState.AMOUNT_OF_WATER;
        } else if ("take".equals(userInput)) {
            this.coffeeMachineState = CoffeeMachineState.TAKE_MONEY;
        } else if ("remaining".equals(userInput)) {
            this.coffeeMachineState = CoffeeMachineState.REMAINING_RESOURCES;
        } else if ("exit".equals(userInput)) {
            this.coffeeMachineState = CoffeeMachineState.EXIT;
        } else if ("1".equals(userInput) && getCoffeeMachineState() == CoffeeMachineState.CHOOSE_COFFEE) {
            this.coffeeMachineState = CoffeeMachineState.MAKE_COFFEE;
        } else if ("2".equals(userInput) && getCoffeeMachineState() == CoffeeMachineState.CHOOSE_COFFEE) {
            this.coffeeMachineState = CoffeeMachineState.MAKE_COFFEE;
        } else if ("3".equals(userInput) && getCoffeeMachineState() == CoffeeMachineState.CHOOSE_COFFEE) {
            this.coffeeMachineState = CoffeeMachineState.MAKE_COFFEE;
        }

        while (!isUserInputNeeded && this.coffeeMachineState != CoffeeMachineState.EXIT) {
            analyzeCoffeeMachineState(userInput);
        }

        if (this.isUserInputNeeded) {
            this.isUserInputNeeded = false;
        }

    }

    private void analyzeCoffeeMachineState(String userInput) {
        switch (getCoffeeMachineState()) {
            case CHOOSE_ACTION:
                askUserToChooseAnAction();
                this.isUserInputNeeded = true;
                break;
            case CHOOSE_COFFEE:
                askBuyingUserForDesiredDrink();
                this.isUserInputNeeded = true;
                break;
            case MAKE_COFFEE:
                brewCoffee(userInput);
                setCoffeeMachineState(CoffeeMachineState.CHOOSE_ACTION);
                break;
            case AMOUNT_OF_WATER:
                askFillingUserForAmountOfWater();
                this.isUserInputNeeded = true;
                setCoffeeMachineState(CoffeeMachineState.REFILL_WATER);
                break;
            case REFILL_WATER:
                refillWater(userInput);
                setCoffeeMachineState(CoffeeMachineState.AMOUNT_OF_MILK);
                break;
            case AMOUNT_OF_MILK:
                askFillingUserForAmountOfMilk();
                this.isUserInputNeeded = true;
                setCoffeeMachineState(CoffeeMachineState.REFILL_MILK);
                break;
            case REFILL_MILK:
                refillMilk(userInput);
                setCoffeeMachineState(CoffeeMachineState.AMOUNT_OF_COFFEE_BEANS);
                break;
            case AMOUNT_OF_COFFEE_BEANS:
                askFillingUserForAmountOfCoffeeBeans();
                this.isUserInputNeeded = true;
                setCoffeeMachineState(CoffeeMachineState.REFILL_COFFEE_BEANS);
                break;
            case REFILL_COFFEE_BEANS:
                refillCoffeeBeans(userInput);
                setCoffeeMachineState(CoffeeMachineState.AMOUNT_OF_CUPS);
                break;
            case AMOUNT_OF_CUPS:
                askFillingUserForAmountOfCups();
                this.isUserInputNeeded = true;
                setCoffeeMachineState(CoffeeMachineState.REFILL_CUPS);
                break;
            case REFILL_CUPS:
                refillCups(userInput);
                setCoffeeMachineState(CoffeeMachineState.CHOOSE_ACTION);
                break;
            case REMAINING_RESOURCES:
                printCoffeeMachineStatus();
                setCoffeeMachineState(CoffeeMachineState.CHOOSE_ACTION);
                break;
            case TAKE_MONEY:
                takeMoney();
                setCoffeeMachineState(CoffeeMachineState.CHOOSE_ACTION);
                break;
            case EXIT:
                return;
            default:
                break;
        }
    }

    public void printCoffeeMachineStatus() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(this.coffeeMachineResources.getWater() + " ml of water");
        System.out.println(this.coffeeMachineResources.getMilk() + " ml of milk");
        System.out.println(this.coffeeMachineResources.getCoffeeBeans() + " g of coffee beans");
        System.out.println(this.coffeeMachineResources.getCups() + " disposable cups");
        System.out.println("$" + this.coffeeMachineResources.getMoney() + " of money");
        printEmptyLine();
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void askUserToChooseAnAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    private void printIfCoffeeCanBeMade(String missingResource) {
        if ("".equals(missingResource)) {
            System.out.println("I have enough resources, making you a coffee!");
            printEmptyLine();
        } else {
            System.out.printf("Sorry, not enough %s", missingResource);
            printEmptyLine();
            return;
        }
    }

    private void askBuyingUserForDesiredDrink() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }

    private void brewCoffee(String drinkNumber) {
        switch (drinkNumber) {
            case "1":
                brewEspresso();
                break;
            case "2":
                brewLatte();
                break;
            case "3":
                brewCappuccino();
                break;
            case "back":
                returnToMainMenu();
            default:
                System.out.println("Selection does not exist!");
                printEmptyLine();
                break;
        }
    }

    private void brewEspresso() {
        String missingResource = findMissingResource(250, 0, 16);
        printIfCoffeeCanBeMade(missingResource);
        if (!"".equals(missingResource)) {
            return;
        }
        updateWater(250);
        updateCoffeeBeans(16);
        updateCups();
        addPaidMoney(4);
    }

    private void brewLatte() {
        String missingResource = findMissingResource(350, 75, 20);
        printIfCoffeeCanBeMade(missingResource);
        if (!"".equals(missingResource)) {
            return;
        }
        updateWater(350);
        updateMilk(75);
        updateCoffeeBeans(20);
        updateCups();
        addPaidMoney(7);
    }

    private void brewCappuccino() {
        String missingResource = findMissingResource(200, 100, 12);
        printIfCoffeeCanBeMade(missingResource);
        if (!"".equals(missingResource)) {
            return;
        }
        updateWater(200);
        updateMilk(100);
        updateCoffeeBeans(12);
        updateCups();
        addPaidMoney(6);
    }

    private void returnToMainMenu() {

    }

    private void updateWater(int usedWater) {
        this.coffeeMachineResources.setWater(this.coffeeMachineResources.getWater() - usedWater);
    }

    private void updateMilk(int usedMilk) {
        this.coffeeMachineResources.setMilk(this.coffeeMachineResources.getMilk() - usedMilk);
    }

    private void updateCoffeeBeans(int usedCoffeeBeans) {
        this.coffeeMachineResources.setCoffeeBeans(this.coffeeMachineResources.getCoffeeBeans() - usedCoffeeBeans);
    }

    private void updateCups() {
        this.coffeeMachineResources.setCups(this.coffeeMachineResources.getCups() - 1);
    }

    private void addPaidMoney(int paidMoney) {
        this.coffeeMachineResources.setMoney(this.coffeeMachineResources.getMoney() + paidMoney);
    }

    private void refillWater(String userInput) {
        int amountOfWater = Integer.parseInt(userInput);
        this.coffeeMachineResources.setWater(this.coffeeMachineResources.getWater() + amountOfWater);
    }

    private void refillMilk(String userInput) {
        int amountOfMilk = Integer.parseInt(userInput);
        this.coffeeMachineResources.setMilk(this.coffeeMachineResources.getMilk() + amountOfMilk);
    }

    private void refillCoffeeBeans(String userInput) {
        int amountOfCoffeeBeans = Integer.parseInt(userInput);
        this.coffeeMachineResources.setCoffeeBeans(this.coffeeMachineResources.getCoffeeBeans() + amountOfCoffeeBeans);
    }

    private void refillCups(String userInput) {
        int amountOfCups = Integer.parseInt(userInput);
        this.coffeeMachineResources.setCups(this.coffeeMachineResources.getCups() + amountOfCups);
    }

    private void askFillingUserForAmountOfWater() {
        System.out.println("Write how many ml of water you want to add:");
    }

    private void askFillingUserForAmountOfMilk() {
        System.out.println("Write how many ml of milk you want to add:");
    }

    private void askFillingUserForAmountOfCoffeeBeans() {
        System.out.println("Write how many grams of coffee beans you want to add:");
    }

    private void askFillingUserForAmountOfCups() {
        System.out.println("Write how many disposable cups of coffee you want to add:");
    }

    public void takeMoney() {
        System.out.println("I gave you $" + this.coffeeMachineResources.getMoney());
        this.coffeeMachineResources.setMoney(0);
        printEmptyLine();
    }

    private String findMissingResource(int waterNeededPerCup, int milkNeededPerCup, int coffeeBeansNeededPerCup) {
        if (!enoughWater(waterNeededPerCup)) {
            return "water";
        } else if (!enoughMilk(milkNeededPerCup)) {
            return "milk";
        } else if (!enoughCoffeeBeans(coffeeBeansNeededPerCup)) {
            return "coffeeBeans";
        } else if (!enoughCups(1)) {
            return "cups";
        } else {
            return "";
        }
    }

    private boolean enoughWater(int waterNeededPerCup) {
        return this.coffeeMachineResources.getWater() - waterNeededPerCup > 0;
    }

    private boolean enoughMilk(int milkNeededPerCup) {
        return this.coffeeMachineResources.getMilk() - milkNeededPerCup > 0;
    }

    private boolean enoughCoffeeBeans(int coffeeBeansNeededPerCup) {
        return this.coffeeMachineResources.getCoffeeBeans() - coffeeBeansNeededPerCup > 0;
    }

    private boolean enoughCups(int cupsNeededPerCup) {
        return this.coffeeMachineResources.getCups() - cupsNeededPerCup > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeMachine that = (CoffeeMachine) o;
        return isUserInputNeeded == that.isUserInputNeeded && coffeeMachineResources.equals(that.coffeeMachineResources) && coffeeMachineState == that.coffeeMachineState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeeMachineResources, coffeeMachineState, isUserInputNeeded);
    }
}
