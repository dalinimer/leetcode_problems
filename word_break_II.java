/*Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.*/
import java.awt.*;
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict){
        HashMap<Point,List<String>> memo=new HashMap<Point,List<String>>();
        HashSet<String> dict=new HashSet<String>();
        for (int i=0;i<wordDict.size();i++){
            dict.add(wordDict.get(i));
        }
        return f(memo,s,new Point(0,s.length()-1),dict);
        
    }
    
    public List<String> f(HashMap<Point,List<String>> memo,String s,Point p,HashSet<String> dict){
        if(!memo.containsKey(p)){
            int i=p.x;
            int j=p.y;
            List<String> out=new ArrayList<String>();
            for(int l=i+1;l<=j;l++){
                String word=s.substring(i,l);
                if(dict.contains(s.substring(i,l))){
                    List<String> list=f(memo,s,new Point(l,j),dict);
                    if(!list.isEmpty()){
                        for(int k=0;k<list.size();k++){
                            out.add(word+" "+list.get(k));
                        }
                    }
                }
            }
            if(dict.contains(s.substring(i,j+1))){
                out.add(s.substring(i,j+1));
            }
            memo.put(p,out);
        }
        return memo.get(p);
    }
}