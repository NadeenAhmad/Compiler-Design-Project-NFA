# Compiler-Design-Project-NFA  
The aim of this project is to implement the classical algorithm for constructing a deterministic
finite automaton (DFA) equivalent to a non-deterministic finite automaton (NFA).  
An NFA is a quintuple (Q, Σ, δ, q0, F):
* Q is a non-empty
* finite set of states; Σ is non-empty,
* finite set of symbols (an alphabet); δ : Q × (Σ ∪ {ε}) −→ P(Q) is the transition function;
* q0 ∈ Q is the start state
* F ⊆ Q is the set of accept states
Given a description of an NFA, the implemented functions construct an equivalent DFA.  
• The project is implemented in Java
• Assumptions:  
a) The alphabet Σ is always the binary alphabet {0, 1}.  
b) The set of states Q is always of the form {0, . . ., n}, for some n ∈ N.  
c) The start state is always state 0.  
• A string describing an NFA is of the form Z#O#E#F, where Z, O, and E, respectively,
represent the 0-transitions, the 1-transitions, and the ε-transitions. F represents the set
of accept state.
• Z, O, and E are semicolon-separated sequences of pairs of states; each pair is a commaseparated sequence of two states. A pair i, j represents a transition from state i to state
j; for Z this means that δ(i, 0) = j, similarly for O and E.  
• F is a comma-separated sequence of states.  
• For example:  
• Input NFA: 0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3  
<img src="https://i.ibb.co/rck4X4J/NFS.jpg"  width="500" >
• Output DFA: 0,0,012;012,02,0123;02,0,0123;0123,023,0123;023,03,0123;03,03,0123#0123,023,03
<img src="https://i.ibb.co/CM7gJPw/dfa.jpg"  width="400" >


