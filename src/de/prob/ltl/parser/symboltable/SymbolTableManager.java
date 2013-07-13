package de.prob.ltl.parser.symboltable;

import java.util.Stack;

import de.prob.ltl.parser.semantic.PatternDefinition;
import de.prob.ltl.parser.semantic.Variable;

public class SymbolTableManager {

	private final SymbolTable globalScope = new SymbolTable();
	private final Stack<SymbolTable> scopeStack = new Stack<SymbolTable>();

	public SymbolTableManager() {
		scopeStack.push(globalScope);
	}

	public void pushScope(SymbolTable scope) {
		scopeStack.push(scope);
	}

	public void popScope() {
		if (scopeStack.size() > 1) {
			scopeStack.pop();
		}
	}

	public SymbolTable getCurrentScope() {
		return scopeStack.peek();
	}

	public SymbolTable getGlobalScope() {
		return globalScope;
	}

	// Delegates

	/**
	 * Defines a variable
	 * 
	 * @param var
	 * @return false, if a variable with the same name was already defined; otherwise true
	 */
	public boolean define(Variable var) {
		return getCurrentScope().define(var);
	}

	/**
	 * Defines a pattern
	 * 
	 * @param pattern
	 * @return false, if a pattern with the same name was already defined
	 * or this symbol table is not the root; otherwise true
	 */
	public boolean define(PatternDefinition pattern) {
		return getCurrentScope().define(pattern);
	}

	/**
	 * Resolves a defined variable by name
	 * 
	 * @param name
	 * @return Variable with this name; null, if no variable was found with this name
	 */
	public Variable resolveVariable(String name) {
		return getCurrentScope().resolveVariable(name);
	}

	/**
	 * Resolves a defined pattern by name
	 * 
	 * @param name
	 * @return Pattern with this name; null, if no pattern was found with this name
	 */
	public PatternDefinition resolvePattern(String name) {
		return getCurrentScope().resolvePattern(name);
	}

	/**
	 * Checks whether a variable with the given name was defined
	 * 
	 * @param name
	 * @return true, if variable with the given name was defined; otherwise false
	 */
	public boolean isDefinedVariable(String name) {
		return getCurrentScope().isDefinedVariable(name);
	}

	/**
	 * Checks whether a pattern with the given name was defined
	 * 
	 * @param name
	 * @return true, if pattern with the given name was defined; otherwise false
	 */
	public boolean isDefinedPattern(String name) {
		return getCurrentScope().isDefinedPattern(name);
	}

}