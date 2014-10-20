package org.codehaus.plexus.archiver;


import java.io.IOException;

public interface ArchiveEntryConsumer
{
    void acceptArchiveEntry( ArchiveEntry archiveEntry ) throws IOException;
}
