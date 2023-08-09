# My Personal Project

## Fitness System

### Features

This application will provide users with a platform to aid 
their fitness journey. Features of this program will
include:
- recording **calorie intake**
- recording day to day **workout sets**

This platform will be useful to anyone who would like to better manage
their macronutrients and execute progressive overload at the gym. I am 
passionate about this project because I believe recording reps and sets
can be very beneficial in ensuring consistency while resistance training,
and I have yet to find a system that I enjoy using. Thus, I would like to
create one!

### User Stories

- As a user, I want to be able to add a new workout day to my collection 
- As a user, I want to be able to add a new exercise to my workout
- As a user, I want to be able to add a new meal day to my collection
- As a user, I want to be able to add a new food to my meal
- As a user, I want to be able to remove a workout from my database by date
- As a user, I want to be able to remove a meal day from my database by date
- As a user, I want to be able to view a workout from my database by date
- As a user, I want to be able to view a meal day from my database by date
- As a user, when I select the quit option from the application menu, I want to be reminded to save my fitness data
to file and have the option to do so or not. 
- As a user, when I start the application, I want to be given the option to load my fitness data from file.

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by 
  - going to the log food panel 
  - entering a date for the meal
  - adding a food item to said meal
- You can generate the second required event related to adding Xs to a Y by 
  - going to the add workout panel
  - entering a date for the workout
  - adding a exercise to the workout
  - entering the amount of sets to the exercise
- You can locate my visual component on the sidebar after clicking to load data or start a new data file.
- You can save the state of my application by clicking save in the menu bar, or the application will prompt user to 
save the application when the application is quit
- You can reload the state of my application by clicking one of the options on the initial load window.


# Phase 4: Task 2
Sample of events that can occur in the program: <br>

Tue Aug 08 23:34:36 PDT 2023 <br>
New sets for Lunges logged.

Tue Aug 08 23:34:36 PDT 2023 <br>
Lunges added to workout on 2023-08-08.

Tue Aug 08 23:34:37 PDT 2023 <br>
New workout on 2023-08-08 logged.

Tue Aug 08 23:34:46 PDT 2023 <br>
Apples logged for meal on 2023-09-09.

Tue Aug 08 23:34:47 PDT 2023 <br>
New meal for 2023-09-09 logged.

Tue Aug 08 23:34:49 PDT 2023 <br>
Meal on 2023-09-09 removed.

Tue Aug 08 23:34:52 PDT 2023 <br>
Workout on 2023-08-08 removed.

# Phase 4: Task 3
One improvement of this code would be the simplification of the meal and workout classes. Instead of having a date and
an ArrayList of logs, I could implement a HashMap, using the date as a key. This would eliminate the need for said
classes, allowing the AllWorkout and AllMeals classes to contain the newly implemented HashMaps.

Furthermore, I would improve the cohesion of my program. For instance, the FitnessInterface class handles the sidebar,
menu bar, and all the buttons associated with it. I realize now that all of these components could be refactored into
separate classes, all instantiated in the FitnessInterface class. In general, the classes in my program handle the
actions incited by the buttons, but these functionalities should be separated into another class. Moreover, the main
FitnessInterface frame is passed throughout the entire GUI to refresh with any change of the data. This could be
avoided by improving my type hierarchy or implementing a design pattern, such as the observer pattern.