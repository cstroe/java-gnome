/*
 * EnumGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintWriter;
import java.util.List;

import com.operationaldynamics.defsparser.TypeBlock;

/**
 * Output the file header necessary to declare the class containing the
 * constant objects of our representation of C enums via subclasses of
 * Constant.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public class EnumGenerator extends TypeGenerator
{
    private String[] values;
    
    /**
     * 
     * @param forObject
     *      The EnumThing you are generating code for.
     * @param values
     *      The different values enum could take.
     */
    public EnumGenerator(String forObject, String[][] values, TypeBlock block) {
        
        /*
         * FIXME are there enums with functions??
         */
        super( Thing.lookup(forObject), block );
        
        this.values = new String[values.length];
        for ( int i = 0; i < values.length; ++i ) {
            
            String value = values[i][0];
            
            /*
             * We need to convert values in the form:
             * select-folder, save
             * to
             * CREATE_FOLDER, SAVE
             */
            this.values[i] = value.toUpperCase().replace('-', '_');
            //TODO what about put this in a Generator helper function?
        }
    }

    protected void writeJavaBody(final List blocks, final PrintWriter out) {
            
        translationClassDeclaration(out);
        
        /* and write the values */
        for ( int i = 0; i < values.length; ++i ) {

            out.print("\n");
            out.print("    ");
            out.print("static final int ");
            out.print(values[i]);
            out.print(" = ");
            out.print(i);
            out.print(";\n");
        }

        out.println("}");
    }
    
    public boolean writeCCode(PrintWriter out) {
        return false;
    }
}
