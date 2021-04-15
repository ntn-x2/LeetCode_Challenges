class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int r1Top = D;
        int r1Bottom = B;
        int r2Top = H;
        int r2Bottom = F;
        int r1Left = A;
        int r1Right = C;
        int r2Left = E;
        int r2Right = G;
        
        int r1Area = (r1Top - r1Bottom) * (r1Right - r1Left);
        int r2Area = (r2Top - r2Bottom) * (r2Right - r2Left);
        
        int[] sortedHeights = {r1Top, r1Bottom, r2Top, r2Bottom};
        Arrays.sort(sortedHeights);
        
        int top = sortedHeights[3];
        int secondToTop = sortedHeights[2];
        int secondToBottom = sortedHeights[1];
        int bottom = sortedHeights[0];
        
        // Default case. Covers case when rectangle Y values are disjoint (no match)
        int height = 0;
        
        // First and second cases are when one rectangle is enclosed in the other
        if (top == r1Top && bottom == r1Bottom) {
            height = r2Top - r2Bottom;
        } else if (top == r2Top && bottom == r2Bottom) {
            height = r1Top - r1Bottom;
        // Third and fourth cases are when the two rectangles actually intersect
        } else if (top == r1Top && secondToTop == r2Top) {
            height = r2Top - r1Bottom;
        } else if (top == r2Top && secondToTop == r1Top) {
            height = r1Top - r2Bottom;
        }
        
        // Not overlapping, so total area = sum of areas
        if (height == 0) {
            return r1Area + r2Area;
        }
        
        int[] sortedWidths = {r1Left, r1Right, r2Left, r2Right};
        Arrays.sort(sortedWidths);
        
        int left = sortedWidths[0];
        int secondToLeft = sortedWidths[1];
        int secondToRight = sortedWidths[2];
        int right = sortedWidths[3];
        
        // Default case. Covers case when rectangle X values are disjoint (no match)
        int width = 0;
        
        // First and second cases are when one rectangle is enclosed in the other
        if (left == r1Left && right == r1Right) {
            width = r2Right - r2Left;
        } else if (left == r2Left && right == r2Right) {
            width = r1Right - r1Left;
        // Third and fourth cases are when the two rectangles actually intersect
        } else if (left == r1Left && secondToLeft == r2Left) {
            width = r1Right - r2Left;
        } else if (left == r2Left && secondToLeft == r1Left) {
            width = r2Right - r1Left;
        }
        
        // Not overlapping, so total area = sum of areas
        if (width == 0) {
            return r1Area + r2Area;
        }
        
        int intersectionArea = height * width;
        
        // Total area = sum of areas - overlapping area
        return r1Area + r2Area - intersectionArea;
    }
}