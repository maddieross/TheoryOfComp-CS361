JFLAGS=

# Recognize files with .class and .java extensions
.SUFFIXES: .class .java

# This is a rule to convert a file with .java extension
# into a file with a .class extension. The macro $< just
# supplies the name of the file (without the extension)
# that invoked this rule.
.java.class:
	javac $(JFLAGS) $<

all: interface state dfa

interface: fa/FAInterface.class fa/dfa/DFAInterface.class

state: fa/State.class fa/dfa/DFAState.class

dfa: fa/dfa/DFA.class fa/dfa/DFADriver.class
		
clean:
	cd fa; rm -f *.class
	cd fa/dfa; rm -f *.class