# Projeto de Jogos

## Visão Geral

Este projeto é um jogo baseado em Java "Freeze Monster", utilizando o Sprite Framework.

Ele utiliza o padrão de design Factory Method para criar instâncias de objetos do jogo.

Desenvolvido para a conclusão da disciplina de Princípios e Padrões de Projeto na Faculdade de Computação.

## Padrão Factory Method

O padrão Factory Method é utilizado para criar instâncias de objetos do jogo sem especificar a classe exata do objeto a ser criado, promovendo flexibilidade e reutilização do código.

### Classes Principais

* **`AbstractBoard`** : Define métodos abstratos `createPlayers` e `createPlayer`.
* **`FreezeMonsterBoard`** : Implementa `createPlayer` para criar uma instância de `Woody`.
* **`MainFrame`** : Define o método abstrato `createBoard`.
* **`FreezeMonsterGame`** : Implementa `createBoard` para criar uma instância de `FreezeMonsterBoard`.

### Freeze Monster

No jogo Freeze Monster, o jogador controla o personagem Woody para navegar pelo tabuleiro, evitando ou interagindo com vários sprites como raios congelantes, gosmas e bombas.

#### Como Executar

1. Clone o repositório para sua máquina local.
2. Abra o projeto no Visual Code
3. Certifique-se de que todas as dependências estão resolvidas.
4. Execute a classe `FreezeMonsterGame` para iniciar o jogo Freeze Monster.

#### Controles

* **Setas do Teclado** : Movem Woody para cima, baixo, esquerda ou direita.
* **Barra de Espaço** : Dispara gelo (ataque).

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
