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
    
    public int sumEvenGrandparent(TreeNode root) {
        
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.removeFirst();
            if (node == null) {
                continue;
            }
            nodes.addLast(node.left);
            nodes.addLast(node.right);
            
            if (node.val % 2 == 0) {
                if (node.left != null) {
                    sum += node.left.left == null ? 0 : node.left.left.val;
                    sum += node.left.right == null ? 0 : node.left.right.val;
                }
                if (node.right != null) {
                    sum += node.right.left == null ? 0 : node.right.left.val;
                    sum += node.right.right == null ? 0 : node.right.right.val;
                }
            }
        }
        return sum;
    }
}