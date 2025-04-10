package entity;

import java.util.ArrayList;
import java.util.List;

public class Document {
    List<Row> rows;
    CharacterFactory factory;
    public Document(){
        rows = new ArrayList<>();
        factory = new CharacterFactory();
    }

    public void addCharacter(int ro,int col, char c, String font, boolean isItalic, boolean isBold, int size){
        if(ro > rows.size()) return;
        if(ro == rows.size()) rows.add(new Row());
        Row row = rows.get(ro);
        Character obj = factory.getCharacterObject(c, font, isItalic, isBold, size);
        row.setCharacter(obj, col);
    }

    public void display(){
        for(int i = 0; i < rows.size();i++){
            List<Character> row = rows.get(i).getRow();
            for(int j = 0; j <row.size();j++){
                System.out.print(row.get(j).toString() + "    ");
            }
            System.out.println();
        }
    }
}
