import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= 8; i++) {
            queue.add(i);
        }
        
        while (!queue.isEmpty()) {
            int num = queue.poll();
            int lastDigit = num % 10;

            if (lastDigit < 9) {
                int nextNum = num * 10 + (lastDigit + 1);

                if (nextNum >= low && nextNum <= high) {
                    result.add(nextNum);
                }

                if (nextNum < high) {
                    queue.add(nextNum);
                }
            }
        }
        
        Collections.sort(result);
        return result;
    }
}
