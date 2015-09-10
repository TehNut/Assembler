#Assembler

A simple utility to assemble modpacks into the FTB modpack format. Built originally for the TPPI Team, but anybody is free to use it.

##Arguments

`-d`: This is the input directory of your pack. You can either use an absolute path (All the way from your drive to the directory) or add onto the directory that you opened your terminal in. Defaults to current directory.
`-o`: This is the name of your modpack. This can be almost anything without spaces. Defaults to "Modpack"
`-v`: This is the version of your modpack. Again, almost anything without spaces. Defaults to "0.0.1"
`-client`: Builds the client zip. Does not have a modifier. Defaults to false.
`-server`: Builds teh server zip. Does not have a modifier. Defaults to false.

###Example Command

`java -jar Assembler-1.0.0-1.jar -o=MyModpack -v=1.0.0 -client -server`