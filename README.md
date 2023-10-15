# Projeto  2  -  Programação Modular

Integrantes : Leandro Sena e Gabriel Galindo 

## Requisitos do Projeto

O XuBank é uma fintech emergente que busca um sistema modular e confiável para administrar as contas de seus clientes. Desenvolva um sistema de informação aplicando conceitos avançados de programação orientada a objetos aprendidos até hoje na disciplina. Dessa forma, seu sistema deve controlar as contas dos clientes, descontando mensalmente as taxas, impostos e coisas do tipo. O nosso banco não pode levar prejuízo, por exemplo, permitindo operações sem descontar as taxas e impostos.

### Requisitos dos clientes

- **Atributos:** nome, CPF e senha.
- **Tipos de cliente:** regular, gold e vip.
- **Todos os clientes** podem possuir múltiplos tipos de contas.

### Requisitos das operações realizadas pelos clientes

- **Consulta de saldo:** Pode consultar o saldo de qualquer uma de suas contas a qualquer momento.
- **Consulta de extrato:** Pode consultar o extrato das suas transações dos últimos 30 dias.
- **Depósito:** Pode depositar qualquer quantia em suas contas.
- **Saque:** Pode sacar até o limite de saldo disponível em sua conta-corrente. Para a conta poupança, o saque é limitado ao saldo atual. Para contas de renda fixa e investimento, os saques podem incorrer em penalidades ou impostos.
- **Transferências:** Pode realizar transferências entre suas contas e para contas de outros clientes, desde que não ultrapasse o saldo disponível.

### Tipos de conta

1. **Conta Corrente:**
    - Taxa mensal exclusiva de R$20.
    - Saque especial até R$200 além do saldo.
2. **Conta Poupança:**
    - Sem taxa mensal.
    - Rendimento fixo de 0,5% no dia 5 de cada mês.
3. **Renda Fixa:**
    - Sem taxa mensal.
    - Rendimento contratado no momento da criação ( veja a explicação no final).
    - Imposto de 15% sobre o rendimento no saque.
4. **Investimento:**
    - Rendimento diário (positivo ou negativo). Veja a explicação no final do documento.
    - Imposto de 15% e taxa de 1,5% sobre o rendimento no saque.

### Fidelidade de Clientes

1. **Clientes Gold:** Taxa mensal de R$10. Acumulam 10 pontos de fidelidade mensalmente e 10 pontos para cada R$1.000 de saldo.
2. **Clientes Vip:** Mensalidade de R$30. Acumulam 35 pontos mensalmente e 30 pontos a cada R$2.000 de saldo.
3. **Sistema de Recompensas:** Troca de pontos acumulados por prêmios, descontos em serviços ou conversão em dinheiro.

### Visão da Diretoria

1. Total em custódia para cada tipo de conta.
2. Saldo médio de todas as contas.
3. Número de clientes com saldo total negativo.
4. Clientes com maior e menor saldo total.
