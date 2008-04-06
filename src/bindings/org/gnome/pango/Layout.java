/*
 * Layout.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.gnome.glib.Object;

/**
 * A Layout represents a paragraph of text, together with its attributes.
 * 
 * @author Vreixo Formoso
 * @since 4.0.8
 */
public class Layout extends Object
{
    protected Layout(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Layout configured to draw using the given Cairo Context
     * backend.
     * 
     * <p>
     * This Layout can be used to set up the text to draw and its properties.
     * 
     * <p>
     * To actually draw the text, you will need to call
     * {@link org.freedesktop.cairo.Context#showLayout(Layout) showLayout()}
     * method on the Cairo Context.
     * 
     * TODO If you change the transformation or target surface for context,
     * you need to call pango_cairo_update_layout()
     */
    public Layout(org.freedesktop.cairo.Context context) {
        super(PangoLayout.createLayout(context));
    }

    /**
     * Sets the text of the Layout. This is the text that will be draw.
     * 
     * @see #setMarkup(String)
     */
    public void setText(String text) {
        /*
         * we cannot use text.length(), as the length is in bytes, so we use
         * -1, to make Pango compute it.
         */
        PangoLayout.setText(this, text, -1);
    }

    /**
     * Set the text of this Layout. Its format is specified using Pango Markup
     * format [TODO we need to document pango markup somewhere]
     */
    public void setMarkup(String markup) {
        /*
         * we cannot use text.length(), as the length is in bytes, so we use
         * -1, to make Pango compute it.
         */
        PangoLayout.setMarkup(this, markup, -1);
    }

    /**
     * Set the width of the Layout.
     * 
     * <p>
     * This will determine the positioning of the text and how the lines are
     * wrapped. If a text line is greater than the given size, it is splitted
     * in several lines.
     * 
     * @param width
     *            The width in Pango units [TODO a pixel is 1024 pango units,
     *            but this may change in a future. Should we add a class for
     *            functions to manage this?]
     */
    public void setWidth(int width) {
        PangoLayout.setWidth(this, width);
    }

    /**
     * Sets whether each complete line should be stretched to fill the entire
     * width of the layout.
     * 
     * <p>
     * his stretching is typically done by adding whitespace, but for some
     * scripts (such as Arabic), the justification may be done in more complex
     * ways, like extending the characters.
     * 
     * @see #setWidth(int)
     */
    public void setJustify(boolean justify) {
        PangoLayout.setJustify(this, justify);
    }

    /**
     * Sets the alignment for the layout.
     * 
     * This determines how partial lines are positioned within the horizontal
     * space available, i.e., within the <code>width</code> of the Layout.
     * 
     * @see #setWidth(int)
     * @see #setJustify(boolean)
     */
    public void setAlignment(Alignment alignment) {
        PangoLayout.setAlignment(this, alignment);
    }
}
