/* Copyright (c)2020 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK Run_Conv
  * @author JHC
  * @version 20201024/JHC
  */
public class Run_Conv extends FBInstance
{
/** VAR RUN */
  public BOOL RUN = new BOOL();
/** Initialization Request */
 public EventServer INIT = new EventInput(this);
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("INITO".equals(s)) return INITO;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("RUN".equals(s)) return RUN;
    return super.ovNamed(s);}
private static final int index_START = 0;
private void state_START(){
  eccState = index_START;
}
private static final int index_INIT = 1;
private void state_INIT(){
  eccState = index_INIT;
  alg_INIT();
  INITO.serviceEvent(this);
state_START();
}
/** The default constructor. */
public Run_Conv(){
    super();
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == INIT) service_INIT();
  }
/** Services the INIT event. */
  public void service_INIT(){
    if ((eccState == index_START)) state_INIT();
  }
  /** ALGORITHM INIT IN Java*/
public void alg_INIT(){
RUN.value = true;

}
}
