import SwiftUI
import GoPeakDesignSystem
import SharedLogic

/// SwiftUI counterpart of `com.danielyan.gopeak.featureOnboarding.ui.OnboardingScreen`.
///
/// A paged onboarding: a branded top bar with a Skip action, a swipeable pager, and a bottom
/// navigation bar (Back / step indicator / Continue). Page state lives locally since the
/// component no longer exposes a view state — it only provides `uiCallbacks`.
struct OnboardingScreen: View {

    private let component: OnboardingComponent

    init(component: OnboardingComponent) {
        self.component = component
    }

    var body: some View {
        OnboardingScreenContent(callbacks: component.uiCallbacks)
    }
}

private struct OnboardingScreenContent: View {

    let callbacks: OnboardingUiCallbacks

    @State private var currentPage = 0
    private let pageCount = Int(OnboardingComponentCompanion.shared.OnboardingPageCount)

    var body: some View {
        VStack(spacing: 0) {
            TopBarWithLogo {
                TertiaryButton("Skip") { callbacks.onSkipClick() }
                    .opacity(isFirstPage ? 0 : 1)
                    .disabled(isFirstPage)
                    .accessibilityHidden(isFirstPage)
            }

            TabView(selection: $currentPage) {
                OnboardingPage1(onGetStarted: goToNextPage, onSkip: callbacks.onSkipClick)
                    .tag(0)

                ForEach(1..<pageCount, id: \.self) { index in
                    OnboardingPlaceholderPage(index: index)
                        .tag(index)
                }
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            .frame(maxWidth: .infinity, maxHeight: .infinity)

            Divider()
                .overlay(GoPeakTheme.colors.outline)

            OnboardingNavigation(
                currentPage: currentPage,
                totalPages: pageCount,
                onBack: goToPreviousPage,
                onContinue: goToNextPage
            )
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(GoPeakTheme.colors.background)
        .animation(.snappy(duration: 0.35), value: currentPage)
    }

    private var isFirstPage: Bool { currentPage == 0 }

    private func goToNextPage() {
        currentPage = min(currentPage + 1, pageCount - 1)
    }

    private func goToPreviousPage() {
        currentPage = max(currentPage - 1, 0)
    }
}

/// Bottom navigation bar: Back on the left, the step indicator centered, Continue on the right.
/// Back and Continue only appear after the first page (they fade in and out) but keep their
/// space reserved so the bar height stays stable.
private struct OnboardingNavigation: View {

    let currentPage: Int
    let totalPages: Int
    let onBack: () -> Void
    let onContinue: () -> Void

    private var isVisible: Bool { currentPage > 0 }

    var body: some View {
        HStack(spacing: 0) {
            TertiaryButton("Back", icon: .leading("arrow.backward"), action: onBack)
                .frame(maxWidth: .infinity, alignment: .leading)
                .opacity(isVisible ? 1 : 0)
                .disabled(!isVisible)
                .accessibilityHidden(!isVisible)

            StepIndicator(stepCount: totalPages, currentStep: currentPage)

            PrimaryButton("Continue", icon: .trailing("arrow.forward"), action: onContinue)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .opacity(isVisible ? 1 : 0)
                .disabled(!isVisible)
                .accessibilityHidden(!isVisible)
        }
        .padding(16)
    }
}

/// Placeholder body for onboarding pages 2…N (their real content isn't designed yet).
private struct OnboardingPlaceholderPage: View {
    let index: Int

    var body: some View {
        Text(verbatim: "Onboarding \(index + 1)")
            .goPeakTextStyle(GoPeakTheme.typography.heading)
            .foregroundStyle(GoPeakTheme.colors.onBackground)
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(GoPeakTheme.colors.background)
    }
}

// MARK: - Previews

private final class PreviewOnboardingUiCallbacks: OnboardingUiCallbacks {
    func onFinish() {}
    func onSkipClick() {}
}

#Preview("OnboardingScreen – Light") {
    OnboardingScreenContent(callbacks: PreviewOnboardingUiCallbacks())
        .preferredColorScheme(.light)
}

#Preview("OnboardingScreen – Dark") {
    OnboardingScreenContent(callbacks: PreviewOnboardingUiCallbacks())
        .preferredColorScheme(.dark)
}
