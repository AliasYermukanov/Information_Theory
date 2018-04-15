import java.util.Collections;
import java.util.Comparator;

public class Letters implements Comparable<Letters>{
    private char letter;
    private Integer number;
    private Double probabilty;
    private String code;

    public Letters(char letter, Integer number, Double probabilty, String code) {
        this.letter = letter;
        this.number = number;
        this.probabilty = probabilty;
        this.code = code;
    }

    public Letters(char letter) {
        this.letter = letter;
        this.number = 0;
    }




    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getProbabilty() {
        return probabilty;
    }

    public void setProbabilty(Double probabilty) {
        this.probabilty = probabilty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "\nLetters{" +
                "letter=" + letter +
                ", number=" + number +
                ", probabilty=" + probabilty +
                "}";
    }

    @Override
    public int compareTo(Letters other) {
        return other.getProbabilty().compareTo(this.getProbabilty());
    }
}

