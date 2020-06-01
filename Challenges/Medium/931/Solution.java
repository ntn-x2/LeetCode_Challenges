class Solution {
    
    public int minFallingPathSum(int[][] A) {
        for (int row = A.length-2; row >= 0; row--) {               // Starting from the end, modify the input array to store temporary minimum sums.
            for (int col = 0; col < A[0].length; col++) {
                int localMin = A[row+1][col];
                
                if (col > 0) {
                    localMin = Math.min(localMin, A[row+1][col-1]);
                }
                
                if (col < A[0].length-1) {
                    localMin = Math.min(localMin, A[row+1][col+1]);
                }
                
                A[row][col] += localMin;
            }
        }
        
        int totalMin = A[0][0];
        for (int col = 1; col < A[0].length; col++) {               // Iterate over the first row, containing the minimums for each starting element. Take the minimum.
            totalMin = Math.min(totalMin, A[0][col]);
        }
            
        return totalMin;        
    }
}