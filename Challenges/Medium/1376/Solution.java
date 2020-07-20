class Solution {
    
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (manager.length == 1) {
            return 0;
        }
        
        HashMap<Integer, LinkedList<Integer>> result = this.buildTree(manager);
        
        LinkedList<Integer> nextNodes = new LinkedList<>();
        nextNodes.add(headID);
        int max = informTime[headID];
        
        while (!nextNodes.isEmpty()) {
            int nextNodeIndex = nextNodes.removeFirst();
            LinkedList<Integer> nextNodeSuccessors = result.remove(nextNodeIndex);
            if (nextNodeSuccessors != null) {
                for (int nextNodeSuccessorIndex: nextNodeSuccessors) {
                    informTime[nextNodeSuccessorIndex] += Math.min(0, informTime[nextNodeSuccessorIndex]) + informTime[nextNodeIndex];
                    max = Math.max(max, informTime[nextNodeSuccessorIndex]);
                    nextNodes.add(nextNodeSuccessorIndex);
                }
            }
        }
        
        return max;
    }
    
    private HashMap<Integer, LinkedList<Integer>> buildTree(int[] manager) {
        HashMap<Integer, LinkedList<Integer>> result = new HashMap<>(manager.length);
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] != -1) {
                result.putIfAbsent(manager[i], new LinkedList<>());
                result.get(manager[i]).add(i);
            }
        }
        return result;
    }
}