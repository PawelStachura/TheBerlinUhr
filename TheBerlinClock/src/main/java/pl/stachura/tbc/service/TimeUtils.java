package pl.stachura.tbc.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TimeUtils {

	/**
	 * Parses a 24-hour clock string to an array [hours, minutes, seconds].
	 * 
	 * @throws WrongTimeFormatException
	 */
	public static int[] parseTime(String aTime) throws WrongTimeFormatException {
		final String timePattern = "^(2[0-4]|1[0-9]|0[0-9]|[^0-9][0-9]):([0-5][0-9]|[0-9]):([0-5][0-9]|[0-9])$";
		Pattern pattern = Pattern.compile(timePattern);

		Matcher timeSplit = pattern.matcher(aTime);

		Integer hours = null, minutes = null, seconds = null;
		if (timeSplit.find()) {
			hours = Integer.valueOf(timeSplit.group(1));
			minutes = Integer.valueOf(timeSplit.group(2));
			seconds = Integer.valueOf(timeSplit.group(3));
		} else {
			throw new WrongTimeFormatException("Wrong time format ");
		}
		return new int[] { hours, minutes, seconds };
	}

}
