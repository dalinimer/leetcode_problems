#You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
#Merge all the linked-lists into one sorted linked-list and return it.

from heapq import heappush, heappop, heapify
class ListFront(object):
        def __init__(self,front):
            self.front=front
        def next(self):
            self.front=self.front.next
        
        def __eq__(self, other):
            return (self.front.val==other.front.val)

        def __ne__(self, other):
            return (self.front.val!=other.front.val)

        def __lt__(self, other):
            return ((self.front.val<other.front.val))

        def __le__(self, other):
            return ((self.front.val<=other.front.val))

        def __gt__(self, other):
            return ((self.front.val>other.front.val))

        def __ge__(self, other):
            return ((self.front.val>=other.front.val))
class Solution(object):
  
          
    def mergeKLists(self, lists):
        h=[]
     
        for i in range(len(lists)):
            if lists[i]:
                h.append(ListFront(lists[i]))
        heapq.heapify(h)
        front=None
        current=None
        currentNode=None
        if h:
            current=heapq.heappop(h)
            front=current.front
            currentNode=front
            current.next()
            if current.front:
                heappush(h,current)
        while h:
            current=heapq.heappop(h)
            currentNode.next=current.front
            currentNode=currentNode.next
            if current.front and current.front.next:
                current.next()
                heapq.heappush(h,current)
        if currentNode:
            currentNode.next=None
        return front
            

        