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
@aux1 db "sentencia 3", 0
@aux2 db "sentencia 1", 0
@aux3 db "sentenciaa 2", 0
_3 db 3
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
invoke MessageBox, NULL, addr @aux1, addr @aux1, MB_OK
MOV AL, _30
CMP AL, _3
JLE etiqueta1
invoke MessageBox, NULL, addr @aux2, addr @aux2, MB_OK
etiqueta1: 
invoke MessageBox, NULL, addr @aux3, addr @aux3, MB_OK
invoke ExitProcess, 0
end start 
