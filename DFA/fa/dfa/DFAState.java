package fa.dfa;

import java.util.HashMap;

import fa.State;

/**
 * Representation of a DFA state.
 * Every state has a hash map that maps the transition 
 * from the current state on a given symbol
 * 
 * @author maddie, ravi
 *
 */
public class DFAState extends State {
	/** 
	 * Stores the current DFAState's transitions  
	 * 'Character' is the symbol from the alphabet, Character ∈ Σ. 
	 * 'DFAState' is the “to” state.
	 */
   private HashMap<Character,DFAState> transitions;

   /**
    * Constructor - Initialize the name of the state and the set of transitions
    * f				from the state
    * 
    * @param name The name(string) of the DFAState
    */
    public DFAState(String name) {
        this.name = name;
        transitions = new HashMap<Character,DFAState>();
    }


    /**
     * On receiving 'symb', the current DFAState transitions to the 
     * 'targ' DFAState
     * 
     * @param symb The symbol from the alphabet applied to the current DFAState
     * @param toState The "to" state the current DFAState transitions to on receiving 'symb'
     */
    public void toState(char symb, DFAState toState){
        transitions.put(symb,toState);
    }
    

    /**
     * Return a DFAState on receiving 'symb'. Returns null if no mapping present
     * 
     * @param symb The symbol from the alphabet applied to the current DFAState
     * @return The "to" DFAState from the current DFAState on receiving 'symb'
     */
    public DFAState getToState(char symb){
        return transitions.get(symb);
    }



}