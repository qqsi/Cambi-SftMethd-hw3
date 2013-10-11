package edu.cmu.deiis.types;
/* First created by JCasGen Tue Sep 24 20:43:43 EDT 2013 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import edu.cmu.deiis.types.Annotation;


/** 
 * Updated by JCasGen Tue Sep 24 20:43:43 EDT 2013
 * XML source: /Users/Cambi/Documents/Program/git/Cambi-SftMethd-hw2/hw2-taog/src/main/resources/descriptors/hw2-taog-aae.xml
 * @generated */
public class Evaluation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Evaluation.class);
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
  protected Evaluation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Evaluation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Evaluation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Evaluation(JCas jcas, int begin, int end) {
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
  //* Feature: N

  /** getter for N - gets 
   * @generated */
  public int getN() {
    if (Evaluation_Type.featOkTst && ((Evaluation_Type)jcasType).casFeat_N == null)
      jcasType.jcas.throwFeatMissing("N", "Evaluation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Evaluation_Type)jcasType).casFeatCode_N);}
    
  /** setter for N - sets  
   * @generated */
  public void setN(int v) {
    if (Evaluation_Type.featOkTst && ((Evaluation_Type)jcasType).casFeat_N == null)
      jcasType.jcas.throwFeatMissing("N", "Evaluation");
    jcasType.ll_cas.ll_setIntValue(addr, ((Evaluation_Type)jcasType).casFeatCode_N, v);}    
   
    
  //*--------------*
  //* Feature: precisionOfToken

  /** getter for precisionOfToken - gets 
   * @generated */
  public double getPrecisionOfToken() {
    if (Evaluation_Type.featOkTst && ((Evaluation_Type)jcasType).casFeat_precisionOfToken == null)
      jcasType.jcas.throwFeatMissing("precisionOfToken", "Evaluation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Evaluation_Type)jcasType).casFeatCode_precisionOfToken);}
    
  /** setter for precisionOfToken - sets  
   * @generated */
  public void setPrecisionOfToken(double v) {
    if (Evaluation_Type.featOkTst && ((Evaluation_Type)jcasType).casFeat_precisionOfToken == null)
      jcasType.jcas.throwFeatMissing("precisionOfToken", "Evaluation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Evaluation_Type)jcasType).casFeatCode_precisionOfToken, v);}    
   
    
  //*--------------*
  //* Feature: precisionOfNGram

  /** getter for precisionOfNGram - gets 
   * @generated */
  public double getPrecisionOfNGram() {
    if (Evaluation_Type.featOkTst && ((Evaluation_Type)jcasType).casFeat_precisionOfNGram == null)
      jcasType.jcas.throwFeatMissing("precisionOfNGram", "Evaluation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Evaluation_Type)jcasType).casFeatCode_precisionOfNGram);}
    
  /** setter for precisionOfNGram - sets  
   * @generated */
  public void setPrecisionOfNGram(double v) {
    if (Evaluation_Type.featOkTst && ((Evaluation_Type)jcasType).casFeat_precisionOfNGram == null)
      jcasType.jcas.throwFeatMissing("precisionOfNGram", "Evaluation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Evaluation_Type)jcasType).casFeatCode_precisionOfNGram, v);}    
  }

    