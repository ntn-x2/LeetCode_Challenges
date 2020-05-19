class Solution {
    
    class ArrayInfo {
        Map<Integer, Integer> occurrences;
        int maxValue;
        
        ArrayInfo(Map<Integer, Integer> occurrences, int maxValue) {
            this.occurrences = occurrences;
            this.maxValue = maxValue;
        }
    }
    
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        
        ArrayInfo info = this.buildArrayInfoFor(nums);
        return isPossibleDivide(info.occurrences, nums.length, info.maxValue, k);
    }
    
    private ArrayInfo buildArrayInfoFor(int[] array) {
        Map<Integer, Integer> occurrences = new HashMap<>(array.length);
        int arrayMax = Integer.MIN_VALUE;
        
        for (int element: array) {
            int existingOccurrences = occurrences.getOrDefault(element, 0);
            occurrences.put(element, existingOccurrences+1);
            arrayMax = Math.max(arrayMax, element);
        }
        
        return new ArrayInfo(occurrences, arrayMax);
    }
    
    private boolean isPossibleDivide(Map<Integer, Integer> occurrences, int remainingElementsCount, int max, int k) {
        if (remainingElementsCount == 0) {
            return true;
        }
        
        // Find next remaining element in the set
        while (occurrences.getOrDefault(max, 0) == 0) {
            max--;
        }
        
        // Check if the next k elements are all in the set (the first iteration will be about max, which is always present at this point)
        for (int i = 0; i < k; i++) {
            int elementToFind = max-i;
            
            int elementToFindRemainingOccurrences = occurrences.getOrDefault(elementToFind, 0);
            
            if (elementToFindRemainingOccurrences == 0) {
                return false;
            }
            
            occurrences.put(elementToFind, elementToFindRemainingOccurrences-1);
        }
        
        return isPossibleDivide(occurrences, remainingElementsCount-k, max, k);
    }
}