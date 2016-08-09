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
 * A signal for an offside event. The name of the team whose player should be offside is specific to this event signal 
 * 
 * @author Andreas Tasoulas
 *
 */

public class Offside extends Signal {
    
    private String teamName;
    
    public Offside(int timerStart, String teamName) {
        super(timerStart);
        this.teamName = teamName;
    }
    
    public String toString() {
        return teamName + " caught offside";
    }
    
    public String getTeamName() {
        return this.teamName;
    }

}
