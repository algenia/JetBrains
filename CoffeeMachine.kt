package machine

class CoffeeMachine {
    var water = 400
    var milk = 540
    var beans = 120
    var cups = 9
    var money = 550
    var refill = 0
    var currentState = "level1"

    fun begin() {
        println("Write action (buy, fill, take, remaining, exit): ")
    }

    fun remaining(water: Int, milk: Int, beans: Int, cups: Int, money: Int) {
        println()
        println(
            """
        The coffee machine has:
        $water ml of water
        $milk ml of milk
        $beans g of coffee beans
        $cups disposable cups
        $$money of money
    """.trimIndent()
        )
        println()
    }

    fun take(money: Int): Int {
        println()
        println("I gave you $$money")
        println()
        return 0
    }

    fun chooseAction(action: String){
        when (currentState) {
            "buy" -> {
                when (action) {
                    //espresso(water - 250, milk, beans - 16, cups - 1, money + 4)
                    "1" -> {
                        if (water - 250 < 0) println("Sorry, not enough water!")
                        else if (beans - 16 < 0) println("Sorry, not enough beans!")
                        else if (cups - 1 < 0) println("Sorry, not enough cups!")
                        else {
                            println("I have enough resources, making you a coffee!")
                            water -= 250
                            beans -= 16
                            cups --
                            money += 4
                        }
                    }
                    //latte(water - 350, milk - 75, beans - 20, cups - 1, money + 7)
                    "2" -> {
                        if (water - 350 < 0) println("Sorry, not enough water!")
                        else if (milk - 75 < 0) println("Sorry, not enough milk!")
                        else if (beans - 20 < 0) println("Sorry, not enough beans!")
                        else if (cups - 1 < 0) println("Sorry, not enough cups!")
                        else {
                            println("I have enough resources, making you a coffee!")
                            water -= 350
                            milk -= 75
                            beans -= 20
                            cups --
                            money += 7
                        }
                    }
                    //cappuccino(water - 200, milk - 100, beans - 12, cups - 1, money + 6)
                    "3" -> {
                        if (water - 200 < 0) println("Sorry, not enough water!")
                        else if (milk - 100 < 0) println("Sorry, not enough milk!")
                        else if (beans - 12 < 0) println("Sorry, not enough beans!")
                        else if (cups - 1 < 0) println("Sorry, not enough cups!")
                        else {
                            println("I have enough resources, making you a coffee!")
                            water -= 200
                            milk -= 100
                            beans -= 12
                            cups --
                            money += 6
                        }
                    }
                }
                currentState = "level1"
                println()
                begin()
            }

            "refill" -> {
                when (refill) {
                    0 -> {
                        water += action.toInt()
                        println("Write how many ml of milk do you want to add: ")
                        refill++
                    }
                    1 -> {
                        milk += action.toInt()
                        println("Write how many grams of coffee beans do you want to add: ")
                        refill++
                    }
                    2 -> {
                        beans += action.toInt()
                        println("Write how many disposable cups of coffee do you want to add: ")
                        refill++
                    }
                    3 -> {
                        cups += action.toInt()
                        refill = 0
                        currentState = "level1"
                        println()
                        begin()
                    }
                }
            }

            else -> {
                when (action) {
                    "buy" -> {
                        println()
                        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                        currentState = "buy"
                    }
                    "fill" -> {
                        println()
                        println("Write how many ml of water do you want to add: ")
                        currentState = "refill"
                    }
                    "take" -> {
                        money = take(money)
                        currentState = "level1"
                        begin()
                    }
                    "remaining" -> {
                        remaining(water, milk, beans, cups, money)
                        currentState = "level1"
                        begin()
                    }
                }
            }
        }
    }
}

fun main() {
    val coffee = CoffeeMachine()
    var input = ""

    println("Write action (buy, fill, take, remaining, exit): ")

    while (input != "exit") {
        input = readln()
        coffee.chooseAction(input)
    }
}
