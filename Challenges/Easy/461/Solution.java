class Solution {
    
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int total = 0;
        
        while (xor > 0) {
            if (xor % 2 != 0) {
                total += 1;
            }
            xor >>= 1;
        }
        
        return total;
    }
}