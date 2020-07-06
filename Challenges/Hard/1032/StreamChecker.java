class StreamChecker {
    
    class Node {
        Character value;
        Map<Character, Node> nextValues;
        boolean isTerminal;
        
        Node(Character value) {
            this.value = value;
            this.nextValues = new HashMap<>();
            this.isTerminal = false;
        }
        
        boolean hasNext(char value) {
            return this.nextValues.containsKey(value);
        }
        
        Node getNext(char value) {
            return this.nextValues.get(value);
        }
        
        void addNext(Node nextValue) {
            this.nextValues.put(nextValue.value, nextValue);
        }
    }
    
    private Node rootNode = new Node(null);
    private HashSet<Node> validNodes = new HashSet<>();
    private LinkedList<Character> history = new LinkedList<>();

    public StreamChecker(String[] words) {
        for (String word: words) {
            this.addWord(word);
        }
    }
    
    private void addWord(String word) {
        Node currentNode = this.rootNode;
        for (int i = word.length()-1; i >= 0; i--) {
            char c = word.charAt(i);
            if (!currentNode.hasNext(c)) {
                Node newNode = new Node(c);
                currentNode.addNext(newNode);
            }
            currentNode = currentNode.getNext(c);
        }
        currentNode.isTerminal = true;
    }
    
    public boolean query(char letter) {
        this.history.add(letter);
        return this.isQueryInHistory();
    }

    private boolean isQueryInHistory() {
        Node currentNode = this.rootNode;

        for (int i = this.history.size()-1; i >= 0; i--) {
            char c = this.history.get(i);
            if (!currentNode.hasNext(c)) {
                break;
            }
            currentNode = currentNode.getNext(c);
            if (currentNode.isTerminal) {
                return true;
            }
        }
        return false;
    }
}