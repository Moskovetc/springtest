package loggers;

import loggers.entity.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public void init() throws IOException {
        this.file = new File(fileName);
        if (!file.canWrite()) throw new IOException();
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(file, event.toString(), true);
    }
}
