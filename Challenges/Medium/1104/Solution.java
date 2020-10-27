class Solution {
    
    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> result = new LinkedList<>();
        
        while (label >= 1) {
            result.addFirst(label);
            int row = (int) Math.floor(Math.log(label) / Math.log(2)) + 1;
            int indexInRange = (int) (row % 2 != 0 ? label - Math.pow(2, row-1) : Math.pow(2, row) - 1 - label);
            int parentIndex = indexInRange / 2;
            label = (int) ((row-1) % 2 != 0 ? Math.pow(2, row-2) + parentIndex : Math.pow(2, row-1) - 1 - parentIndex);
        }
        
        return result;
    }
}