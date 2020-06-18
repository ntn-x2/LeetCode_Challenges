class Solution {
    
    public int[] searchRange(int[] nums, int target) {
        return searchRange(nums, target, 0, nums.length-1);
    }
    
    private int[] searchRange(int[] nums, int target, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return new int[] {-1, -1};
        }
        
        int middleIndex = startIndex + ((endIndex-startIndex) / 2);
        int middleElement = nums[middleIndex];
        
        if (middleElement > target) {
            return searchRange(nums, target, startIndex, middleIndex-1);
        } else if (middleElement < target) {
            return searchRange(nums, target, middleIndex+1, endIndex);
        } else {
            int[] leftResult = searchRange(nums, target, startIndex, middleIndex-1);
            int[] rightResult = searchRange(nums, target, middleIndex+1, endIndex);
            int[] result = new int[] {middleIndex, middleIndex};
            if (leftResult[0] != -1) {
                result[0] = leftResult[0];
            }
            if (rightResult[1] != -1) {
                result[1] = rightResult[1];
            }
            return result;
        }
    }
}