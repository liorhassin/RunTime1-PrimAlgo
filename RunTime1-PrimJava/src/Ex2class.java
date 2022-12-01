import java.util.LinkedList;
import java.util.List;

public class Ex2class {
    LinkedList<Integer> finalPath =new LinkedList<>();

    public  void EX2(Graph g,Edge e)
    {
        LinkedList<Integer> path=new LinkedList<>();
        path.add(e.source);
        findCyclePath(g,path,e.destination);
        System.out.println(this.finalPath);
        List<Edge> pathEdges=new LinkedList<>();

        //Add both directions of the edge to finalPath.
        for(int i = 0; i< finalPath.size()-1; i++) {
            pathEdges.add(g.edgeBySource(finalPath.get(i), finalPath.get(i + 1)));
            pathEdges.add(g.edgeBySource(finalPath.get(i+1), finalPath.get(i)));
        }

        double maxWeight=Double.MIN_VALUE;
        int maxWeightIndex=-1;

        //Search if any of the edges has bigger weight than new given edge.
        for(int i=0;i<pathEdges.size();i++){
            if(maxWeight<pathEdges.get(i).weight)
            {
                maxWeight=pathEdges.get(i).weight;
                maxWeightIndex=i;
            }
        }
        if(e.weight<maxWeight){
            g.adj[finalPath.get(maxWeightIndex)].remove(g.edgeBySource(finalPath.get(maxWeightIndex), finalPath.get(maxWeightIndex+1))); //remove first edge found.(src -> dest)
            g.adj[finalPath.get(maxWeightIndex+1)].remove(g.edgeBySource(finalPath.get(maxWeightIndex+1), finalPath.get(maxWeightIndex))); //remove second edge found.(dest -> src)
            //Add new edge(src->dest)
            Main.addMstEdge(g,e.source,e.destination,e.weight);
            g.edgeBySource(e.source,e.destination).isMSTEdge =true;

            //Add new edge(dest->src)
            Main.addMstEdge(g, e.destination, e.source, e.weight);
            g.edgeBySource(e.destination, e.source).isMSTEdge=true;
        }
    }
    public  void findCyclePath(Graph g,LinkedList<Integer> visited,int END){
        LinkedList<Integer> nodes= new LinkedList<>();
        for(Edge e:g.adj[visited.getLast()]) {
            nodes.add(e.destination);
        }
        for (Integer node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(END)) {
                visited.add(node);
                setPath(visited);
                visited.removeLast();
                break;
            }
        }
        for (Integer node : nodes) {
            if (visited.contains(node) || node.equals(END)) {
                continue;
            }
            visited.addLast(node);
            findCyclePath(g, visited,END);
            visited.removeLast();
        }

    }

    public void setPath(LinkedList<Integer> visited) {
        if(this.finalPath.isEmpty())
            this.finalPath =new LinkedList<>(visited);
    }
}
