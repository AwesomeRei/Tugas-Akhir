
package com.jaeger.tugasakhir.Classification;

import com.stateforge.statemachine.context.AbstractContext;

public class statemachineContext
    extends AbstractContext<statemachineClassificationState, statemachineContext>
{
    private ClassificationAction _action;

    /**
     * Context constructor
     */
    public statemachineContext(ClassificationAction action) {
        super();
        _action = action;
        setName("statemachineContext");
        setInitialState(statemachineInitial_StateState.getInstance());
    }

    public ClassificationAction getClassificationAction() {
        return _action;
    }

    /**
     * Enter the initial state
     */
    public void enterInitialState() {
        com.stateforge.statemachine.algorithm.StateOperation.walkTreeEntry(this, statemachineClassificationState.getInstance(), statemachineInitial_StateState.getInstance());
    }

    /**
     * Leave the current state
     */
    public void leaveCurrentState() {
        com.stateforge.statemachine.algorithm.StateOperation.walkTreeExit(this, this.getStateCurrent(), statemachineClassificationState.getInstance());
    }

    /**
     * Event evKeyword
     */
    public void evKeyword() {
        getStateCurrent().evKeyword(this);
    }

    /**
     * Event evClass
     */
    public void evClass() {
        getStateCurrent().evClass(this);
    }

    /**
     * Event evUnknown
     */
    public void evUnknown() {
        getStateCurrent().evUnknown(this);
    }

    /**
     * Event evBrackets
     */
    public void evBrackets() {
        getStateCurrent().evBrackets(this);
    }

    /**
     * Event evCurlyBrackets
     */
    public void evCurlyBrackets() {
        getStateCurrent().evCurlyBrackets(this);
    }

    /**
     * Event evSemicolon
     */
    public void evSemicolon() {
        getStateCurrent().evSemicolon(this);
    }

    /**
     * Event evExtends
     */
    public void evExtends() {
        getStateCurrent().evExtends(this);
    }

    /**
     * Event evImplements
     */
    public void evImplements() {
        getStateCurrent().evImplements(this);
    }

    /**
     * Event evEquals
     */
    public void evEquals() {
        getStateCurrent().evEquals(this);
    }

    /**
     * Event evNew
     */
    public void evNew() {
        getStateCurrent().evNew(this);
    }
}
