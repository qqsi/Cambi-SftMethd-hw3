package edu.cmu.deiis.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.cas.FSIndex;

import java.util.Iterator;

import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.Token;
import edu.cmu.deiis.types.NGram;
import edu.cmu.deiis.types.AnswerScore;

public class AnswerScoreAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    FSIndex<?> questionIndex = aJCas.getAnnotationIndex(Question.type);
    Iterator<?> questionIterator = questionIndex.iterator();
    
    FSIndex<?> answerIndex =aJCas.getAnnotationIndex(Answer.type);
    Iterator<?> answerIterator = answerIndex.iterator();
    
    while (questionIterator.hasNext())  {
      Question aQuestion = (Question) questionIterator.next();
      //Initial the number of Tokens and nGrams it contains;
      int numberOfTokens = aQuestion.getTokenList().size();
      int numberOfNGrams = aQuestion.getNGramList().size();
      
      while (answerIterator.hasNext()){

        //Initialize the answerScore object
        Answer anAnswer = (Answer) answerIterator.next();
        AnswerScore anAnswerScoreAnnotation = new AnswerScore(aJCas);
        anAnswerScoreAnnotation.setAnswer(anAnswer);
        anAnswerScoreAnnotation.setBegin(anAnswer.getBegin());
        anAnswerScoreAnnotation.setEnd(anAnswer.getEnd());
        anAnswerScoreAnnotation.setConfidence(1.0d);
        anAnswerScoreAnnotation.setCasProcessorId(this.getClass().getName());
        if (anAnswer.getIsCorrect()) anAnswerScoreAnnotation.setScoreBasedOnGoldenAnswer(1.0d);
        else anAnswerScoreAnnotation.setScoreBasedOnGoldenAnswer(0.0d);
        
        //doing Token Overlap Scoring       
        int numberOfMatchedTokens = 0;
        for (int i = 0; i < numberOfTokens; i++){
          for (int j = 0; j < anAnswer.getTokenList().size(); j++){
            if (aQuestion.getTokenList(i).getCoveredText().equals(anAnswer.getTokenList(j).getCoveredText())){
              numberOfMatchedTokens ++;
            }
          }    
        }
        anAnswerScoreAnnotation.setScoreBasedOnToken(1.0d*numberOfMatchedTokens/anAnswer.getTokenList().size());
        
        //doing nGram Overlap Scoring
        int numberOfMatchedNGrams = 0;
        for (int i = 0; i< numberOfNGrams; i++){
          for (int j = 0; j< anAnswer.getNGramList().size();j++){
            if (aQuestion.getNGramList(i).getCoveredText().equals(anAnswer.getNGramList(j).getCoveredText())){
              numberOfMatchedNGrams ++;
            }
          }
        }
        anAnswerScoreAnnotation.setScore(1.0d*numberOfMatchedNGrams/anAnswer.getNGramList().size());
        
        anAnswerScoreAnnotation.addToIndexes();
      }
       
    }

  }

}
