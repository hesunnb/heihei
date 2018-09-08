/*Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number 
(floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where 
equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/

class Solution {
    
    /*Image a/b = k as a link between node a and b, the weight from a to b is k, the reverse link is 1/k. Query is to find a path 
    between two nodes.*/
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        //把各种为null的判断加上
        HashMap<String, ArrayList<String>> pairs = new HashMap<String, ArrayList<String>>(); //有向图用一个哈希表就能存下来
        HashMap<String, ArrayList<Double>> valuesPair = new HashMap<String, ArrayList<Double>>(); //pair图对应的values
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            if (!pairs.containsKey(equation[0])) {
                pairs.put(equation[0], new ArrayList<String>());
                valuesPair.put(equation[0], new ArrayList<Double>());
            }
            if (!pairs.containsKey(equation[1])) {
                pairs.put(equation[1], new ArrayList<String>());
                valuesPair.put(equation[1], new ArrayList<Double>());
            }
            pairs.get(equation[0]).add(equation[1]); //把equation有向图存入哈希表中
            pairs.get(equation[1]).add(equation[0]);
            valuesPair.get(equation[0]).add(values[i]); //把values对应的存入哈希表
            valuesPair.get(equation[1]).add(1/values[i]); //比如1.0/7, java会把结果当做分数保存, 当这个值*7的时候还会变回1
        }
        
        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            result[i] = dfs(query[0], query[1], pairs, valuesPair, new HashSet<String>(), 1.0);
            if (result[i] == 0.0) result[i] = -1.0;
        }
        return result;
    }
    
    private double dfs(String start, String end, HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> values, 
    HashSet<String> set, double value) {
        if (set.contains(start)) return 0.0; //对于["a", "e"]这种, end不存在的, tmp一直都返回0; 
        if (!pairs.containsKey(start)) return 0.0; //["x", "x"]start就不存在, 直接返回0了
        if (start.equals(end)) return value; //["a", "c"], 最后就是c与c相等, 计算成功; ["a", "a"]直接返回传入的1.0
        set.add(start);
        
        ArrayList<String> strList = pairs.get(start);
        ArrayList<Double> valueList = values.get(start);
        double tmp = 0.0;
        for (int i = 0; i < strList.size(); i++) {
            tmp = dfs(strList.get(i), end, pairs, values, set, value*valueList.get(i)); //tmp等于0, 表示此次递归无效
            if (tmp != 0.0) {
                break;
            }
        }
        set.remove(start);
        return tmp;
    }
}
