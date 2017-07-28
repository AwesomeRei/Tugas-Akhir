
package com.jaeger.tugasakhir.Classification;


public class statemachineFinal_StateState
    extends statemachineClassificationState
{
    private final static statemachineFinal_StateState instance = new statemachineFinal_StateState();

    /**
     * Protected Constructor
     */
    protected statemachineFinal_StateState() {
        setName("Final_State");
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
        action.doPrint("Entering Final");
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
    }

    /**
     * Event id: evClass
     */
    public void evClass(statemachineContext context) {
    }

    /**
     * Event id: evUnknown
     */
    public void evUnknown(statemachineContext context) {
    }

    /**
     * Event id: evBrackets
     */
    public void evBrackets(statemachineContext context) {
    }

    /**
     * Event id: evCurlyBrackets
     */
    public void evCurlyBrackets(statemachineContext context) {
    }

    /**
     * Event id: evSemicolon
     */
    public void evSemicolon(statemachineContext context) {
    }

    /**
     * Event id: evExtends
     */
    public void evExtends(statemachineContext context) {
    }

    /**
     * Event id: evImplements
     */
    public void evImplements(statemachineContext context) {
    }

    /**
     * Event id: evEquals
     */
    public void evEquals(statemachineContext context) {
    }

    /**
     * Event id: evNew
     */
    public void evNew(statemachineContext context) {
    }
}
