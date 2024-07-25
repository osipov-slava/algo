package ru.job4j.algo.stack;

import java.util.Map;
import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        var mapBrackets = Map.of(')', '(', '}', '{', ']', '[');
        boolean result = true;

        for (char ch : chars) {
            if (mapBrackets.containsValue(ch)) {
                stack.push(ch);
            }
            if (mapBrackets.containsKey(ch)) {
                if (stack.empty()) {
                    result = false;
                    break;
                }
                var stackCh = stack.pop();
                if (stackCh != mapBrackets.get(ch)) {
                    result = false;
                    break;
                }
            }
        }
        return stack.isEmpty() && result;
    }

}
