package com.objectstyle.appfirst.jmx.collector.config;

import com.google.common.io.InputSupplier;
import com.google.common.io.LineReader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleCommandLinesSource implements CommandLinesSource {
    private final InputSupplier<Readable> inputSupplier;

    public SimpleCommandLinesSource(InputSupplier<Readable> inputSupplier) {
        this.inputSupplier = inputSupplier;
    }

    @Override
    public List<String> readLines() throws IOException {
        LineReader reader = new LineReader(inputSupplier.getInput());
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            String striped = StringUtils.strip(line);
            if (striped.startsWith(Constants.COMMAND_KEYWORD)) {
                lines.add(striped);
            }
        }
        return lines;
    }

    @Override
    public boolean hasChanges() throws IOException {
        return true;
    }
}
