package duxiaoman;

import java.util.ArrayList;
import java.util.List;

public class leo50{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        for(int i=0;i<list.size();i++){
            System.out.print(list.remove(i));
        }
    }


}