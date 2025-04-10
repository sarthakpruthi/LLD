package entity;

public class Character {
    char character;
    String font;
    boolean isBold;
    boolean isItalic;
    int size;

    public Character(char character, String font, boolean isBold, boolean isItalic, int size) {
        this.character = character;
        this.font = font;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.size = size;
    }

    @Override
    public String toString() {
        return character + "-" + font + '-' + isBold + "-" + isItalic + "-" + size;
    }
}
