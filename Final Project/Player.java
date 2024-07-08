public class Player {
    private static int AfterMathPlayerNum = 1;// keeps track of the player number
    private final int playerNum;//The Instance that represent the player number
    private int Amountwon;//number of games won
    private int AmountLost;//number of game lost
    private int score;//the players current score
    private int rolledDice;// the number of times the player has rolled the dice
    private String playerName; // create the player name
    private int playDuration; //  documents the play duration in minutes

    public Player() {
        playerNum = AfterMathPlayerNum++;// Construct increments for the next player
        playerName = "Player " + playerNum; // Default name
        playDuration = 30; // Default play duration in minutes
    }

    // Set player name
    public void setPlayerName(String name) {
        playerName = name;
    }

    // Set play duration in minutes
    public void setPlayDuration(int duration) {
        playDuration = duration;
    }

    // Get player name
    public String getPlayerName() {
        return playerName;
    }
    public int getdiceRolls() {
        return rolledDice;
    }
    public void PlaceScores(int score) {
        this.score = score;
    }

    // Get play duration in minutes
    
    public int getPlayDuration() {
        return playDuration;
    }

    public static void main(String[] args) {
        Player player1 = new Player();
        player1.setPlayerName("Alice");
        player1.setPlayDuration(45);

        System.out.println(player1);
    }

    // Override toString() method to include player name and play duration
    @Override
    public String toString() {
        return String.format("Player %d (%s): %d (Won: %d, Lost: %d) [Play Duration: %d minutes]",
                playerNum, playerName, score, Amountwon, AmountLost, playDuration);
    }
}
