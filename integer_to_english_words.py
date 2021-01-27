#Convert a non-negative integer num to its English words representation.

class Solution(object):
    def numberToWords(self, num):
        """
        :type num: int
        :rtype: str
        """
        if num==0:
            return "Zero"
        elif num>0 and num<=10:
            return self.zero_digit(num)
        elif num>10 and num<100:
            c1=num/10
            c0=num%10
            if c1==1:
                return self.teens(num)
            elif c0==0:
                return self.ten_digit(c1)
            else:
                return self.ten_digit(c1)+" "+self.zero_digit(c0)
        elif num>=100 and num<1000:
            c2=num/100
            c1=num%100
            if c1==0:
                return self.zero_digit(c2)+" Hundred"
            else:
                return self.zero_digit(c2)+" Hundred "+self.numberToWords(c1)
        elif num<1000000 and num>=1000:
            c3=num/1000
            c2=num%1000
            if c2==0:
                 return self.numberToWords(c3)+" Thousand"
            else:
                return self.numberToWords(c3)+" Thousand "+self.numberToWords(c2)
        elif num>=1000000 and num<1000000000:
            c6=num/1000000
            c5=num%1000000
            if c5==0:
                return self.numberToWords(c6)+" Million"
            else:
                return self.numberToWords(c6)+" Million "+self.numberToWords(c5)
            
        else:
            c9=num/1000000000
            c8=num%1000000000
            if c8==0:
                return self.numberToWords(c9)+" Billion"
            else:
                return self.numberToWords(c9)+" Billion "+self.numberToWords(c8)
            
            
            
    def zero_digit(self,c):
        if c==0:
            return ""
        elif c==1:
            return "One"
        elif c==2:
            return "Two"
        elif c==3:
            return "Three"
        elif c==4:
            return "Four"
        elif c==5:
            return "Five"
        elif c==6:
            return "Six"
        elif c==7:
            return "Seven"
        elif c==8:
            return "Eight"
        elif c==9:
            return "Nine"
        else:
            return "Ten"
    
    def ten_digit(self,c):
        if c==0:
            return ""
        elif c==2:
            return "Twenty"
        elif c==3:
            return "Thirty"
        elif c==4:
            return "Forty"
        elif c==5:
            return "Fifty"
        elif c==6:
            return "Sixty"
        elif c==7:
            return "Seventy"
        elif c==8:
            return "Eighty"
        elif c==9:
            return "Ninety"
        
    def teens(self,c):
        if c==11:
                return "Eleven"
        elif c==12:
                return "Twelve"
        elif c==13:
                return "Thirteen"
        elif c==14:
                return "Fourteen"
        elif c==15:
                return "Fifteen"
        elif c==16:
                return "Sixteen"
        elif c==17:
                return "Seventeen"
        elif c==18:
                return "Eighteen"
        else:
                return "Nineteen"
