/*Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the 
height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an 
algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]*/

class Solution {
    
    //总体是O(nlogn), 因为排了序
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
        	return new int[0][0];
        }

        Arrays.sort(people, new Comparator<int[]>() { //接口式匿名类, new这个接口的实例, 同时实现接口里面的方法, compare这个方法会被
        	//Arrays.sort调用
            public int compare(int[] a, int[] b) {
                if (b[0] == a[0]) return a[1] - b[1]; //第二个元素按从小到大
                return b[0] - a[0]; //第一个元素按从大到小
            }
        }); //按照题目例子, 现在的结果是[7 0], [7 1], [6 1], [5 0], [5 2], [4 4]
        
        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
        	result.add(people[i][1], people[i]); //add(index, value)是O(n)
        }
        
        return result.toArray(new int[people.length][2]);
    }
    /*思路:
    1. Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people taller than them,
    therefore each guy's index will be just as same as his k value.
    2. For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
    
    E.g.
    input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
    subarray after step 1: [[7,0], [7,1]]
    subarray after step 2: [[7,0], [6,1], [7,1]]
    ...
    第二步之后, 要插入[5,0],[5,2], 那么一定要先插入[5,0], 再插入[5,2], 小的在前大的在后, 否则按照k位置插入的时候就该放错位置了*/
}
