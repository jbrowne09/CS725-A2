/* Copyright (c)2020 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK Cas_Stop
  * @author JHC
  * @version 20201024/JHC
  */
public class Cas_Stop extends FBInstance
{
/** VAR PREV_RUN */
  public BOOL PREV_RUN = new BOOL();
/** VAR RUN */
  public BOOL RUN = new BOOL();
/** Initialization Request */
 public EventServer INIT = new EventInput(this);
/** EVENT IN */
 public EventServer IN = new EventInput(this);
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** EVENT OUT */
 public EventOutput OUT = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    if("IN".equals(s)) return IN;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("INITO".equals(s)) return INITO;
    if("OUT".equals(s)) return OUT;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("PREV_RUN".equals(s)) return PREV_RUN;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("RUN".equals(s)) return RUN;
    return super.ovNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("PREV_RUN".equals(ivName)) connect_PREV_RUN((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable PREV_RUN
  * @param newIV The variable to connect
 */
  public void connect_PREV_RUN(BOOL newIV){
    PREV_RUN = newIV;
    }
private static final int index_START = 0;
private void state_START(){
  eccState = index_START;
}
private static final int index_INIT = 1;
private void state_INIT(){
  eccState = index_INIT;
  alg_INIT();
  INITO.serviceEvent(this);
  OUT.serviceEvent(this);
state_START();
}
private static final int index_UPD_RUN_S = 2;
private void state_UPD_RUN_S(){
  eccState = index_UPD_RUN_S;
  alg_UPD_RUN_A();
  OUT.serviceEvent(this);
state_START();
}
/** The default constructor. */
public Cas_Stop(){
    super();
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == INIT) service_INIT();
    else if (e == IN) service_IN();
  }
/** Services the INIT event. */
  public void service_INIT(){
    if ((eccState == index_START)) state_INIT();
  }
/** Services the IN event. */
  public void service_IN(){
    if ((eccState == index_START)) state_UPD_RUN_S();
  }
  /** ALGORITHM INIT IN Java*/
public void alg_INIT(){
RUN.value = true;
}
  /** ALGORITHM UPD_RUN_A IN Java*/
public void alg_UPD_RUN_A(){
RUN.value = PREV_RUN.value;
}
}
