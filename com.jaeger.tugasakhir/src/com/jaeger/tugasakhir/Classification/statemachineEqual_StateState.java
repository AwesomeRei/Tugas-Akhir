
package com.jaeger.tugasakhir.Classification;


public class statemachineEqual_StateState
    extends statemachineClassificationState
{
    private final static statemachineEqual_StateState instance = new statemachineEqual_StateState();

    /**
     * Protected Constructor
     */
    protected statemachineEqual_StateState() {
        setName("Equal_State");
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
        action.doPrint("Entering Equal");
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
        // Transition from Equal_State to Name_Identifier_State triggered by evUnknown
        // The next state is within the context statemachineContext
        context.setTransitionName("evUnknown");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineName_Identifier_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineName_Identifier_StateState.getInstance());
        return ;
    }

    /**
     * Event id: evNew
     */
    public void evNew(statemachineContext context) {
        ClassificationAction action = context.getClassificationAction();
        // Transition from Equal_State to New_State triggered by evNew
        // The next state is within the context statemachineContext
        context.setTransitionName("evNew");
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionBegin(context, statemachineNew_StateState.getInstance());
        com.stateforge.statemachine.algorithm.StateOperation.processTransitionEnd(context, statemachineNew_StateState.getInstance());
        return ;
    }
}
