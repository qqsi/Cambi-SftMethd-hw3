package edu.cmu.deiis.types;
/* First created by JCasGen Tue Sep 24 20:43:43 EDT 2013 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import edu.cmu.deiis.types.Annotation_Type;

/** 
 * Updated by JCasGen Tue Sep 24 20:43:43 EDT 2013
 * @generated */
public class Evaluation_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Evaluation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Evaluation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Evaluation(addr, Evaluation_Type.this);
  			   Evaluation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Evaluation(addr, Evaluation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Evaluation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("Evaluation");
 
  /** @generated */
  final Feature casFeat_N;
  /** @generated */
  final int     casFeatCode_N;
  /** @generated */ 
  public int getN(int addr) {
        if (featOkTst && casFeat_N == null)
      jcas.throwFeatMissing("N", "Evaluation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_N);
  }
  /** @generated */    
  public void setN(int addr, int v) {
        if (featOkTst && casFeat_N == null)
      jcas.throwFeatMissing("N", "Evaluation");
    ll_cas.ll_setIntValue(addr, casFeatCode_N, v);}
    
  
 
  /** @generated */
  final Feature casFeat_precisionOfToken;
  /** @generated */
  final int     casFeatCode_precisionOfToken;
  /** @generated */ 
  public double getPrecisionOfToken(int addr) {
        if (featOkTst && casFeat_precisionOfToken == null)
      jcas.throwFeatMissing("precisionOfToken", "Evaluation");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_precisionOfToken);
  }
  /** @generated */    
  public void setPrecisionOfToken(int addr, double v) {
        if (featOkTst && casFeat_precisionOfToken == null)
      jcas.throwFeatMissing("precisionOfToken", "Evaluation");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_precisionOfToken, v);}
    
  
 
  /** @generated */
  final Feature casFeat_precisionOfNGram;
  /** @generated */
  final int     casFeatCode_precisionOfNGram;
  /** @generated */ 
  public double getPrecisionOfNGram(int addr) {
        if (featOkTst && casFeat_precisionOfNGram == null)
      jcas.throwFeatMissing("precisionOfNGram", "Evaluation");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_precisionOfNGram);
  }
  /** @generated */    
  public void setPrecisionOfNGram(int addr, double v) {
        if (featOkTst && casFeat_precisionOfNGram == null)
      jcas.throwFeatMissing("precisionOfNGram", "Evaluation");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_precisionOfNGram, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Evaluation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_N = jcas.getRequiredFeatureDE(casType, "N", "uima.cas.Integer", featOkTst);
    casFeatCode_N  = (null == casFeat_N) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_N).getCode();

 
    casFeat_precisionOfToken = jcas.getRequiredFeatureDE(casType, "precisionOfToken", "uima.cas.Double", featOkTst);
    casFeatCode_precisionOfToken  = (null == casFeat_precisionOfToken) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_precisionOfToken).getCode();

 
    casFeat_precisionOfNGram = jcas.getRequiredFeatureDE(casType, "precisionOfNGram", "uima.cas.Double", featOkTst);
    casFeatCode_precisionOfNGram  = (null == casFeat_precisionOfNGram) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_precisionOfNGram).getCode();

  }
}



    