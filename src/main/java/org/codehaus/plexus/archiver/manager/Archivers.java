package org.codehaus.plexus.archiver.manager;

import org.codehaus.plexus.archiver.Archiver;
import org.codehaus.plexus.archiver.UnArchiver;
import org.codehaus.plexus.archiver.bzip2.BZip2Archiver;
import org.codehaus.plexus.archiver.bzip2.BZip2UnArchiver;
import org.codehaus.plexus.archiver.dir.DirectoryArchiver;
import org.codehaus.plexus.archiver.ear.EarArchiver;
import org.codehaus.plexus.archiver.gzip.GZipArchiver;
import org.codehaus.plexus.archiver.gzip.GZipUnArchiver;
import org.codehaus.plexus.archiver.jar.JarArchiver;
import org.codehaus.plexus.archiver.tar.TarArchiver;
import org.codehaus.plexus.archiver.tar.TarUnArchiver;
import org.codehaus.plexus.archiver.war.WarArchiver;
import org.codehaus.plexus.archiver.zip.ZipArchiver;
import org.codehaus.plexus.archiver.zip.ZipUnArchiver;

import java.io.File;

class Archivers
{
    public static <T extends Archiver> T createArchiver( String type, File archiveFile )
    {
        if ( "zip".equals( type ) )
        {
            return setDestFile( archiveFile, new ZipArchiver() );
        }
        else if ( "tar".equals( type ) )
        {
            return setDestFile( archiveFile, new TarArchiver() );
        }
        else if ( "dir".equals( type ) )
        {
            return setDestFile( archiveFile, new DirectoryArchiver() );
        }
        else if ( "bzip2".equals( type ) )
        {
            return setDestFile( archiveFile, new BZip2Archiver() );
        }
        else if ( "ear".equals( type ) )
        {
            return setDestFile( archiveFile, new EarArchiver() );
        }
        else if ( "gzip".equals( type ) )
        {
            return setDestFile( archiveFile, new GZipArchiver() );
        }
        else if ( "jar".equals( type ) )
        {
            return setDestFile( archiveFile, new JarArchiver() );
        }
        else if ( "war".equals( type ) )
        {
            return setDestFile( archiveFile, new WarArchiver() );
        }
        else if ( "rar".equals( type ) )
        {
            // there is no implementaion of RarArchiver, but JarArchiver will do the job
            return setDestFile( archiveFile, new JarArchiver() );
        }
        throw new IllegalArgumentException( "Unknown archiver type " + type );
    }

    public static <T extends UnArchiver> T createUnarchiver( String type, File archiveFile )
    {
        if ( "zip".equals( type ) || "jar".equals( type ) ||  "war".equals( type ))
        {
            return setDestFile( archiveFile, new ZipUnArchiver() );
        }
        else if ( "tar".equals( type ) )
        {
            return setDestFile( archiveFile, new TarUnArchiver( ));
        }
        else if ( "bzip2".equals( type ) )
        {
            return setDestFile( archiveFile, new BZip2UnArchiver() );
        }
        else if ( "ear".equals( type ) )
        {
            return setDestFile( archiveFile, new ZipUnArchiver() );
        }
        else if ( "gzip".equals( type ) )
        {
            return setDestFile( archiveFile, new GZipUnArchiver() );
        }
        else if ( "jar".equals( type ) )
        {
            return setDestFile( archiveFile, new ZipUnArchiver() );
        }
        else if ( "war".equals( type ) )
        {
            return setDestFile( archiveFile, new WarArchiver() );
        }
        else if ( "rar".equals( type ) )
        {
            // there is no implementaion of RarArchiver, but JarArchiver will do the job
            return setDestFile( archiveFile, new JarArchiver() );
        }
        throw new IllegalArgumentException( "Unknown archiver type " + type );
    }

    /*
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>jar</role-hint>
      <!-- there is no implementation of JarUnArchiver, but ZipUnArchive will do the job -->
      <implementation>org.codehaus.plexus.archiver.zip.ZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>war</role-hint>
      <!-- there is no implementation of WarUnArchiver, but ZipUnArchive will do the job -->
      <implementation>org.codehaus.plexus.archiver.zip.ZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>swc</role-hint>
      <!-- there is no implementation of SWCUnArchiver, but ZipUnArchive will do the job -->
      <implementation>org.codehaus.plexus.archiver.zip.ZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>nar</role-hint>
      <!-- there is no implementation of NARUnArchiver, but ZipUnArchive will do the job -->
      <implementation>org.codehaus.plexus.archiver.zip.ZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>esb</role-hint>
      <!-- there is no implementation of ESBUnArchiver, but ZipUnArchive will do the job -->
      <implementation>org.codehaus.plexus.archiver.zip.ZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>sar</role-hint>
      <!-- there is no implementation of SARUnArchiver, but ZipUnArchive will do the job -->
      <implementation>org.codehaus.plexus.archiver.zip.ZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>car</role-hint>
      <!-- there is no implementation of CARUnArchiver, but ZipUnArchive will do the job -->
      <implementation>org.codehaus.plexus.archiver.zip.ZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>par</role-hint>
      <!-- there is no implementation of PARUnArchiver, but ZipUnArchive will do the job -->
      <implementation>org.codehaus.plexus.archiver.zip.ZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>rar</role-hint>
      <!-- there is no implementation of RarUnArchiver, but ZipUnArchive will do the job -->
      <implementation>org.codehaus.plexus.archiver.zip.ZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>

    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>tgz</role-hint>
      <implementation>org.codehaus.plexus.archiver.tar.TarGZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>tar.gz</role-hint>
      <implementation>org.codehaus.plexus.archiver.tar.TarGZipUnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>

    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>tbz2</role-hint>
      <implementation>org.codehaus.plexus.archiver.tar.TarBZip2UnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>

    <component>
      <role>org.codehaus.plexus.archiver.UnArchiver</role>
      <role-hint>tar.bz2</role-hint>
      <implementation>org.codehaus.plexus.archiver.tar.TarBZip2UnArchiver</implementation>
      <instantiation-strategy>per-lookup</instantiation-strategy>
    </component>
     */

    @SuppressWarnings( "unchecked" )
    private static <T extends Archiver> T setDestFile( File archiveFile, Archiver archiver )
    {
        archiver.setDestFile( archiveFile );
        return (T) archiver;
    }
    @SuppressWarnings( "unchecked" )
    private static <T extends UnArchiver> T setDestFile( File archiveFile, UnArchiver archiver )
    {
        archiver.setSourceFile( archiveFile );
        return (T) archiver;
    }

}
