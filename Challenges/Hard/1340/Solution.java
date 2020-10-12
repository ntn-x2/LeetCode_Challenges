class Solution {
    
    public int maxJumps(int[] arr, int d) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        int max = 0;
        
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, jump(arr, d, i, cache));
        }
        
        return max;
    }
    
    private int jump(int[] arr, int d, int startPos, HashMap<Integer, Integer> cache) {
        if (cache.containsKey(startPos)) {
            return cache.get(startPos);
        }
        
        int maxSubLength = 0;
        
        // Consider first the right side. Stop whenever there is an element higher than the current one considered.
        for (int rightIndex = startPos+1; rightIndex < arr.length && rightIndex-startPos <= d && arr[rightIndex] < arr[startPos]; ++rightIndex) {
            maxSubLength = Math.max(maxSubLength, 1 + jump(arr, d, rightIndex, cache));
        }
        
        // Consider the left side. Stop whenever there is an element higher than the current one considered.
        for (int leftIndex = startPos-1; leftIndex >= 0 && startPos-leftIndex <= d && arr[leftIndex] < arr[startPos]; --leftIndex) {
            maxSubLength = Math.max(maxSubLength, 1 + jump(arr, d, leftIndex, cache));
        }
        
        cache.put(startPos, maxSubLength);
        
        return maxSubLength;
    }
}