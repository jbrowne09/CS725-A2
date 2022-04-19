/* Copyright (c)2020 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK Multicast_Client
  * @author JHC
  * @version 20201028/JHC
  */
public class Multicast_Client extends FBInstance
{
/** VAR PE_ENTRY */
  public BOOL PE_ENTRY = new BOOL();
/** VAR PE_EXIT */
  public BOOL PE_EXIT = new BOOL();
/** VAR TIME_I */
  public REAL TIME_I = new REAL();
/** VAR RUN */
  public BOOL RUN = new BOOL();
/** VAR TIME_O */
  public REAL TIME_O = new REAL();
/** VAR CS_Status */
  public BOOL CS_Status = new BOOL();
/** Initialization Request */
 public EventServer INIT = new EventInput(this);
/** EVENT IN */
 public EventServer IN = new EventInput(this);
/** EVENT REQI */
 public EventServer REQI = new EventInput(this);
/** EVENT RESPI */
 public EventServer RESPI = new EventInput(this);
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** EVENT OUT */
 public EventOutput OUT = new EventOutput();
/** EVENT REQO */
 public EventOutput REQO = new EventOutput();
/** EVENT RESPO */
 public EventOutput RESPO = new EventOutput();
/** EVENT OUT_Print */
 public EventOutput OUT_Print = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    if("IN".equals(s)) return IN;
    if("REQI".equals(s)) return REQI;
    if("RESPI".equals(s)) return RESPI;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("INITO".equals(s)) return INITO;
    if("OUT".equals(s)) return OUT;
    if("REQO".equals(s)) return REQO;
    if("RESPO".equals(s)) return RESPO;
    if("OUT_Print".equals(s)) return OUT_Print;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("PE_ENTRY".equals(s)) return PE_ENTRY;
    if("PE_EXIT".equals(s)) return PE_EXIT;
    if("TIME_I".equals(s)) return TIME_I;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("RUN".equals(s)) return RUN;
    if("TIME_O".equals(s)) return TIME_O;
    if("CS_Status".equals(s)) return CS_Status;
    return super.ovNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("PE_ENTRY".equals(ivName)) connect_PE_ENTRY((BOOL)newIV);
    else if("PE_EXIT".equals(ivName)) connect_PE_EXIT((BOOL)newIV);
    else if("TIME_I".equals(ivName)) connect_TIME_I((REAL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable PE_ENTRY
  * @param newIV The variable to connect
 */
  public void connect_PE_ENTRY(BOOL newIV){
    PE_ENTRY = newIV;
    }
/** Connect the given variable to the input variable PE_EXIT
  * @param newIV The variable to connect
 */
  public void connect_PE_EXIT(BOOL newIV){
    PE_EXIT = newIV;
    }
/** Connect the given variable to the input variable TIME_I
  * @param newIV The variable to connect
 */
  public void connect_TIME_I(REAL newIV){
    TIME_I = newIV;
    }
private static final int index_START = 0;
private void state_START(){
  eccState = index_START;
  alg_RUN_A();
  OUT.serviceEvent(this);
}
private static final int index_INIT = 1;
private void state_INIT(){
  eccState = index_INIT;
  alg_INIT();
  INITO.serviceEvent(this);
  OUT.serviceEvent(this);
state_START();
}
private static final int index_REQ_S = 2;
private void state_REQ_S(){
  eccState = index_REQ_S;
  alg_STOP_A();
  OUT.serviceEvent(this);
  REQO.serviceEvent(this);
}
private static final int index_RUN_S = 3;
private void state_RUN_S(){
  eccState = index_RUN_S;
  alg_RUN_A();
  OUT.serviceEvent(this);
  alg_ENTRY_CS_A();
  OUT_Print.serviceEvent(this);
}
private static final int index_RESP_S = 4;
private void state_RESP_S(){
  eccState = index_RESP_S;
  RESPO.serviceEvent(this);
state_START();
}
private static final int index_EXIT_S = 5;
private void state_EXIT_S(){
  eccState = index_EXIT_S;
  RESPO.serviceEvent(this);
  alg_UPD_TIME_A();
  OUT.serviceEvent(this);
  alg_EXIT_CS_A();
  OUT_Print.serviceEvent(this);
    if(PE_ENTRY.value) state_START();
    else if(!PE_ENTRY.value) state_REQ_S();
}
private static final int index_STOP_S = 6;
private void state_STOP_S(){
  eccState = index_STOP_S;
  alg_STOP_A();
  OUT.serviceEvent(this);
state_HOLD_S();
}
private static final int index_HOLD_S = 7;
private void state_HOLD_S(){
  eccState = index_HOLD_S;
}
private static final int index_CH_TIME_S = 8;
private void state_CH_TIME_S(){
  eccState = index_CH_TIME_S;
  alg_CH_TIME_A();
  OUT.serviceEvent(this);
state_REQ_S();
}
/** The default constructor. */
public Multicast_Client(){
    super();
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == INIT) service_INIT();
    else if (e == IN) service_IN();
    else if (e == REQI) service_REQI();
    else if (e == RESPI) service_RESPI();
  }
/** Services the INIT event. */
  public void service_INIT(){
    if ((eccState == index_START)) state_INIT();
  }
/** Services the IN event. */
  public void service_IN(){
    if ((eccState == index_START) && (!PE_ENTRY.value)) state_REQ_S();
    else if ((eccState == index_RUN_S) && (PE_ENTRY.value)) state_HOLD_S();
    else if ((eccState == index_HOLD_S) && (!PE_EXIT.value)) state_EXIT_S();
    else if ((eccState == index_HOLD_S) && (!PE_ENTRY.value)) state_STOP_S();
  }
/** Services the REQI event. */
  public void service_REQI(){
    if ((eccState == index_START)) state_RESP_S();
    else if ((eccState == index_REQ_S)) state_CH_TIME_S();
  }
/** Services the RESPI event. */
  public void service_RESPI(){
    if ((eccState == index_REQ_S)) state_RUN_S();
  }
  /** ALGORITHM INIT IN Java*/
public void alg_INIT(){
RUN.value = true;
TIME_O.value = 0;

}
  /** ALGORITHM RUN_A IN Java*/
public void alg_RUN_A(){
RUN.value = true;

}
  /** ALGORITHM STOP_A IN Java*/
public void alg_STOP_A(){
RUN.value = false;

}
  /** ALGORITHM CH_TIME_A IN Java*/
public void alg_CH_TIME_A(){
if (TIME_O.value >= TIME_I.value) {
RESPO.serviceEvent(this);
}

}
  /** ALGORITHM UPD_TIME_A IN Java*/
public void alg_UPD_TIME_A(){
if (TIME_O.value < TIME_I.value) {
TIME_O.value = TIME_I.value + 1;
} else {
TIME_O.value += 1;
}

}
  /** ALGORITHM ENTRY_CS_A IN Java*/
public void alg_ENTRY_CS_A(){
CS_Status.value = true;

}
  /** ALGORITHM EXIT_CS_A IN Java*/
public void alg_EXIT_CS_A(){
CS_Status.value = false;

}
}
