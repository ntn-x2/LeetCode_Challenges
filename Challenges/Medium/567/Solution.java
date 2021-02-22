class Solution {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;                   // s1 cannot be longer than s2
        }

        int[] s1Occurrences = new int[26];          // Each index is a character from 'a' to 'z'
        int[] s2Occurrences = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Occurrences[s1.charAt(i) - 'a']++;
            s2Occurrences[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (areEqual(s1Occurrences, s2Occurrences)) {
                return true;
            }

            char lastS2Char = s2.charAt(i);
            char nextS2Char = s2.charAt(i+s1.length());
            s2Occurrences[lastS2Char - 'a']--;
            s2Occurrences[nextS2Char - 'a']++;
        }

        return areEqual(s1Occurrences, s2Occurrences);
    }

    private boolean areEqual(int[] m1, int[] m2) {
        for (int i = 0; i < m1.length; i++) {
            if (m1[i] != m2[i]) {
                return false;
            }
        }
        return true;
    }
}