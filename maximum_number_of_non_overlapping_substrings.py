#Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:
#The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
#A substring that contains a certain character c must also contain all occurrences of c.
#Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings, return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.
#Notice that you can return the substrings in any order.

class Solution(object):
    def maxNumOfSubstrings(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        cd = {}     # charDict
        for i in xrange(len(s)):
            if s[i] not in cd:
                cd[s[i]] = [i]
            else:
                cd[s[i]] += i,

        seen, toCheck = [], []

        # find the range for all characters
        for k in cd.keys():
            toCheck += (cd[k][-1] - cd[k][0] + 1, k),

        # sort them based on length
        toCheck.sort()
        
        # check those chars
        # if within the range there's any char that's already been added, ignore it.
        res = []
        for _, c in toCheck:
            if c in seen: continue
            checker = True
            b, e, cnt = cd[c][0], cd[c][-1], 0
            tmp = []

            # keep update start and end until the range includes all appearances
            while cnt != e-b+1 and checker:
                for x in set(s[b:e+1]):
                    if x in seen:
                        checker = False
                        break
                    if x not in tmp:
                        cnt += len(cd[x])       # add the count (use len!)
                        b, e = min(b, cd[x][0]), max(e, cd[x][-1])
                        tmp += x,
            if checker:
                res += s[b:e+1],
                for y in tmp:
                    seen += y,
            
        return res

