JFLAGS=

# Recognize files with .class and .java extensions
.SUFFIXES: .class .java

# This is a rule to convert a file with .java extension
# into a file with a .class extension. The macro $< just
# supplies the name of the file (without the extension)
# that invoked this rule.
.java.class:
	javac $(JFLAGS) $<

all: re REDriver

re: re/RE.java re/REInterface.java

REDriver: 
	javac -cp ".:./CS361FA.jar" re/REDriver.java 
		
clean:
	cd re; rm -f *.class