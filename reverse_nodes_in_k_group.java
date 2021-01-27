



/**

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes itself may be changed.
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pointer2=head;
        ListNode oldFront=null;
        boolean firstTime=true;
        ListNode output=head;
        ListNode oldHead=head;
        
        ListNode temp=null;
        while(pointer2!=null){
            ListNode pointer1=null;
            int count=0;
            while(pointer2!=null && count<k){
                count++;
                pointer2=pointer2.next;
            }
            if(count<k){
                if(oldFront==null){
                    output=oldHead;
                }
                else{
                    oldFront.next=oldHead;
                }
            }
            else{
                count=0;
                pointer2=oldHead;
                while(pointer2!=null && count<k){
                    temp=pointer2.next;
                    pointer2.next=pointer1;
                    pointer1=pointer2;
                    pointer2=temp;
                    count++;
                }
            
                if(oldFront!=null){
                    oldFront.next=pointer1;
                }
                oldFront=oldHead;
                oldHead=pointer2;
                if(firstTime){
                    output=pointer1;
                    firstTime=false;
                }
            }
        }
        return output;
    }
}