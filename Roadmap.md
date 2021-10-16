# From Paris to Berlin Roadmap

## Roadmap to v1.0.0

2021/10/16
- Cars try to move every random minute(s) between 1 and 5 minutes

2021/10/15
- Create 10 cars
- Coroutines to advance vehicles
- Tables contain the link between cities and complete network to destination

---

- Cars must have:
  - Start of Block timestamp
  - Current waiting time (can be updated)
  - Location
  - Model
  - Name
  - Circuit break must choose randomly between the last two options
  - If still blocked, player gets a block timestamp when applicable, and the blocking waiting time is raised for 1 minute.
- Make case
- Generate table of occurrences
- The 100th car is the players car
- The table contains programmed road blocks on minute time stamps
- Game lasts a maximum of 30 minutes
- Player car can only move every 10 seconds.
- Every circuit break adds 1 minute to downtime
- If an error is still returned, user will get delayed for 5 minutes
- Player chooses city to move to
- Error types are timeout, exceptions

---

2021/10/14
- List important cities between Paris and Berlin

---

2021/10/13
- Research Resilience4J
- Research Kystrix

---

- Establish different routes
- Create random roadblock
- Make Datamodel for roads