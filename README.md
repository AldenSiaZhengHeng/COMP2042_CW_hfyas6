<h1>Brick_Destroy</h1>
<h2>Description of the game</h2>
<p>
This is a simple arcade video game.
Player's goal is to destroy a wall made up of brick with a small ball.<br />
The gameController has  very simple command: <br />
1. SPACE start/pause the Game <br />
2. A move left the player <br />
3. D move right the player <br />
4. ESC enter/exit pause menu <br />
5. ALT+SHIFT+F1 open Debug console <br />
6. The gameController automatically pause if the frame loses focus <br />
</p>
<hr>
<h2>Refactor Process</h2>
<p>
1. The classes in the original source folder had been <b>separate</b> and <b>move into different new packages</b> that related. <br />
&nbsp&nbsp&nbsp <b>-Reason:</b> To organize the class from the original folder well and to prevent confusion. The class is also can be easily found for maintaining and extending process.
</p>
<p>
2. Renaming <b>class name</b> and <b>variable name</b><br />
&nbsp&nbsp&nbsp <b>-Reason:</b> Some of the class name and variables are meaningless and hard to understand from it. Therefore, changing the name and variable will allow others easily to understand when looking at the source code.
</p>
<p>
3. <b>Separate</b> Crack class from Brick class<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> Not all the sub class from Brick class need to use Crack class. If a brick need to access it, they can just directly add it in.
</p>
<p>
4. Create <b>model, controller and view class</b> for HomeMenu and GameBoard in the existing source code.<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> It is because by applying MVC pattern in the project, it will make the project more scalable, maintainable, and easy to expand.
                        Furthermore, all the View class rely on the Controller for its existence in the project, which also known as composition relationship.
                        Therefore, I don't consider that my MVC implementation is proper and obey the rule.
</p>
<p>
5. <b>Renaming</b> wall class to GameModel after applying MVC pattern.<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> The wall class containing the variables for updating the game and others. Therefore, the wall class is renamed and act as GameModel class.
</p>
<p>
6. <b>Create</b> encapsulation field and <b>delete</b> unused variable<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> To easily use the private variable from other classes and delete unused variable will make the code clean and tidy.
</p>
<p>
7. <b>Implement</b> Factory design pattern for Brick class<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> To provide a best way to create the bricks classes object based on the type of brick passed from Levels class
</p>
<p>
8. <b>Add</b> maven to the project<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> Allow us to add JUNIT dependency to create test case and build the project.
</p>
<hr>
<h2>Additions</h2>
<p>
1. <b>Adding</b> highscore and display it on the middle of the GameBoard <br />
2. <b>Create</b> new type of brick which is Diamond brick and <b>increase</b> the amount of levels with the new brick.<br />
3. <b>Adding</b> score implementation.
4. <b>Add</b> bonus and penalty gameplay in the game.<br />
5. Different bonus score will be given based on the ball lives left when move to next levels and deduct 50 points per lives lose.<br />
6. <b>Create</b> and <b>add</b> an instruction pages for showing the instruction of the game.<br />
7. <b>Adding</b> background images in HomeMenu pages and Instruction pages for decoration.<br />
</p>

Please Enjoy ;-)
