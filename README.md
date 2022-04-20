# COMPSYS 725 Assignment 2 #
Project Authors: Jonathan Browne, Blake Williams, Alex Monk

## Group 15
*Ring token implementation was not fully functioning at completion of the project and is known to deadlock
*Inputting Bags too fast is known to cause a visual 'glitch' where extra bags are rendered completely still on the conveyor
*Project files includes fbdk software available online at https://www.holobloc.com/fbdk11/index.htm

## How to run

1. Run the fbdk editor in terminal by navigating to the FBDK folder. Use the command: java -jar editor.jar
2. Accept the User Agreement
3. Open BaggageSystemCTL from the cs725 folder in the src directory
4. Press Run in the top bar of BaggageSystemCTL
5. Press the Bag Input buttons to input bags to the conveyors


## Individual Implementations

* Blake: Central Server, Critical section #1 (FCOne & FCThree & Central Server)
* Alex: Ring Token, Critical section #2 (FCTwo & TCOne & Ring)
* Jonathan: Multicast, Critical section #3 (Section_3_Control)
