# From Paris to Berlin Roadmap

## Roadmap to v1.0.0

- Every circuit break adds 5 minutes to downtime
- A circuit break timeout adds the timeout on top of the 5 minute delay

---

2021/10/16
- Create random roadblock
- Establish different routes
- Player car can only move every 1 minute.
- The table contains programmed road blocks on minute time stamps
- The 10th car is the players car
- Generate table of occurrences
- Cars try to move every random minute(s) between 1 and 5 minutes
- Start of Block timestamp
- Current waiting time (can be updated)
- Location
- Model
- Name

---

2021/10/15
- Make case
- Create 10 cars
- Coroutines to advance vehicles
- Tables contain the link between cities and complete network to destination

---

2021/10/14
- List important cities between Paris and Berlin

---

2021/10/13
- Research Resilience4J
- Research Kystrix

---

- Make Data model for roads
- Cars must have:
  - Circuit break must choose randomly between the last two options
  - If still blocked, player gets a block timestamp when applicable, and the blocking waiting time is raised for 1 minute.
- Game lasts a maximum of 30 minutes
- If an error is still returned, user will get delayed for 5 minutes
- Player chooses city to move to
- Error types are timeout, exceptions
