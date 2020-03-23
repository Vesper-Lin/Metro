package comp1110.ass2;

public class PieceTypeAndNumber {
//determine the numbers of pieces for this type in the deck at the beginning
    private String type;
    private int number;

    public PieceTypeAndNumber(String type, int number) {
        this.type = type;
        this.number = number;
    }

    public String GetType()
    {
        return type;
    }
    public int GetNumber()
    {
        return number;
    }
    public void SetNumber()
    {
        number=number-1;
    }
}

