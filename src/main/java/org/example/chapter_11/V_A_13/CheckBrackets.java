package org.example.chapter_11.V_A_13;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CheckBrackets {

    public static boolean check(String str) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else if (map.containsValue(c)) {
                if (stack.isEmpty() || c != map.get(stack.pop())) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String input = "{[()]}";
        boolean result = check(input);
        System.out.println("Правильность расстановки: " + result);
    }
}
