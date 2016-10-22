# WC Concurrent Problem
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Flag_of_Brazil.svg/2000px-Flag_of_Brazil.svg.png" align="right" width="100">

Pequeno projeto desenvolvido como trabalho prático sobre sincronização em programação concorrente.

<table class="tg">
  <tr>
    <th class="tg-yw4l">Aluno</th>
    <td class="tg-yw4l">Francisco Laércio de Morais</td>
  </tr>
  <tr>
    <th class="tg-yw4l">Disciplina</th>
    <td class="tg-yw4l">DIM0612 - Programação Concorrente</td>
  </tr>
  <tr>
    <th class="tg-yw4l">Professor</th>
    <td class="tg-yw4l">Everton Ranielly de Sousa Cavalcante</td>
  </tr>
</table>

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
    * Se uma pessoa do mesmo sexo das pessoas que estão no banheiro que chegar na fila, ela terá que esperar. Por exemplo:
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

----------

# WC Concurrent Problem
<img src="http://geo5.net/imagens/Bandeira-dos-Estados-Unidos-2000px.png" align="right" width="100">

A small project developed as a practice work about threads synchronization in concurrent programs.

<table class="tg">
  <tr>
    <th class="tg-yw4l">Student</th>
    <td class="tg-yw4l">Francisco Laércio de Morais</td>
  </tr>
  <tr>
    <th class="tg-yw4l">Discipline</th>
    <td class="tg-yw4l">DIM0612 - Programação Concorrente</td>
  </tr>
  <tr>
    <th class="tg-yw4l">Teacher</th>
    <td class="tg-yw4l">Everton Ranielly de Sousa Cavalcante</td>
  </tr>
</table>

## Problem
The problem is about a scenario with one bathroom, and men and women trying to use it randomly. The bathroom have a limited number of "slots", and him just can be used by more then one person if thoose people have the same sex (gender).

This problema can be maped to the classic problem of the producers and consumers.

## Approach

### Business rules
Tha priority implementation was defined using the following logic:

* If there's no one in the bathroom, the queue's head can go in
* If there's someone in the bathroom, but there are still "slots":
  * If the sex (gender) of the people who are inside of the bathroom is the same sex of the head, she can go in
  * If the sex (gender) of the people who are inside of the bathroom is different of the head's sex, she have to wait
  * If one person of the same sex of the people who are inside of the bathroom came to the queue, she have to wait. For example:
	  * There are two women in the bathroom, and the head of the queue is a man
	  * The man will have to wait until the two women leave for him can go to the bathroom
	  * If a third woman arrived when the man was still waiting, she's gonna stand behind him in the queue
	  * The third woman will have to wait the man use the bathroom for she can go to the bathroom
* If the bathroom is full:
	* of people of the same sex of the head of the queue, she gonna use the bathroom when at least one come out
	* of people of the opposite sex of the head of the queue, she gonna use the bathroom when all of they come out


![UFRN LOGO](http://www.quimica.ufrn.br/quimica/download/UFRN.png)
