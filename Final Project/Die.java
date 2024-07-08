import java.util.Random;

public class Die {
    
    private static char nextDieNum = 'a';
    private final Random ranGenerator = new Random();
    private Random rand;             // Random number generator
    private int value;               // Die value
    private final char NumOfDie;
    public final int NUM_SIDES = 6;  // Number of sides
    private final int sides;
    private boolean allTogether;
    private int Value;

    private Die(int sides) {
        rand = new Random();
        this.NumOfDie = nextDieNum++;
        this.sides = sides;
        toss();

        OnTherollDie();
    }

    public char getDieNum() {
        return NumOfDie;
    }

    public int getAllValues() {
        return Value;
    }

    public int getValue()
   {
      return value;
   }

    public void GrabDie() {
        this.allTogether = true;
    }
    

    public void toss()
   {
      value = rand.nextInt(NUM_SIDES) + 1;
   }

    public boolean isbeingKept() {
        return this.allTogether;
    }

    public boolean keptDie() {
        return this.allTogether;
    }

    public void rebootDie() {
        this.allTogether = false;
    }

    public void OnTherollDie() {
        if (!allTogether) {
            Value = ranGenerator.nextInt(sides) + 1;
        }
    }

    public String toString() {
        return NumOfDie + ". " + getAllValues() + (allTogether ? " (H)" : " (-)");
    }
}