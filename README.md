# Call-Center
This project is a simulation of a call center queue system, developed for my Data Structures & Algorithms course (ITSC 2214) in Spring 2025. The system models a real-world call center using a custom FIFO (First In, First Out) queue implemented with arrays.

## What I Learned
- How to build and manipulate queues manually using arrays
- Simulating real-time systems using artificial "ticks" or clocks
- Prioritizing items in queues (VIP vs non-VIP callers)
- Writing and running unit tests using JUnit 4
- Calculating test coverage at the line level for better reliability
- Handling edge cases, exceptions, and queue overflows

## Key Features
- Add calls to the queue with error checking and priority handling
- VIP callers are placed at the front of the queue
- Calls are answered one at a time, and duration is decremented each clock tick
- Accurate simulation of a working call center with time-based logic

## Project Structure
Call.java             -- Represents a single call with attributes like name, duration, and VIP status

CallCenter.java       -- Manages the call queue and handles simulation logic

CallTest.java         -- JUnit tests for Call

CallCenterTest.java   -- JUnit tests for CallCenter

Project3.java         -- Demo runner
