package kombucha.testutils;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileSplitter {
	
	public Collection<String> split(File file) throws Exception {
		List<String> lines = FileUtils.readLines(file);
		Collections.sort(lines);
		return lines;
	}

}
