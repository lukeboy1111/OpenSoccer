/*
 * Copyright 2010 Andreas Tasoulas
 *  
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *  
 */

package com.lukeyboy1.interactivity;

/**
 * The end of match object. Its type is used as a flag for denoting that the end of match has been reached
 * 
 * @author Andreas Tasoulas
 *
 */

public class EndOfMatch extends Signal {
    
    public EndOfMatch(int time) {
        super(time);
    }
    
    public String toString() {
        return "End of match signal";
    }

}
