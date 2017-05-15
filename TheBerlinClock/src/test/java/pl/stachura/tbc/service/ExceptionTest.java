package pl.stachura.tbc.service;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.stachura.tbc.service.DefaultTimeConverter;
import pl.stachura.tbc.service.WrongTimeFormatException;

public class ExceptionTest {

	DefaultTimeConverter timeConventerImpl = new DefaultTimeConverter();
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// should throw WrongTimeFormatException if time format is not correct
	@Test
	public void testWrongTimeFormatException() throws WrongTimeFormatException {
		thrown.expect(WrongTimeFormatException.class);
		thrown.expectMessage(is("Wrong time format"));
		timeConventerImpl.convertTime("23s:00:00");

	}
}
