package at.ac.iiasa.ime.enrima.client.datawrapper.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

	private final char separator;
	private final char quotechar;
	private final char escape;
	private final boolean strictQuotes;
	private String pending;
	private boolean inField = false;
	private final boolean ignoreLeadingWhiteSpace;
	public static final char DEFAULT_SEPARATOR = ',';
	public static final int INITIAL_READ_SIZE = 128;
	public static final char DEFAULT_QUOTE_CHARACTER = '"';
	public static final char DEFAULT_ESCAPE_CHARACTER = '\\';
	public static final boolean DEFAULT_STRICT_QUOTES = false;
	public static final boolean DEFAULT_IGNORE_LEADING_WHITESPACE = true;
	public static final char NULL_CHARACTER = '\0';
	
	public CSVParser() {
		this(DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER,
				DEFAULT_ESCAPE_CHARACTER);
	}

	public CSVParser(char separator) {
		this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER);
	}

	public CSVParser(char separator, char quotechar) {
		this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER);
	}

	public CSVParser(char separator, char quotechar, char escape) {
		this(separator, quotechar, escape, DEFAULT_STRICT_QUOTES);
	}

	public CSVParser(char separator, char quotechar, char escape,
			boolean strictQuotes) {
		this(separator, quotechar, escape, strictQuotes,
				DEFAULT_IGNORE_LEADING_WHITESPACE);
	}

	public CSVParser(char separator, char quotechar, char escape,
			boolean strictQuotes, boolean ignoreLeadingWhiteSpace) {
		if (anyCharactersAreTheSame(separator, quotechar, escape)) {
			throw new UnsupportedOperationException(
					"The separator, quote, and escape characters must be different!");
		}
		if (separator == NULL_CHARACTER) {
			throw new UnsupportedOperationException(
					"The separator character must be defined!");
		}
		this.separator = separator;
		this.quotechar = quotechar;
		this.escape = escape;
		this.strictQuotes = strictQuotes;
		this.ignoreLeadingWhiteSpace = ignoreLeadingWhiteSpace;
	}

	private boolean anyCharactersAreTheSame(char separator, char quotechar,
			char escape) {
		return isSameCharacter(separator, quotechar)
				|| isSameCharacter(separator, escape)
				|| isSameCharacter(quotechar, escape);
	}

	private boolean isSameCharacter(char c1, char c2) {
		return c1 != NULL_CHARACTER && c1 == c2;
	}

	public boolean isPending() {
		return pending != null;
	}

	public String[] parseLineMulti(String nextLine) throws IOException {
		return parseLine(nextLine, true);
	}

	public String[] parseLine(String nextLine) throws IOException {
		return parseLine(nextLine, false);
	}

	private String[] parseLine(String nextLine, boolean multi)
			throws IOException {

		if (!multi && pending != null) {
			pending = null;
		}

		if (nextLine == null) {
			if (pending != null) {
				String s = pending;
				pending = null;
				return new String[] { s };
			} else {
				return null;
			}
		}

		List<String> tokensOnThisLine = new ArrayList<String>();
		StringBuilder sb = new StringBuilder(INITIAL_READ_SIZE);
		boolean inQuotes = false;
		if (pending != null) {
			sb.append(pending);
			pending = null;
			inQuotes = true;
		}
		for (int i = 0; i < nextLine.length(); i++) {

			char c = nextLine.charAt(i);
			if (c == this.escape) {
				if (isNextCharacterEscapable(nextLine, inQuotes || inField, i)) {
					sb.append(nextLine.charAt(i + 1));
					i++;
				}
			} else if (c == quotechar) {
				if (isNextCharacterEscapedQuote(nextLine, inQuotes || inField,
						i)) {
					sb.append(nextLine.charAt(i + 1));
					i++;
				} else {
					// inQuotes = !inQuotes;

					// the tricky case of an embedded quote in the middle:
					// a,bc"d"ef,g
					if (!strictQuotes) {
						if (i > 2 // not on the beginning of the line
								&& nextLine.charAt(i - 1) != this.separator // not
																			// at
																			// the
																			// beginning
																			// of
																			// an
																			// escape
																			// sequence
								&& nextLine.length() > (i + 1)
								&& nextLine.charAt(i + 1) != this.separator // not
																			// at
																			// the
																			// end
																			// of
																			// an
																			// escape
																			// sequence
						) {

							if (ignoreLeadingWhiteSpace && sb.length() > 0
									&& isAllWhiteSpace(sb)) {
								sb.setLength(0); // discard white space leading
													// up to quote
							} else {
								sb.append(c);
								// continue;
							}

						}
					}

					inQuotes = !inQuotes;
				}
				inField = !inField;
			} else if (c == separator && !inQuotes) {
				tokensOnThisLine.add(sb.toString());
				sb.setLength(0); // start work on next token
				inField = false;
			} else {
				if (!strictQuotes || inQuotes) {
					sb.append(c);
					inField = true;
				}
			}
		}
		// line is done - check status
		if (inQuotes) {
			if (multi) {
				// continuing a quoted section, re-append newline
				sb.append("\n");
				pending = sb.toString();
				sb = null; // this partial content is not to be added to field
							// list yet
			} else {
				throw new IOException(
						"Un-terminated quoted field at end of CSV line");
			}
		}
		if (sb != null) {
			tokensOnThisLine.add(sb.toString());
		}
		return tokensOnThisLine.toArray(new String[tokensOnThisLine.size()]);

	}

	private boolean isNextCharacterEscapedQuote(String nextLine,
			boolean inQuotes, int i) {
		return inQuotes // we are in quotes, therefore there can be escaped
						// quotes in here.
				&& nextLine.length() > (i + 1) // there is indeed another
												// character to check.
				&& nextLine.charAt(i + 1) == quotechar;
	}

	protected boolean isNextCharacterEscapable(String nextLine,
			boolean inQuotes, int i) {
		return inQuotes // we are in quotes, therefore there can be escaped
						// quotes in here.
				&& nextLine.length() > (i + 1) // there is indeed another
												// character to check.
				&& (nextLine.charAt(i + 1) == quotechar || nextLine
						.charAt(i + 1) == this.escape);
	}

	protected boolean isAllWhiteSpace(CharSequence sb) {
		boolean result = true;
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);

			if (!Character.isWhitespace(c)) {
				return false;
			}
		}
		return result;
	}
}
