class Solution:
    def mergeAlternately(self, word1: str, word2: str) -> str:
        res=""
        small=""
        k=-1
        if (len(word1)<=len(word2)):
            small = word1
            big=word2
        else:
            small= word2
            big=word1
        
        n=len(small)
        m=len(big)
        
        for i in range(n):
            res+=word1[i]
            res+=word2[i]

        for i in range(n,m):
            res+=big[i]

        return res