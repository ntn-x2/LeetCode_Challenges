class Solution {

    public List<Integer> lexicalOrder(int n) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 1; i <= 9; i++) {
            this.get(i, n, result);
        }
        return result;
    }

    private void get(int current, int target, LinkedList<Integer> result) {
        if (current > target) {
            return;
        }

        result.addLast(current);
        
        if (current*10 <= target) {
            get(current*10, target, result);
        }

        if (current % 10 == 0) {
            for (int i = 1; i <= 9 && current+i <= target; i++) {
                get(current+i, target, result);
            }
        }
    }
}