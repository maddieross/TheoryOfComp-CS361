/**
 * 
 */
package fa.nfa;

import java.util.*;

/**
 * @author maddie, ravi
 *
 */
public class NFAState extends fa.State{

	/* NFAState transitions collection */
	private LinkedHashMap<Character, Set<NFAState>> delta;
	/* Indicates if a state is final or not */
    private boolean isFinal;
   
    /**
     * Default constructor
     * 
     * @param name The name of the state
     */
    public NFAState(String name){
    	this.name = name;
        delta = new LinkedHashMap<Character, Set<NFAState>>();
        isFinal = false;
    }

    /**
     * Constructor if state is final
     * 
     * @param name The name of the state
     * @param isFinal True - indicates a final state
     */
    public NFAState(String name, boolean isFinal){
    	this.name = name;
        delta = new LinkedHashMap<Character, Set<NFAState>>();
        this.isFinal = isFinal;
    }
    

    /**
     * Check if a state is final
     * 
     * @return True if final state else false
     */
    public boolean isFinal(){
        return isFinal;
    }


    /**
     * Adds transition
     * 
     * @param onSymb
     * @param toState
     */
    public void addTransition(char onSymb, NFAState toState){
        if(delta.containsKey(onSymb)){
            delta.get(onSymb).add(toState);
        }else{
            Set<NFAState> temp = new LinkedHashSet<>();
            temp.add(toState);
            delta.put(onSymb, temp);
        }
    }

    /**
     * Returns the set of transition states
     * 
     * @param onSymb The alphabet symbol
     * @return Set<NFAState> A set of states that the given state transitions to 
     * 						 on an alphabet symbol. Empty set if none.
     */
    public Set<NFAState> getTo(char onSymb){
        if(delta.containsKey(onSymb)){
            return delta.get(onSymb);
        }else{
            return new LinkedHashSet<>();
        }
    }
}