/* Copyright (c)2020 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK RingTokenControlUnit
  * @author JHC
  * @version 20201027/JHC
  */
public class RingTokenControlUnit extends FBInstance
{
/** VAR PE7 */
  public BOOL PE7 = new BOOL();
/** VAR PE4 */
  public BOOL PE4 = new BOOL();
/** VAR PE13 */
  public BOOL PE13 = new BOOL();
/** Initialization Request */
 public EventOutput INIT = new EventOutput();
/** Initialization Confirm */
 public EventOutput INITO = new EventOutput();
/** Execution Confirmation */
 public EventOutput CNF = new EventOutput();
/** EVENT StartConveyor4 */
 public EventOutput StartConveyor4 = new EventOutput();
/** EVENT StopConveyor4 */
 public EventOutput StopConveyor4 = new EventOutput();
/** EVENT StartConveyor7 */
 public EventOutput StartConveyor7 = new EventOutput();
/** EVENT StopConveyor7 */
 public EventOutput StopConveyor7 = new EventOutput();
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
    if("CNF".equals(s)) return CNF;
    if("StartConveyor4".equals(s)) return StartConveyor4;
    if("StopConveyor4".equals(s)) return StopConveyor4;
    if("StartConveyor7".equals(s)) return StartConveyor7;
    if("StopConveyor7".equals(s)) return StopConveyor7;
    return super.eoNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
* @throws FBRManagementException {@inheritDoc}
*/
  public ANY ivNamed(String s) throws FBRManagementException{
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
    if("PE7".equals(ivName)) connect_PE7((BOOL)newIV);
    else if("PE4".equals(ivName)) connect_PE4((BOOL)newIV);
    else if("PE13".equals(ivName)) connect_PE13((BOOL)newIV);
    else super.connectIV(ivName, newIV);
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
/** FB conveyor7RingUnit */
  protected RingTokenLogicConveyor7 conveyor7RingUnit = new RingTokenLogicConveyor7() ;
/** FB conveyor4ringUnit */
  protected RingTokenLogicConveyor4 conveyor4ringUnit = new RingTokenLogicConveyor4() ;
/** The default constructor. */
public RingTokenControlUnit(){
    super();
    INIT.connectTo(conveyor7RingUnit.INIT);
    INIT.connectTo(conveyor4ringUnit.INIT);
    conveyor7RingUnit.TokenO.connectTo(conveyor4ringUnit.Token);
    conveyor7RingUnit.StopConveyor4.connectTo(StopConveyor4);
    conveyor7RingUnit.StartConveyor4.connectTo(StartConveyor4);
    conveyor4ringUnit.StopConveyor7.connectTo(StopConveyor7);
    conveyor4ringUnit.StartConveyor7.connectTo(StartConveyor7);
    conveyor4ringUnit.TokenO.connectTo(conveyor7RingUnit.Token);
  }
/** {@inheritDoc}
 * @param fbName {@inheritDoc}
 * @param r {@inheritDoc}
 * @throws FBRManagementException {@inheritDoc} */
  public void initialize(String fbName, Resource r)
  throws FBRManagementException{
    super.initialize(fbName,r);
    conveyor7RingUnit.initialize("conveyor7RingUnit",r);
    conveyor4ringUnit.initialize("conveyor4ringUnit",r);
}
/** Start the FB instances. */
public void start( ){
  conveyor7RingUnit.start();
  conveyor4ringUnit.start();
}
/** Stop the FB instances. */
public void stop( ){
  conveyor7RingUnit.stop();
  conveyor4ringUnit.stop();
}
}
