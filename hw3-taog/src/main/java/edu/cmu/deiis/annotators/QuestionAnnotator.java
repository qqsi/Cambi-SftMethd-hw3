package edu.cmu.deiis.annotators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.Question;

public class QuestionAnnotator extends JCasAnnotator_ImplBase {
  
  //pattern to extract the questions, using regex
  private Pattern questionPattern = Pattern.compile("Q\\s(.*)?");
  
  
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    //get document text
    String documentText = aJCas.getDocumentText();
    
    //search for the question (Discard Multliple questions...)
    Matcher matcher = questionPattern.matcher(documentText);
    if (matcher.find())    {
      //one question founded and set their features
      Question questionAnnotation = new Question(aJCas);
      questionAnnotation.setBegin(matcher.start()+2);
      questionAnnotation.setEnd(matcher.end());
      questionAnnotation.setConfidence(1.0d);
      questionAnnotation.setCasProcessorId(this.getClass().getName());
      questionAnnotation.addToIndexes();
    }

  }

}
