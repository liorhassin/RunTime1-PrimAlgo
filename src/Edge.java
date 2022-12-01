public class Edge {
    int destination;
    int source;
    double weight;
    public boolean isMSTEdge;
    Edge(int destination,int source, double weight)
    {
        this.destination = destination;
        this.source = source;
        this.weight = weight;
        isMSTEdge =false;
    }
}
