public class Logger {

	public Logger() {}

	public void log() {
		System.out.println();
	}

	public void log(String message) {
		String callerMethod = StackWalker
				.getInstance()
				.walk(stream -> stream.skip(1).findFirst().get())
				.getMethodName();
		System.out.println(callerMethod + ":: " + message);
	}
}
