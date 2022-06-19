package com.example.shandi_memo;

//캐릭터 리사이클러뷰에 사용될 CharacterItem
public class CharacterItem {
    String name;
    String className;
    String level;

    public CharacterItem(String name, String className, String level){
        this.name = name;
        this.className = className;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}