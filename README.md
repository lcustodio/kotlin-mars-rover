Mars Rover Kata
==

The repository is divided in two main areas:

* Core logic around Mars and Rover
* String manipulation to interface with the commands dispatched to the rover.

### Model
Model is the core part intentionally kept as simple as possible and highly covered by test scenarios

### Adapter Layer 
Separation of Responsibilities
Contains an interface to string commands, this is likely to evolve in the event of porting to REST, gRPC etc.


## Troubleshoot

If the tests are not running correctly, please refer to
this [post](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360006656399-Run-JUnit-test-from-within-class-test-NOT-GRADLE)
that describes setting the test running to be Intellij instead of Gradle.