

/* First created by JCasGen Wed Sep 11 13:44:28 EDT 2013 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/**   
 * Updated by JCasGen Tue Sep 24 21:44:42 EDT 2013
 * XML source: /Users/Cambi/Documents/Program/git/Cambi-SftMethd-hw2/hw2-taog/src/main/resources/descriptors/evaluatorDecriptor.xml
 * @generated */
public class AnswerScore extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(AnswerScore.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AnswerScore() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AnswerScore(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AnswerScore(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public AnswerScore(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: score

  /** getter for score - gets 
   * @generated */
  public double getScore() {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "edu.cmu.deiis.types.AnswerScore");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets  
   * @generated */
  public void setScore(double v) {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "edu.cmu.deiis.types.AnswerScore");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_score, v);}    
   
    
  //*--------------*
  //* Feature: answer

  /** getter for answer - gets 
   * @generated */
  public Answer getAnswer() {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_answer == null)
      jcasType.jcas.throwFeatMissing("answer", "edu.cmu.deiis.types.AnswerScore");
    return (Answer)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_answer)));}
    
  /** setter for answer - sets  
   * @generated */
  public void setAnswer(Answer v) {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_answer == null)
      jcasType.jcas.throwFeatMissing("answer", "edu.cmu.deiis.types.AnswerScore");
    jcasType.ll_cas.ll_setRefValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_answer, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: scoreBasedOnToken

  /** getter for scoreBasedOnToken - gets 
   * @generated */
  public double getScoreBasedOnToken() {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_scoreBasedOnToken == null)
      jcasType.jcas.throwFeatMissing("scoreBasedOnToken", "edu.cmu.deiis.types.AnswerScore");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_scoreBasedOnToken);}
    
  /** setter for scoreBasedOnToken - sets  
   * @generated */
  public void setScoreBasedOnToken(double v) {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_scoreBasedOnToken == null)
      jcasType.jcas.throwFeatMissing("scoreBasedOnToken", "edu.cmu.deiis.types.AnswerScore");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_scoreBasedOnToken, v);}    
   
    
  //*--------------*
  //* Feature: scoreBasedOnGoldenAnswer

  /** getter for scoreBasedOnGoldenAnswer - gets 
   * @generated */
  public double getScoreBasedOnGoldenAnswer() {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_scoreBasedOnGoldenAnswer == null)
      jcasType.jcas.throwFeatMissing("scoreBasedOnGoldenAnswer", "edu.cmu.deiis.types.AnswerScore");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_scoreBasedOnGoldenAnswer);}
    
  /** setter for scoreBasedOnGoldenAnswer - sets  
   * @generated */
  public void setScoreBasedOnGoldenAnswer(double v) {
    if (AnswerScore_Type.featOkTst && ((AnswerScore_Type)jcasType).casFeat_scoreBasedOnGoldenAnswer == null)
      jcasType.jcas.throwFeatMissing("scoreBasedOnGoldenAnswer", "edu.cmu.deiis.types.AnswerScore");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AnswerScore_Type)jcasType).casFeatCode_scoreBasedOnGoldenAnswer, v);}    
  }

    