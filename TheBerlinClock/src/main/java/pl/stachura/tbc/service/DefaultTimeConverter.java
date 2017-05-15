package pl.stachura.tbc.service;

import static pl.stachura.tbc.model.LampModel.HOURS_ROW_1;
import static pl.stachura.tbc.model.LampModel.HOURS_ROW_2;
import static pl.stachura.tbc.model.LampModel.MINUTES_ROW_1;
import static pl.stachura.tbc.model.LampModel.MINUTES_ROW_2;
import static pl.stachura.tbc.model.LampModel.YELLOW_BLINKING_LAMP;

public class DefaultTimeConverter implements TimeConverter {

	public String convertTime(String aTime) {

		int hours = 0, minutes = 0, seconds = 0;
		int[] timeParts;
		try {
			timeParts = TimeUtils.parseTime(aTime);
			hours = timeParts[0];
			minutes = timeParts[1];
			seconds = timeParts[2];
		} catch (WrongTimeFormatException e) {
			System.out.println("Please input valid time (Format hh:ss:mm 0-24h:0-59m:0-59s)");
			System.exit(1);
		}

		return String.join("\n", YELLOW_BLINKING_LAMP.setLights(booleanToInt(seconds % 2 == 0)),
				HOURS_ROW_1.setLights(hours / 5), HOURS_ROW_2.setLights(hours % 5),
				MINUTES_ROW_1.setLights(minutes / 5), MINUTES_ROW_2.setLights(minutes % 5));
	}

	public static int booleanToInt(boolean value) {
		// Convert true to 1 and false to 0.
		return value ? 1 : 0;
	}

}
