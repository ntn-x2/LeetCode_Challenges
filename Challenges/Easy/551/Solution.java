class Solution {
    public boolean checkRecord(String s) {
        
        boolean alreadyAbsent = false;
        boolean previousLate = false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                if (alreadyAbsent) {
                    return false;
                } else {
                    alreadyAbsent = true;
                    previousLate = false;
                }
            } else if (c == 'L') {
                if (previousLate && i < s.length()-1 && s.charAt(i+1) == 'L') {
                    return false;
                } else {
                    previousLate = true;
                }
            } else {
                previousLate = false;
            }
        }
        return true;
    }
}