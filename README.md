READ ME

Este projeto implementa um simulador de transferência de dados utilizando classes Java para modelar os componentes principais de um sistema de transmissão de dados. O projeto tem como objetivo simular a transmissão, recepção e manipulação de dados entre dois sistemas, demonstrando conceitos relacionados à comunicação digital e redes de computadores.

Arquivos Principais

1.	Transmissor.java
Esta classe é responsável por simular o componente transmissor do sistema de comunicação. Ela envia dados para o receptor por meio de uma interface de transferência de dados.
2.	Receiver.java
Uma classe Receptor que representa o componente receptor do sistema de comunicação. Ela recebe os dados transmitidos pelo Transmissor e os processos em conformidade com as regras definidas pelo protocolo de comunicação.
3.	TransferenciaDadosTP.java
Esta classe serve como uma interface de transferência de dados entre o transmissor e o receptor. Ela encapsula a lógica necessária para realizar a comunicação, incluindo possíveis simulações de erros, atrasos ou outros comportamentos típicos de uma rede de dados.
Estrutura do Projeto
O projeto é composto por três classes principais, cada uma com responsabilidades bem definidas no processo de transmissão de dados:

•	Transmissor:

o	Prepare e envie os dados.
o	Defina o formato dos dados que serão enviados.
o	Possui métodos para tratar erros ou simular perda de pacotes, se aplicável.

•	Receptor:

o	Recebe os dados enviados pelo transmissor.
o	Realiza a validação dos dados recebidos.
o	Pode aplicar correções de erros ou notificar falhas na transmissão.

•	‘TransferenciaDadosTP’:

o	Intermediário a comunicação entre o transmissor e o receptor.
o	Pode incluir mecanismos para simular condições de rede, como latência ou ruído.
Como Executar
1.	Certifique-se de ter o Java Development Kit (JDK) instalado.
2.	Compile os arquivos ‘.java’ usando o comando:
javac Transmissor.java Receptor.java TransferenciaDadosTP.java
3.	Execute o programa principal, que provavelmente está na classe ‘TransferenciaDadosTP’:
java TransferenciaDadosTP
4.	O sistema segue a simulação da transmissão e recepção de dados entre os componentes.

Funcionalidades Adicionais

•	Simulação de erros de transmissão.
•	Simulação de atrasos ou perdas de pacotes.
•	Tratamento de protocolo de correção de erros.

Contribuições

Este projeto foi desenvolvido para fins educacionais, permitindo a visualização e simulação de um sistema básico de comunicação de dados.


