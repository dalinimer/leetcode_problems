#Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

#Note: The input string may contain letters other than the parentheses ( and ).

class Solution(object):
    def removeInvalidParentheses(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        stack=[]
        i=0
        invalidleft=0
        invalidright=0
        while i<len(s):
            if s[i]=='(' or s[i]==')': 
                if s[i]!=')':
                    stack.append(s[i])
                else:
                    if len(stack)==0 or stack[-1]!='(':
                        invalidright=invalidright+1
                    else:
                        stack.pop()
            i=i+1
        print(invalidright)
        invalidleft=len(stack)
        output=set()
        self.f(s,"",0,invalidleft,invalidright,output)
        return output
        
        
    def  f(self,old_s,new_s,index,invLeft,invRight,output):
        if index==len(old_s):
            if invLeft==0 and invRight==0 and self.isValid(new_s):
                output.add(new_s)
        else:
            self.f(old_s,new_s+""+old_s[index],index+1,invLeft,invRight,output)
            if old_s[index]==')' and invRight>0:
                self.f(old_s,new_s,index+1,invLeft,invRight-1,output)
            if old_s[index]=='(' and invLeft>0:
                self.f(old_s,new_s,index+1,invLeft-1,invRight,output)

    
    def isValid(self,s):
        stack=[]
        for i in range(len(s)):
            if s[i]=='(' or s[i]==')':    
                if s[i]!=')':
                    stack.append(s[i])
                else:
                    if len(stack)==0 or stack[-1]!='(':
                        return False
                    else:
                        stack.pop()
        return len(stack)==0