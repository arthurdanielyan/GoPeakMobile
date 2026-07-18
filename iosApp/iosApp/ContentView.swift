import SwiftUI
import GoPeakDesignSystem
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
        ) { childComponent in
            
            switch childComponent {
                case let onboardingComponent as OnboardingComponent:
                    OnboardingScreen(component: onboardingComponent)
                    
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
            Color(GoPeakTheme.colors.background)
                .ignoresSafeArea()
            Text("Not Yet Implemented")
                .goPeakTextStyle(GoPeakTheme.typography.heading)
                .foregroundColor(GoPeakTheme.colors.onBackground)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}


#Preview("NotYetImplementedView – Dark") {
    NotYetImplementedView().preferredColorScheme(.dark)
}

#Preview("NotYetImplementedView – Light") {
    NotYetImplementedView().preferredColorScheme(.light)
}
