package DynamicProgramming;

public class MaximumSumIncreasingSubsequence {
	public int maximumSum(int arr[]){
		int sumContainer[]=(int[]) arr.clone();
		
		for(int i=1;i<sumContainer.length;i++){
			for(int j=0;j<i;j++){
				if(arr[j]<arr[i]){
					sumContainer[i]=Math.max(sumContainer[i], sumContainer[j]+arr[i]);
				}
			}
		}
		int max=sumContainer[0];
		for(int i=1;i<sumContainer.length;i++){
			if(sumContainer[i]>max){
				max=sumContainer[i];
			}
		}
		return max;
	}
 public static void main(String args[]){
	 MaximumSumIncreasingSubsequence obj =new MaximumSumIncreasingSubsequence();
	 int arr[]={4,6,1,3,8,4,6};
	 int maximumSumSequence=obj.maximumSum(arr);
	 System.out.print("Answer : "+maximumSumSequence);
 }
}
