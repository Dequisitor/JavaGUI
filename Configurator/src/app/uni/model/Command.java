package app.uni.model;

public class Command {

	public String name;
	public String alias;
	public String command;
	public String args;
	public boolean prompt;
	public int result;

	public Command(String name, String alias, String command, String args, boolean prompt, int result) {
		this.name = name;
		this.alias = alias;
		this.command = command;
		this.args = args;
		this.prompt = prompt;
		this.result = result;
	};

	public Command() {
	};

	public Object[] toArray() {
		Object array[] = {this.name, this.alias, this.command, this.args, this.result, this.prompt};
		return array;
	};

	public static String[] getResponseTypes() {
		String responses[] = { "HTTP only", "Execution", "Output" };
		return responses;
	};
}
