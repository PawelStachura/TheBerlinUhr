package pl.stachura.tbc.app;

import pl.stachura.tbc.service.DefaultTimeConverter;

public class TheBerlinClock {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Write time as a parameter");
			System.exit(0);
		} else {
			System.out.println("====== Berlin Clock ======");
			DefaultTimeConverter timeConventerImpl = new DefaultTimeConverter();
			System.out.println(timeConventerImpl.convertTime(args[0]));
			System.out.println("Introduced time " + args[0]);
			System.out.println("==========================");
		}
	}
}
