# JavaGUI
This program is a Configuration tool for one of my hobbi projects. It exports a given configuration to a selected file, which can be used by the aforementioned program.
The exported file contains commands, which can be issued through a web interface. A command record consist of
- name: this is a general identifier
- alias: the text that will be shown for the user
- command: the actual file to execute (path is restricted by the server, it is always ./exec/)
- args: arguments for the command (like which pin to toggle, etc.)
- result: what type of result do we expect, i.e. we only want to know if the HTTP request failed or succeded, we want to know that the actual command has finished without errors, or the third option is to get the output as well (this is necessary for toggle buttons or queries)
- prompt: for commands such as shutdown and restart it is better to ask for confirmation from the user, so we won't allow a misclick to restart or halt the device

## Additional features
- i18n
- i10n - a little
- async write (using thread)
- export available in plain text, json or xml
