#Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

#Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

#Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 import Queue
class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """
        q1=Queue.Queue()
        q2=Queue.Queue()
        gen=0
        out=""
        if root==None:
            return "null"
        else:
            q1.put(root)
            firstGen=True
            while not q1.empty():
                if not firstGen:
                        out+="; "
                else:
                    firstGen=False
                firstInGen=True
                while not q1.empty():
                    current=q1.get()
                    if not firstInGen:
                        out+=","
                    else:
                        firstInGen=False
                    if current==None:
                        out+="null"
                    else:
                        out+=str(current.val)
                        q2.put(current.left)
                        q2.put(current.right)
                temp=q1
                q1=q2
                q2=temp
           
            return out

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        
        generations=data.split('; ')
        if generations[0]=="null":
            return None
        generations[0]
        qprev=Queue.Queue()
        root=TreeNode(int(generations[0]))
        qprev.put(root)
        i=1
        while not qprev.empty():
            if i<len(generations):
                nextgen=generations[i]
                nextList=nextgen.split(',')
            k=0
            
            
            length=qprev.qsize()
           
            
            qnext=Queue.Queue()
            while not qprev.empty():
                node=qprev.get()
                if node!=None:
                    if k<len(nextList):
                        left=nextList[k]
                        leftNode=None
                        if left!="null":
                            leftNode=TreeNode(int(left))
                        node.left=leftNode
                        qnext.put(leftNode)
                        k+=1
                    if k<len(nextList):
                        right=nextList[k]
                        rightNode=None
                        if right!="null":
                            rightNode=TreeNode(int(right))
                        node.right=rightNode
                        qnext.put(rightNode)
                        k+=1
            
           
            qprev=qnext
            i+=1
        return root
                        
                
                
            

    def root(self,s):
        if s=="null":
            return None
        else:
            return TreeNode(int(s))


# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.deserialize(codec.serialize(root))