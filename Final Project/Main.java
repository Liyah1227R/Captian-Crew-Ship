import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner keyboard = new Scanner(System.in);

            System.out.print("How many will be playing? ");
            int players = keyboard.nextInt();
            keyboard.nextLine();

            String[] playerNames = new String[players];
            for (int i = 0; i < players; i++) {
                System.out.print("Enter name of Player " + (i + 1) + ": ");
                playerNames[i] = keyboard.nextLine();
            }

            DiceGame diceGame = new DiceGame(players, 5, 3);

            char response;
            do {
                PlayNow(diceGame, keyboard);

                System.out.print("\nPlay Again? (y/n) ");
                try {
                    response = keyboard.nextLine().toLowerCase().charAt(0);
                } catch (StringIndexOutOfBoundsException ex) {
                    System.out.println("Wrong input");
                    response = 'n';
                }
            } while (response == 'y');

            System.out.printf("The winner is: %s", diceGame.getWinner());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void PlayNow(DiceGame game, Scanner keyboard){
        game.startNewGame();

        do {
            System.out.print("Player 1: ");
            keyboard.nextLine();
            game.rollDice();

            while (game.nextPlayer()) {
                game.rollDice();

                if (game.keptDie(6) && game.keptDie(5) && game.keptDie(4)) {
                    System.out.println(game.getRolledResults());

                    if (game.nextPlayer()) {
                        char choice;
                        do {
                            System.out.print("Press the letter of the cargo die to keep, or (r) to roll again: ");
                            try {
                                choice = keyboard.nextLine().toLowerCase().charAt(0);
                            } catch (StringIndexOutOfBoundsException ex) {
                                System.out.println("Incorrect input. Rolling again.");
                                choice = 'r';
                            }
                            if (choice != 'r') {
                                game.PlayerLayaway(choice);
                                System.out.println(game.getRolledResults());
                            }
                        } while (choice != 'r');
                    }
                } else {
                    System.out.println("There is no ship, captain, and crew. Please try again.");
                    keyboard.nextLine();
                }
            }

            game.CurrentScoreP();
            System.out.printf("Player 1 Score: %d%n", game.getGameResults());
            System.out.println("Press enter for the next player's turn.");
            keyboard.nextLine();
        } while (game.nextPlayer());

        System.out.println("Final Game Results: " + game.getGameResults());
    }
}
