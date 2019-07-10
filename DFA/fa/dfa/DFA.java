package fa.dfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.FAInterface;
import fa.State;


/**
 * A DFA implementation using java collections data structures
 * Every DFA has a start state, a set of final states, a set of 
 * states and a set containing its alphabet
 * 
 * @author maddie, ravi
 *
 */
public class DFA implements DFAInterface, FAInterface{
	
	/* Set of states of the DFA */
	private Set<DFAState> states;
	/* Set of final states of the DFA */
	private Set<DFAState> finalStates;
	/* The start state of the DFA */
	private DFAState startState;
	/* The alphabet of the DFA */
	private Set<Character> alphabet;

	/**
	 * Default constructor - initialize member variables
	 */
	public DFA(){
		startState = null;
		states = new LinkedHashSet<>();
		finalStates = new LinkedHashSet<>();
		alphabet = new LinkedHashSet<>();
	}

	@Override
	public void addStartState(String name) {
		/* First check if the start state has already been added to the set */
		/* This basically checks if the start state is also the final state */
		for(DFAState d : states) {
			if(d.getName().equals(name)) {
				startState = d;
				return;
			}
		}
		/* Else create a new DFAState */
		startState = new DFAState(name);
		/* Add it to the set of states */
		states.add(startState);
	}

	@Override
	public void addState(String name) {
		/* Create a new DFAState and add it to the set of states */
		states.add(new DFAState(name));		
	}

	@Override
	public void addFinalState(String name) {
		/* Create a new DFAState */ 
		DFAState finalState = new DFAState(name);
		/* Add it to the set of states and the set of final states */
		states.add(finalState);
		finalStates.add(finalState);
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		/* Add the symbol to the alphabet set */
		alphabet.add(onSymb); 
		
		/* Iterate over the set of states and find the fromState */
		for (DFAState currentState : states) {
			if(currentState.getName().equals(fromState)) {
				/* Add the transition to the state's transition set */
				currentState.toState(onSymb, getDfaState(toState));
			}
		}
	}

	/**
	 * Given a String representation of a state, return the corresponding DFAState
	 * 
	 * @param stateName
	 * @return
	 */
	public DFAState getDfaState(String stateName) {
		for(DFAState d: states) {
			if(d.getName().equals(stateName)) {
				return d;
			}
		}
		return null;
	}


	@Override
	public Set<? extends State> getStates() {
		return states;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		return finalStates;
	}

	@Override
	public State getStartState() {
		return startState;
	}

	@Override
	public Set<Character> getABC() {
		// TODO Auto-generated method stub
		return alphabet;
	}

	@Override
	public boolean accepts(String s) {
		DFAState currState = startState; 	
		for (int i = 0; i < s.length(); i++) {
			currState = getToState(currState, s.charAt(i));
		}
		if(finalStates.contains(currState)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public DFAState getToState(DFAState from, char onSymb){
		return from.getToState(onSymb);
	}

	
	/**
     * Textual representation of the DFA
     * Q = { a b }
     * Sigma = { 0 1 }
     * delta =	
     *          		0		1
     *     		a		a		b
     *     		b		a		b
     * q0 = a
     * F = { b }
     *
     * @return string representation of the DFA
     */
    public String toString() {
        StringBuilder build = new StringBuilder();
        
        //Create Q
        build.append("Q =  { ");
        for (DFAState s : states) {
            build.append(s.getName() + " ");
        }
        build.append("}\n");

        //Create Sigma
        build.append("Sigma = { ");
            for (Character a : alphabet ) {
                build.append(a + " ");
            }
        build.append("}\n" );
        
        //Create delta
        build.append("delta =  \n" );
        build.append("\t\t");
        //Set of symbols
        for (Character a : alphabet ) {
            build.append(a.toString() + "\t");
        }
        build.append("\n");
        //Set of states
        for(DFAState s : states) {
        	build.append("\t" + s.getName());
        	//States on symbol
        	for(Character a : alphabet) {
        		build.append("\t" + s.getToState(a));
        	}
        	build.append("\n");
        }
        
        //Start state
        build.append("q0 = { ");
        build.append(startState.getName() + " }\n");
       
        //Final states
        build.append("F = { ");
        for (DFAState f : finalStates ) {
            build.append(f + " ");
        }
        build.append("}\n");
        
        //Print DFA
        return build.toString();
    }


}