/*
 * SVGDisplayPanel.java
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
 * Created on February 20, 2004, 12:29 PM
 */

package com.kitfox.svg;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * @author Mark McKay
 * @author <a href="mailto:mark@kitfox.com">Mark McKay</a>
 */
public class SVGDisplayPanel extends javax.swing.JPanel implements Scrollable
{
    public static final long serialVersionUID = 1;
    
    SVGDiagram diagram = null;
    float scale = 1f;
    Color bgColor = null;

    /** Creates new form SVGDisplayPanel */
    public SVGDisplayPanel()
    {
        initComponents();
    }

    public SVGDiagram getDiagram()
    {
        return diagram;
    }
    
    public void setDiagram(SVGDiagram diagram)
    {
        this.diagram = diagram;
        diagram.setDeviceViewport(getBounds());
        
        setDimension();
    }

    public void setScale(float scale)
    {
        this.scale = scale;
        setDimension();
    }

    public void setBgColor(Color col)
    {
        bgColor = col;
    }

    private void setDimension()
    {
        if (diagram == null)
        {
            setPreferredSize(new Dimension(1, 1));
            revalidate();
            return;
        }

        final Rectangle2D.Float rect = new Rectangle2D.Float();
        diagram.getViewRect(rect);

        int w = (int)(rect.width * scale);
        int h = (int)(rect.height * scale);

        setPreferredSize(new Dimension(w, h));
        revalidate();
    }

    /**
     * Update this image to reflect the passed time
     */
    public void updateTime(double curTime) throws SVGException
    {
        if (diagram == null) return;
        
        diagram.updateTime(curTime);
    }
    
    public void paintComponent(Graphics gg)
    {
        Graphics2D g = (Graphics2D)gg;

        if (bgColor != null)
        {
            Dimension dim = getSize();
            g.setColor(bgColor);
            g.fillRect(0, 0, dim.width, dim.height);
        }

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (diagram != null) 
        {
            try
            {
                diagram.render(g);
            }
            catch (SVGException e)
            {
                e.printStackTrace();
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setLayout(new java.awt.BorderLayout());

        addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                formComponentResized(evt);
            }
        });

    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_formComponentResized
    {//GEN-HEADEREND:event_formComponentResized
        if (diagram != null)
        {
            diagram.setDeviceViewport(getBounds());
            setDimension();
        }

    }//GEN-LAST:event_formComponentResized

    public Dimension getPreferredScrollableViewportSize()
    {
        return getPreferredSize();
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
    {
        if (orientation == SwingConstants.HORIZONTAL)
        {
            return visibleRect.width;
        }
        else return visibleRect.height;
    }

    public boolean getScrollableTracksViewportHeight()
    {
        return false;
    }

    public boolean getScrollableTracksViewportWidth()
    {
        return false;
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
    {
        return getScrollableBlockIncrement(visibleRect, orientation, direction) / 16;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
