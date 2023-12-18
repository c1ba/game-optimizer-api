# Game Optimizer

A desktop application which provides its users with files that improve their game performance depending on the user’s PC components. This app is made with the gamers in mind whose personal computers can’t play games at a performance good enough to offer a gameplay without lagging and that are willing to sacrifice the beauty of the graphics in the favor of a speedy performance.

## Database Structure:
### GAMES
-	Id (uuid)
-	Name (varchar)
-	Year_release (int)
-	Minimum_components_id (uuid)

### COMPONENTS
-	id (uuid)
-	Name (varchar)
-	Type (PROCESSOR, GRAPHICS_CARD, RAM)
-	Capacity (float)

### COMPONENT_COMBOS
-	Id (uuid)
-	Processor (uuid)
-	Graphics_card (uuid)
-	Ram (uuid)

### PERFORMANCE_FILES
-	Component_combo_id (uuid)
-	Game_id (uuid)
-	User_id (uuid)
-	Performance_fps_score (int)
-	Likes (int)
-	Dislikes (int)
-	Files (varchar[])

### USERS
-	Id (uuid)
-	Component_combo (uuid)
-	Username (varchar)
-	Password (varchar)
-	Role (USER, ADMIN)

### Business Requirements

1. As a Guest, I should have access to all available games.
2. As a Guest, I should be able to combine existing components and get a performance score based on the selected game, with a warning only for guests that these results are based on modifications only someone registered has access to download them.
3. As a Guest, I should be able to log in.
4. As an User, I should have my components’ data and my component combo posted in the database after login if it does not exist. In case any of the components does not exist, it should be added as well.
5. As an User, I should be able to post performance files as long as they are associated with my component combo.
6. As an Admin, I should be able to delete any existing component or component combo. Upon the component’s deletion, every other component combo along with the associated performance files shall be deleted as well.
7. As an Admin, I should be able to modify any performance file.
8. As an Admin, I should be able to delete any performance file.
9. As an Admin, I should be able to post performance files.
10. As an Admin, I should be able to modify any user as banned for a period of time along with a reason.

## MVP Features

1. As a Guest, I should have access to all available games.
2. As a Guest, I should be able to combine existing components and get a performance score based on the selected game, with a warning only for guests that these results are based on modifications only someone registered has access to download them.
3. As a Guest, I should be able to log in.
4. As an User, I should have my components’ data and my component combo posted in the database after login.
5. As an Admin, I should be able to delete any existing component or component combo. Upon the component’s deletion, every other component combo along with the associated performance files shall be deleted as well.


