package pl.stachura.tbc.service;

public class WrongTimeFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongTimeFormatException(String string) {
		super(string);
	}

}
