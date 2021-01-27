/*There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.*/

class Solution {
  
    
         public class Node{
        public int data;
        public ArrayList<Node> list;
        public Node previous;
        public boolean visited;
        public int first;
        
        public Node(int d){
            data=d;
            list=new ArrayList<Node>();
            previous=null;
            visited=false;
            first=0;
        }
    } 
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Node[] nodes=new Node[n];
        for(int i=0;i<n;i++){
            nodes[i]=new Node(i);
        }
        for(int i=0;i<connections.size();i++){
            Node n1=nodes[connections.get(i).get(0)];
            Node n2=nodes[connections.get(i).get(1)];
            n1.list.add(n2);
            n2.list.add(n1);
        }
        List<List<Integer>> output=new ArrayList<List<Integer>>();
        int[] timer=new int[1];
        visit(0,output,nodes,timer);
        return output;
    }
    public void visit(int n,List<List<Integer>> output,Node[] nodes,int[] timer){
            timer[0]+=1;
            nodes[n].first=timer[0];
            nodes[n].visited=true;
            int current_time=nodes[n].first;
            for(Node neigh:nodes[n].list){
                if(nodes[n].previous==null || neigh.data!=nodes[n].previous.data){
                    if(!neigh.visited){
                        neigh.previous=nodes[n];
                        visit(neigh.data,output,nodes,timer);
                    }
                    nodes[n].first=Math.min(nodes[n].first,neigh.first);
                    if(neigh.first>current_time){
                        ArrayList<Integer> list=new ArrayList<Integer>();
                        list.add(n);
                        list.add(neigh.data);
                        output.add(list);
                    }
                }
            }
    }
    

  }

