# Site Clearing Simulation
A challenge code for job interview


Lets analize the requirements with **Gherkin** syntax which can be used in a **BDD** tools

```
  Feature: Clear the construction site  
  
  # Start Simulator 
  Scenario: A trainee starts the simulator  
  Given file site-map containing the map of the site  
  When trainee starts the simulator with the given site-map  
  Then the map is displayed  
  And the simulator is waiting for operation commands  
  
  # Initial position of Bulldozer
  Scenario: Bulldozer should be placed on position (0,0) at the initial stage
  When the simulator has started
  Then the bulldozer is located on (0,0)
  
  # Enter command quite
  Scenario: The simulator must terminate once trainee enter quite command
  When trainee run command 'quite'
  Then the simulator terminates
  
  # Navigate beyond the boundaries
  Scenario: The simulator must terminate when navigate beyond the bounderies
  Give the bulldozer placed on position (0,0)
  Given the bulldozer faced top
  When trainee run command 'advance' with one step
  Then the simulator terminates

  # Remove a protected tree
  Scenario: If the bulldozer remove a protected tree, the simulator must terminate
  Give the bulldozer placed on position (0,0)
  And the bulldozer is facing down
  Give Position (1,0) contains a protected tree
  When trainee run command 'advance' with one step
  Then the simulator terminates
  
  # Showing report after ending simulation
  Scenario: After ending simulation a report needs to be displayed
  Give the bulldozer placed on position (0,0)
  And the bulldozer is facing down
  Give Position (1,0) contains a protected tree
  When trainee enter command advance with one step
  Then the simulator terminates
  And display a table of costs
  
  # Enter command advance
  Scenario: Using command advance must drive the bulldozer n squares forward
  Give the bulldozer placed on position (0,0)
  And the bulldozer is facing down
  When trainee run command 'advance' for 4 steps 
  Then bulldozer will be placed on position (4,0)

  # Enter command left
  Scenario: Using command advance must drive the bulldozer n squares forward
  Give the bulldozer placed on position (2,2)
  And the bulldozer is facing down
  When trainee run command 'left' 
  Then bulldozer will be facing right 
  But bulldozer will remain on position (2,2)
  When trainee run command 'left' 
  Then bulldozer will be facing up 
  But bulldozer will remain on position (2,2)
  When trainee run command 'left' 
  Then bulldozer will be facing left 
  But bulldozer will remain on position (2,2)
  When trainee run command 'left' 
  Then bulldozer will be facing down 
  But bulldozer will remain on position (2,2)

  # Enter command right
  Scenario: Using command right must rotate the bulldozer 90 degree toward its right
  Give the bulldozer placed on position (2,2)
  And the bulldozer is facing down
  When trainee run command 'right' 
  Then bulldozer will be facing left 
  But bulldozer will remain on position (2,2)
  When trainee run command 'right' 
  Then bulldozer will be facing up 
  But bulldozer will remain on position (2,2)
  When trainee run command 'right' 
  Then bulldozer will be facing right 
  But bulldozer will remain on position (2,2)
  When trainee run command 'right' 
  Then bulldozer will be facing down 
  But bulldozer will remain on position (2,2)
 
  
```

