package pl.stachura.tbc.service;

import static org.junit.Assert.*;
import static pl.stachura.tbc.model.LampModel.*;

import java.util.Random;
import org.junit.Test;
import pl.stachura.tbc.service.DefaultTimeConverter;


public class TheBerlinClockTest {

	DefaultTimeConverter timeConventerImpl = new DefaultTimeConverter();
	Random random = new Random();

	// Yellow seconds should have 1 lamp
	@Test
	public void testYellowSecondsShouldHave1Lamp() {
		assertEquals("1 lamp must be here ", 1, YELLOW_BLINKING_LAMP.AllLightsOn().length());
	}

	// Should blinks on/off every two seconds
	@Test
	public void YellowBlinkingLampTest() {
		assertEquals("Even number must be on", "Y", timeConventerImpl.convertTime("00:00:" + evenNumberGenerator(0, 59)).substring(0, 1));
		assertEquals("Even number must be on", "Y",	timeConventerImpl.convertTime("00:00:" + evenNumberGenerator(0, 59)).substring(0, 1));
		assertEquals("Odd number must be off", "O",	timeConventerImpl.convertTime("00:00:" + oddNumberGenerator(0, 59)).substring(0, 1));
		assertEquals("Odd number must be off", "O",	timeConventerImpl.convertTime("00:00:" + oddNumberGenerator(0, 59)).substring(0, 1));
	}

	// Top hours should have 4 lamps
	@Test
	public void testTopHoursShouldHave4Lamps() {
		assertEquals("4 lamps must be here", 4, HOURS_ROW_1.AllLightsOn().length());
	}

	// Top hours should light a red lamp for every 5 hours
	//@formatter:off
	@Test
	public void testTopHoursShouldLightRedLampForEvery5Hours() {
		assertEquals("even range 0,4 lamps must be off", "OOOO",timeConventerImpl.convertTime(evenNumberGenerator(0, 4) + ":00:00").substring(2, 6));
		assertEquals("odd range 0,4 lamps must be off", "OOOO",timeConventerImpl.convertTime(oddNumberGenerator(0, 4) + ":00:00").substring(2, 6));
		assertEquals("even range 5,9 first lamp must be on", "ROOO",timeConventerImpl.convertTime(evenNumberGenerator(5, 9) + ":00:00").substring(2, 6));
		assertEquals("odd range 5,9 first lamp must be on", "ROOO",	timeConventerImpl.convertTime(oddNumberGenerator(5, 9) + ":00:00").substring(2, 6));
		assertEquals("even range 10,14 first 2 lamps must be on", "RROO",timeConventerImpl.convertTime(evenNumberGenerator(10, 14) + ":00:00").substring(2, 6));
		assertEquals("odd range 10,14 first lamp must be on", "RROO",timeConventerImpl.convertTime(oddNumberGenerator(10, 14) + ":00:00").substring(2, 6));
		assertEquals("even range 15,19 first 3 lamp must be on", "RRRO",timeConventerImpl.convertTime(evenNumberGenerator(15, 19) + ":00:00").substring(2, 6));
		assertEquals("odd range 15,19 first 3 lamp must be on", "RRRO",timeConventerImpl.convertTime(oddNumberGenerator(15, 19) + ":00:00").substring(2, 6));
		assertEquals("even range 20,24 all lamp must be on", "RRRR",timeConventerImpl.convertTime(evenNumberGenerator(20, 24) + ":00:00").substring(2, 6));
		assertEquals("odd range 20,24 all lamps must be on", "RRRR",timeConventerImpl.convertTime(oddNumberGenerator(20, 24) + ":00:00").substring(2, 6));
	}
	//@formatter:on
	// Bottom hours should have 4 lamps
	@Test
	public void testBottomHoursShouldHave4Lamps() {
		assertEquals("4 lamps must be here", 4, HOURS_ROW_2.AllLightsOn().length());
	}

