package util;

import java.util.ArrayList;
import java.util.HashMap;

public class KeyWordTree {
    String name;
    ArrayList<KeyWordTree> children;
    KeyWordTree parent;
    private HashMap<Integer, String> keywordMap;

    public KeyWordTree(String name) {
        keywordMap = new HashMap<>();
        this.name = name;
        children = new ArrayList<>();
        parent = null;
    }

    public KeyWordTree(String name, KeyWordTree parent) {
        keywordMap = new HashMap<>();
        this.name = name;
        children = new ArrayList<>();
        this.parent = parent;
    }

    public ArrayList<KeyWordTree> getChildren(){
        return children;
    }

    public void addChild(String data){
        KeyWordTree child = new KeyWordTree(data, this);
        this.children.add(child);
    }


    public boolean removeFirstChild(){
        if(this.children.size() == 0){
            return false;
        }
        children.remove(0);
        return true;
    }

    public boolean removeLastChild(){
        if(this.children.size() == 0){
            return false;
        }
        children.remove(children.size()-1);
        return true;
    }
}