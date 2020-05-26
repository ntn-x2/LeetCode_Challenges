class RLEIterator {

    private int[] encodedSequence;
    private int currentIndex = 0;
    private long totalElementsLeft;

    public RLEIterator(int[] A) {
        this.encodedSequence = A;
        for (int i = 0; i < A.length; i+=2) {
            this.totalElementsLeft += A[i];
        }
    }
    
    public int next(int n) {
        int originalN = n;

        if (n > this.totalElementsLeft) {
            this.totalElementsLeft = 0;
            return -1;
        }
        
        while (this.encodedSequence[this.currentIndex] < n) {           // No check on the index within bounds, since we know that n <= totalElementsLeft
            n -= this.encodedSequence[this.currentIndex];
            this.currentIndex += 2;
        }
        this.encodedSequence[this.currentIndex] -= n;
        this.totalElementsLeft -= originalN;
        
        return this.encodedSequence[this.currentIndex+1];
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */