/*
 * FillElement.java
 *
 *
 *  The Salamander Project - 2D and 3D graphics libraries in Java
 *  Copyright (C) 2004 Mark McKay
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 *  Mark McKay can be contacted at mark@kitfox.com.  Salamander and other
 *  projects can be found at http://www.kitfox.com
 *
 * Created on March 18, 2004, 6:52 AM
 */

package com.kitfox.svg;

import com.kitfox.svg.xml.StyleAttribute;
import java.awt.*;
import java.awt.geom.*;
import java.net.*;
import java.util.*;

import com.kitfox.svg.xml.*;
import org.xml.sax.*;

/**
 * @author Mark McKay
 * @author <a href="mailto:mark@kitfox.com">Mark McKay</a>
 */
public class FeDistantLight extends FeLight 
{
    float azimuth = 0f;
    float elevation = 0f;
    

    /** Creates a new instance of FillElement */
    public FeDistantLight() {
    }

    
    protected void build() throws SVGException
    {
        super.build();
        
        StyleAttribute sty = new StyleAttribute();
        String strn;
        
        if (getPres(sty.setName("azimuth"))) azimuth = sty.getFloatValueWithUnits();
        
        if (getPres(sty.setName("elevation"))) elevation = sty.getFloatValueWithUnits();
    }

    public float getAzimuth() { return azimuth; }
    public float getElevation() { return elevation; }
    
    public boolean updateTime(double curTime) throws SVGException
    {
//        if (trackManager.getNumTracks() == 0) return false;

        //Get current values for parameters
        StyleAttribute sty = new StyleAttribute();
        boolean stateChange = false;
        
        if (getPres(sty.setName("azimuth")))
        {
            float newVal = sty.getFloatValueWithUnits();
            if (newVal != azimuth)
            {
                azimuth = newVal;
                stateChange = true;
            }
        }
        
        if (getPres(sty.setName("elevation")))
        {
            float newVal = sty.getFloatValueWithUnits();
            if (newVal != elevation)
            {
                elevation = newVal;
                stateChange = true;
            }
        }
        
        return stateChange;
    }
}

