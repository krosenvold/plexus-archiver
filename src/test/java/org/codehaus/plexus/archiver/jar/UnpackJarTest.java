package org.codehaus.plexus.archiver.jar;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.codehaus.plexus.PlexusTestCase;
import org.codehaus.plexus.archiver.Archiver;
import org.codehaus.plexus.archiver.util.DefaultArchivedFileSet;
import org.codehaus.plexus.components.io.filemappers.IdentityMapper;
import org.codehaus.plexus.components.io.functions.InputStreamTransformer;
import org.codehaus.plexus.components.io.resources.PlexusIoResource;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;

public class UnpackJarTest extends PlexusTestCase
{

    public static final String[] DEFAULT_INCLUDES_ARRAY = { "**/*" };

    static class IdentityTransformer
        implements InputStreamTransformer
    {
        IdentityTransformer()
        {
        }

        @Nonnull
        public InputStream transform( @Nonnull PlexusIoResource resource, @Nonnull InputStream inputStream )
            throws IOException
        {
            return inputStream;
        }
    }


    @Test
    public void test_dependency_sets_depSet_unpacked_rdonly()
        throws Exception
    {
        File src = new File("src/test/resources/unpack_issue.jar");
        assertTrue( src.exists());
        DefaultArchivedFileSet afs = DefaultArchivedFileSet.archivedFileSet( src );
        afs.setIncludes( DEFAULT_INCLUDES_ARRAY );
        afs.setExcludes( null );
        afs.setPrefix( "child-1/" );
        afs.setStreamTransformer( new IdentityTransformer() );
        Archiver archiver = (Archiver)lookup( Archiver.ROLE, "dir" );
        archiver.setDestFile( new File( "target/depset_unpack" ) );
        archiver.addArchivedFileSet( afs, Charset.forName("UTF-8" ));
        archiver.createArchive();
        assertTrue( new File("target/depset_unpack/child-1/META-INF/MANIFEST.MF").exists());
    }

    @Test
    public void testCheckZipFIle()
        throws IOException
    {
        dumpZf( new ZipFile( "src/test/resources/child1-broken.jar" ) );
        System.out.println( "Next file");
        dumpZf( new ZipFile( "src/test/resources/child2-works.jar" ) );
    }

    private void dumpZf( ZipFile zf )
    {
        Enumeration<ZipArchiveEntry> entriesInPhysicalOrder = zf.getEntriesInPhysicalOrder();
        while (entriesInPhysicalOrder.hasMoreElements()){
            ZipArchiveEntry zipArchiveEntry = entriesInPhysicalOrder.nextElement();
            System.out.println( "zipArchiveEntry = " + zipArchiveEntry.getName());
        }
    }
}
