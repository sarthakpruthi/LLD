package entity;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {

    Map<String, Character> cache;

    CharacterFactory(){
        cache = new HashMap<>();
    }

    public Character getCharacterObject(char c, String font, boolean isItalic, boolean isBold, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(c).append("-").append(font).append("-").append(size);
        if(isItalic) sb.append("-").append("i");
        if(isBold) sb.append("-").append("b");
        String str = sb.toString();
        if(!cache.containsKey(str)){
            System.out.println("not in cache " + sb);
            Character obj = new Character(c, font, isBold, isItalic, size);
            cache.put(str, obj);
        }
        return cache.get(str);
    }
}
