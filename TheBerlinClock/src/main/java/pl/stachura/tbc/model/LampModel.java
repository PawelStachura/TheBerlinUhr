package pl.stachura.tbc.model;

import java.util.Collections;

public enum LampModel {

	YELLOW_BLINKING_LAMP("Y"), HOURS_ROW_1("RRRR"), HOURS_ROW_2("RRRR"), MINUTES_ROW_1("YYRYYRYYRYY"), MINUTES_ROW_2(
			"YYYY");

	private final String AllLightsOn;
	private final char LightOff = 'O';

	LampModel(String AllLightsOn) {
		this.AllLightsOn = AllLightsOn;
	}

	public String AllLightsOn() {
		return AllLightsOn;
	}

	public String setLights(int LightCount) {
		int AmountsOfLightsTurnedOff = AllLightsOn.length() - LightCount;
		return AllLightsOn.substring(0, LightCount)
				+ String.join("", Collections.nCopies(AmountsOfLightsTurnedOff, String.valueOf(LightOff)));
	}
}
