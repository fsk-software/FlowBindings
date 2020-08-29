# FlowBindings
Bindings to Android UI elements using Kotlin Flows.  The listeners automatically remove when the coroutine scope expires.

[![](https://jitpack.io/v/fsk-software/FlowBindings.svg)](https://jitpack.io/#fsk-software/FlowBindings)

To use the library add jitpack to the projects repositories: 
 
   ```gradle
   repositories { 
        jcenter()
        maven { url "https://jitpack.io" }
   }
   ```
   
Then add the following the library to your modules gradle file
   ```
   dependencies {
         compile 'com.github.jitpack:gradle-simple:1.0.5'
   }
   ```  

To use the bindings, just call the binding extensions.
    
    button.clicks().onEach {
       //respond to each click
    }.flowOn(Dispatchers.Main).launchIn(lifecycleScope)



License
=======
    Copyright 2020 N. Lipelt

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.