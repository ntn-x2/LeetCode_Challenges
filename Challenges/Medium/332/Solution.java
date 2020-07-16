class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> graph = this.buildGraph(tickets);
        LinkedList<String> result = new LinkedList<>();
        this.findPath(graph, "JFK", result);
        return result;
    }

    private HashMap<String, PriorityQueue<String>> buildGraph(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> result = new HashMap<>();
        
        for (List<String> ticket: tickets) {
            if (result.get(ticket.get(0)) == null) {
                result.put(ticket.get(0), new PriorityQueue<>());
            }
            result.get(ticket.get(0)).offer(ticket.get(1));
        }

        return result;
    }

    private void findPath(HashMap<String, PriorityQueue<String>> graph, String currentNode, LinkedList<String> result) {
        PriorityQueue<String> connections = graph.get(currentNode);
        while (!(connections == null || connections.isEmpty())) {
            this.findPath(graph, connections.poll(), result);
        }
        result.addFirst(currentNode);
    }
}