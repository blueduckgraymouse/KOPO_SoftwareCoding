package pro;

/**    # Phonemes Rearrangement Program
 *      - an action of rearrangement : move the n-th Phoneme of a input String to the last location
 *       - run until the input string equals the target string
 *      - return least time of rearrangement
 */


public class Quiz02 {

   public static void main(String[] args) {
//         String inputString = "abcdaefgh";
//         String targetString = "gcdafhaeb";
         String inputString = "lorl HeWlod";
         String targetString = "Hello World";

         int result = RearrangementPhonemes(inputString, targetString);
         
         System.out.println("\nThe least Times : " + result);
      }

      private static int RearrangementPhonemes(String inputString, String targetString) {
         // Print input String and target String
         System.out.println("# Input string :  " + inputString);
         System.out.println("  Target string : " + targetString);
         
         /* PREPROCESS */
         // Check a index of first alphabet of 'targetString' in 'inputString'
         int IndexOfFirstAlp = inputString.indexOf(targetString.charAt(0));         // => index of 'g' in inputString - 7

         // Check Alphabets with a common sequence between InputString and targetString from 'IndexOfFirstAlp'
         String commonArrangement= "";                                    // => ...gh / gc... => commonArrangement = "g"
         for (int i = 0 ; i < inputString.length() - IndexOfFirstAlp ; i++) {      // from 'IndexOfFirstAlp' to last alphabet
            if (inputString.charAt(IndexOfFirstAlp + i) == targetString.charAt(i))
               commonArrangement = commonArrangement + targetString.charAt(i);
            else
               break;
         }
         
         /* PROCESS */
         // Divide 'inputString' by alphabets with a common sequence
         String[] iString = new String[4];
         iString[0] = inputString.substring(0, IndexOfFirstAlp);           // Before the 'IndexOfFirstAlp'                                                => "abcdeaef"
         iString[1] = commonArrangement;                                   // Alphabets with a common sequence between input string and target string from 'IndexOfFirstAlp'   => "g"
                                                                           // ex) abcde / bcaed => a'bc'de / 'bc'aed
         iString[2] = inputString.substring(IndexOfFirstAlp + commonArrangement.length());   // After the end of the alphabets with a common sequence, 'commonArrangement'            => "h"
         iString[3] = "";                                                  // A space where alphabets will be saved after each actions of rearrangement
                                                            // ex) a / bc / de / _
         System.out.println(iString[0]);
         System.out.println(iString[1]);
         System.out.println(iString[2]);
         
         // Rearrange and Print the steps
         System.out.println("\n* Before action : \t" + inputString);
         int commonArrangementSize = iString[1].length();
         int count = 0;
         for (int i = 0 ; i < inputString.length() - commonArrangementSize ; i++) {         // Loop time : inputString length - 'commonArrangement' length
            // Check a target Alphabet to rearrange
            String targetAlphabet = targetString.substring(iString[1].length() + i, iString[1].length() + i + 1);
            System.out.print(" -> rearrange \'" + targetAlphabet + "\' , index : ");
            // Check the target alphabet is before the 'IndexOfFirstAlp' or after the end of the alphabets with a common sequence AND Rearrange the alphabets in iString[0] or iString[2] to iString[3]
            if (iString[0].contains(targetAlphabet)) {
               System.out.println(iString[0].indexOf(targetAlphabet));
               iString[3] = iString[3] + targetAlphabet;            
               iString[0] = iString[0].replaceFirst(targetAlphabet, "");
               count++;
            } else if (iString[2].contains(targetAlphabet)) {
               System.out.println(iString[0].length() + iString[0].length() + iString[1].length() + iString[2].indexOf(targetAlphabet));
               iString[3] = iString[3] + targetAlphabet;            
               iString[2] = iString[2].replaceFirst(targetAlphabet, "");
               count++;
            }
            System.out.println("After " + (i + 1) + "th action : \t" + iString[0] + iString[1] + iString[2] + iString[3]);
         }
         
         return count;
      }

}