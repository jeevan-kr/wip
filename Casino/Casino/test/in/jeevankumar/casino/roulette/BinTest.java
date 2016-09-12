/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jeevankumar.casino.roulette;

import junit.framework.TestCase;

/**
 *
 * @author jeevan
 */
public class BinTest extends TestCase{
    public void testAdd() {
        Outcome five= new Outcome( "00-0-1-2-3", 6 );
        Outcome[] ocZero = {
            new Outcome("0", 35 ), five };
        Outcome[] ocZeroZero = {
            new Outcome("00", 35 ), five };
        Bin zero= new Bin( ocZero );
        Bin zerozero= new Bin( ocZeroZero );
    }
}
