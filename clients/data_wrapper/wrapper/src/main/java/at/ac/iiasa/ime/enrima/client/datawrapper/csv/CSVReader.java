package at.ac.iiasa.ime.enrima.client.datawrapper.csv;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements Closeable {

	private BufferedReader br;

	private boolean hasNext = true;

	private CSVParser parser;

	private int skipLines;

	private boolean linesSkiped;

	private String skipComments = "#";

	public static final int DEFAULT_SKIP_LINES = 0;

	public CSVReader(Reader reader) {
		this(reader, CSVParser.DEFAULT_SEPARATOR,
				CSVParser.DEFAULT_QUOTE_CHARACTER,
				CSVParser.DEFAULT_ESCAPE_CHARACTER);
	}

	public CSVReader(Reader reader, char separator) {
		this(reader, separator, CSVParser.DEFAULT_QUOTE_CHARACTER,
				CSVParser.DEFAULT_ESCAPE_CHARACTER);
	}

	public CSVReader(Reader reader, char separator, char quotechar) {
		this(reader, separator, quotechar, CSVParser.DEFAULT_ESCAPE_CHARACTER,
				DEFAULT_SKIP_LINES, CSVParser.DEFAULT_STRICT_QUOTES);
	}

	public CSVReader(Reader reader, char separator, char quotechar,
			boolean strictQuotes) {
		this(reader, separator, quotechar, CSVParser.DEFAULT_ESCAPE_CHARACTER,
				DEFAULT_SKIP_LINES, strictQuotes);
	}

	public CSVReader(Reader reader, char separator, char quotechar, char escape) {
		this(reader, separator, quotechar, escape, DEFAULT_SKIP_LINES,
				CSVParser.DEFAULT_STRICT_QUOTES);
	}

	public CSVReader(Reader reader, char separator, char quotechar, int line) {
		this(reader, separator, quotechar, CSVParser.DEFAULT_ESCAPE_CHARACTER,
				line, CSVParser.DEFAULT_STRICT_QUOTES);
	}

	public CSVReader(Reader reader, char separator, char quotechar,
			char escape, int line) {
		this(reader, separator, quotechar, escape, line,
				CSVParser.DEFAULT_STRICT_QUOTES);
	}

	public CSVReader(Reader reader, char separator, char quotechar,
			char escape, int line, boolean strictQuotes) {
		this(reader, separator, quotechar, escape, line, strictQuotes,
				CSVParser.DEFAULT_IGNORE_LEADING_WHITESPACE);
	}

	public CSVReader(Reader reader, char separator, char quotechar,
			char escape, int line, boolean strictQuotes,
			boolean ignoreLeadingWhiteSpace) {
		this.br = new BufferedReader(reader);
		this.parser = new CSVParser(separator, quotechar, escape, strictQuotes,
				ignoreLeadingWhiteSpace);
		this.skipLines = line;
	}

	public List<String[]> readAll() throws IOException {

		List<String[]> allElements = new ArrayList<String[]>();
		while (hasNext) {
			String[] nextLineAsTokens = readNext();
			if (nextLineAsTokens != null)
				allElements.add(nextLineAsTokens);
		}
		return allElements;

	}

	public String[] readNext() throws IOException {

		String[] result = null;
		do {
			String nextLine = getNextLine();
			if (!hasNext) {
				return result; // should throw if still pending?
			}
			String[] r = parser.parseLineMulti(nextLine);
			if (r.length > 0) {
				if (result == null) {
					result = r;
				} else {
					String[] t = new String[result.length + r.length];
					System.arraycopy(result, 0, t, 0, result.length);
					System.arraycopy(r, 0, t, result.length, r.length);
					result = t;
				}
			}
		} while (parser.isPending());
		return result;
	}

	private String getNextLine() throws IOException {
		if (!this.linesSkiped) {
			for (int i = 0; i < skipLines; i++) {
				br.readLine();
			}
			this.linesSkiped = true;
		}
		String nextLine = br.readLine();
		if (nextLine == null) {
			hasNext = false;
		} else {
			if (nextLine.startsWith(skipComments)) {
				return getNextLine();
			}
			if(nextLine.trim().isEmpty())
			{
				return getNextLine();
			}
		}

		return hasNext ? nextLine : null;

	}

	public void close() throws IOException {
		br.close();
	}

}
