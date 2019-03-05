package Strings;

public class FirstNonRepeatingCharacter {
    /*
     *@Author : Sahil
     * Date : 06 March 2019
     *
     *First non-repeating character using one traversal of string
     *
     * Solution :
     * 1. take 256 character array and initialize it with -1
     * 2. on first appeareance mark charArr[s.charAt(i)] position with actual index in string
     * 3. if appearing more than once, mark corresponding position in charArr as -2
     * 4. Search for minimum positive value in charArray that would be the answer.
     *
     * References :
     * https://www.geeksforgeeks.org/first-non-repeating-character-using-one-traversal-of-string-set-2/
     */

    private static int answer = Integer.MAX_VALUE;

    public static  int firstNonRepeating(String s){

        int[] charArray=new int[256];
        //Initialize every char with -1
        for(int i=0;i<256;i++)
            charArray[i]=-1;

        //if repeated more than twice, let the value be -2, else if appears just once or first time make its value as i
        for(int i=0;i<s.length();i++) {
            if (charArray[s.charAt(i)] == -1) {
                charArray[s.charAt(i)] = i;
            } else {
                charArray[s.charAt(i)] = -2;
            }
        }

        //Now check if there is any character whose index is positive integer,and the minimum positive integer is the
        //answer as, it would be first to appear

        for(int i=0;i<256;i++) {
            //checking only those values that are positive as we have now to return first non repeating character
            if(charArray[i]>=0) {
                answer=Math.min(answer,charArray[i]);
            }
        }


        return answer;

    }

    public static void main(String [] args){
        FirstNonRepeatingCharacter firstNonRepeatingCharacter = new FirstNonRepeatingCharacter();

        String str = "geeksforgeeks";

        answer= firstNonRepeating(str);
        if (answer == Integer.MAX_VALUE)
            System.out.print("Either all characters are " +
                    "repeating or string is empty");
        else
            System.out.print("First non-repeating character"+
                    " is " + str.charAt(answer));
    }

}


