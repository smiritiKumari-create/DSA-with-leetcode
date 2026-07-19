import java.util.Stack;

public class Solution {
    public String smallestSubsequence(String s) {
        int[] count = new int[26];
        boolean[] visited = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            count[index]--;
            if (visited[index]) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
                char removed = stack.pop();
                visited[removed - 'a'] = false;
            }
            stack.push(c);
            visited[index] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}
