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

import java.util.Random;

/**
 *
 * @author v-fnjeev
 */
public class NonRandomForTest extends Random {
    private int seed;
    public NonRandomForTest(int seed) {
        this.seed = seed;
    }
    public int nextInt() {
        return seed;
    }
    public void setSeed(int seed) {
        this.seed = seed;
    }
}
