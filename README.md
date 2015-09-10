#Assembler

A simple utility to assemble modpacks into the FTB modpack format. Built originally for the TPPI Team, but anybody is free to use it.

##What it does

Mods in `mods/client` will only be loaded into the client zip while mods in `mods/server` will only be loaded into the server zip. Mods found in `mods/common` will be loaded into both. The same is true for `extra/(client/server/common)`. All configs found in `config` will be added to both zips.

##Arguments

* `-d`: This is the input directory of your pack. You can either use an absolute path (All the way from your drive to the directory) or add onto the directory that you opened your terminal in. Defaults to current directory.
* `-o`: This is the name of your modpack. This can be almost anything without spaces. Defaults to "Modpack"
* `-v`: This is the version of your modpack. Again, almost anything without spaces. Defaults to "0.0.1"
* `-client`: Builds the client zip. Does not have a modifier. Defaults to false.
* `-server`: Builds teh server zip. Does not have a modifier. Defaults to false.

###Example Command

`java -jar Assembler-1.0.0-1.jar -o=MyModpack -v=1.0.0 -client -server`