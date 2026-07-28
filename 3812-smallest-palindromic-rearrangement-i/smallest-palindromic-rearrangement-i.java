class Solution {
    public String smallestPalindrome(String s) {
     int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        String middle = "";
        for (int i = 0; i < 26; i++) {

            for (int j = 0; j < count[i] / 2; j++) {
                left.append((char) (i + 'a'));
            }
            if (count[i] % 2 == 1) {
                middle = String.valueOf((char) (i + 'a'));
            }
        }
        String right = new StringBuilder(left).reverse().toString();

        return left.toString() + middle + right;
    }
}