/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    public boolean isCompleteTree(TreeNode root) {
        boolean shouldStop = false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode nextNode = queue.pollFirst();
            
            if (nextNode.left == null && nextNode.right != null) {
                return false;
            }
            
            if (shouldStop) {
                if (nextNode.left != null || nextNode.right != null) {
                    return false;
                }
            } else {
                if (nextNode.left == null || nextNode.right == null) {
                    shouldStop = true;
                }
            }
            
            if (nextNode.left != null) {
                queue.add(nextNode.left);
            }
            if (nextNode.right != null) {
                queue.add(nextNode.right);
            }
        }
        
        return true;
    }
}