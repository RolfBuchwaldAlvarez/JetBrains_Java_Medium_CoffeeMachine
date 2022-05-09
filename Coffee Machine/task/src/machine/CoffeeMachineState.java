package machine;

public enum CoffeeMachineState {
    START,
    CHOOSE_ACTION,
    CHOOSE_COFFEE,
    MAKE_COFFEE,
    REFILL,
    REFILL_WATER,
    REFILL_MILK,
    REFILL_COFFEE_BEANS,
    REFILL_CUPS,
    AMOUNT_OF_WATER,
    AMOUNT_OF_MILK,
    AMOUNT_OF_COFFEE_BEANS,
    AMOUNT_OF_CUPS,
    REMAINING_RESOURCES,
    TAKE_MONEY,
    ERROR,
    EXIT
}