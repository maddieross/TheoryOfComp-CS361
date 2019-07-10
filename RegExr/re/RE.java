package re;

import fa.State;
import fa.dfa.DFA;
import fa.nfa.NFA;
import fa.nfa.NFAState;

/**
 * This projects seeks to construct an NFA for a given regular expression
 * (RegEx) using recursive descent parsing
 *
 * @author maddie, ravi
 */
public class RE implements REInterface {

	private String regEx; //RegEx input
	private int stateCount = 0; 

	/**
	 * Default constructor
	 *
	 * @param regEx The regular expression 
	 */
	public RE(String regEx) {
		this.regEx = regEx;
	}


	@Override
	public NFA getNFA() {
		return regex();
	}

	/* Recursive descent parsing internals. */

	/**
	 * Return the next item of the regular expression without consuming it
	 *
	 * @return char The next character of the regular expression
	 */
	private char peek() {
		return regEx.charAt(0);
	}

	/**
	 * Consumes the next item of the regular expression failing if not equal to 'c'
	 * 
	 * @param c The character to be compared with
	 */
	private void eat(char c) {
		if (peek() == c){
			regEx = regEx.substring(1);
		}else{
			throw new RuntimeException("Expected: " + c + "; got: " + peek());
		}
	}


	/**
	 * Returns the next item of input and consumes it
	 * 
	 * @return char The next character of the regular expression
	 */
	private char next() {
		char c = peek();
		eat(c);
		return c;
	}
	

	/**
	 * Check if there are any more characters
	 * 
	 * @return boolean True if there are any more characters else False
	 */
	private boolean more() {
		return regEx.length() > 0;
	}


	/* Regular expression term types. */

	/**
	 * Must parse at least one term, and whether we parse another depends only
	 * on what we find afterward
	 * 
	 * @return NFA
	 */
	private NFA regex() {
		NFA term = term();

		if (more() && peek() == '|') {
			eat('|');
			return union(term, regex());
		}else{
			return term;
		}
	}
	
	
	/**
	 * Perform a union of two NFAs
	 * 
	 * @param nfa
	 * @param nfa2
	 * @return NFA The union of two NFAs
	 */
	public NFA union(NFA nfa, NFA nfa2){
		//System.out.println("union");
		NFAState nfaState = (NFAState) nfa.getStartState();
		NFAState nfaState2 = (NFAState) nfa2.getStartState();

		nfa.addNFAStates(nfa2.getStates());
		nfa.addAbc(nfa2.getABC());

		String startState = Integer.toString(stateCount++);
		nfa.addStartState(startState);
		String finalState = Integer.toString(stateCount++);
		nfa.addFinalState(finalState);

		nfa.addTransition(startState, 'e', nfaState.getName());
		nfa.addTransition(startState, 'e', nfaState2.getName());  

		for(State s:nfa2.getFinalStates()){
			NFAState state = (NFAState)s;
			state.setNonFinal();
			nfa.addTransition(state.getName(), 'e', finalState);
		}
		return nfa; 
	}

	/**
	 * Check that it has not reached the boundary of a term or the end of the
	 * input
	 * 
	 * @return NFA
	 */
	private NFA term() {
		NFA factor = new NFA(); 
		
		factor.addStartState(Integer.toString(stateCount++));
		String finalstate = Integer.toString(stateCount);
		factor.addFinalState(Integer.toString(stateCount++));
		factor.addTransition(factor.getStartState().getName(), 'e', finalstate);

		while (more() && peek() != ')' && peek() != '|') {
			NFA nextFactor = factor();
			factor = concatenate(factor, nextFactor); // factor = new Sequence(factor,nextFactor) ;
		}

		return factor;
	}

	
	/**
	 * Concatenate two NFAs
	 * 
	 * @param nfa
	 * @param nfa2
	 * @return NFA The concatenated NFA
	 */
	public NFA concatenate(NFA nfa,NFA nfa2){
		//System.out.println("concat");
		nfa.addAbc(nfa2.getABC());
		for(State s:nfa.getFinalStates()){
			NFAState state = (NFAState)s;
			state.setNonFinal();
			state.addTransition( 'e', (NFAState)nfa2.getStartState());
		}
		nfa.addNFAStates(nfa2.getStates());
		return nfa;
	}
	
	
	/**
	 * Parse a base and then any number of Kleene stars
	 *
	 * @return NFA
	 */
	private NFA factor() {
		NFA base = base() ;
		while (more() && peek() == '*') {
			eat('*') ;
			base =  repetition(base) ;
		}
		return base ;
	}

	
	/**
	 * Returns the NFA if an '|' (OR) symbol is found
	 * 
	 * @param base
	 * @return NFA
	 */
	public NFA repetition(NFA base){
		NFAState nfaState = (NFAState) base.getStartState();
		for(State nfa : base.getFinalStates()){
			base.addTransition(nfa.getName(), 'e', nfaState.getName());
		}

		String state = Integer.toString(stateCount++);
		base.addStartState(state);
		base.addFinalState(state);
		base.addTransition(state, 'e', nfaState.getName());
		return base;
	}

	
	/**
	 * Check for opening parethesis else returns the 
	 * next primitive (NFA from a given symbol)
	 * 
	 * @return NFA
	 */
	private NFA base() {
		switch (peek()) {
		case '(':
			eat('(');
			NFA nfa = regex();
			eat(')');
			return nfa;

		default:
			return primitive(next());
		}
	}


	/**
	 * Returns an NFA from a given symbol
	 * 
	 * @param c
	 * @return NFA
	 */
	public NFA primitive(char c){
		NFA nfa = new NFA();

		String startState = Integer.toString(stateCount++);
		nfa.addStartState(startState);

		String finalState = Integer.toString(stateCount++);
		nfa.addFinalState(finalState);

		nfa.addTransition(startState, c, finalState);

		return nfa;
	}
}