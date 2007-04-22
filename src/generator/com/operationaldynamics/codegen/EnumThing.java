/*
 * EnumThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * Types corresponding to objects defined in (define-enum ...) blocks. These
 * map to Constant subclasses in our bindings.
 * 
 * @author Andrew Cowie
 */
class EnumThing extends Thing
{
    EnumThing(String gType, String javaPackage, String javaClass, String javaType) {
        super(gType, javaPackage, javaClass, javaType, "numOf", "int", "jint");
    }

}