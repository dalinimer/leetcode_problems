/*A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words such that:

The first word in the sequence is beginWord.
The last word in the sequence is endWord.
Only one letter is different between each adjacent pair of words in the sequence.
Every word in the sequence is in wordList.
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.*/

class Solution {
    public class WordNode{
        public String word;
        public ArrayList<WordNode> adjList;
        public boolean visited;
        public int dist;
        
        public WordNode(String s){
            word=s;
            adjList=new ArrayList<WordNode>();
            visited=false;
            dist=-1;
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        WordNode start=new WordNode(beginWord);
        HashMap<String,WordNode> map=new HashMap<String,WordNode>();
        for(String word:wordList){
            WordNode s=new WordNode(word);
            map.put(word,s);
            if(isAdjacent(word,beginWord)){
                start.adjList.add(s);
                s.adjList.add(start);
            }
        }
        for(int i=0;i<wordList.size();i++){
            for(int j=0;j<wordList.size();j++){
                String s1=wordList.get(i);
                String s2=wordList.get(j);
                if(isAdjacent(s1,s2)){
                    map.get(s1).adjList.add(map.get(s2));
                    map.get(s2).adjList.add(map.get(s1));
                }
            }
        }
        Queue<WordNode> q=new LinkedList<WordNode>();
        start.dist=0;
        q.add(start);
        while(!q.isEmpty() && !q.peek().word.equals(endWord)){
            WordNode current=q.remove();
            if(!current.visited){
                current.visited=true;
                for(WordNode neighbor:current.adjList){
                    if(neighbor.dist==-1){
                        neighbor.dist=current.dist+1;
                        q.add(neighbor);
                    }
                    
                    
                }
            }
        }
        if(q.isEmpty()){
            return 0;
        }
        else{
            return q.peek().dist+1;
        }
        
    }
    
    public boolean isAdjacent(String s1,String s2){
        if(s1.length()==s2.length()){
            int count=0;
            for(int i=0;i<s1.length();i++){
                if(s1.charAt(i)!=s2.charAt(i)){
                    count++;
                }
            }
            return count==1;
        }
        else{
            return false;
        }
    }
}