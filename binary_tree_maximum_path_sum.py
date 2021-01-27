#A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
#The path sum of a path is the sum of the node's values in the path.
#Given the root of a binary tree, return the maximum path sum of any path.

class Solution(object):
    def maxPathSum(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        maxP=[float("-inf")]
        self.maxPathHelper(root,maxP)
        return maxP[0]
    def maxPathHelper(self,root,maxP):
        if root is None: 
            return 0
        else: 
            left=self.maxPathHelper(root.left,maxP)
            right=self.maxPathHelper(root.right,maxP)
            val=root.val
            maxP[0]=max(maxP[0],max(left+right+val,max(left+val,right+val)))
            maxP[0]=max(maxP[0],val)
            return max(max(left+root.val,right+root.val),val)