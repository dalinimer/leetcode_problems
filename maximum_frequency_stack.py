#Implement FreqStack, a class which simulates the operation of a #stack-like data structure.

#FreqStack has two functions:

#push(int x), which pushes an integer x onto the stack.
#pop(), which removes and returns the most frequent element in the stack.
#If there is a tie for most frequent element, the element closest to the #top of the stack is removed and returned.

class StackNode:
    def __init__(self,val,index,freq):
        self.val=val
        self.last=index
        self.freq=freq
    def __eq__(self, other):
        return self.freq==other.freq and self.last==other.last 

    def __ne__(self, other):
        return self.freq!=other.freq or self.last!=other.last 

    def __lt__(self, other):
        return self.freq>other.freq or (self.freq==other.freq and self.last>other.last)

 
        
class FreqStack(object):

    def __init__(self):
        self.count=0
        self.heap=[]
        self.freq=dict()
        
    def push(self, x):
        """
        :type x: int
        :rtype: None
        """
        if x not in self.freq.keys():
            self.freq[x]=0
        self.count+=1
        self.freq[x]+=1
        heapq.heappush(self.heap,StackNode(x,self.count,self.freq[x]))
        
        
            
    def pop(self):
        """
        :rtype: int
        """
        n=heapq.heappop(self.heap)
        self.freq[n.val]-=1
        return n.val
        


# Your FreqStack object will be instantiated and called as such:
# obj = FreqStack()
# obj.push(x)
# param_2 = obj.pop()