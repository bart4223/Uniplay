package Uniplay.Storage;

public class NGSerialize2DLevel extends NGSerializeLevel {

    protected NGSerialize2DGameField GameField;

    public NGSerialize2DLevel() {
        super();
    }

    public void setGameField(NGSerialize2DGameField value) {
        GameField = value;
    }

    public NGSerialize2DGameField getGameField() {
        return GameField;
    }


}
