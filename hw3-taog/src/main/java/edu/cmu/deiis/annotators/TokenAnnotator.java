package edu.cmu.deiis.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.cas.FSIndex;

import java.util.Iterator;

import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.Token;
import edu.cmu.deiis.types.NGram;

public class TokenAnnotator extends JCasAnnotator_ImplBase {
//********* This class includes both TOKEN and NGRAM ANNOTATORs!!!*****
  private int maxNumberOfGrams;
    
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    
    String documentText = aJCas.getDocumentText();
    
    FSIndex<?> questionIndex = aJCas.getAnnotationIndex(Question.type);
    Iterator<?> questionIterator = questionIndex.iterator();
    
    while (questionIterator.hasNext()){
      Question aQuestion = (Question) questionIterator.next();
      String stringOfQuestion = aQuestion.getCoveredText();
      //using split to get the tokenStrings based on spaces 
      String[] tokenStringArray = stringOfQuestion.substring(0, stringOfQuestion.length()-1).split("\\s");
      //Be careful to get rid of the punctuation in the end;
      int numberOfTokens = tokenStringArray.length; 
      
      int skewing = aQuestion.getBegin();
      FSArray tokenList = new FSArray (aJCas, numberOfTokens);
 
      for (int i = 0; i < numberOfTokens; i++){
        Token aTokenAnnotation = new Token(aJCas);
        aTokenAnnotation.setBegin(skewing);
        skewing = skewing + tokenStringArray[i].length()+1;
        aTokenAnnotation.setEnd(skewing - 1);
        aTokenAnnotation.setConfidence(1.0d);
        aTokenAnnotation.setCasProcessorId(this.getClass().getName());
        
        aTokenAnnotation.addToIndexes();
        tokenList.set(i, aTokenAnnotation);;
      }
      
      aQuestion.setTokenList(tokenList);
      //We assume that there's only ONE question in each input.
      //If accidently there's multiple, this only records the length of the last one
      this.maxNumberOfGrams = numberOfTokens;
      aQuestion.setNGramList(this.nGramAnnotator(aJCas, maxNumberOfGrams, maxNumberOfGrams, tokenList));
//      
//      //Determine the size of the FSArray of nGram
//      //size = n + n-1 + n-2 + ... + 1 = n(n+1)/2
//      int numberOfNGrams = (maxNumberOfGrams+1)*maxNumberOfGrams/2; 
//      FSArray nGramList = new FSArray (aJCas, numberOfNGrams);
//            
//      int nGramIndex = 0;
//      for (int numberOfGrams =1; numberOfGrams<= maxNumberOfGrams; numberOfGrams++){
//        
//        for (int nGramStartPosition = 0; nGramStartPosition <= maxNumberOfGrams - numberOfGrams; nGramStartPosition ++){
//          
//          NGram anNGramAnnotation = new NGram(aJCas);
//          anNGramAnnotation.setBegin(((Annotation) tokenList.get(nGramStartPosition)).getBegin());
//          anNGramAnnotation.setEnd(((Annotation)tokenList.get(nGramStartPosition+numberOfGrams - 1)).getEnd());
//          anNGramAnnotation.setConfidence(1.0d);
//          anNGramAnnotation.setCasProcessorId(this.getClass().toString());
//          anNGramAnnotation.setNumberOfGrams(numberOfGrams);
//          anNGramAnnotation.setElementType(Token.class.toString());
//          //set NGram's TokenList
//          FSArray anotherTokenList = new FSArray (aJCas ,numberOfGrams);
//
//          for (int j=0; j<numberOfGrams; j++){
//            anotherTokenList.set(j, tokenList.get(nGramStartPosition + j));
//           
//          }
//          anNGramAnnotation.setElements(anotherTokenList);     
//          
//          nGramList.set(nGramIndex, anNGramAnnotation);
//          nGramIndex ++;
//          anNGramAnnotation.addToIndexes();
//          
//        }
//        
//      }
//      
    }
    
