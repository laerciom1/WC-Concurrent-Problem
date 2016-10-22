# WC-Concurrent-Problem
Pequeno projeto desenvolvido como trabalho prático sobre sincronização em programação concorrente.

## Problema
O problema trata-se de um cenário com um banheiro unissex, e homens e mulheres tentam aleatoriamente usá-lo. O banheiro tem um numero máximo de "vagas", e só pode ser usado por mais de uma pessoa se essas pessoas são do mesmo sexo.

Este problema pode ser mapeado para o clássico problema dos produtores e consumidores.

## Abordagem

### Regras de negócio
A implementação da prioridade foi definida da seguinte forma:

* Se não houver ninguém no banheiro, a cabeça da fila poderá entrar
* Se existem pessoas no banheiro, mas ainda há "vagas":
	* Se o sexo das pessoas que estão dentro do banheiro é o mesmo do sexo da cabeça da fila, ela deve entrar
	* Se o sexo das pessoas que estão dentro do banheiro é diferente do sexo da cabeça da fila, ele deve esperar
    * Se uma pessoa do mesmo sexo das pessoas que estão no banheiro que chegar na fila , ela terá que esperar. Por exemplo:
      * Existem duas mulheres no banheiro, e a cabeça da fila é um homem
      * O homem terá que esperar as duas mulheres saírem para poder entrar
      * Se uma terceira mulher chegar enquanto o homem está esperando, ela ficará atrás dele na fila
      * A terceira mulher terá que esperar o homem usar o banheiro para que possa entrar
* Se o banheiro está lotado
    * de pessoas do mesmo sexo da cabeça da fila, ela usará o banheiro assim que pelo menos uma sair do banheiro
    * de pessoas do sexo oposto ao da cabeça da fila, ela usará o banheiro assim que todos saiam do banheiro
    
### Classes

#### WC
> Thread responsável por, caso exista alguém na fila e vagas no banheiro que a cabeça da fila pode usar, fazer com que a cabeça da fila entre no banheiro. Além disso ela exibe no console a cada determinado intervalo de tempo o estado atual do cenário (pessoas na fila e pessoas dentro do banehiro).

#### Person
> Therad responsável por, a cada determinado intervalo de tempo (definida aleatoriamente em tempo de execução, baseado no sexo da pessoa representada), fazer com que a pessoa entre na fila. Para isso a pessoa não pode estar nem na fila e nem dentro do banheiro. São criádas várias instancias dessa classe, e cada uma representa uma pessoa, que fica indo ao banheiro de tempos em tempos. 

#### Action
> Thread responsável por coordenar a ação de entrar no banheiro e sair após um determinado intervalo de tempo (definida aleatoriamente em tempo de execução, baseado no sexo da pessoa representada). Quando a classe WC detecta que é possível que a cabeça da fila entre no banheiro, ela criar uma instância da classe Action, que fará com que essa pessoa entre e saia do banheiro.

![UFRN LOGO](http://www.quimica.ufrn.br/quimica/download/UFRN.png)
