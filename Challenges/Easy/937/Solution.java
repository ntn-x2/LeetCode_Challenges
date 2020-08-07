class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (String log1, String log2) -> {
            int identifierIndex1 = log1.indexOf(' ')+1;
            int identifierIndex2 = log2.indexOf(' ')+1;
            
            char firstLog1Char = log1.charAt(identifierIndex1);
            char firstLog2Char = log2.charAt(identifierIndex2);
            
            if (Character.isDigit(firstLog1Char) && Character.isDigit(firstLog2Char)) {
                return 0;
            } else if (Character.isDigit(firstLog1Char)) {
                return 1;
            } else if (Character.isDigit(firstLog2Char)) {
                return -1;
            } else {
                int comparisonResult = log1.substring(identifierIndex1).compareTo(log2.substring(identifierIndex2));
                return comparisonResult != 0 ? comparisonResult : log1.substring(0, identifierIndex1-1).compareTo(log2.substring(0, identifierIndex2-1));
            }
        });
        return logs;
    }
}