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
    
    class ModifiedTreeNode {
        int val;
        ModifiedTreeNode left;
        ModifiedTreeNode right;
        int leftCount;
        int rightCount;
        
        ModifiedTreeNode(int val) {
            this.val = val;
        }
    }
    
    public int kthSmallest(TreeNode root, int k) {
        ModifiedTreeNode modifiedRoot = this.enrichTree(root);
        
        ModifiedTreeNode currentNode = modifiedRoot;
        while (k > 0) {
            if (k == currentNode.leftCount+1) {
                return currentNode.val;
            } else if (k <= currentNode.leftCount) {
                currentNode = currentNode.left;
            } else {            // k > currentNode.leftCount+1
                k -= (currentNode.leftCount+1);
                currentNode = currentNode.right;
            }
        }
        return -1;                      // Should never happen, given the constraints.
    }
    
    private ModifiedTreeNode enrichTree(TreeNode node) {
        return enrichTree(node, 0);
    }
    
    private ModifiedTreeNode enrichTree(TreeNode node, int lowerValues) {
        if (node == null) {
            return null;
        }
        
        ModifiedTreeNode result = new ModifiedTreeNode(node.val);
        
        result.left = this.enrichTree(node.left, lowerValues);
        result.leftCount = result.left == null ? 0 : result.left.leftCount + result.left.rightCount + 1;
        result.right = this.enrichTree(node.right, result.leftCount + 1);
        result.rightCount = result.right == null ? 0 : result.right.leftCount + result.right.rightCount + 1;
        
        return result;
    }
}