    FSIndex answerIndex = aJCas.getAnnotationIndex(Answer.type);
    Iterator answerIterator = answerIndex.iterator();
    System.out.println("Question Finished");
    
    while (answerIterator.hasNext()){
      Answer anAnswer = (Answer) answerIterator.next();
      String stringOfAnswer = anAnswer.getCoveredText();
      //using split to get the tokenStrings based on spaces 
      String[] tokenStringArray = stringOfAnswer.substring(0, stringOfAnswer.length()-1).split("\\s");
      //Be careful to get rid of the punctuation in the end;
      //System.out.println(tokenStringArray);
      int numberOfTokens = tokenStringArray.length; 
      
      int skewing = anAnswer.getBegin();
      FSArray tokenList = new FSArray (aJCas, numberOfTokens);
 
      for (int i = 0; i < numberOfTokens; i++){
        Token aTokenAnnotation = new Token(aJCas);
        aTokenAnnotation.setBegin(skewing);
        skewing = skewing + tokenStringArray[i].length()+1;
        aTokenAnnotation.setEnd(skewing - 1);
        aTokenAnnotation.setConfidence(1.0d);
        aTokenAnnotation.setCasProcessorId(this.getClass().toString());
        
        aTokenAnnotation.addToIndexes();
        tokenList.set(i, aTokenAnnotation);;
      }
      
      anAnswer.setTokenList(tokenList);
      int maxNumberOfGramsForAnswer;
      if (this.maxNumberOfGrams > numberOfTokens) 
        maxNumberOfGramsForAnswer = numberOfTokens;
      else 
        maxNumberOfGramsForAnswer = this.maxNumberOfGrams;
      
      anAnswer.setNGramList(this.nGramAnnotator(aJCas, maxNumberOfGramsForAnswer, numberOfTokens, tokenList));
//  
      
    }
  }

  
  private FSArray nGramAnnotator(JCas aJCas, int maxNumberOfGramsInSentence,int lengthOfSentence, FSArray tokenList)
  {
    //System.out.print(lengthOfSentence);
    //System.out.println(maxNumberOfGramsInSentence);
    //Determine the size of the FSArray of nGram
    //size = n + n-1 + n-2 + ... + n-k+1 = k(2n-k+1)/2
    int numberOfNGrams = (2*lengthOfSentence-maxNumberOfGramsInSentence+1)*maxNumberOfGramsInSentence/2; 
    FSArray nGramList = new FSArray (aJCas, numberOfNGrams);
          
    int nGramIndex = 0;
    for (int numberOfGrams =1; numberOfGrams<= maxNumberOfGramsInSentence; numberOfGrams++){
      
      for (int nGramStartPosition = 0; nGramStartPosition <= lengthOfSentence - numberOfGrams; nGramStartPosition ++){

        //System.out.print(numberOfGrams);
        //System.out.println(nGramStartPosition);
        NGram anNGramAnnotation = new NGram(aJCas);
        anNGramAnnotation.setBegin(((Annotation) tokenList.get(nGramStartPosition)).getBegin());
        anNGramAnnotation.setEnd(((Annotation)tokenList.get(nGramStartPosition+numberOfGrams - 1)).getEnd());
        anNGramAnnotation.setConfidence(1.0d);
        anNGramAnnotation.setCasProcessorId(this.getClass().getName());
        anNGramAnnotation.setNumberOfGrams(numberOfGrams);
        anNGramAnnotation.setElementType(Token.class.toString());
        //set NGram's TokenList
        FSArray anotherTokenList = new FSArray (aJCas ,numberOfGrams);

        for (int j=0; j<numberOfGrams; j++){
          anotherTokenList.set(j, tokenList.get(nGramStartPosition + j));
         
        }
        anNGramAnnotation.setElements(anotherTokenList);     
        anNGramAnnotation.setElementType(Token.class.getName());
        nGramList.set(nGramIndex, anNGramAnnotation);
        nGramIndex ++;
        anNGramAnnotation.addToIndexes();
        
      }
      
      
    }
    

  return nGramList;
  }
}
