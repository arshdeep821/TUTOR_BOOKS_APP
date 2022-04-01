# My Personal Project Proposal

### Arshdeep Jaggo

### Project *Details* and *Description*:

The general idea of my project is to be a tutoring app 
that students can log onto and book appointments as **FREQUENTLY** 
as they want to for tutoring sessions. It will let students(the user)
book times for tutoring for a variety of subjects that they need aid with.
If the student can not make the appointment then they can **CANCEL** the appointment
and maybe try to **BOOK** for a different day and time of their choice. For example,
a student can book a session for tomorrow and next week, but they realize they can not make
it to the session for tomorrow. So they can cancel the session for tomorrow and maybe **REBOOK or CHANGE** it
later this week. They could select from a variety of courses that they would like to have help with.
For instance, some options could be **MATH, PHYSICS, ENGLISH, CHEMISTRY, etc**.

This application will be best for students who are struggling to keep up with their academics
and course load. They will be able to use this app in order to get the extra help they need to pass
and excel in their courses. Anyone who needs that extra helping hand with an academic subject can log
onto this app and book tutoring sessions as often as they want, as long as it adheres to their schedule
to get the help they need. This would be especially useful during *midterms/finals* as a lot of material
needs to be covered and revising could be easier with the help of a tutor, which one can find via this app.

### *Why did I select this as my project?*

This idea interests me because I am currently a university student, and I understand that at times
the course load can be intense. I understand the perspective of a typical university student and how
they might need assistance with their work or help to catch up on a course. Sometimes the office hours
are just not enough, and you could really just use a one on one session with a tutor. This idea of making 
a tutoring application interests me because I could imagine myself utilizing an application like this in my
remaining years to get help with course work. I feel that it is something that would massively impact my studies
and improve my understanding of course material as well.

### User Stories

Phase 1:
- As a user, I want to be able to book a single or multiple tutoring sessions at different 
times. In a sense, adding multiple tutoring sessions to my schedule
- As a user, I want to be informed that a session or sessions have been booked successfully after making a single
appointment
- As a user, I want to be informed when a session is taken, after attempting to book a session
(that is already booked by another student) and be told to rebook for a different time 
- As a user, I want the option to continue booking more tutoring sessions or to stop after making a single booking
  a session (to be able to exit out of booking whenever I feel I have booked enough sessions)

Phase 2:
- As a user, I want to be able to save my tutor session list to file
- As a user, I want to be able to be able to load my tutor session list from file

Phase 3:
- As a user, I want to be able to book a single or multiple tutoring sessions at different
  times. In a sense, adding multiple tutoring sessions to my schedule
- As a user, I want to be informed that a session or sessions have been booked successfully after making a single
  appointment
- As a user, I want to be informed when a session is taken, after attempting to book a session
  (that is already booked by another student) and be told to rebook for a different time
- As a user, I want the option to continue booking more tutoring sessions or to stop after making a single booking
  a session (to be able to exit out of booking whenever I feel I have booked enough sessions)
- As a user, I want to be able to save my tutor session list to file
- As a user, I want to be able to be able to load my tutor session list from file
- As a user, I want to be able to see the sessions/time slots that are unbooked
- AS a user, I want to see which sessions are booked

### Referenced Code:

For my project, It is similar to the HairSolon starter in the github repositories, I did reference some
code in the starter for my model package as it had a similar system of making bookings at certain times. 
At times I would code my own methods and check if it was working correctly via tests and comparing it to similar
methods in that file. Other times, when I was stuck, I would go into that starter, trying to find a starting point
of where I can start to fix my issue. Overall, references were made to this starter and the link for the repository
is posted here

gh repo clone github.students.cs.ubc.ca/CPSC210/DataAbstractionLectureStarters

I also have referenced to the JsonSerializationDemo to aid with implementing my data persistence classes. Below is 
a link to the repository if you want to check it out and look at the similarities:

gh repo clone github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

Reference to AlarmSystem

gh repo clone github.students.cs.ubc.ca/CPSC210/AlarmSystem

### Phase 4: Task 2
Event: Successful Booking Session for Arshdeep at 15 on Wed Mar 30 12:33:23 PDT 2022

Event: Successful Booking Session for Jason at 4 on Wed Mar 30 12:33:31 PDT 2022

Event: Successful Booking Session for Luke at 12 on Wed Mar 30 12:33:44 PDT 2022

Event: Unsuccessful Booking Session at 12 on Wed Mar 30 12:33:50 PDT 2022

Event: Successful Booking Session for Jason at 14 on Wed Mar 30 12:33:59 PDT 2022

Event: Unsuccessful Booking Session at 14 on Wed Mar 30 12:34:01 PDT 2022

Event: Tutor Organization Saved on Wed Mar 30 12:34:06 PDT 2022

### Phase 4: Task 3

- If I had more time I would implement a bi-directional relationship between Student and TutorOrganization because
that would aid in cancelling a booking or changing a booking. I would implement a bidirectional relationship because every time
a student gets booked in TutorOrganization, the time assigned to the student also changes and vice versa. Every time a student
gets remove from the tutorOrganization, it's time assigned also gets taken away. So every time you change the tutorOrganization,
it impacts the student, and every time you change or alter the student, it impacts the tutorOrganization. These seem like perfect 
conditions to implement a bidirectional relationship.
- I would definitely implement some refactoring within my GUI as there is a lot of duplication in relation with the swing
objects. For example almost all my JFrames, JLabels and JButtons needed to set the visibility to true, had to have their 
bounds set, set it to hide on close, setting font size and font, for every single instance of it. 
I think this could be extracted into three different methods (one for each type JLabel, JButton, JFrame) 
that can be called whenever I want to instantiate a new JLabel, JButton, and JFrame and have it set up the way I want 
it without manually doing it over and over. This would reduce the duplication of code in this class and as a result, 
it could possibly aid with the readability as there is less repetitive code to read through.
- I would also refactor my GUI2 class by splitting it into several smaller classes in order to improve the cohesion. Each class
should theoretically have one concept but my GUI2 class has several concepts implemented all related to my GUI. This 
could be fixed by making several classes separating the GUI2 class into components that only have one concept. For example,
one class for the main frame, one class for the booking frame, etc. It would increase the readability of the code all while
improving the cohesion of it as well. These smaller classes could all be put into one GUI package making it more
organized if an outside source ever wants to look into the code.