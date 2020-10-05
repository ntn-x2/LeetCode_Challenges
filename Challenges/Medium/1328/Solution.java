class Solution {
    public String breakPalindrome(String palindrome) {
        if (palindrome.length() == 1) {
            return "";
        }
        
        int inputLength = palindrome.length();
        char[] result = new char[inputLength];
        
        //Try to put an "a" in the first half of the string
        int firstHalfEndIndex = inputLength/2 - 1;
        for (int i = 0; i <= firstHalfEndIndex; i++) {
            if (palindrome.charAt(i) != 'a') {
                result[i] = 'a';
                //Copy the rest of the string
                for (int j = i+1; j < inputLength; j++) {
                    result[j] = palindrome.charAt(j);
                }
                return new String(result);
            }
            result[i] = palindrome.charAt(i);
        }
        
        // If no 'a' can be put, copy the rest of the string and put a 'b' at the end
        
        for (int i = firstHalfEndIndex+1; i < inputLength; i++) {
            result[i] = palindrome.charAt(i);
        }
        result[inputLength-1] = 'b';
            
        return new String(result);
    }
}