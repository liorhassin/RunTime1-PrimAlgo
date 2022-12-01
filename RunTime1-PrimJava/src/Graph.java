import java.util.LinkedList;

public class Graph {
    int V; // Represent number of vertices in graph.
    LinkedList<Edge>[] adj;
    Graph(int e)
    {
        V = e;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new LinkedList<>();
    }
    Graph(Graph g){
        this.V =g.V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new LinkedList<>(g.adj[i]);
    };

    //Search for edges by source.
    public Edge edgeBySource(int src, int dest){
        for(int i=0;i<V;i++)
        {
            for(int j=0;j<adj[i].size();j++)
            {
                if(adj[i].get(j).destination == dest && src == i)
                    return adj[i].get(j);
            }
        }
        return null;
    }

    public void printGraph(){
        for(int i = 0;  i < this.adj.length; i++){
            for(int j = 0; j < this.adj[i].size(); j++){
                System.out.println(i+ " ---" + " Weight: " + this.adj[i].get(j).weight +  " --> " + this.adj[i].get(j).destination);
            }
        }
    }

}
