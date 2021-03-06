/* Copyright (c)2020 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK CSPrint
  * @author JHC
  * @version 20201028/JHC
  */
public class CSPrint extends FBInstance
{
/** VAR STATUS_8 */
  public BOOL STATUS_8 = new BOOL();
/** VAR STATUS_11 */
  public BOOL STATUS_11 = new BOOL();
/** VAR TIME_8 */
  public REAL TIME_8 = new REAL();
/** VAR TIME_11 */
  public REAL TIME_11 = new REAL();
/** EVENT IN8 */
 public EventServer IN8 = new EventInput(this);
/** EVENT IN11 */
 public EventServer IN11 = new EventInput(this);
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("IN8".equals(s)) return IN8;
    if("IN11".equals(s)) return IN11;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("STATUS_8".equals(s)) return STATUS_8;
    if("STATUS_11".equals(s)) return STATUS_11;
    if("TIME_8".equals(s)) return TIME_8;
    if("TIME_11".equals(s)) return TIME_11;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("STATUS_8".equals(ivName)) connect_STATUS_8((BOOL)newIV);
    else if("STATUS_11".equals(ivName)) connect_STATUS_11((BOOL)newIV);
    else if("TIME_8".equals(ivName)) connect_TIME_8((REAL)newIV);
    else if("TIME_11".equals(ivName)) connect_TIME_11((REAL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable STATUS_8
  * @param newIV The variable to connect
 */
  public void connect_STATUS_8(BOOL newIV){
    STATUS_8 = newIV;
    }
/** Connect the given variable to the input variable STATUS_11
  * @param newIV The variable to connect
 */
  public void connect_STATUS_11(BOOL newIV){
    STATUS_11 = newIV;
    }
/** Connect the given variable to the input variable TIME_8
  * @param newIV The variable to connect
 */
  public void connect_TIME_8(REAL newIV){
    TIME_8 = newIV;
    }
/** Connect the given variable to the input variable TIME_11
  * @param newIV The variable to connect
 */
  public void connect_TIME_11(REAL newIV){
    TIME_11 = newIV;
    }
private static final int index_START = 0;
private void state_START(){
  eccState = index_START;
}
private static final int index_ENTER_8_S = 1;
private void state_ENTER_8_S(){
  eccState = index_ENTER_8_S;
  alg_PRINT_8_ENTER_A();
state_START();
}
private static final int index_EXIT_8_S = 2;
private void state_EXIT_8_S(){
  eccState = index_EXIT_8_S;
  alg_PRINT_8_EXIT_A();
state_START();
}
private static final int index_ENTER_11_S = 3;
private void state_ENTER_11_S(){
  eccState = index_ENTER_11_S;
  alg_PRINT_11_ENTER_A();
state_START();
}
private static final int index_EXIT_11_S = 4;
private void state_EXIT_11_S(){
  eccState = index_EXIT_11_S;
  alg_PRINT_11_EXIT_A();
state_START();
}
/** The default constructor. */
public CSPrint(){
    super();
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == IN8) service_IN8();
    else if (e == IN11) service_IN11();
  }
/** Services the IN8 event. */
  public void service_IN8(){
    if ((eccState == index_START) && (STATUS_8.value)) state_ENTER_8_S();
    else if ((eccState == index_START) && (!STATUS_8.value)) state_EXIT_8_S();
  }
/** Services the IN11 event. */
  public void service_IN11(){
    if ((eccState == index_START) && (STATUS_11.value)) state_ENTER_11_S();
    else if ((eccState == index_START) && (!STATUS_11.value)) state_EXIT_11_S();
  }
  /** ALGORITHM PRINT_11_ENTER_A IN Java*/
public void alg_PRINT_11_ENTER_A(){
System.out.println("MULTICAST: Conv 11 entering CS | TIME: "+TIME_11.value);

}
  /** ALGORITHM PRINT_11_EXIT_A IN Java*/
public void alg_PRINT_11_EXIT_A(){
System.out.println("MULTICAST: Conv 11 exiting CS | TIME: "+TIME_11.value);

}
  /** ALGORITHM PRINT_8_ENTER_A IN Java*/
public void alg_PRINT_8_ENTER_A(){
System.out.println("MULTICAST: Conv 8 entering CS | TIME: "+TIME_8.value);

}
  /** ALGORITHM PRINT_8_EXIT_A IN Java*/
public void alg_PRINT_8_EXIT_A(){
System.out.println("MULTICAST: Conv 8 exiting CS | TIME: "+TIME_8.value);

}
}
