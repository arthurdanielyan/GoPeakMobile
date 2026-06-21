import SwiftUI
import SharedLogic

/// SwiftUI counterpart of `RootScreen` (Jetpack Compose) in
/// `com.danielyan.gopeak.root.impl.RootScreen`.
///
/// Android (`MainActivity`):
///   - Koin is started in `GoPeakApplication`.
///   - `RootComponent` is created with a lifecycle-aware `AppComponentContext`.
///   - `RootScreen` renders `component.childStack` and shows "Not Yet Implemented"
///     for the active child.
///
/// iOS mirrors this: Koin is started in `iOSApp.init()`, `RootComponentHolder` creates
/// and owns the component (with a manual `LifecycleRegistry`), and this view renders the
/// active child of the stack — currently every screen is "Not Yet Implemented".
struct RootView: View {

    @StateObject private var model = RootObservableModel()

    var body: some View {
        StackView(
            stackValue: ObservableValue(model.holder.component.childStack),
            onBack: model.holder.component.onBack
        ) { component in
            // Switch over the active child *component*, like Kotlin's
            // `when (it.instance) { is LoginComponent -> LoginScreen(it) ... }`.
            // Real child components don't exist yet, so everything is the placeholder.
            switch component {
            // case let login as LoginComponent:       LoginScreen(login)
            // case let onboarding as OnboardingComponent: OnboardingScreen(onboarding)
            // case let main as MainComponent:          MainScreen(main)
            default:
                NotYetImplementedView()
            }
        }
    }
}

/// Owns the Decompose root component and its lifecycle. Equivalent to what `MainActivity`
/// gets for free from the Android Activity; `StackView` handles the observation.
private final class RootObservableModel: ObservableObject {

    let holder = RootComponentHolder()

    deinit {
        holder.destroy()
    }
}

/// Equivalent of the centered "Not Yet Implemented" Box in the Compose `RootScreen`.
private struct NotYetImplementedView: View {
    var body: some View {
        ZStack {
            Color(.systemBackground)
                .ignoresSafeArea()
            Text("Not Yet Implemented")
                .font(.largeTitle.weight(.bold))
                .foregroundColor(.primary)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

#Preview {
    NotYetImplementedView()
}
