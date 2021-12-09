<h1>Brick_Destroy</h1>
<h2>Description of the game</h2>
<p>
This is a simple arcade video game.
Player's goal is to destroy a wall made up of brick with a small ball.<br />
</p>
<hr>
<h2>Refactor Process</h2>
<p>
1. The classes in the original source folder had been <b>separated</b> and <b>move into different new packages</b> that related. <br />
&nbsp&nbsp&nbsp <b>-Reason:</b> To organize the class from the original folder well and to prevent confusion. The class is also can be easily found for maintaining and extending processes.
</p>
<p>
2. Renaming <b>class name</b> and <b>variable name</b><br />
&nbsp&nbsp&nbsp <b>-Reason:</b> Some of the class name and variables are meaningless and hard to understand. Therefore, changing the name and variable will allow others easily to understand when looking at the source code.
</p>
<p>
3. <b>Separate</b> Crack class from Brick class<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> Not all the sub-class from Brick class need to use the Crack class. If a brick needs to access it, they can just directly add it in.
</p>
<p>
4. Create <b>model, controller and view class</b> for HomeMenu and GameBoard in the existing source code.<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> It is because applying MVC pattern in the project, it will make the project more scalable, maintainable, and easy to expand.
                        Furthermore, all the View classes rely on the Controller for its existence in the project, which also known as composition relationship.
                        Therefore, I don't consider that my MVC implementation is proper and obeying the rule.
</p>
<p>
5. <b>Renaming</b> wall class to GameModel after applying MVC pattern.<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> The wall class contains the variables for updating the game and others. Therefore, the wall class is renamed and acts as GameModel class.
</p>
<p>
6. <b>Create</b> encapsulation field and <b>delete</b> unused variable<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> To easily use the private variable from other classes and delete unused variables will make the code clean and tidy.
</p>
<p>
7. <b>Implement</b> Factory design pattern for Brick class<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> To provide the best way to create the bricks classes object based on the type of brick passed from Levels class
</p>
<p>
8. <b>Add</b> maven to the project<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> Allow us to add JUNIT dependency to create test cases and build the project.
</p>
<p>
9. <b>Add</b> Levels class and move all the methods to generate the levels into it<br />
&nbsp&nbsp&nbsp <b>-Reason:</b> To easily add or modify the type of levels generated for the game.
</p>
<hr>
<h2>Additions</h2>
<p>
1. <b>Adding</b> high score and displaying it in the middle of the GameBoard <br />
</p>
<p>
2. <b>Create</b> a new type of brick which is Diamond brick and <b>increase</b> the number of levels with the new brick.<br />
&nbsp&nbsp&nbsp - Increase the type of levels to generate. <br />
</p>
<p>
3. <b>Adding</b> score implementation.<br />
</p>
<p>
4. <b>Add</b> bonus and penalty gameplay in the game.<br />
&nbsp&nbsp&nbsp - Different bonus scores will be awarded based on the ball lives left when moving to the next levels and deducting 50 points per life lost.<br />
</p>
<p>
5. <b>Create</b> and <b>add</b> an instruction page for showing the instruction of the game.<br />
</p>
<p>
6. <b>Adding</b> background images in HomeMenu pages and Instruction pages for decoration.<br />
</p>

Please Enjoy ;-)
