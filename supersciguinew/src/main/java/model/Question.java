package model;
/**
 * Holds the infromation for the question
 * @author Amaya Shabazz
 */
import java.util.ArrayList;
public class Question {
    public int difficulty;
    public ArrayList<String> answers;
    public String question;
    public String correctAnswer;
    public String subject;

    /**
     * Constructs a new question
     * @param question Holds the question
     * @param difficulty Holds the difficulty of the question
     * @param subject Holds the question's subject
     * @param answers Holds the other answers for the question
     * @param correctAnswer Holds the correct answer
     */
    public Question(String question, int difficulty, String subject, ArrayList<String> answers, String correctAnswer)
    {
        this.answers = new ArrayList<String>();
        this.setQuestion(question);
        this.setDifficulty(difficulty);
        this.setSubject(subject);
        this.setAnswers(answers);
        this.setAnswer(correctAnswer);
    }

    /**
     * Gets the difficulty of the question
     * @return Will return a difficulty number
     */
    public int getDifficulty()
    {
        return difficulty;
    }

    /**
     * Sets the difficulty
     * @param difficulty Holds a number to be used as the difficulty
     */
    private void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    /**
     * Sets the current question
     * @param question Holds the question
     */
    private void setQuestion(String question)
    {
        this.question = question;
    }

    /**
     * Sets the list of incorrect answers
     * @param answers Holds a list a answers
     */
    private void setAnswers(ArrayList<String> answers)
    {
        for(int i=0;i<answers.size();i++)
        {
            this.answers.add(answers.get(i));
        }
    }

    /**
     * Accesses the current question
     * @return Will return the question as a string
     */
    public String getQuestion()
    {
        return question;
    }

    /**
     * Accesses what subject the question is from
     * @return Will return the subject as a string
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * Sets the question's subject
     * @param subject Holds the question's subject
     */
    private void setSubject(String subject)
    {
        this.subject = subject;
    }

    /**
     * Accesses the correct answer
     * @return Will return the right answer as a string
     */
    public String getAnswer()
    {
        return correctAnswer;
    }
    
    /**
     * Sets the correct answer to the current question
     * @param correctAnswer Holds the correct answer to the question
     */
    private void setAnswer(String correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Prints the actual question
     * @return Will return the question as a string
     */
    @Override
    public String toString()
    {
        return getQuestion();
    }


}