	// Bottom hours should light a red lamp for every hour left from top hours
	@Test
	public void testBottomHoursShouldLightRedLampForEveryHourLeftFromTopHours() {

		assertEquals("all lamps off at midnight ", "OOOO", timeConventerImpl.convertTime("00:00:00").substring(7, 11));
		assertEquals("13%10 - 3 lamps must be on", "RRRO", timeConventerImpl.convertTime("13:00:00").substring(7, 11));
		assertEquals("23%10 - 3 lamps must be on", "RRRO", timeConventerImpl.convertTime("23:00:00").substring(7, 11));
		assertEquals("24%10 - all lamps must be on", "RRRR",
				timeConventerImpl.convertTime("24:00:00").substring(7, 11));
	}

	// Top minutes should have 11 lamps
	@Test
	public void testTopMinutesShouldHave11Lamps() {
		assertEquals("11 lamps must be here", 11, MINUTES_ROW_1.AllLightsOn().length());
	}

	// Top minutes should have 3rd, 6th and 9th lamps in red
	@Test
	public void testTopMinutesShouldHave3rd6thAnd9thLampsInRed() {

		assertEquals("R", timeConventerImpl.convertTime("00:55:00").substring(14, 15));
		assertEquals("R", timeConventerImpl.convertTime("00:55:00").substring(17, 18));
		assertEquals("R", timeConventerImpl.convertTime("00:55:00").substring(20, 21));
	}

	// Top minutes should light a yellow lamp for every 5 minutes unless it's
	// first quarter, half or last quarter
	@Test
	public void testTopMinutesShouldLightYellowLampForEvery5MinutesUnlessItIsFirstQuarterHalfOrLastQuarter() {

		assertEquals("midnight all lamps off", "OOOOOOOOOOO",
				timeConventerImpl.convertTime("00:00:00").substring(12, 23));
		assertEquals("17m first quarter", "YYROOOOOOOO", timeConventerImpl.convertTime("00:17:00").substring(12, 23));
		assertEquals("55m all lamps on ", "YYRYYRYYRYY", timeConventerImpl.convertTime("00:55:00").substring(12, 23));
	}

	// Bottom minutes should have 4 lamps
	@Test
	public void testBottomMinutesShouldHave4Lamps() {
		assertEquals("4 lamps must be here", 4, MINUTES_ROW_2.AllLightsOn().length());
	}

	// Bottom minutes should light a yellow lamp for every minute left from top
	// minutes
	@Test
	public void testBottomMinutesShouldLightYellowLampForEveryMinuteLeftFromTopMinutes() {
		assertEquals("0lamps on", "OOOO", timeConventerImpl.convertTime("00:00:00").substring(24, 28));
		assertEquals("17%5- 2 first laps on", "YYOO", timeConventerImpl.convertTime("00:17:00").substring(24, 28));
		assertEquals("59%5- all lamps on", "YYYY", timeConventerImpl.convertTime("00:59:00").substring(24, 28));
	}

	// Berlin Clock it should "result in correct seconds, hours and minutes" in
	// {
	@Test
	public void testBerlinClockShouldResultInCorrectSecondsHoursAndMinutes() {
		String berlinTime = timeConventerImpl.convertTime("16:37:16");
		String[] expected = new String[] { "Y", "RRRO", "ROOO", "YYRYYRYOOOO", "YYOO" };
		assertEquals("Y must be first", expected[0], berlinTime.substring(0, 1));
		assertEquals("RRRO must be second", expected[1], berlinTime.substring(2, 6));
		assertEquals("ROOO 3rd", expected[2], berlinTime.substring(7, 11));
		assertEquals("YYRYYRYOOOO is 4th", expected[3], berlinTime.substring(12, 23));
		assertEquals("YYOO is on last position", expected[4], berlinTime.substring(24, 28));
	}

	private String evenNumberGenerator(int low, int high) {
		int randomEvenNumber = random.nextInt(high - low) + low;
		if (randomEvenNumber % 2 != 0) {
			randomEvenNumber = randomEvenNumber + 1;
		}
		return String.format("%02d", randomEvenNumber);
	}

	private String oddNumberGenerator(int low, int high) {
		int randomNumber = random.nextInt(high - low) + low;
		while (randomNumber % 2 != 1) {
			randomNumber = random.nextInt(high - low) + low;
		}
		return String.format("%02d", randomNumber);
	}
}
