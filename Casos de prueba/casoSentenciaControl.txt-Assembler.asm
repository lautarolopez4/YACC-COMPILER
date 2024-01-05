.386 
.model flat, stdcall 
.stack 200h 
option casemap :none  
include \masm32\include\windows.inc 
include \masm32\include\kernel32.inc 
include \masm32\include\user32.inc 
include\masm32\include\masm32rt.inc 
dll_dllcrt0 PROTO C 
printf PROTO C : VARARG 
includelib \masm32\lib\kernel32.lib 
includelib \masm32\lib\masm32.lib 
includelib \masm32\lib\user32.lib 

.data 
_id_j_main db ?  
_5 db 5
_id_i_main db ?  
_3 db 3
_1 db 1
_30 db 30
msj_error_overflow_suma_flot db "Error: Overflow.", 0 
msj_error_resta_neg db "Error: La resta dio como resultado un numero negativo.", 0 
msj_error_recursion_mutua db "Error: Recursion mutua en invocacion de funciones .", 0 
_ultimoLlamado dd ? 
@aux_conversion dw ? 

.code
errorOverflow: 
invoke MessageBox, NULL, addr msj_error_overflow_suma_flot, addr msj_error_overflow_suma_flot, MB_OK
invoke ExitProcess, 1 
errorRecursion: 
invoke MessageBox, NULL, addr msj_error_recursion_mutua, addr msj_error_recursion_mutua, MB_OK
invoke ExitProcess, 1 
errorResta: 
invoke MessageBox, NULL, addr msj_error_resta_neg, addr msj_error_resta_neg, MB_OK
invoke ExitProcess, 1 

start:
MOV AL, _3
MOV _id_i_main, AL
JMP condition1 
etiqueta1: 
MOV AL, _3
MOV _id_j_main, AL
JMP condition2 
etiqueta2: 
MOV AL, _30
CMP AL, _3
JGE etiqueta3
JMP next2 
 JMP etiqueta4
etiqueta3:
JMP increment2 
 etiqueta4:
increment2: 
MOV AL, _5
ADD AL, _id_j_main
MOV _id_j_main, AL
condition2: 
MOV AL, _id_j_main
CMP AL, _30
JGE etiqueta2
next2: 
increment1: 
MOV AL, _1
ADD AL, _id_i_main
MOV _id_i_main, AL
condition1: 
MOV AL, _id_i_main
CMP AL, _30
JGE etiqueta1
next1: 
invoke ExitProcess, 0
end start 
