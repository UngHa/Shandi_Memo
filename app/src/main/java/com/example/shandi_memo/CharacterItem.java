package com.example.shandi_memo;

public class CharacterItem {
    String charName;
    String className;
    String itemLevel;

    public CharacterItem(String charName, String className, String itemLevel){
        this.charName = charName;
        this.className = className;
        this.itemLevel = itemLevel;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(String itemLevel) {
        this.itemLevel = itemLevel;
    }
}