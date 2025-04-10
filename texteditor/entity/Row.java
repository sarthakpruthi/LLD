package entity;

import java.util.ArrayList;
import java.util.List;

public class Row {
    List<Character> row;
    Row(){
        row = new ArrayList<>();
    }


    public void setCharacter(Character obj, int col) {
        if(col > row.size()) return;
        if(col == row.size()) row.add(obj);
        else row.set(col, obj);
    }

    public List<Character> getRow() {
        return row;
    }
}
