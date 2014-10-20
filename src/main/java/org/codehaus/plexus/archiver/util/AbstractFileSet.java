package org.codehaus.plexus.archiver.util;

/*
 * Copyright 2014 The Codehaus Foundation.
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
import org.codehaus.plexus.archiver.BaseFileSet;
import org.codehaus.plexus.components.io.fileselectors.FileSelector;
import org.codehaus.plexus.components.io.functions.InputStreamTransformer;

import java.util.Arrays;

import javax.annotation.Nonnull;


/**
 * Default implementation of {@link BaseFileSet}.
 * @since 1.0-alpha-9
 */
public abstract class AbstractFileSet<T extends AbstractFileSet>
    implements BaseFileSet
{
    private static final InputStreamTransformer[] empty = new InputStreamTransformer[0];

    private String prefix;

    private String[] includes;

    private String[] excludes;

    private FileSelector[] fileSelectors;

    private boolean caseSensitive = true;

    private boolean usingDefaultExcludes = true;

    private boolean includingEmptyDirectories = true;

    private InputStreamTransformer[] streamTransformers = empty;


    /**
     * Sets a string of patterns, which excluded files
     * should match.
     */
    public void setExcludes( String[] excludes )
    {
        this.excludes = excludes;
    }

    public String[] getExcludes()
    {
        return excludes;
    }

    /**
     * Sets a set of file selectors, which should be used
     * to select the included files.
     */
    public void setFileSelectors( FileSelector[] fileSelectors )
    {
        this.fileSelectors = fileSelectors;
    }

    public FileSelector[] getFileSelectors()
    {
        return fileSelectors;
    }

    /**
     * Sets a string of patterns, which included files
     * should match.
     */
    public void setIncludes( String[] includes )
    {
        this.includes = includes;
    }

    public String[] getIncludes()
    {
        return includes;
    }

    /**
     * Sets the prefix, which the file sets contents shall
     * have.
     */
    public void setPrefix( String prefix )
    {
        this.prefix = prefix;
    }

    public String getPrefix()
    {
        return prefix;
    }

    /**
     * Sets, whether the include/exclude patterns are
     * case sensitive. Defaults to true.
     */
    public void setCaseSensitive( boolean caseSensitive )
    {
        this.caseSensitive = caseSensitive;
    }

    public boolean isCaseSensitive()
    {
        return caseSensitive;
    }

    /**
     * Sets, whether the default excludes are being
     * applied. Defaults to true.
     */
    public void setUsingDefaultExcludes( boolean usingDefaultExcludes )
    {
        this.usingDefaultExcludes = usingDefaultExcludes;
    }

    public boolean isUsingDefaultExcludes()
    {
        return usingDefaultExcludes;
    }

    /**
     * Sets, whether empty directories are being included. Defaults
     * to true.
     */
    public void setIncludingEmptyDirectories( boolean includingEmptyDirectories )
    {
        this.includingEmptyDirectories = includingEmptyDirectories;
    }

    public boolean isIncludingEmptyDirectories()
    {
        return includingEmptyDirectories;
    }

    public T prefixed(String prefix){
        setPrefix(  prefix );
        return (T)this;
    }

    public T include(String[] includes){
        setIncludes( includes );
        return (T)this;
    }

    public T exclude(String[] excludes){
        setExcludes(excludes);
        return (T) this;
    }

    public T includeExclude( String[] includes, String[] excludes ){
        return (T) include( includes ).exclude( excludes );
    }

    public T includeEmptyDirs( boolean includeEmptyDirectories  ){
        setIncludingEmptyDirectories(includeEmptyDirectories);
        return (T) this;
    }

    public void addStreamTransformers( @Nonnull InputStreamTransformer... streamTransformer )
    {
        final int orgLength = this.streamTransformers.length;
        streamTransformers = Arrays.copyOf( this.streamTransformers, orgLength + streamTransformer.length );
        System.arraycopy(streamTransformer, 0, streamTransformers, orgLength, streamTransformer.length);
    }

    public InputStreamTransformer[] getStreamTransformers()
    {
        return streamTransformers;
    }
}
