# git-all-scala
Program which utilizes Ammonite ops library to checkout all previous versions of a git repository and store them in a ../temp/ directory

Usability: 
	As of the writing of this paper the necessary parameters for the git-all-scala program are provided through command line arguments.  There are five possible arguments when running the program:

--url  This argument is where the url for the desired git repo is provided.  If not provided then the program will fail 
--src This argument should be the name of the repo you wish to analyze.  It should also include all folders after root that specify the desired location of the folder.  It must match the name on GitHub otherwise the program will fail
--dst This argument is the user’s desired name for the result of the program.  It can be any name that is not already in use 
--start This argument allows for the downloading of hash codes to start with a hash code later than the first one.  If left unspecified it is automatically set to 1 and every hash code is included
--stride This argument allows you to skip every n hash codes.  When used in conjunction with the start command it allows for the downloading of each hash to be spread more easily between compute nodes 

Repo Link: https://github.com/samw430/git-all-scala

Example command in terminal to run program:
cd git-all-scala
sbt compile
sbt "run-main allCommits --url https://github.com/gkthiruvathukal/metrics-dashboard-bash-scala.git  --src metrics-dashboard-bash-scala --dst oddmetrics --start 1 --stride 2"
