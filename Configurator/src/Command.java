public class Command {

	public static enum ResultTypes { Request, Execution, Response };

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

	public String toString() {
		return this.name + "\t" + this.alias + "\t" + this.command + " " + this.args + "\tprompt: " + this.prompt;
	};

	public Object[] toArray() {
		Object array[] = {this.name, this.alias, this.command, this.args, this.result, this.prompt};
		return array;
	};
}
