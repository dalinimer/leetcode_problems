#In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

#Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

#Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

class Solution(object):
    def maxSumOfThreeSubarrays(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """
        n=len(nums)
        sigma=[]
        out=0
        for i in range(k):
            out+=nums[i]
        sigma.append(out)
        for j in range(1,n-k+1):
            sigma.append(sigma[j-1]+nums[j+k-1]-nums[j-1])
        memo=[[0 for i in range(3)] for j in range(n-k+1)]
        memo[n-k][0]=sigma[n-k]
        for l in reversed(range(n-k)):
            memo[l][0]=max(memo[l+1][0],sigma[l])
        memo[n-2*k][1]=sigma[n-k]+sigma[n-2*k]
        for p in range(1,3):
            for l in reversed(range(n-2*k)):
                memo[l][p]=max(memo[l+1][p],sigma[l]+memo[l+k][p-1])
        output=[]
        for i in range(n-k+1):
            if memo[0][2]==sigma[i]+memo[i+k][1]:
                output.append(i)
                break
        for i in range(output[-1]+k,n-k+1):
            if memo[output[-1]+k][1]==sigma[i]+memo[i+k][0]:
                output.append(i)
                break
        for i in range(output[-1]+k,n-k+1):
            if memo[output[-1]+k][0]==sigma[i]:
                output.append(i)
                break
        return output