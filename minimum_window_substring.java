/*Given two strings s and t, return the minimum window in s which will contain all the characters in t. If there is no such window in s that covers all characters in t, return the empty string "".

Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.

 */

class Solution {
    public String minWindow(String s, String t) {
       HashMap<Character,ArrayList<Integer>> map= new HashMap<Character,ArrayList<Integer>>();
        for(int i=0;i<t.length();i++){
            if(!map.containsKey(t.charAt(i))){
                ArrayList<Integer> templist=new ArrayList<Integer>();
                templist.add(0);
                templist.add(0);
                map.put(t.charAt(i),templist);
            }
            ArrayList<Integer> list=map.get(t.charAt(i));
            list.set(1,list.get(1)+1);
            
        } 
        
        
        
        int n=map.size();
       
        int start=0;
        int end=0;
        int window=Integer.MAX_VALUE;
        int windowS=0;
        int windowE=0;
        int full=0;
        while(start<s.length() && !map.containsKey(s.charAt(start))){
           start++;     
        }
        end=start;
        while(end<s.length()){
            while(end<s.length() && full<n){
                while(end<s.length() && !map.containsKey(s.charAt(end))){
                    end++;
                }
                if(end<s.length() && map.containsKey(s.charAt(end))){
                    ArrayList<Integer> list=map.get(s.charAt(end));
                    int prev=list.get(0);
                    list.set(0,prev+1);
                    
                    if(list.get(0)>list.get(1)-1 && list.get(0)<list.get(1)+1){
                        full++;
                    }
                    
                    end++;
                }
            }
            while(start<=end && full==n){
               
                while(start<s.length() && !map.containsKey(s.charAt(start))){
                    start++;     
                }
                if(end-start<window){
                    window=end-start;
                    windowS=start;
                    windowE=end;
                }
                ArrayList<Integer> list2=map.get(s.charAt(start));
                list2.set(0,list2.get(0)-1);
                if(list2.get(0)<list2.get(1)){
                    full--;
                }
                start++;
            }
            while(start<end && !map.containsKey(s.charAt(start))){
                start++;     
            }
        }
     
        return s.substring(windowS,windowE);
    }
}