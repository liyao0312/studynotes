package laohu;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class kuohao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        boolean re = isValid(s);
        if(re) {
            System.out.println("符合规则");
        }else{
            System.out.println("不符合规则");
        }

    }

    public static boolean isValid(String s) {
        HashMap<Character, Character> m= new HashMap<Character, Character>();
        m.put(')', '(');
        m.put('}', '{');
        m.put(']', '[');

        Stack<Character> st = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (m.containsKey(c)) {
                char topElement = st.empty() ? '#' : st.pop();
                if (topElement != m.get(c)) {
                    return false;
                }
            } else {
                st.push(c);
            }
        }
        return st.isEmpty();
    }

}
