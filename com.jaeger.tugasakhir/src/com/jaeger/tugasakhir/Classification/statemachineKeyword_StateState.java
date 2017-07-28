
package com.jaeger.tugasakhir.Classification;


public class statemachineKeyword_StateState
    extends statemachineClassificationState
{
    private final static statemachineKeyword_StateState instance = new statemachineKeyword_StateState();

    /**
     * Protected Constructor
     */
    protected statemachineKeyword_StateState() {
        setName("Keyword_State");
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
        action.doPrint("Entering Keyword");
    }

    /**
     * onExit
     */
    @Override
    public void onExit(statemachineContext context) {
        context.getObserver().onExit(context.getName(), this.getName());
    }

    /**
     * Event id: evClass
     */
    public void evClass(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Keyword_State to Class_State triggered by evClass
        // The next state is within the context statemachineContext
        context.setTransitionName("evClass");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineClass_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineClass_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evUnknown
     */
    public void evUnknown(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Keyword_State to Name_Identifier_State triggered by evUnknown
        // The next state is within the context statemachineContext
        context.setTransitionName("evUnknown");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineName_Identifier_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineName_Identifier_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evKeyword
     */
    public void evKeyword(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Self transition triggered by evKeyword
        context.setTransitionName("evKeyword");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineKeyword_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineKeyword_StateState.getInstance());
        return ;
    }
}
