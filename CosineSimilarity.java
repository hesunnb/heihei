/*
Cosine similarity is a measure of similarity between two vectors of an inner product space 
that measures the cosine of the angle between them. The cosine of 0° is 1, and it is less than 1 for any other angle.

See wiki: Cosine Similarity

Here is the formula:

/media/problem/cosine-similarity.png

Given two vectors A and B with the same size, calculate the cosine similarity.

Return 2.0000 if cosine similarity is invalid (for example A = [0] and B = [0]).

Example

Given A = [1, 2, 3], B = [2, 3 ,4].

Return 0.9926.

Given A = [0], B = [0].

Return 2.0000
*/

class Solution {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: Cosine similarity.
     */
    public double cosineSimilarity(int[] A, int[] B) {
        // write your code here
        
        if(A == null || B == null || A.length == 0 || B.length == 0 || A.length != B.length) {
            return 2.0;
        }
        
        double sum1 = 0;
        double sum2 = 0;
        double sum3 = 0;
        for(int i = 0; i < A.length; i++) {
            sum1 += A[i] * B[i];
            sum2 += A[i] * A[i];
            sum3 += B[i] * B[i];
        }
        
        double denominator = Math.sqrt(sum2) * Math.sqrt(sum3);
        if(denominator == 0) {
            return 2.0;
        } else {
            return sum1 / denominator;
        }
    }
}
