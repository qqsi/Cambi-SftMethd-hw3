package edu.cmu.deiis.annotators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.Answer;


public class AnswerAnnotator extends JCasAnnotator_ImplBase {

  //Pattern to extract the answer, as well as the isCorrect
  private Pattern answerPattern = Pattern.compile("A\\s\\d\\s(.*).");
  
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    //get doc text
    String documentText = aJCas.getDocumentText();
    
    //search for the answers
    Matcher matcher = answerPattern.matcher(documentText);
    while ( matcher.find() ){
    
      Answer answerAnnotation = new Answer(aJCas);
      answerAnnotation.setBegin(matcher.start()+4);
      answerAnnotation.setEnd(matcher.end());
      answerAnnotation.setConfidence(1.0d);
      answerAnnotation.setCasProcessorId(this.getClass().getName());
      char goldAnswerScore = matcher.group(0).charAt(2);
      //System.out.println(goldAnswerScore);
      
      if (goldAnswerScore == '1') {
        answerAnnotation.setIsCorrect(true);
      }
      else answerAnnotation.setIsCorrect(false);
      answerAnnotation.addToIndexes();
    }
  }

}
