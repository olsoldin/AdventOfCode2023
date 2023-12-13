public class Logger {

	private static boolean enabled = true;

	private Logger() {
	}

	public static void setEnabled() {
		enabled = true;
	}

	public static void setDisabled() {
		enabled = false;
	}

	public static void log() {
		if (enabled) {
			System.out.println();
		}
	}

	public static void log(String message) {
		if (enabled) {
			String callerMethod = StackWalker
					.getInstance()
					.walk(stream -> stream.skip(1).findFirst().get())
					.getMethodName();
			System.out.println(callerMethod + ":: " + message);
		}
	}
}
