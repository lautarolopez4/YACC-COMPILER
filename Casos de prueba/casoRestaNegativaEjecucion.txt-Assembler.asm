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
@aux1 db ?  
_3 db 3
_30 db 30
_id_a_main db ?  
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
SUB AL, _30
JS errorResta
MOV @aux1, AL
MOV AL, @aux1
MOV _id_a_main, AL
invoke ExitProcess, 0
end start 
