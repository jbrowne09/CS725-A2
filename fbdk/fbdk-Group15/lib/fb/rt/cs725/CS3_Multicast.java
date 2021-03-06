/* Copyright (c)2020 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK CS3_Multicast
  * @author JHC
  * @version 20201028/JHC
  */
public class CS3_Multicast extends FBInstance
{
/** VAR PE8 */
  public BOOL PE8 = new BOOL();
/** VAR PE11 */
  public BOOL PE11 = new BOOL();
/** VAR PE_EXIT */
  public BOOL PE_EXIT = new BOOL();
/** VAR RUN_8 */
  public BOOL RUN_8 = new BOOL();
/** VAR RUN_11 */
  public BOOL RUN_11 = new BOOL();
/** VAR RUN_9 */
  public BOOL RUN_9 = new BOOL();
/** VAR RUN_10 */
  public BOOL RUN_10 = new BOOL();
/** Initialization Request */
 public EventOutput INIT = new EventOutput();
/** Normal Execution Request */
 public EventOutput REQ = new EventOutput();
/** Execution Confirmation */
 public EventOutput CNF = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("INIT".equals(s)) return INIT;
    if("REQ".equals(s)) return REQ;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("CNF".equals(s)) return CNF;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
    if("PE8".equals(s)) return PE8;
    if("PE11".equals(s)) return PE11;
    if("PE_EXIT".equals(s)) return PE_EXIT;
    return super.ivNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ovNamed(String s) throws FBRManagementException{
    if("RUN_8".equals(s)) return RUN_8;
    if("RUN_11".equals(s)) return RUN_11;
    if("RUN_9".equals(s)) return RUN_9;
    if("RUN_10".equals(s)) return RUN_10;
    return super.ovNamed(s);}
/** {@inheritDoc}
* @param ivName {@inheritDoc}
* @param newIV {@inheritDoc}
* @throws FBRManagementException {@inheritDoc} */
  public void connectIV(String ivName, ANY newIV)
    throws FBRManagementException{
    if("PE8".equals(ivName)) connect_PE8((BOOL)newIV);
    else if("PE11".equals(ivName)) connect_PE11((BOOL)newIV);
    else if("PE_EXIT".equals(ivName)) connect_PE_EXIT((BOOL)newIV);
    else super.connectIV(ivName, newIV);
    }
/** Connect the given variable to the input variable PE8
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_PE8(BOOL newIV) throws FBRManagementException{
    PE8 = newIV;
    CONV_8.connectIVNoException("PE_ENTRY",PE8);
    }
/** Connect the given variable to the input variable PE11
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_PE11(BOOL newIV) throws FBRManagementException{
    PE11 = newIV;
    CONV_11.connectIVNoException("PE_ENTRY",PE11);
    }
/** Connect the given variable to the input variable PE_EXIT
  * @param newIV The variable to connect
  * @throws FBRManagementException An internal connection failed.
 */
  public void connect_PE_EXIT(BOOL newIV) throws FBRManagementException{
    PE_EXIT = newIV;
    CONV_8.connectIVNoException("PE_EXIT",PE_EXIT);
    CONV_11.connectIVNoException("PE_EXIT",PE_EXIT);
    }
/** FB CONV_8 */
  protected Multicast_Client CONV_8 = new Multicast_Client() ;
/** FB CONV_11 */
  protected Multicast_Client CONV_11 = new Multicast_Client() ;
/** FB CONV_9 */
  protected Run_Conv CONV_9 = new Run_Conv() ;
/** FB CONV_10 */
  protected Cas_Stop CONV_10 = new Cas_Stop() ;
/** FB PRINT */
  protected CSPrint PRINT = new CSPrint() ;
/** The default constructor. */
public CS3_Multicast(){
    super();
    CONV_8.RESPO.connectTo(CONV_11.RESPI);
    CONV_8.REQO.connectTo(CONV_11.REQI);
    INIT.connectTo(CONV_8.INIT);
    INIT.connectTo(CONV_11.INIT);
    CONV_11.REQO.connectTo(CONV_8.REQI);
    CONV_11.RESPO.connectTo(CONV_8.RESPI);
    REQ.connectTo(CONV_8.IN);
    CONV_8.OUT.connectTo(CNF);
    CONV_11.OUT.connectTo(CNF);
    REQ.connectTo(CONV_11.IN);
    INIT.connectTo(CONV_9.INIT);
    CONV_9.INITO.connectTo(CNF);
    INIT.connectTo(CONV_10.INIT);
    CONV_10.OUT.connectTo(CNF);
    CONV_11.OUT.connectTo(CONV_10.IN);
    CONV_11.OUT_Print.connectTo(PRINT.IN11);
    CONV_8.OUT_Print.connectTo(PRINT.IN8);
    CONV_8.connectIVNoException("PE_ENTRY",PE8);
    CONV_8.connectIVNoException("PE_EXIT",PE_EXIT);
    CONV_11.connectIVNoException("PE_ENTRY",PE11);
    CONV_11.connectIVNoException("PE_EXIT",PE_EXIT);
    RUN_11 = (BOOL)CONV_11.ovNamedNoException("RUN");
    CONV_8.connectIVNoException("TIME_I",CONV_11.ovNamedNoException("TIME_O"));
    CONV_11.connectIVNoException("TIME_I",CONV_8.ovNamedNoException("TIME_O"));
    RUN_8 = (BOOL)CONV_8.ovNamedNoException("RUN");
    RUN_9 = (BOOL)CONV_9.ovNamedNoException("RUN");
    CONV_10.connectIVNoException("PREV_RUN",RUN_11);
    RUN_10 = (BOOL)CONV_10.ovNamedNoException("RUN");
    PRINT.connectIVNoException("STATUS_11",CONV_11.ovNamedNoException("CS_Status"));
    PRINT.connectIVNoException("TIME_11",CONV_11.ovNamedNoException("TIME_O"));
    PRINT.connectIVNoException("STATUS_8",CONV_8.ovNamedNoException("CS_Status"));
    PRINT.connectIVNoException("TIME_8",CONV_8.ovNamedNoException("TIME_O"));
  }
/** {@inheritDoc}
 * @param fbName {@inheritDoc}
 * @param r {@inheritDoc}
 * @throws FBRManagementException {@inheritDoc} */
  public void initialize(String fbName, Resource r)
  throws FBRManagementException{
    super.initialize(fbName,r);
    CONV_8.initialize("CONV_8",r);
    CONV_11.initialize("CONV_11",r);
    CONV_9.initialize("CONV_9",r);
    CONV_10.initialize("CONV_10",r);
    PRINT.initialize("PRINT",r);
}
/** Start the FB instances. */
public void start( ){
  CONV_8.start();
  CONV_11.start();
  CONV_9.start();
  CONV_10.start();
  PRINT.start();
}
/** Stop the FB instances. */
public void stop( ){
  CONV_8.stop();
  CONV_11.stop();
  CONV_9.stop();
  CONV_10.stop();
  PRINT.stop();
}
}
