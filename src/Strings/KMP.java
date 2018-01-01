package Strings;

public class KMP {

    void patterMatchKMP(String text, String pattern, int lps[]) {
        int textIterator = 0;
        int patternIterator = 0;
        int textLength = text.length();
        int patternLength = pattern.length();
        while (textIterator < textLength) {

            if (pattern.charAt(patternIterator) == text.charAt(textIterator)) {
                patternIterator++;
                textIterator++;
            }
            /*
             * if pattern iterator reaches the length of pattern
             * then that means pattern is found at least once.
             * but it maybe possible that there are present multiple times
             * so... for other pattern reset the pattern iterator value to
             * lps value of the pattern-1 position
             */
            if (patternIterator == patternLength) {
                System.out.println("Pattern present at position : " + (textIterator - patternIterator));
                patternIterator = lps[patternIterator - 1];
            }

            /*
             * if text at pattern iterator mismatch with text at
             * text iterator then there are two cases like lps
             *
             * Case 1
             *
             * textIterator is already at position 0 and it mismatch
             * with position 0 of pattern
             * then in that case increment text iterator (simple)
             * because first character mismatches itself, then
             *
             * Case 2
             *
             * check for the lps value of the character mismatch -1
             */
            else if (textIterator < textLength && pattern.charAt(patternIterator) != text.charAt(textIterator)) {

                //Case 1
                if (patternIterator == 0) {
                    textIterator++;
                }

                //Case 2

                else if (patternIterator != 0) {
                    patternIterator = lps[patternIterator - 1];
                }
            }
        }
    }

    public static void main(String args[]) {
        String pattern = "aabaabc";
        String text = "aabaabfaabaabaabaabaabcaabaabxaabaabcaabaabk";
        int lps[] = new int[pattern.length()];
        LongestPrefixSuffix longestPrefixSuffix = new LongestPrefixSuffix();
        lps = longestPrefixSuffix.computeLPSArray(pattern, lps);
        KMP kmp = new KMP();
        kmp.patterMatchKMP(text, pattern, lps);
    }
}
