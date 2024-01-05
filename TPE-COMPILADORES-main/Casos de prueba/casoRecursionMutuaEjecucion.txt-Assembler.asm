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
@aux3 db ?  
@aux2 db ?  
@aux1 db ?  
_3 db 3
_2 db 2
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

id_f2_main_f1:
MOV EAX, _ultimoLlamado 
CMP EAX, id_f1_main
JE errorRecursion 
MOV EAX, id_f1_main
MOV _ultimoLlamado, EAX 
call id_f1_main
MOV @aux2, AL
MOV AL, _3 
ret  

id_f1_main:
MOV EAX, _ultimoLlamado 
CMP EAX, id_f2_main_f1
JE errorRecursion 
MOV EAX, id_f1_main
MOV _ultimoLlamado, EAX 
call id_f2_main_f1
MOV @aux3, AL
MOV AL, _2 
ret  

start:
call id_f1_main
MOV @aux1, AL
invoke ExitProcess, 0
end start 
