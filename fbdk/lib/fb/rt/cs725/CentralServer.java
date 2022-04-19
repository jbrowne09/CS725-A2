/* Copyright (c)2020 Rockwell Automation. All rights reserved. */
package fb.rt.cs725;
import fb.datatype.*;
import fb.rt.*;
import fb.rt.events.*;
/** FUNCTION_BLOCK CentralServer
  * @author JHC
  * @version 20201020/JHC
  */
public class CentralServer extends FBInstance
{
/** EVENT REQUEST_1 */
 public EventServer REQUEST_1 = new EventInput(this);
/** EVENT REQUEST_2 */
 public EventServer REQUEST_2 = new EventInput(this);
/** EVENT RELEASE_1 */
 public EventServer RELEASE_1 = new EventInput(this);
/** EVENT RELEASE_2 */
 public EventServer RELEASE_2 = new EventInput(this);
/** EVENT GRANT_1 */
 public EventOutput GRANT_1 = new EventOutput();
/** EVENT GRANT_2 */
 public EventOutput GRANT_2 = new EventOutput();
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventServer eiNamed(String s){
    if("REQUEST_1".equals(s)) return REQUEST_1;
    if("REQUEST_2".equals(s)) return REQUEST_2;
    if("RELEASE_1".equals(s)) return RELEASE_1;
    if("RELEASE_2".equals(s)) return RELEASE_2;
    return super.eiNamed(s);}
/** {@inheritDoc}
* @param s {@inheritDoc}
* @return {@inheritDoc}
*/
  public EventOutput eoNamed(String s){
    if("GRANT_1".equals(s)) return GRANT_1;
    if("GRANT_2".equals(s)) return GRANT_2;
    return super.eoNamed(s);}
private static final int index_IDLE = 0;
private void state_IDLE(){
  eccState = index_IDLE;
}
private static final int index_REQ2 = 1;
private void state_REQ2(){
  eccState = index_REQ2;
  GRANT_2.serviceEvent(this);
}
private static final int index_REQ1 = 2;
private void state_REQ1(){
  eccState = index_REQ1;
  GRANT_1.serviceEvent(this);
}
private static final int index_CRIT2 = 3;
private void state_CRIT2(){
  eccState = index_CRIT2;
}
private static final int index_CRIT1 = 4;
private void state_CRIT1(){
  eccState = index_CRIT1;
}
/** The default constructor. */
public CentralServer(){
    super();
  }
/** {@inheritDoc}
* @param e {@inheritDoc} */
  public void serviceEvent(EventServer e){
    if (e == REQUEST_1) service_REQUEST_1();
    else if (e == REQUEST_2) service_REQUEST_2();
    else if (e == RELEASE_1) service_RELEASE_1();
    else if (e == RELEASE_2) service_RELEASE_2();
  }
/** Services the REQUEST_1 event. */
  public void service_REQUEST_1(){
    if ((eccState == index_IDLE)) state_REQ1();
    else if ((eccState == index_REQ2)) state_CRIT2();
  }
/** Services the REQUEST_2 event. */
  public void service_REQUEST_2(){
    if ((eccState == index_IDLE)) state_REQ2();
    else if ((eccState == index_REQ1)) state_CRIT1();
  }
/** Services the RELEASE_1 event. */
  public void service_RELEASE_1(){
    if ((eccState == index_REQ1)) state_IDLE();
    else if ((eccState == index_CRIT1)) state_REQ2();
  }
/** Services the RELEASE_2 event. */
  public void service_RELEASE_2(){
    if ((eccState == index_REQ2)) state_IDLE();
    else if ((eccState == index_CRIT2)) state_REQ1();
  }
}
