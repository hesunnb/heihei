/*Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level 
sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, 
in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including 
the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted 
file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.*/

class Solution {
    public int lengthLongestPath(String input) {
        
        if(input == null || input.length() == 0) {
			return 0;
		}
        
        //有效路径必须以文件结尾(包含.), 不能是文件夹
        //"\t"本身就是一个字符, 不是由'\'和't'组成的, 别懵了, "\t"就占下标的一个位置
        String[] paths = input.split("\n");
        int[] pathLenArray = new int[paths.length + 1]; //保证长度足够用
        int maxLen = 0;
        int curLen = 0;
        for(String s : paths) {
            int lev = s.lastIndexOf("\t") + 1; // number of "\t"; 注意比如第一级目录dir没有"\t", 那么这时候lastIndexOf找不到就会返回-1,
            //正好第一级目录"\t"的个数就是-1+1=0
            pathLenArray[lev + 1] = pathLenArray[lev] + s.length()-lev + 1; // remove "\t", add"/"; 用lev+1存的原因是能够产生迭代, 如果
            //0位置的运算完放在0位置, 1位置的还得用0位置, 没法迭代
            curLen = pathLenArray[lev + 1];
            if(s.contains(".")) { // check if it is file
                maxLen = Math.max(maxLen, curLen - 1); //curLen - 1是移除掉最后一个"/"; curLen在这里不用也行, 
                //直接用pathLenArray[lev + 1] - 1, 不过为了清楚理解, 在这里还是用上, 如果面试的时候说多余, 再去掉
            }
        }
        return maxLen;
    }
}
