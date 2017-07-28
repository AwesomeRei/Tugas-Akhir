
package com.jaeger.tugasakhir.Classification;


public class statemachineInitial_StateState
    extends statemachineClassificationState
{
    private final static statemachineInitial_StateState instance = new statemachineInitial_StateState();

    /**
     * Protected Constructor
     */
    protected statemachineInitial_StateState() {
        setName("Initial_State");
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
        action.doPrint("Initial State");
    }

    /**
     * onExit
     */
    @Override
    public void onExit(statemachineContext context) {
        context.getObserver().onExit(context.getName(), this.getName());
    }

    /**
     * Event id: evKeyword
     */
    public void evKeyword(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Initial_State to Keyword_State triggered by evKeyword
        // The next state is within the context statemachineContext
        context.setTransitionName("evKeyword");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineKeyword_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineKeyword_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evClass
     */
    public void evClass(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Initial_State to Class_State triggered by evClass
        // The next state is within the context statemachineContext
        context.setTransitionName("evClass");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineClass_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineClass_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evExtends
     */
    public void evExtends(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Initial_State to Extends_State triggered by evExtends
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
        // Transition from Initial_State to Implements_State triggered by evImplements
        // The next state is within the context statemachineContext
        context.setTransitionName("evImplements");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineImplements_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineImplements_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evUnknown
     */
    public void evUnknown(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Initial_State to Name_Identifier_State triggered by evUnknown
        // The next state is within the context statemachineContext
        context.setTransitionName("evUnknown");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineName_Identifier_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineName_Identifier_StateState.getInstance());
        return ;
    }
}
