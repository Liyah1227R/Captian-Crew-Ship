import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DiceGame {
    private final List<Player> users = new ArrayList<>();
    private final List<Die> dice = new ArrayList<>();
    public final int maximumRolls;
    private Player nowPlaying;

    public DiceGame(int numberOfPlayers, int diceNumber, int maximumRolls){
        if (numberOfPlayers < 2) {
            throw new IllegalArgumentException("Number of users must be at least 2.");
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            users.add(new Player());
        }
        for (int i = 0; i < diceNumber; i++) {
            dice.add(new Die(6));
        }
        this.maximumRolls = maximumRolls;
    }

    public String getGameResults() {
        users.sort(Comparator.comparingInt(Player::getTotalscore));
        List<Player> highList = new ArrayList<>();
    
        int highestScore = Integer.MIN_VALUE;
        for (Player player : users) {
            if (player.getTotalscore() > highestScore) {
                highList.clear();
                highList.add(player);
                highestScore = player.getTotalscore();
            } else if (player.getTotalscore() == highestScore) {
                highList.add(player);
            }
        }
    
        for (Player player : users) {
            if (highList.contains(player)) {
                player.TotalWon();
            } else {
                player.totalLoss();
            }
        }
    
        return "Game results: " + highList.toString();
    }
    


    public void setCurrentUser(Player player) {
        this.nowPlaying = player;
    }

    public String getRolledResults() {
        return dice.stream().map(Die::toString).collect(Collectors.joining("\n"));
    }

    public boolean keptDie(int GreatValue) {
        if (dice.stream().anyMatch(die -> die.isbeingKept() && die.getAllValues() == GreatValue)) {
            return true;
        } else if (dice.stream().anyMatch(die -> die.getAllValues() == GreatValue)) {
            dice.stream()
                .filter(die -> die.getAllValues() == GreatValue)
                .findFirst()
                .ifPresent(Die::keptDie);
            return true;
        } else {
            return false;
        }
    }
    

    public boolean nextPlayer() {
        int currentIndex = users.indexOf(nowPlaying);
        if (currentIndex == users.size() - 1) {
            return false;
        } else {
            nowPlaying = users.get(currentIndex + 1);
            return true;
        }
    }

    public void PlayerLayaway(char dieNum) {
        dice.stream()
                .filter(die -> die.getDieNum() == dieNum)
                .findFirst()
                .ifPresent(Die::GrabDie);
    }

    public void redoAlldice() {
        dice.forEach(Die::rebootDie);
    }

    public void redoplayers() {
        users.forEach(Player::redoplayers);
    }

    public void rollDice() {
        CurrentScoreP();
        nowPlaying.roll();
        dice.forEach(Die::OnTherollDie);
    }

    public void CurrentScoreP() {
        int score = 0;
    
        if (keptDie(6) && keptDie(5) && keptDie(4)) {
            int[] GreatdieValues = dice.stream().mapToInt(Die::getAllValues).toArray();
    
            for (int imagedice : GreatdieValues) { // declare your variables
                score += imagedice;
            }
    
            score -= 15;
        }
    
        nowPlaying.PlaceScores(score);
    }
    

    public void startNewGame() {
        redoplayers();
    }
    
}
