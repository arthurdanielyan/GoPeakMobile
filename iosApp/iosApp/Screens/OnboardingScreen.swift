import SwiftUI
import GoPeakDesignSystem
import SharedLogic

struct OnboardingScreen: View {

    private let component: OnboardingComponent
    @StateObject private var uiState: ObservableValue<OnboardingViewState>

    init(component: OnboardingComponent) {
        self.component = component
        _uiState = StateObject(wrappedValue: ObservableValue(component.uiState))
    }

    var body: some View {
        OnboardingScreenContent(
            state: uiState.value,
            callbacks: component.uiCallbacks
        )
    }
}

private struct OnboardingScreenContent: View {

    let state: OnboardingViewState
    let callbacks: OnboardingUiCallbacks

    var body: some View {
        ZStack {
            Color(GoPeakTheme.colors.background)
                .ignoresSafeArea()
            Text("Onboarding \(state.currentPageIndex + 1)")
                .goPeakTextStyle(GoPeakTheme.typography.heading)
                .foregroundColor(GoPeakTheme.colors.onBackground)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .contentShape(Rectangle())
        .onTapGesture {
            callbacks.onContinueClick()
        }
    }
}

private final class PreviewOnboardingUiCallbacks: OnboardingUiCallbacks {
    func onContinueClick() {}
    func onBackClick() {}
}

#Preview("OnboardingScreen – Light") {
    OnboardingScreenContent(
        state: OnboardingViewState(currentPageIndex: 0),
        callbacks: PreviewOnboardingUiCallbacks()
    )
    .preferredColorScheme(.light)
}

#Preview("OnboardingScreen – Dark") {
    OnboardingScreenContent(
        state: OnboardingViewState(currentPageIndex: 0),
        callbacks: PreviewOnboardingUiCallbacks()
    )
    .preferredColorScheme(.dark)
}
