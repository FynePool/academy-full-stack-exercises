package com.riccardorocco.IO;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class SpaceFilterWriter extends FilterWriter {

	protected SpaceFilterWriter(Writer out) {
		super(out);
	}

	@Override
	public void write(int c) throws IOException {
		if (!Character.isSpaceChar(c)) {
			super.write(c);
		}
	}
}
