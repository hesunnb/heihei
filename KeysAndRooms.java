/*There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys 
to access the next room. 

Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  
A key rooms[i][j] = v opens the room with number v.

Initially, all the rooms start locked (except for room 0). 

You can walk back and forth between rooms freely.

Return true if and only if you can enter every room.

Example 1:

Input: [[1],[2],[3],[]]
Output: true
Explanation:  
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.  Since we were able to go to every room, we return true.
Example 2:

Input: [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can't enter the room with number 2.
Note:

1 <= rooms.length <= 1000
0 <= rooms[i].length <= 1000
The number of keys in all rooms combined is at most 3000.*/

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms == null || rooms.size() == 0) {
            return false;
        }
        
        Set<Integer> visited = new HashSet<>();
        return helper(0, rooms, visited);
    }
    
    public boolean helper(int room, List<List<Integer>> rooms, Set<Integer> visited) {
        visited.add(room);
        if(visited.size() == rooms.size()) {
            return true;
        }
        
        List<Integer> list = rooms.get(room);
        for(int i : list) { //其实就是按房间扫, 把访问过的都加入到visited当中, 访问过的就不再访问了
            if(visited.contains(i)) {
                continue;
            }
            if(helper(i, rooms, visited)) {
                return true;
            }
        }
        return false;
    }
    /*以这个为例[[1,3],[3,0,1],[2],[0]]:
    其实就是担心重复访问的问题, 比如0->1->3->0, 最初的想法是到0之后再去取没访问到的钥匙3, 但是0号房间已经访问过了, 回溯到0房间的时候
    自然会去拿3号钥匙的, 所以在这里就是访问过的就不用再访问了*/
}
