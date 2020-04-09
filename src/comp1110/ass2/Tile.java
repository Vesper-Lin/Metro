package comp1110.ass2;

public class Tile {
//determine the numbers of pieces for this type in the deck at the beginning
    private String type;
    private int number;

    public Tile(String type, int number) {
        this.type = type;
        this.number = number;
    }

    public String getType()
    {
        return type;
    }
    public int getNumber()
    {
        return number;
    }
    public void setNumber()
    {
        number=number-1;
    }
    public void addNumber()
    {
        number=number+1;
    }
}

