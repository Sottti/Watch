# Watch

## Intro

Welcome to the Watch app guide.

<a href="https://ibb.co/ngzFFDR"><img src="https://i.ibb.co/ysc82GW/Screenshot-1563618481.png" alt="Screenshot-1561399689" border="0" width="250" height="480"></a>
<a href="https://ibb.co/zPDMmVX"><img src="https://i.ibb.co/ZmqG7Bm/Screenshot-1563618490.png" alt="Screenshot-1561399686" border="0" width="250" height="480"></a>

## Videos

Included in the app as well, you can find my thoughts about different aspects of the app here (coming).

## Run Tests

Execute the next gradle tasks from the terminal.

For executing all unit tests --> `gradlew testLiveDebugUnitTest`

For executing all android tests --> `gradlew connectedLiveDebugAndroidTest --no-parallel`

It's important to run the Android Tests with the animations disabled on the device.
If the animations are enabled while while running the Android Tests, they can be flaky, or in the best case scenario slower.

## License

The contents of this repository are covered under the [MIT license](https://en.wikipedia.org/wiki/MIT_License).
