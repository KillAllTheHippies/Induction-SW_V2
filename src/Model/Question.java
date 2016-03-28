package Model;

import java.io.Serializable;

/**

 A question with a text and an answer.

 */

public class Question implements Serializable

{

    private String text;
    private String answer;
    private int index; // Location in the ArrayList

    /**

     Constructs a question with empty question and answer.

     */


    public Question(String text)

    {

        this.text = text;

        answer = "";

    }
    public Question()

    {



    }

    /**

     Sets the question text.

     @param questionText the text of this question

     */


    public void setText(String questionText)

    {

        text = questionText;

    }

    /**

     Sets the answer for this question.

     @param correctResponse the answer

     */

    public void setAnswer(String correctResponse)

    {

        answer = correctResponse;

    }

    /**

     Checks a given response for correctness.

     @param response the response to check

     @return true if the response was correct, false otherwise

     */

    public boolean checkAnswer(String response)

    {

        return response.equals(answer);

    }

    /**

     Displays this question.

     */

    public void display()

    {

        System.out.println(text);

    }
    public String getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

