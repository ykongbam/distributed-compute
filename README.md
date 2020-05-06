# Distributed Compute

This is an implementation of distributed parallel divide and conquer strategy. The goal is to have break down a task into small chunks and distribute it across a semi-reliable cluster. Collect results and compute the result on a smaller set. The emphasis is on high compute - low memory.


## Design 
### Task
The large compute task which needs to be executed.

### PartialTask
A Task is broken down into PartialTasks. A partial task is what a node receives for execution. It's an atomic execution unit.



## Implementations
### Simple adder
A task consist of a long list of integers which needs to be added. The list is split into multiple smaller list and distributed across nodes. 
Each node adds the numbers and returns the result. These results are then collected and added to get the final result.

### Map Reduce
Simple implementation of Map-Reduce using the same principle of distributing the task.
Sample example of word count included

