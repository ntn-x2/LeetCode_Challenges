/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    
    public TreeNode sortedListToBST(ListNode head) {
        
        if (head == null) {
            return null;
        }
        
        ArrayList<Integer> list = this.buildIndexableList(head);        
        int listSize = list.size();
        
        return this.getSubTree(0, listSize-1, list);
    }
    
    private ArrayList<Integer> buildIndexableList(ListNode head) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        ListNode currentNode = head;
        while (currentNode != null) {
            list.add(currentNode.val);
            currentNode = currentNode.next;
        }
        
        return list;
    }
    
    private TreeNode getSubTree(int startIndex, int endIndex, ArrayList<Integer> list) {
        if (startIndex > endIndex) {
            return null;
        }
        
        int nodeIndex = startIndex + (endIndex - startIndex) / 2;
        int nodeValue = list.get(nodeIndex);
        
        TreeNode node = new TreeNode(nodeValue);
        node.left = this.getSubTree(startIndex, nodeIndex-1, list);
        node.right = this.getSubTree(nodeIndex+1, endIndex, list);
        
        return node;
    }
}