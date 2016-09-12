/*
 * Copyright 2015 v-fnjeev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package casino.Roulette;

import junit.framework.TestCase;

/**
 *
 * @author v-fnjeev
 */
public class WheelTest extends TestCase {
    public void testWheelBinBuilder() {
        Wheel wheel = new Wheel(new NonRandomForTest(1));
        BinBuilder bb = BinBuilder.getInstance();
        bb.buildBins(wheel);
        //Bin zeroBin = wheel.getBin(0);
        for(int i = 0; i < 38; i++) {
            Bin currentBin = wheel.getBin(i);
            System.out.println("~~~~~~~~ "+ i + " ~~~~~~~~");
            for(Outcome outcome: currentBin.outcomes) {
                System.out.println(outcome.toString());
            }
        }
    }
}
