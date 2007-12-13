/*
 * ComboBox.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A Widget used to choose from a list of items. <img src="ComboBox"
 * class="snapshot" />
 * 
 * <p>
 * <b>FIXME WARNING FIXME WARNING<br>
 * This API is entirely subject to change.</b>
 * 
 * <p>
 * ComboBox actually uses a TreeModel to store the items, giving you the same
 * MVC power of GTK's TreeView/TreeModel system. There is also an alternative
 * API which can be used to create and manipulate ComboBoxes which are
 * comprised only of text. TODO Describe this properly, or better yet,
 * refactor.
 * 
 * <p>
 * <i>The underlying <code>GtkComboBox</code> is actually presents two APIs
 * which, while not mutually exclusive, don't tend to go together very well.
 * We may well split this into two public classes.</i>
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 */
public class ComboBox extends Bin implements CellEditable, CellLayout
{
    protected ComboBox(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new ComboBox that can be used with the text-only
     * convenience functions.
     * 
     * <p>
     * The ComboBox will be backed by a built-in simple model instance which
     * is suitable for managing strings, only. If this constructor is used,
     * manipulation of the ComboBox should only be done by the convenience
     * methods {@link #appendText(String) appendText()},
     * {@link #insertText(int, String) insertText()},
     * {@link #getActiveText() getActiveText()}, etc.
     * 
     * @deprecated
     */
    public ComboBox() {
        super(GtkComboBox.createComboBoxText());
    }

    /**
     * Construct a new full-power TreeModel-backed ComboBox.
     * 
     * <p>
     * If subclassing ComboBox, passing <code>null</code> will allow you to
     * use this constructor and thus the full power ComboBox; you can set the
     * Model later with {@link #setModel(TreeModel) setModel()}.
     */
    public ComboBox(TreeModel model) {
        super(GtkComboBox.createComboBoxWithModel(model));
    }

    /**
     * Returns the index of the active item in the ComboBox. This counts from
     * a zero origin, so a return value of <code>2</code> means the third
     * item in the list is currently the active item.
     * 
     * @since 4.0.6
     */
    public int getActive() {
        return GtkComboBox.getActive(this);
    }

    /**
     * Change the active item within this ComboBox. Items are numbered from
     * <code>0</code>.
     * 
     * @since 4.0.6
     */
    public void setActive(int active) {
        GtkComboBox.setActive(this, active);
    }

    /**
     * Appends a text item to the list. This method should only be used if the
     * ComboBox was created with <code>textItemsOnly</code>. FIXME We can
     * do better than this.
     * 
     * @deprecated
     */
    public void appendText(String text) {
        GtkComboBox.appendText(this, text);
    }

    /**
     * Appends a text item at the supplied position. This method should only
     * be used, if the ComboBox was created with <code>textItemsOnly</code>.
     * FIXME We can do better than this.
     * 
     * @deprecated
     * @param position
     *            The position beginning from 0, where the new item should be
     *            placed
     */
    public void insertText(int position, String text) {
        GtkComboBox.insertText(this, position, text);
    }

    /**
     * Prepends a text item to the list. This method should only be used, if
     * the ComboBox was created with <code>textItemsOnly</code>. FIXME We
     * can do better than this.
     * 
     * @deprecated
     */
    public void prependText(String text) {
        GtkComboBox.prependText(this, text);
    }

    /**
     * Returns the text of the active item. This method should only be used,
     * if the ComboBox was created with <code>textItemsOnly</code>. FIXME
     * We can do better than this.
     * 
     * @deprecated
     */
    public String getActiveText() {
        return GtkComboBox.getActiveText(this);
    }

    /**
     * Handler interface for the <code>changed</code> signal. This event
     * occurs whenever a different item gets selected by the user.
     */
    public interface CHANGED extends GtkComboBox.CHANGED
    {
        public void onChanged(ComboBox source);
    }

    /**
     * Hook up a {@link CHANGED} handler to the Widget.
     */
    public void connect(CHANGED handler) {
        GtkComboBox.connect(this, handler);
    }

    /**
     * Cause the popup part of the ComboBox to raise and present itself. You
     * don't tend to need this (after all it's the user who clicks on the
     * ComboBox to cause the popup to present). The ComboBox must already have
     * been realized to the screen before you will be able to use this.
     * 
     * @since 4.0.6
     */
    public void popup() {
        GtkComboBox.popup(this);
    }

    TreeModel getModel() {
        return GtkComboBox.getModel(this);
    }

    /**
     * Get a TreeIter pointing at the currently selected row. If no row is
     * currently active then <code>null</code> will be returned.
     * 
     * @since 4.0.6
     */
    public TreeIter getActiveIter() {
        final TreeIter active;

        active = new TreeIter(this.getModel());

        if (GtkComboBox.getActiveIter(this, active)) {
            return active;
        } else {
            return null;
        }
    }
}
