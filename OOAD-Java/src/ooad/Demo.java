/*
 * Copyright 2015 Jeevan.
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
package ooad;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jeevan
 */
public class Demo {
    public static void main( String arg[] ) {
        Demo x= new Demo();
        x.run();
    }
    public void run() {
        Map<Integer,Integer> combo= new HashMap<Integer,Integer>();
        for( int i= 1; i <= 6; i++ ) {
            for( int j= 1; j <= 6; j++ ) {
                Integer roll = new Integer(i + j);
                if( ! combo.containsKey( roll ) ) { combo.put(roll,new Integer(0)); }
                combo.put(roll, combo.get(roll) + 1 );
            }
        }
        DecimalFormat fmt= new DecimalFormat("0.00");
        for( int n= 2; n <= 12; ++n ) {
            System.out.println( "" + n + " " + fmt.format(combo.get(n)/36.0) + "%" );
        }
    }
    
}