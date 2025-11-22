package pl.mg.javatst.leetcode;

/**
 * https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
 * <p>
 * Given a sentence that consists of some words separated by a single space, and a searchWord.
 * <p>
 * You have to check if searchWord is a prefix of any word in sentence.
 * <p>
 * Return the index of the word in sentence where searchWord is a prefix of this word (1-indexed).
 * <p>
 * If searchWord is a prefix of more than one word, return the index of the first word (minimum index). If there is no such word return -1.
 * <p>
 * A prefix of a string S is any leading contiguous substring of S.
 */
public class PrefixWord {

    public int isPrefixOfWord(String sentence, String searchWord) {
        int index = 1;
        boolean found = false;
        for (String s : sentence.split(" ")) {
            if (s.startsWith(searchWord)) {
                found = true;
                break;
            }
            index++;
        }
        return found ? index : -1;
    }

}
