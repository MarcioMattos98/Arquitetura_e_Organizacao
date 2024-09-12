.data
prompt_casos: .asciiz "Digite o numero de casos de teste: "
prompt_vagoes: .asciiz "Digite o numero de vagoes: "
prompt_ordem: .asciiz "Digite a ordem dos vagoes: "
resultado: .asciiz "Numero de trocas: "
nova_linha: .asciiz "\\n"

.text
principal:
    # Leitura do número de casos de teste
    li $v0, 4
    la $a0, prompt_casos
    syscall

    li $v0, 5
    syscall
    move $t0, $v0  # $t0 = N (número de casos de teste)

loop_casos:
    beqz $t0, fim_casos  # Se N for 0, sair do loop

    # Leitura do número de vagões
    li $v0, 4
    la $a0, prompt_vagoes
    syscall

    li $v0, 5
    syscall
    move $t1, $v0  # $t1 = L (número de vagões)

    # Alocar espaço para os vagões
    li $t2, 0
    sll $t3, $t1, 2  # $t3 = L * 4 (espaço necessário)
    li $v0, 9
    move $a0, $t3
    syscall
    move $s0, $v0  # $s0 aponta para o array de vagões

    # Leitura da ordem dos vagões
    li $t4, 0
leitura_vagoes:
    beq $t4, $t1, ordena_vagoes

    li $v0, 5
    syscall
    mul $t5, $t4, 4
    add $t6, $s0, $t5
    sw $v0, 0($t6)

    addi $t4, $t4, 1
    j leitura_vagoes

ordena_vagoes:
    # Inicializa o contador de trocas
    li $s1, 0  # $s1 = contador de trocas

    li $t7, 0
loop_externo:
    beq $t7, $t1, imprime_resultado
    li $t8, 0
loop_interno:
    sub $t9, $t1, $t7  # Substitui $t10
    sub $t9, $t9, 1
    beq $t8, $t9, fim_loop_externo

    mul $s2, $t8, 4  
    add $s3, $s0, $s2  
    lw $s4, 0($s3)   
    lw $s5, 4($s3)   
    
    ble $s4, $s5, pula_troca  # Pula a troca se estiverem na ordem correta

    # Troca os elementos
    sw $s5, 0($s3)   # Coloca o próximo valor na posição atual
    sw $s4, 4($s3)   # Coloca o valor atual na próxima posição
    addi $s1, $s1, 1   # Incrementa o contador de trocas

pula_troca:
    addi $t8, $t8, 1
    j loop_interno

fim_loop_externo:
    addi $t7, $t7, 1
    j loop_externo

imprime_resultado:
    # Imprimir o número de trocas
    li $v0, 4
    la $a0, resultado
    syscall

    li $v0, 1
    move $a0, $s1
    syscall

    li $v0, 4
    la $a0, nova_linha
    syscall

    # Reduzir o contador de casos de teste e repetir o loop
    sub $t0, $t0, 1
    j loop_casos

fim_casos:
    # Finalizar o programa
    li $v0, 10
    syscall
