
package com.jaeger.tugasakhir.Classification;


public class statemachineBracket_StateState
    extends statemachineClassificationState
{
    private final static statemachineBracket_StateState instance = new statemachineBracket_StateState();

    /**
     * Protected Constructor
     */
    protected statemachineBracket_StateState() {
        setName("Bracket_State");
        setStateParent(statemachineClassificationState.getInstance());
    }

    /**
     * Get the State Instance
     */
    public static statemachineClassificationState getInstance() {
        return instance;
    }

    /**
     * onEntry
     */
    @Override
    public void onEntry(statemachineContext context) {
        context.getObserver().onEntry(context.getName(), this.getName());
        ClassificationAction action = context.getClassificationAction();
        action.doPrint("Entering Bracket");
    }

    /**
     * onExit
     */
    @Override
    public void onExit(statemachineContext context) {
        context.getObserver().onExit(context.getName(), this.getName());
    }

    /**
     * Event id: evUnknown
     */
    public void evUnknown(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Self transition triggered by evUnknown
        context.setTransitionName("evUnknown");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineBracket_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineBracket_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evKeyword
     */
    public void evKeyword(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Self transition triggered by evKeyword
        context.setTransitionName("evKeyword");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineBracket_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineBracket_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evCurlyBrackets
     */
    public void evCurlyBrackets(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Bracket_State to Final_State triggered by evCurlyBrackets
        // The next state is within the context statemachineContext
        context.setTransitionName("evCurlyBrackets");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineFinal_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineFinal_StateState.getInstance());
        context.onEnd();
        return ;
    }

    /**
     * Event id: evSemicolon
     */
    public void evSemicolon(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Bracket_State to Final_State triggered by evSemicolon
        // The next state is within the context statemachineContext
        context.setTransitionName("evSemicolon");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineFinal_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineFinal_StateState.getInstance());
        context.onEnd();
        return ;
    }
}
