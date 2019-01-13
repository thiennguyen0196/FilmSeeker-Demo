<img src="https://user-images.githubusercontent.com/25218255/47353220-e7d2b200-d6e5-11e8-807b-627aaed56308.png" align="right" />

# Film Seeker - Demo
>  This is a demo source code version of my complete application which will be available on Google Play Store soon.

Review and apply Android programming techniques such as Dagger, DbFlow, OkHttp, Retrofit, RxJava, RxAndroid, MVP Architecture and Clean Architecture in order to build a native mobile app which allows users to do some interesting tasks in finding trending movies or nearby theaters.

## Table of contents
* [Installation](#installation)
    * [Requirements](#requirements)
    * [Instruction to install](#instruction-to-install)
        * [Android simulator](#android-simulator)
        * [Android device](#android-device)
* [Features](#features)
* [Libraries](#libraries)
* [Author](#author)
* [License](#license)

## Installation
> If you are a developer and want to explore code, follow the [Android simulator](#android-simulator) steps. If you just want to have a glance of this application, follow instructions in [Android device](#android-device)

### Requirements
* [Android Studio](https://developer.android.com/studio/) version 3.2.1 and up
* [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) version 8 and up

### Instruction to install
#### Android simulator
Firstly, clone this repository and open it in Android Studio.

This app uses [The MovieDB API](https://developers.themoviedb.org/3/getting-started/introduction) and [Google Map API](https://cloud.google.com/maps-platform/). Register and grab your API keys, then paste them inside: `.../app/src/main/res/values/api_key.xml`.

Then start building code and run it on an Android simulator.

#### Android device
Download and copy [FilmSeeker.apk](FilmSeeker.apk) into your Android device, then install application and explore interesting features.

## Features
A few of things Film Seeker - Demo application can do:
* Find trending movies
* Search for list of similar movies to a particular film
* Find top rated movies
* Find nearby movie theaters
* View detail information of a particular movie

<img src="https://user-images.githubusercontent.com/25218255/51084454-69256800-175c-11e9-8e5b-8aba4c263ded.png" align="center" />

<img src="https://user-images.githubusercontent.com/25218255/51084452-688cd180-175c-11e9-93b9-961d7e69d8b3.png" align="center" />

<img src="https://user-images.githubusercontent.com/25218255/51084453-69256800-175c-11e9-8b63-6c2eb7caecbf.png" align="center" />

## Libraries
This mobile application uses following open source libraries:
* [Dagger](https://google.github.io/dagger/) version 2.15
* [DBFlow](https://guides.codepath.com/android/DBFlow-Guide) version 4.2.4
* [OkHttp](http://square.github.io/okhttp/) version 3.9.1
* [Retrofit](https://square.github.io/retrofit/) version 2.3.0
* [RxJava](https://github.com/ReactiveX/RxJava) version 2.2.5
* [RxAndroid](https://github.com/ReactiveX/RxAndroid) version 2.1.0

This mobile application uses following API:
* [The Movie Database API](https://developers.themoviedb.org/3/getting-started/introduction)
* [Google Map API](https://cloud.google.com/maps-platform/)

This mobile application applies these architectures:
* [MVP Architecture](https://github.com/MindorksOpenSource/android-mvp-architecture)
* [Clean Architecture](https://github.com/android10/Android-CleanArchitecture)

## Author
Thien, Nguyen Hoang - mobile developer (Android)

[![github-logo](https://user-images.githubusercontent.com/25218255/47360756-794c1f00-d6fa-11e8-86fa-7b1c2e4dda92.png)](https://github.com/thiennguyen0196)     [![linkedin](https://user-images.githubusercontent.com/25218255/47360366-8583ac80-d6f9-11e8-8871-219802a9a162.png)](https://www.linkedin.com/in/thien-nguyen-0196/)     [![facebook](https://user-images.githubusercontent.com/25218255/47360363-84eb1600-d6f9-11e8-8029-818481536200.png)](https://www.facebook.com/hoangthien.nguyen.5209) 

## License
~~~~
Copyright 2019 Thien Nguyen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~~