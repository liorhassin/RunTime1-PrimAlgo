import java.util.*;

public class Main {

    public static void addEdge(Graph graph, int src, int dest, double weight)
    {
        Edge e1 = new Edge(dest, src, weight); //Add edge
        Edge e2 = new Edge(src, dest, weight); //Add reversed edge(Because graph is without directions
        graph.adj[src].addLast(e1);
        graph.adj[dest].addLast(e2);
    }
    public static void addMstEdge(Graph graph, int src, int dest, double weight)
    {

        Edge e1=new Edge(dest, src, weight);
        graph.adj[src].addLast(e1);
    }

    public static Graph primsMst(Graph graph)
    {
        Graph res=new Graph(graph.V);
        Boolean[] setMST = new Boolean[graph.V]; //Represents if node is already visited or not(In queue or not).
        Node[] nodes = new Node[graph.V];

        // Stores the parents of a vertex
        int[] parent = new int[graph.V];
        for(int i=0;i<graph.V;i++)
        {
            nodes[i]=new Node();
        }
        for (int i = 0; i < graph.V; i++) {
            setMST[i] = false;
            nodes[i].distance = Integer.MAX_VALUE;
            nodes[i].vertex = i;
            parent[i] = -1;
        }
        setMST[0] = true;
        nodes[0].distance = 0; // Setting first node distance to 0.

        //Priority queue with custom comparator to compare between distances of nodes.
        PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int)(o1.distance - o2.distance);
            }
        });

        queue.addAll(Arrays.asList(nodes).subList(0, graph.V));
        while(!queue.isEmpty())
        {
            Node n=queue.poll(); //Pull first element out of queue(minimum distance element).
            setMST[n.vertex]=true; //Update that the node was already visited.
            for(Edge v:graph.adj[n.vertex])// Update nodes distance from last removed node.
            {
                if(!setMST[v.destination])
                {
                    if(nodes[v.destination].distance >v.weight)
                    {
                        queue.remove(nodes[v.destination]);
                        nodes[v.destination].distance =v.weight;
                        queue.add(nodes[v.destination]);
                        parent[v.destination]=n.vertex;
                    }
                }
            }
        }
    //Make final graph(res)
    for(int i=1;i<graph.V;i++)
    {
        addEdge(res,parent[i],i,graph.edgeBySource(parent[i],i).weight);
        res.edgeBySource(parent[i],i).isMSTEdge =true;
    }
        return res;
    }


    public static void main(String[] args) {
        int V = 20;

        Graph graph = new Graph(V);

//        addEdge(graph,9,4,14);
//        addEdge(graph,7,11,8);
//        addEdge(graph,5,1,7);
//        addEdge(graph,5,11,20);
//        addEdge(graph,11,10,12);
//        addEdge(graph,8,10,7);
//        addEdge(graph,8,4,16);
//        addEdge(graph,4,2,17);
//        addEdge(graph,0,1,12);
//        addEdge(graph,0,2,23);
//        addEdge(graph,0,3,5);
//        addEdge(graph,3,2,18);
//        addEdge(graph,3,5,10);
//        addEdge(graph,3,6,9);
//        addEdge(graph,6,7,4);
//        addEdge(graph,6,9,3);

        addEdge(graph,0, 1, 1);
        addEdge(graph,1, 4, 2);
        addEdge(graph,4, 3, 3);
        addEdge(graph,3, 5, 4);
        addEdge(graph,5, 8, 5);
        addEdge(graph,8, 6, 6);
        addEdge(graph,6, 7, 7);
        addEdge(graph,7, 17, 8);
        addEdge(graph,17, 18, 9);
        addEdge(graph,18, 11, 10);
        addEdge(graph,11, 12, 11);
        addEdge(graph,12, 19, 12);
        addEdge(graph,12, 10, 13);
        addEdge(graph,10, 9, 14);
        addEdge(graph,10, 13, 15);
        addEdge(graph,13, 16, 16);
        addEdge(graph,16, 15, 17);
        addEdge(graph,15, 14, 18);
        addEdge(graph,0, 2, 19);
        addEdge(graph,2, 1, 20);
        addEdge(graph,1, 3, 21);
        addEdge(graph,2, 4, 22);
        addEdge(graph,2, 7, 23);
        addEdge(graph,7, 4, 24);
        addEdge(graph,7, 18, 25);
        addEdge(graph,17, 6, 26);
        addEdge(graph,6, 4, 27);
        addEdge(graph,4, 8, 28);
        addEdge(graph,4, 5, 29);
        addEdge(graph,5, 9, 30);
        addEdge(graph,9, 14, 31);
        addEdge(graph,9, 8, 32);
        addEdge(graph,14, 10, 33);
        addEdge(graph,14, 16, 34);
        addEdge(graph,15, 10, 35);
        addEdge(graph,10, 16, 36);
        addEdge(graph,16, 8, 37);
        addEdge(graph,8, 12, 38);
        addEdge(graph,12, 6, 39);
        addEdge(graph,12, 16, 40);
        addEdge(graph,12, 13, 41);
        addEdge(graph,13, 11, 42);
        addEdge(graph,13, 19, 43);
        addEdge(graph,19, 11, 44);
        addEdge(graph,18, 12, 45);
        addEdge(graph,6, 11, 46);
        addEdge(graph,6, 18, 47);

        System.out.println("Before MST: ");
        graph.printGraph();

        System.out.println("After MST: ");
        Graph res= primsMst(graph);
        res.printGraph();

        System.out.println("after add ************************** ");
        Ex2class ex2=new Ex2class();
        ex2.EX2(res,new Edge(10,9, 13));
        res.printGraph();
    }
}
