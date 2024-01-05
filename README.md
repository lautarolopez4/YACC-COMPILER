# Compiler with YACC
## Introduction
This project focuses on designing and implementing a compiler, a crucial component in the translation process from a high-level language to executable code. The compiler was developed using the YACC tool, emphasizing key aspects of execution models, notations, regular and context-free grammars, as well as syntactic analysis techniques and code generation.

## Execution Models
The compiler is designed to support two main execution models: interpretation and compilation. Interpretation enables direct execution of the source code, while compilation generates standalone executable code.

## Lexical Analysis and Regular Grammars
The lexical analysis process was implemented using regular grammars, allowing for the identification and tokenization of elements in the source code. This was achieved through lexical analysis techniques that recognize specific patterns, facilitating the creation of a coherent lexical tree.

## Operator Precedence and Conflict Resolution
The compiler addresses operator precedence to ensure the correct interpretation of complex expressions. Furthermore, it manages shift-reduce and reduce-reduce conflicts to enhance the robustness of syntactic analysis.

## Use of Metacompilers and Code Generation
The project explores the use of metacompilers, particularly YACC, to automate the code generation process. The compiler translates the syntactic tree generated during syntactic analysis into executable code, leveraging YACC's capability to generate syntactic and semantic analyzers.

## Multiple Languages in Development
The development process involved the use of multiple programming languages, including C, Java, and ASM. Each language contributed to different aspects of the compiler's functionality, demonstrating a versatile and integrated approach to development.

## Intermediate Languages and Error Handling
To facilitate code generation, the concept of intermediate languages was introduced, providing an intermediate representation of the program before the final code generation. Additionally, error-handling mechanisms were implemented to improve the compiler's robustness and diagnostic capabilities.

## Learning Compiler Fundamentals with YACC
This project proved instrumental in grasping the fundamental concepts of compilers. While its object-oriented programming (OOP) execution might not have been optimal, its primary goal was to provide a clear and detailed understanding of the underlying basics in compiler construction. Working hands-on with YACC, grammars, lexical analysis, and parsing techniques allowed me to solidify my knowledge of the internal structure of compilers. Despite not excelling in OOP, the project laid a robust and practical foundation for tackling more complex challenges in language translation. It served as a valuable stepping stone for future developments in the compilation field.
