/* Copyright (c)2020 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK RingTokenLogicConveyor4
  * @author JHC
  * @version 20201027/JHC
  */
public class RingTokenLogicConveyor4 extends FBInstance
{
/** Local Group Name-Default is FB Instance Name */
  public WSTRING ID = new WSTRING();
/** VAR PE7 */
  public BOOL PE7 = new BOOL();
/** VAR PE4 */
  public BOOL PE4 = new BOOL();
/** VAR PE13 */
  public BOOL PE13 = new BOOL();
/** Service Initialization */
 public EventServer INIT = new EventInput(this);
/** EVENT Token */
 public EventServer Token = new EventInput(this);
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** Service Indication */
 public EventOutput IND = new EventOutput();
/** EVENT TokenO */
 public EventOutput TokenO = new EventOutput();
/** EVENT StopConveyor7 */
 public EventOutput StopConveyor7 = new EventOutput();
/** EVENT StartConveyor7 */
 public EventOutput StartConveyor7 = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    if("Token".equals(s)) return Token;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("INITO".equals(s)) return INITO;
    if("IND".equals(s)) return IND;
    if("TokenO".equals(s)) return TokenO;
    if("StopConveyor7".equals(s)) return StopConveyor7;
    if("StartConveyor7".equals(s)) return StartConveyor7;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("ID".equals(s)) return ID;
    if("PE7".equals(s)) return PE7;
    if("PE4".equals(s)) return PE4;
    if("PE13".equals(s)) return PE13;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("ID".equals(ivName)) connect_ID((WSTRING)newIV);
    else if("PE7".equals(ivName)) connect_PE7((BOOL)newIV);
    else if("PE4".equals(ivName)) connect_PE4((BOOL)newIV);
    else if("PE13".equals(ivName)) connect_PE13((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable ID
  * @param newIV The variable to connect
 */
  public void connect_ID(WSTRING newIV){
    ID = newIV;
    }
/** Connect the given variable to the input variable PE7
  * @param newIV The variable to connect
 */
  public void connect_PE7(BOOL newIV){
    PE7 = newIV;
    }
/** Connect the given variable to the input variable PE4
  * @param newIV The variable to connect
 */
  public void connect_PE4(BOOL newIV){
    PE4 = newIV;
    }
/** Connect the given variable to the input variable PE13
  * @param newIV The variable to connect
 */
  public void connect_PE13(BOOL newIV){
    PE13 = newIV;
    }
private static final int index_Start = 0;
private void state_Start(){
  eccState = index_Start;
}
private static final int index_HasToken = 1;
private void state_HasToken(){
  eccState = index_HasToken;
state_CheckIfCriticalSectionNeeded();
}
private static final int index_CheckIfCriticalSectionNeeded = 2;
private void state_CheckIfCriticalSectionNeeded(){
  eccState = index_CheckIfCriticalSectionNeeded;
    if(PE4.value) state_StopOtherConveyors();
    else if(!PE4.value) state_PassToken();
}
private static final int index_StopOtherConveyors = 3;
private void state_StopOtherConveyors(){
  eccState = index_StopOtherConveyors;
  StopConveyor7.serviceEvent(this);
state_WaitForBagExit();
}
private static final int index_StartNeededConveyors = 4;
private void state_StartNeededConveyors(){
  eccState = index_StartNeededConveyors;
  StartConveyor7.serviceEvent(this);
state_PassToken();
}
private static final int index_WaitForBagExit = 5;
private void state_WaitForBagExit(){
  eccState = index_WaitForBagExit;
    if(PE13.value) state_StartNeededConveyors();
}
private static final int index_PassToken = 6;
private void state_PassToken(){
  eccState = index_PassToken;
state_Waiting1();
}
private static final int index_Waiting1 = 7;
private void state_Waiting1(){
  eccState = index_Waiting1;
}
/** The default constructor. */
public RingTokenLogicConveyor4(){
    super();
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == INIT) service_INIT();
    else if (e == Token) service_Token();
  }
/** Services the INIT event. */
  public void service_INIT(){
    if ((eccState == index_Start)) state_Waiting1();
  }
/** Services the Token event. */
  public void service_Token(){
    if ((eccState == index_Waiting1)) state_HasToken();
  }
  /** ALGORITHM STOP IN Java*/
public void alg_STOP(){

}
}
