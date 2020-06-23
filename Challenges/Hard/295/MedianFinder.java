class BinaryNode {
    int value;
    
    BinaryNode parent;
    BinaryNode left;
    BinaryNode right;
    int leftCount;
    int rightCount;
    
    BinaryNode(int value) {
        this.value = value;
    }
}

class MedianFinder {
    
    BinaryNode root;
    int totalNodes;

    /** initialize your data structure here. */
    public MedianFinder() {}
    
    public void addNum(int num) {
        if (this.root == null) {
            this.root = new BinaryNode(num);
        } else {
            this.addValue(new BinaryNode(num), this.root);
        }
        this.totalNodes += 1;
    }
    
    public double findMedian() {
        if (this.root == null) {
            return 0;
        }
        
        if (this.totalNodes % 2 != 0) {
            int result = this.findNode(this.root, 0, this.totalNodes/2 + 1);
            return result;
        } else {
            int firstResult = this.findNode(this.root, 0, this.totalNodes/2);
            int secondResult = this.findNode(this.root, 0, this.totalNodes/2+1);
            return (firstResult + secondResult) / 2.0;
        }
    }
    
    private void addValue(BinaryNode node, BinaryNode startingNode) {
        if (node.value > startingNode.value) {
            if (startingNode.right == null) {
                startingNode.right = node;
                node.parent = startingNode;
                this.increaseCounter(node);
            } else {
                this.addValue(node, startingNode.right);
            }
        } else {
            if (startingNode.left == null) {
                startingNode.left = node;
                node.parent = startingNode;
                this.increaseCounter(node);
            } else {
                this.addValue(node, startingNode.left);
            }
        }
    }
    
    private void increaseCounter(BinaryNode startingNode) {
        if (startingNode.parent != null) {
            if (startingNode.parent.left == startingNode) {            //current node is a left child
                startingNode.parent.leftCount += 1;
            } else {
                startingNode.parent.rightCount += 1;
            }
            increaseCounter(startingNode.parent);
        }
    }
    
    private int findNode(BinaryNode currentNode, int seenNodes, int index) {
        int currentNodeIndex = seenNodes + currentNode.leftCount + 1;
        
        if (currentNodeIndex == index) {
            return currentNode.value;
        }
        
        if (currentNodeIndex < index) {
            return findNode(currentNode.right, currentNodeIndex, index);
        } else {
            return findNode(currentNode.left, seenNodes, index);
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */