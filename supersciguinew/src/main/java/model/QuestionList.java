package model;
/**
 * Holds the infromation for the question list
 * @author Amaya Shabazz
 */
import java.util.ArrayList;
import java.util.Random;
public class QuestionList {
    private ArrayList<ArrayList<Question>> questions;
    Random rand = new Random();
    private static QuestionList questionList;

    /**
     * Gets the questions from the JSON file
     * @param subject Holds what subject the questions come from
     */
    private QuestionList(String subject)
    {
        questions = DataLoader.getQuestions("supersciguinew/src/main/java/data/Questions.json", subject);
        System.out.println(questions);
    }
    
    /**
     * Constructs an instance of QuestionList for outside the class
     * @param subject Holds the subject of the questions
     * @return Will return an instance of QuestionList
     */
    public static QuestionList getInstance(String subject)
    {
        if(questionList == null) 
        {
            questionList = new QuestionList(subject);
        }
        return questionList;
    }

    /**
     * Accesses a question from the total bank of questions
     * @param difficulty Holds the question difficulty
     * @return Will return a question
     */
    public Question getQuestion(int difficulty)
    {
        Question myQuestion = questions.get(difficulty).get(rand.nextInt(6));
        return myQuestion;
    }

    /**
     * Checks if the user entered answer matches the correct answer
     * @param userAnswer Holds the user entered answer
     * @param question Holds the question so that the correct answer can be pulled
     * @return Will return true or false based on if the question was answered correctly
     */
    public boolean checkAnswer(String userAnswer, Question question)
    {
        return userAnswer.equals(question.getAnswer()); //case can not be ignored because in some of the English questions letter case will come into play
    }
}
