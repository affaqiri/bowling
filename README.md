The bowling game scoring system is probably one of the most famous exercises in learning TDD.

To be honest, I had already done this exercise back few years ago when learning TDD in university :)

The idea behind TDD and its red-green-refactor pattern is write a failing test (red), then write the MINIMUM of code to pass the test (green)
and then refactor the code for any duplication.. all in small and tiny steps.

One of the main benefit of this approach is that we only code to requirements which are described as test cases and and we only write
code to pass those tests. We get a high quality of code and a high level of code coverage implicitly and it prevents at some extent the risk
of introducing new code in our code base without associated tests.

One of the positive side effects of adopting this approach is that it forces us to solve the whole problem in small steps and we usually find
out that the initial thought we had for the domain model needed to solve the problem was not really required. We get an emergent simple design
through refactoring steps.

If i were in university and i wanted to show my teacher about my understanding of object oriented, design patterns and all the software engineering
goodness, i may have started immediately with a domain model like Game, Frame, Roll, 10th Frame specifics implemented by some kind of inheritance and
maybe an interface for the Game with two methods but the reality is that in professional life, we only need to implement the minimum and the
simplest code to reply to specific client requirements.

I am not saying that we should ignore all these proven and tested patterns and architectures but what i am suggesting is that most of the time
there is no need for all the added complexity if something could be done more simply. If we give our code to our peer and he/she find it
kinda obvious just by skimming the code then we should feel proud of our work.

Is TDD easy as it seems? Hell no :), even for experienced developers who are new to this approach cause we as developers, let's be honest,
prefer jumping straight to code, thinking about the domain model, design, layering, etc and we find it rather irritating coding at such small
steps as described by the TDD approach cause we find it rather obvious that the test will fail so why need to go through those steps.

The main difficulty is that we are getting so focused by the process that we forget the reasoning behind that process. I think only by experience
and applying these rules, we get to know when to break or deviate from these rules depending on the context.

Do we apply it on every project? I wish we could but no, most of the time, we fixed together on how much code we needed to cover by the end of
each iteration, and we had a minimum coverage limit that we should not fall below and we tried to stick together to these quality objectives.

Now to our exercise:

--- DIUS Team Bowling Scoring System Interface Requirements ---
bowlingBowlingGame.roll(noOfPins);
bowlingBowlingGame.score();
---

The (tiny) steps i took to solve this problem:

1.  Write a test to instantiate a new Game object even when the Game class does not exist (red).
    a.  Compilation error. Create a minimum Game class -> Test passes (green).
2.  Write a test to roll a number of pins in the game even if the method roll(noOfPins) is not yet defined in the Game class (red).
    a.  Compilation error. Create the method with only the minimum signature to pass the test with no logic defined yet (green).
    Side note:
        -   I prefer long, descriptive variable and function names as best practice.
        -   I prefer the code to be self descriptive and no need to get up to the calling stack to figure out its meaning and usage.
    b.  Duplication detected: our second test covers the first test cause we create a Game in both classes. Removed the first test (test refactor).
3.  Write a test to roll with a 0 pin down and ask for the game score even if the method score() is not yet defined in the Game class (red).
    a.  Compilation error. Create the method with only the minimum signature to pass the test and return the constant 0 as score in the score() method (green).
    b.  Duplication detected: no need to keep our second test as the third test covers it. Removed the the second test (test refactor).
4.  Write a test to roll with a 1 pin down and ask for the game score of 1 in assertion. Fails because of the constant 0 of the step 3. (red).
    a.  During refactor, I can not return the constant 1 in the score() method cause the previous test would otherwise fail.
        We need to refactor the method score() to return the value of a 'score' instance variable which will have the default value of 0 for
        passing the step 3 but which will get affected the value of the roll's method 'numberOfPinsDown' parameter to pass the step 4.
    b.  The main idea and difficulty behind TDD is to identify the repeating pattern of tests and refactor them in the real code to avoid duplication.
        Here in our roll method, we could have written:
        if (numberOfPinsDown == 0)
            score = 0
        else if (numberOfPinsDown == 1)
            score = 1;
        ...
        This clearly shows that at this stage of our code, we could have refactored it to simply:
            score = numberOfPinsDown;
5.  Write a test to test two consecutive rolls with 1 pin down then another pin down for a score of 2.
    a.  Fails cause we do not accumulate one roll's score with the previous roll.
    b.  Easy to fix score += numberOfPinsDown in each roll.
6.  For completeness, we can write two tests that test a zero point game and another that test only one point per roll game.
    a.  In pure TDD, these two tests are not required cause they will pass to green with current state of the application and we need to write tests that fails before fixing them.
    b.  We could remove if we wish, the tests written in step 3, 4 and 5 cause this step's tests covers them all.
7.  Write a test to check the spare bonus roll : Rolling a spare followed by a 1 pin roll and 17 zero pin rolls should give a game score of 12.
    a.  The test fail cause we get 11 instead of 12, we need to refactor the code to introduce the spare bonus rule (red).
    b.  The code need to be refactored so that the score() method does the actual score calculation instead of the current roll() method.
        The roll() method should simply save in memory the number of pins knocked down in each roll for future score calculation.
8.  I won't go in to further explanation of each refactoring step. Basically, we need to:
    a.  Implement the rule that identify a spare (two consecutive rolls of 10 pins down in the same Frame)
    b.  Implement the rule that identify a strike (a roll of 10 pins downs in the first roll of each Frame)
    c.  Implement the bonus rule for a spare and frame
    d.  Implement the specifics of last frame scoring (we will find out that it is implicitly implemented during our refactor process).

Possible improvement:

    -   I prefer working with software libraries which promote fluent style programming.
    -   It would be great if we could chain together multiple calls to roll() as intermediate operation and score() as a terminal operation
        in a sequence of calls much like the streams manipulation in Java 8.

        Usage : bowlingBowlingGame.roll(5).roll(5).roll(1).score();

        Each call to roll() could have returned our Game object on which we could call subsequent roll() call.
        The score() call needs to return a number value so it put an end to the current call chain.

I have not included any GUI nor an interactive command line utility for testing the application. I guess, if you write descriptive test suites,
there is no need for such tool to test the behavior and usage of your application.

At the end, I took initiative of parameterizing the Game class with Game(int numberOfFrames, int numberOfPinsPerFrame), it was not asked for in the
requirements and i may have deviated from TDD approached and introduced something not tested. Just for your information :)
