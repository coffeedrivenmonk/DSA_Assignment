package QuestionNumber6;

public class B {

    private char[] letters = {'S', 'I', 'X', 'E', 'V', 'N', 'T', 'W', 'Y'};
    private int[] values = {6, 5, 0, 8, 7, 2, 1, 3, 4};


    public boolean showResult(String[] words, String result){
        return getWordsValue(words) == Integer.parseInt(getEachValue(result));
    }

    public int getWordsValue(String[] words){
        int wordValue = 0;

        for(String word: words){

            wordValue += Integer.parseInt(getEachValue(word));
        }
        return wordValue;
    }


    public String getEachValue(String word){
        String letterValue = "";
        char letter;
        for(int i = 0; i< word.length(); i++){

            letter = word.charAt(i);

            letterValue += getValueOf(letter);
        }

        return letterValue;
    }


    public int getValueOf(char letter){

        for(int i = 0; i < letters.length; i++){

            if(letters[i] == letter){
                return values[i];
            }
        }

        throw new IllegalAccessError();

    }
    public static void main(String[] args){

        B answer = B();

        String[] words = {"SIX","SEVEN","SEVEN"};
        String result = "TWENTY";


        System.out.println(answer.showResult(words, result));


        int wordsValue = answer.getWordsValue(words);
        System.out.println("Words value : " + wordsValue);

        String resultValue = answer.getEachValue(result);
        System.out.println("Result value : " + resultValue);


    }

}
