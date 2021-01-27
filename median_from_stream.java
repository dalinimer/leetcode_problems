/*
For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.*/

class MedianFinder {
    PriorityQueue<Integer> leftHeap;
    PriorityQueue<Integer> rightHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
        leftHeap=new PriorityQueue<Integer>(Comparator.reverseOrder());
        rightHeap=new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        if(leftHeap.isEmpty() && rightHeap.isEmpty()){
            leftHeap.add(num);
        }
        else if(leftHeap.size()==rightHeap.size()){
            if(leftHeap.peek()>num){
                leftHeap.add(num);
            }
            else if(rightHeap.peek()<num){
                rightHeap.add(num);
            }
            else{
                leftHeap.add(num);
            }
        }
        else{
            if(rightHeap.isEmpty()){
                if(leftHeap.peek()>num){
                    rightHeap.add(leftHeap.poll());
                    leftHeap.add(num);
                }
                else{
                    rightHeap.add(num);
                }
            }
            else{
                if(leftHeap.peek()>num){
                    if(leftHeap.size()>rightHeap.size()){
                        rightHeap.add(leftHeap.poll());
                    }
                    leftHeap.add(num);
                }
                else if(rightHeap.peek()<num){
                    if(rightHeap.size()>leftHeap.size()){
                        leftHeap.add(rightHeap.poll());
                    }
                    rightHeap.add(num);
                    
                }
                else{
                    if(leftHeap.size()<rightHeap.size()){
                        leftHeap.add(num);
                    }
                    else{
                        rightHeap.add(num);
                    }
                }
            }
        }
    }
    
    public double findMedian() {
        if(leftHeap.isEmpty()){
            throw new IllegalArgumentException();
        }
        else if(rightHeap.isEmpty()){
            return leftHeap.peek();
        }
        else if(rightHeap.size()==leftHeap.size() && rightHeap.size()>0){
            return (((double) rightHeap.peek()+ (double)leftHeap.peek())/2);
        }
        else{
            if(leftHeap.size()>rightHeap.size()){
                return leftHeap.peek();
            }
            else{
                return rightHeap.peek();
            }
        }
        
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
