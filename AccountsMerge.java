/*Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of 
the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to 
both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. 
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the 
elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:

Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", 
"john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", 
"mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

Note:
The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].*/

class Solution {
    
    /*
    1. The key task here is to connect those emails, and this is a perfect use case for union find.
    2. to group these emails, each group need to have a representative, or parent.
    3. At the beginning, set each email as its own representative.
    4. Emails in each account naturally belong to a same group, and should be joined by assigning to the same parent (let's use the 
    parent of first email in that list);
    */
    /*[["John", "johnsmith@mail.com", "john00@mail.com"], 
    ["John", "johnnybravo@mail.com"], 
    ["John", "john_newyork@mail.com", "johnsmith@mail.com"], 题目中的例子这里换个位置更能说明问题
    ["Mary", "mary@mail.com"]] */
    public List<List<String>> accountsMerge(List<List<String>> acts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : acts) { //并查集的初始化, 以email自己为单位, 每一个email最开始自己是一个集合
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i)); //parents是email:email
                owner.put(a.get(i), a.get(0)); //owner是email:name
            }
        }
        /*["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"],
        ["Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"]] 这个例子说明了为什么不能写成String p = a.get(1); 因为这会形成环, 所以一定
        以集合的第一个email的根节点为基准, 要去查找根节点*/
        for (List<String> a : acts) { 
            String p = find(a.get(1), parents); //以集合的第一个email的根节点为基准
            for (int i = 2; i < a.size(); i++) { //所有其他的email指向这个集合的根节点, 这里就是路径压缩了
                parents.put(find(a.get(i), parents), p);
            }
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) { //TreeSet不存在就新建TreeSet, 存在就拿出来加入元素再放回, 这是之前的写法
                //这里提供了一个新的方法: 不存在就新建, 存在直接向存在的TreeSet里面加入就行, 不用用引用先拿出来
                unions.put(p, new TreeSet<>()); //TreeSet有set的所有特点, 同时对加入的元素能够自动排序
            }
            for (int i = 1; i < a.size(); i++) { //把这个list中的所有元素都加入到这个根节点的集合中
                unions.get(p).add(a.get(i));
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p)); //把TreeSet装到arrayList里面了
            emails.add(0, owner.get(p)); //用的是arrayList的add(index, value)方法, TreeSet不能在指定地方加元素
            res.add(emails);
        }
        return res;
    }
    private String find(String s, Map<String, String> p) {
        return p.get(s) == s ? s : find(p.get(s), p); //这个非常强的是直接比较地址, 因为无论怎么改, 哈希表里存的都是这些元素的
        //地址, 用equals方法也可, 稍微慢了点
    }
}
