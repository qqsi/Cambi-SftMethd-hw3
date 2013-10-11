package edu.cmu.deiis.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.cas.FSIndex;


import java.util.Iterator;

import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.AnswerScore;
import edu.cmu.deiis.types.Evaluation;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;


public class EvaluationAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    FSIndex<?> questionIndex = aJCas.getAnnotationIndex(Question.type);
    Iterator<?> questionIterator = questionIndex.iterator();
    while (questionIterator.hasNext()){
      Question aQuestion = (Question) questionIterator.next();
      System.out.println("Question:" + aQuestion.getCoveredText());
    }
   
    //Iterate all the AnswerScores;
    //Using HashMap to sort;
    //Dealing with Token and NGram Scoring in the same time ...
    int numberOfAnswers = 0;
    FSIndex<?> answerScoreIndex = aJCas.getAnnotationIndex(AnswerScore.type);
    Iterator<?> answerScoreIterator = answerScoreIndex.iterator();
    
    Map<AnswerScore, Double> answerTokenScoreMap = new HashMap<AnswerScore, Double>();
    Map<AnswerScore, Double> answerNGramScoreMap = new HashMap<AnswerScore, Double>();
    
    while (answerScoreIterator.hasNext()){
      AnswerScore anAnswerScore = (AnswerScore) answerScoreIterator.next();
      answerTokenScoreMap.put(anAnswerScore, anAnswerScore.getScoreBasedOnToken());
      answerNGramScoreMap.put(anAnswerScore, anAnswerScore.getScore());
      numberOfAnswers++;
    }
    
    ArrayList<Entry<AnswerScore, Double>> tokenScoreList = new ArrayList<Entry<AnswerScore, Double>>(answerTokenScoreMap.entrySet());
    ArrayList<Entry<AnswerScore, Double>> nGramScoreList = new ArrayList<Entry<AnswerScore, Double>>(answerNGramScoreMap.entrySet());
    
    //Collections.sort(tokenScoreList, new Comparator);
    Collections.sort(tokenScoreList, new Comparator<Map.Entry<AnswerScore, Double>>()
    {
    public int compare(Map.Entry<AnswerScore, Double> score1, Map.Entry<AnswerScore, Double> score2){
      if (score1.getValue()<score2.getValue()) return 1;
      else return -1; 
      }
    });
            
    Collections.sort(nGramScoreList, new Comparator<Map.Entry<AnswerScore, Double>>()
            {
            public int compare(Map.Entry<AnswerScore, Double> score1, Map.Entry<AnswerScore, Double> score2){
              if (score1.getValue()<score2.getValue()) return 1;
              else return -1; 
              }
            });
                     
    int N = (numberOfAnswers+1) / 2;
    
    
    //Test the answer with Token Overlap Scoring, print it and calculate the precision
    System.out.println("");
    System.out.println("Evaluation using the Token Overlap Scoring");
    
    int numberOfCorrectAnswers = 0;
    for (int i = 0; i < numberOfAnswers; i++){
      String printLine = "";
      AnswerScore anAnswerScore = tokenScoreList.get(i).getKey();
      if (anAnswerScore.getScoreBasedOnGoldenAnswer()==1.0d){
        printLine += " + ";
        if (i < N) numberOfCorrectAnswers ++;
      }
      else printLine += " - ";
        
      printLine += String.format("%.2f ", anAnswerScore.getScoreBasedOnToken());  
      printLine += anAnswerScore.getCoveredText();
      System.out.println(printLine);
    }
    double precisionOfToken = numberOfCorrectAnswers * 1.0d / N;
    String printLine = String.format("Precision at %d : %2f", N, precisionOfToken);
    System.out.println(printLine);


    //Test the answer with NGram Overlap Scoring, print it and calculate the precision
    System.out.println("");
    System.out.println("Evaluation using the NGram Overlap Scoring");
    
    numberOfCorrectAnswers = 0;
    for (int i = 0; i < numberOfAnswers; i++){
      printLine = "";
      AnswerScore anAnswerScore = nGramScoreList.get(i).getKey();
      if (anAnswerScore.getScoreBasedOnGoldenAnswer() ==1.0d){
        printLine += " + ";
        if (i < N) numberOfCorrectAnswers ++;
      }
      else printLine += " - ";
        
      printLine += String.format("%.2f ", anAnswerScore.getScore());  
      printLine += anAnswerScore.getCoveredText();
      System.out.println(printLine);
    }
    double precisionOfNGram = numberOfCorrectAnswers * 1.0d / N;
    printLine = String.format("Precision at %d : %2f", N, precisionOfNGram);
    System.out.println(printLine);

    System.out.println("");
    /*
    Evaluation anEvaluationAnnotation = new Evaluation(aJCas);
    anEvaluationAnnotation.setBegin(0);
    anEvaluationAnnotation.setEnd(aJCas.getDocumentText().length());
    anEvaluationAnnotation.setCasProcessorId(this.getClass().getName());
    anEvaluationAnnotation.setConfidence(1.0f);
    anEvaluationAnnotation.setN(N);
    anEvaluationAnnotation.setPrecisionOfNGram(precisionOfNGram);
    anEvaluationAnnotation.setPrecisionOfToken(precisionOfToken);
    anEvaluationAnnotation.addToIndexes();
    */
    
  }

}
