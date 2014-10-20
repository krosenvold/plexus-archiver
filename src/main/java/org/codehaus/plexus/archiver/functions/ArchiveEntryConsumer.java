package org.codehaus.plexus.archiver.functions;


import java.io.IOException;

import org.codehaus.plexus.archiver.ArchiveEntry;

public interface ArchiveEntryConsumer
{
    void acceptArchiveEntry( ArchiveEntry archiveEntry ) throws IOException;
}
