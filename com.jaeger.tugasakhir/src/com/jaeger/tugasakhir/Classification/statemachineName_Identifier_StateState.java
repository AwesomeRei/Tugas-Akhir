
package com.jaeger.tugasakhir.Classification;


public class statemachineName_Identifier_StateState
    extends statemachineClassificationState
{
    private final static statemachineName_Identifier_StateState instance = new statemachineName_Identifier_StateState();

    /**
     * Protected Constructor
     */
    protected statemachineName_Identifier_StateState() {
        setName("Name_Identifier_State");
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
        action.doPrint("Entering Name Identifier");
    }

    /**
     * onExit
     */
    @Override
    public void onExit(statemachineContext context) {
        context.getObserver().onExit(context.getName(), this.getName());
    }

    /**
     * Event id: evBrackets
     */
    public void evBrackets(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Name_Identifier_State to Bracket_State triggered by evBrackets
        // The next state is within the context statemachineContext
        context.setTransitionName("evBrackets");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineBracket_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineBracket_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evCurlyBrackets
     */
    public void evCurlyBrackets(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Name_Identifier_State to Final_State triggered by evCurlyBrackets
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
        // Transition from Name_Identifier_State to Final_State triggered by evSemicolon
        // The next state is within the context statemachineContext
        context.setTransitionName("evSemicolon");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineFinal_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineFinal_StateState.getInstance());
        context.onEnd();
        return ;
    }

    /**
     * Event id: evExtends
     */
    public void evExtends(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Name_Identifier_State to Extends_State triggered by evExtends
        // The next state is within the context statemachineContext
        context.setTransitionName("evExtends");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineExtends_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineExtends_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evImplements
     */
    public void evImplements(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Name_Identifier_State to Implements_State triggered by evImplements
        // The next state is within the context statemachineContext
        context.setTransitionName("evImplements");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineImplements_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineImplements_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evEquals
     */
    public void evEquals(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Name_Identifier_State to Equal_State triggered by evEquals
        // The next state is within the context statemachineContext
        context.setTransitionName("evEquals");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineEqual_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineEqual_StateState.getInstance());
        return ;
    }
}
