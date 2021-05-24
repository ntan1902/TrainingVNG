# Factory Method Design Pattern
## 1. Definition:
- The Factory Method defines the interface to create objects, but let subclasses decide which class to instantiate.
- It's like a "virtual" constructor. When the client want to create the instance, they don't have to use the "new" key word (e.g `Animal animal = new Animal()`).
- It's a creational pattern.


## Example:
- Let's assume we create a game called Plane Fighting.

- This game will have 2 type enemy:
  - Low-level enemy: Include `Plane` and `Tank`.
  - Boss enemy: Include `Dragon`.
    
- Both types will implement the interface called `Enemy`.  

- The game has 2 level. 
  - In level 1, game will create random 5 low-level enemies (not include boss enemy).
  - In level 2, game will create only 1 boss enemy.
- In this situation, let's use `Simple Factory` to solve it.    
### Diagram of Simple Factory:<br/>
  ![img_13.png](img_13.png)  
- Interface:<br/>
  ![img_1.png](img_1.png)
  
- Enum:<br/>
  ![img_2.png](img_2.png)
  
- Sub classes:<br/>
  ![img_3.png](img_3.png)<br/>
  ![img_4.png](img_4.png)<br/>
  ![img_5.png](img_5.png)
  
- Factory class:<br/>
  ![img_6.png](img_6.png)
  
- In client, we will create enemies basing on 2 level.
  - Level 1:<br/>
    - Create 5 random enemies.<br/>
      ![img_11.png](img_11.png)
    - createRandomEnemies() function.<br/>
      ![img_9.png](img_9.png)
  
  - Level 2:<br/>
    - Create boss enemy.<br/>
      ![img_12.png](img_12.png)
    - createBoss() function.<br/>
      ![img_10.png](img_10.png)
      

- That's it for 2 level in this game. However, what if the game has the third level?
- Let's assume that the level 3 will create random 5 enemies and only 1 boss enemy in the same time.
- Then in the client, we will implement something like this.
  ![img_7.png](img_7.png)
  
- Let's imagine that we have to implement these levels in several places. After that, whenever there are some changes, we have to change every place in our project.
- To solve that situation, we will use `Factory Method` for refactoring.

### Diagram of Factory Method:<br/>
  ![img_16.png](img_16.png)