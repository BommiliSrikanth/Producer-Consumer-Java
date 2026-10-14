# Producer-Consumer Problem in Java

## Aim
Implement the classic **Producer-Consumer** problem using inter-thread communication (`wait()` and `notify()`) to synchronize access to a shared buffer.

## Problem Statement
- Shared buffer implemented as a `LinkedList<Integer>` with fixed capacity **2**.
- **Producer** thread generates items and adds them to the buffer.
- **Consumer** thread removes items from the buffer and processes them.
- Uses `synchronized` blocks for mutual exclusion.
- Uses `wait()` and `notify()` for inter-thread communication.
- Producer waits when the buffer is full.
- Consumer waits when the buffer is empty.

## Implementation Details

### Class `PC`
- `LinkedList<Integer> list` – shared buffer
- `int capacity = 2`

### `produce()` method
- Infinite loop
- Synchronized on the shared object
- Waits while `list.size() == capacity`
- Adds item and prints `Producer produced-X`
- Calls `notify()`
- Sleeps 1 second

### `consume()` method
- Infinite loop
- Synchronized on the shared object
- Waits while `list.size() == 0`
- Removes first item and prints `Consumer consumed-X`
- Calls `notify()`
- Sleeps 1 second

### Main
- Creates one `PC` instance
- Starts Producer and Consumer threads
- Joins both threads

## How to Run

```bash
cd src
javac ProducerConsumer.java
java ProducerConsumer
```

Press `Ctrl+C` to stop (program runs indefinitely).

## Sample Output
```
Producer produced-0
Producer produced-1
Consumer consumed-0
Consumer consumed-1
Producer produced-2
Producer produced-3
Consumer consumed-2
Consumer consumed-3
...
```

## Constraints Followed
- Fixed buffer capacity
- `synchronized` blocks for mutual exclusion
- `wait()` / `notify()` for inter-thread communication
- Program runs indefinitely

## Project Structure
```
producer-consumer/
├── output/                  # Program output samples & screenshots
│   ├── output1.png
│   ├── output2.jpg
│   └── ...
├── src/
│   └── ProducerConsumer.java
├── README.md
└── .gitignore
```
