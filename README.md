# Movie
Simple app for getting movies images from FLicker API 

# Architecture
- this app is following MVVM clean architecture
- this app has a basic `BaseViewModelActivity` and `BaseViewModel` to handle the communication between activities and ViewModels across the app
- this app contains `dagger` with custom scoops for DI 
- this app contains a global way of handling errors across the app in `AppErrorMapper`
# Third Party 
- this app handle dependency by Gradle Dependency Management w/Kotlin 
- you can find all the dependencies on `Libs` under `BuildSc`
