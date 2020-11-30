class Solution {
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        HashMap<Integer, Integer> occurrences = this.buildOccurrencesMap(nums);
        LinkedList<Integer> currentStrike = new LinkedList<>();
        LinkedList<List<Integer>> result = new LinkedList<>();
        this.buildStrike(currentStrike, occurrences, result);
        return result;
    }
    
    private HashMap<Integer, Integer> buildOccurrencesMap(int[] nums) {
        HashMap<Integer, Integer> result = new HashMap<>(nums.length);
        
        for (int num: nums) {
            Integer currentCount = result.getOrDefault(num, 0);
            int newCount = currentCount + 1;
            result.put(num, newCount);
        }
        
        return result;
    }
    
    private void buildStrike(LinkedList<Integer> currentStrike, HashMap<Integer, Integer> remainingElements, LinkedList<List<Integer>> resultStorage) {
        if (remainingElements.isEmpty()) {
            resultStorage.add((LinkedList<Integer>) currentStrike.clone());
        } else {
            Set<Integer> keySet = remainingElements.keySet().stream().collect(Collectors.toSet());
            for (Integer key: keySet) {
                Integer currentKeyOccurrences = remainingElements.remove(key);
                if (currentKeyOccurrences > 1) {
                    remainingElements.put(key, currentKeyOccurrences-1);
                }
                currentStrike.add(key);
                buildStrike(currentStrike, remainingElements, resultStorage);
                currentStrike.removeLast();
                remainingElements.put(key, currentKeyOccurrences);
            }
        }
    }
}