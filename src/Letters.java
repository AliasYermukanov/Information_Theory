public class Letters {
    char letter;
    int number;
    double probabilty;

    public Letters(char letter, int number, double probabilty) {
        this.letter = letter;
        this.number = number;
        this.probabilty = probabilty;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getProbabilty() {
        return probabilty;
    }

    public void setProbabilty(double probabilty) {
        this.probabilty = probabilty;
    }

    @Override
    public String toString() {
        return "\nLetters{" +
                "letter=" + letter +
                ", number=" + number +
                ", probabilty=" + probabilty +
                "}";
    }

}
