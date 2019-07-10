JFLAGS=

# Recognize files with .class and .java extensions
.SUFFIXES: .class .java

# This is a rule to convert a file with .java extension
# into a file with a .class extension. The macro $< just
# supplies the name of the file (without the extension)
# that invoked this rule.
.java.class:
	javac $(JFLAGS) $<

all: interface state dfa nfa

interface: fa/FAInterface.class fa/dfa/DFAInterface.class fa/nfa/NFAInterface.class

state: fa/State.class fa/dfa/DFAState.class fa/nfa/NFAState.class

dfa: fa/dfa/DFA.class

nfa: fa/nfa/NFA.class fa/nfa/NFADriver.class
		
clean:
	cd fa; rm -f *.class
	cd fa/dfa; rm -f *.class
	cd fa/nfa; rm -rf *.class
