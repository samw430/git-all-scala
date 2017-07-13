if [ -d ".git/" ]; then
 echo "This is a git repo"
else
 echo "This is not a git repo"
fi

if [ -d "../temp" ]; then
 echo "temp/ folder exists"
else
 echo "temp/ folder does not exist"
fi

exit 0
