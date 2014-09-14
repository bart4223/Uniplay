package Uniplay.Storage;

import Uniplay.Kernel.NGGameEngineEvent;

public class NG2DGameCharacterPositionChanged extends NGGameEngineEvent {

    protected NG2DGameCharacter FCharacterPosition;
    protected NG2DGame FGame;

    public NG2DGameCharacterPositionChanged(Object source, NG2DGameCharacter aCharacterPosition) {
        super(source);
        FCharacterPosition = aCharacterPosition;
        FGame = (NG2DGame)source;
    }

    public NG2DGameCharacter getCharacterPosition() {
        return FCharacterPosition;
    }

    public NG2DGame getGame() {
        return FGame;
    }

}
