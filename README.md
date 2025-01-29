# Android Template
A foundation for a scalable, mobile Android project:

- modular project
- single-Activity application with Compose and an app-wide theme
- example feature using modern MVVM, with a unit test suite
- basic Continuous Integration
- basic Gradle convention plugins

## Modular Project

The project is organized by domain, preferring a structure that self-documents while establishing clear boundaries between parts
of the application. It is first organized into sub-projects, with each sub-project having a module per domain.

Sub-projects:

- `apps`: modules that produce applications, starting with a mobile build. The `mobile` module can be modified to produce several
applications or additional modules can be added to the sub-project.
- `features`: one module for each feature, which contains its UI, domain, and data layers. If it improves organization, additional
sub-projects can be added to group feature modules, such as having a multi-step sign up feature grouped into a `sign-up`
sub-project.
- `ui`: modules for UI components to be used across app features, such as drop-in Composables and the app's theme object

As the project scales, sub-projects can be added as necessary, but be wary of over-engineering. Each new sub-project and module
fundamentally adds complexity to a project, so the cost-benefit must be justified.

## Single-Activity Application

The application uses a single Activity with a Compose navigation host, which allows all features to be written
with Compose. This application-level functionality is contained in the `apps` modules, specifically `apps:mobile` if no
other applications have been added to the project.

To add new features, and navigation between them, modify the `NavHost` inside the `App` Composable. Be sure to follow the
patterns of "data down, events up" and state hoisting, where features expose callbacks for navigation instead of passing
the `NavController` itself down to individual screen Composables.

```kotlin
NavHost(
    navController = navController,
    startDestination = ROUTE_FEATURE_1,
) {
    composable(ROUTE_FEATURE_1) {
        FirstFeatureScreen(
            viewModel = hiltViewModel(),
            onButtonClicked = {
                navController.navigate(ROUTE_FEATURE_2)
            }
        )
    }
    composable(ROUTE_FEATURE_2) {
        SecondFeatureScreen(
            viewModel = hiltViewModel(),
        )
    }
}
```

## Modern MVVM

The example feature uses "modern MVVM" as described in the Google Android Developer Guides' [Architecture section](https://developer.android.com/topic/architecture/recommendations).
It can be summarized as a mix of MVVM and MVI:

- A layered architecture with 3 layers: UI, Domain (optional), and Data
- The UI Layer contains any Composables and their view model, which provides data as an observable "UI state". The UI calls the view model to trigger state changes, if any.
- The Domain Layer is an optional layer between the view model and data layer, when it improves code quality. It may be omitted.
- The Data Layer is a strong boundary between the view model and data access. It provides easy-to-use APIs, exposed as Repositories, for the view model, instead of the view model manually making API calls, performing database reads, etc.

## Example Feature

The example feature is a scrollable list of items using the architecture outlined in the previous section. A test suite is provided as an example of the best practice of
automated testing for verification, instead of manual testing. It uses [kotest](https://github.com/kotest/kotest), but feel free to replace it with your own preference. These
tests are not just used for verification, but also regression, since they're run during the project's basic Continuous Integration.

## Continuous Integration

This project uses [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) as a source of truth for code formatting. It is integrated into the `check` task and runs during CI.
See their documentation for useful Gradle tasks, like `:ktlintFormat` for auto-formatting and `:ktlintGenerateBaseline` for ignoring acceptable items.

This project provides a basic GitHub Actions Workflow that runs the `check` Gradle task. This task compiles, lints, and tests the project on every push, which includes Pull
Requests.

## Convention Plugins

This projects provides basic [Convention Plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html) to allow easy setup of additional modules. They are
added to the `plugins` block at the beginning of a Gradle build file. See any current modules for examples. See the `build-logic` module for the plugins themselves.

- `example-application`: for application modules, like `apps:mobile`, providing common setup like min SDK version
- `library-android-compose`: for android library modules that need compose, like `features:feature_one`
- `library-android`: for android library modules that don't need compose
- `library-kotlin`: for JVM modules


