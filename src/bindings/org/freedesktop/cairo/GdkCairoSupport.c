/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Claspath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */

#include <jni.h>
#include <gdk/gdk.h>
#include <cairo.h>
#include "bindings_java.h"
#include "org_freedesktop_cairo_GdkCairoSupport.h"

/**
 * This accesses gdk_cairo_create(), a utility function in GDK allowing you to
 * get the Cairo cairo_t for a given GdkDrawable. We have exposed this in our
 * bindings as a constructor to Context.
 */
JNIEXPORT jlong JNICALL
Java_org_freedesktop_cairo_GdkCairoSupport_gdk_1cairo_1create
(
	JNIEnv* env,
	jclass cls,
	jlong _drawable
)
{
	GdkDrawable* drawable;
	cairo_t* result;

	// convert drawable
	drawable = (GdkDrawable*) _drawable;

	// call function
	result = gdk_cairo_create(drawable);

	// cleanup parameter drawable

	// and finally
	return (jlong) result;
}

/**
 * This accesses gdk_cairo_create(), then gdk_cairo_region(), and then
 * cairo_clip it() before returning the constructed Context, all driven by
 * the passed in GdkEventExpose. Thanks to Benjamin Otte for advice about
 * doing this properly. 
 */
JNIEXPORT jlong JNICALL
Java_org_freedesktop_cairo_GdkCairoSupport_gdk_1cairo_1create_1and_1clip
(
        JNIEnv* env,
        jclass cls,
        jlong _event
)
{
        GdkEventExpose* event;
        cairo_t* result;
        
        // convert event
        event = (GdkEventExpose*) _event;
        
        // call constructor function
        result = gdk_cairo_create(event->window);
        
        // get region
        gdk_cairo_region(result, event->region);
        
        // and constrain
        cairo_clip(result);

        // cleanup parameter source
        
        // cleanup parameter event

        // and finally
        return (jlong) result;
}




JNIEXPORT void JNICALL
Java_org_freedesktop_cairo_GdkCairoSupport_gdk_1cairo_1set_1source_1pixbuf
(
        JNIEnv* env,
        jclass cls,
        jlong _context,
        jlong _pixbuf,
        jdouble _x,
        jdouble _y
)
{
        cairo_t* context;
        GdkPixbuf* pixbuf;
        double x;
        double y;
        
        // convert context
        context = (cairo_t*) _context;
        
        // convert pixbuf
        pixbuf = (GdkPixbuf*) _pixbuf;

        // convert x
        x = (double) _x;
        
        // convert x
        y = (double) _y;
        
        // call function
        gdk_cairo_set_source_pixbuf(context, pixbuf, x, y);

        // cleanup parameter context

        // cleanup parameter pixbuf

        // cleanup parameter x

        // cleanup parameter y
}
