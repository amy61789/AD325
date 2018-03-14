import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class Graph {
    int V;
    int e;
    public Graph(){

    }
    private ArrayList<String[]> loadClass(){
        Scanner sc;
        ArrayList<String[]> vertexList = new ArrayList<>();
        try {
            sc = new Scanner(new File("src/Routes.csv"));
            sc.useDelimiter(",");
            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] values = line.split(",");
                vertexList.add(values);
            }
            sc.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return vertexList;
    }

    ArrayList<String[]> vertexList = loadClass();
    Hashtable<String, Vertex> allVertices = loadHashtable(vertexList);

    private Hashtable loadHashtable(ArrayList<String[]> x){
        Hashtable<String, Vertex> allVertices = new Hashtable<>();

        for(String[] i : x){
            if(!allVertices.containsKey(i[1]) && !allVertices.containsKey(i[2])){
                Vertex vertex = new Vertex(i[1]);
                vertex.addEdge(i[2]);
                allVertices.put(i[1], vertex);
                Vertex vertex2 = new Vertex(i[2]);
                vertex2.addEdge(i[1]);
                allVertices.put(i[2], vertex2);
            }else if(!allVertices.containsKey(i[1]) && allVertices.containsKey(i[2])){
                Vertex vertex = new Vertex(i[1]);
                vertex.addEdge(i[2]);
                allVertices.put(i[1], vertex);
                if(!allVertices.get(i[2]).getEdge().contains(i[1])) {
                    allVertices.get(i[2]).addEdge(i[1]);
                }
            }else if(allVertices.containsKey(i[1]) && !allVertices.containsKey(i[2])){
                Vertex vertex = new Vertex(i[2]);
                vertex.addEdge(i[1]);
                allVertices.put(i[2], vertex);
                if(!allVertices.get(i[1]).getEdge().contains(i[2])) {
                    allVertices.get(i[1]).addEdge(i[2]);
                }
            }else{
                if(!allVertices.get(i[2]).getEdge().contains(i[1])) {
                    allVertices.get(i[2]).addEdge(i[1]);
                }
                if(!allVertices.get(i[1]).getEdge().contains(i[2])) {
                    allVertices.get(i[1]).addEdge(i[2]);
                }
            }
        }
        Set<String> keys = allVertices.keySet();

        for(String key : keys){
            allVertices.get(key).addEdge(allVertices.get(key).name);
        }
        return allVertices;
    }

    private class Vertex{
        String name;
        ArrayList<String> edges = new ArrayList<>();
        public Vertex(String w){
            name = w;
        }
        public void addEdge(String w) {
            edges.add(w);
        }
        public ArrayList getEdge(){
            return edges;
        }
    }
    private boolean traceRoute(Vertex w, Vertex v, String f, ArrayList<String> visited){
        Vertex startVertex = allVertices.get(w.name);
        Vertex endVertex = allVertices.get(v.name);
        visited.add(startVertex.name);
        ArrayList<String> startEdges = startVertex.getEdge();
        for(String i : startEdges) {
            if(endVertex.getEdge().contains(i)) {
                return true;
            }
            if (i == f) {
                return false;
            }
            if (!visited.contains(i)) {
                traceRoute(allVertices.get(i), endVertex, f, visited);
            }
        }
        return false;
    }

    private boolean traceRoute(String w, String v){
        Vertex startVertex = allVertices.get(w);
        Vertex endVertex = allVertices.get(v);
        ArrayList<String> visited = new ArrayList<>();
        return traceRoute(startVertex, endVertex, startVertex.name, visited);
    }

    public boolean hasRoute(String w, String v){

        return traceRoute(w, v);
    }

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter in your starting airport: ");
        String firstAirport = reader.next();
        System.out.println("Enter in your ending airport: ");
        String secondAirport = reader.next();

        Graph graph = new Graph();
        if(graph.hasRoute(firstAirport, secondAirport) == false){
            System.out.println("There is no route available to these locations.");
        }else{
            System.out.println("There is a route available for these locations.");
        }

    }

}


