import SwiftUI
import GoPeakDesignSystem

/// First onboarding page — SwiftUI counterpart of Android's `OnboardingPage1`.
/// A full-bleed hero photo with the marketing copy, brand CTAs and feature cards
/// anchored to the bottom.
struct OnboardingPage1: View {

    let onGetStarted: () -> Void
    let onSkip: () -> Void

    var body: some View {
        content
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .bottom)
            .padding(.horizontal, 16)
            .background(heroImage)
    }

    private var heroImage: some View {
        Image("onboarding_hero")
            .resizable()
            .scaledToFill()
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(GoPeakTheme.colors.dim)
            .clipped()
    }

    private var content: some View {
        VStack(alignment: .leading, spacing: 0) {
            Spacer(minLength: 0)

            Text("ZERO-FRICTION PERFORMANCE")
                .goPeakTextStyle(GoPeakTheme.typography.eyebrow)
                .foregroundStyle(GoPeakTheme.colors.primary)

            Spacer().frame(height: 4)

            (Text("Reach Your").foregroundStyle(GoPeakTheme.colors.onBackground)
                + Text(verbatim: " ")
                + Text("Peak").foregroundStyle(GoPeakTheme.colors.brandEmphasis))
                .goPeakTextStyle(GoPeakTheme.typography.heading)

            Spacer().frame(height: 16)

            Text("High-performance tracking for those who value precision, momentum, and clarity in every rep.")
                .goPeakTextStyle(GoPeakTheme.typography.body)
                .foregroundStyle(GoPeakTheme.colors.primary)

            Spacer().frame(height: 24)

            BrandPrimaryButton("Get Started", icon: .trailing("arrow.forward"), action: onGetStarted)
                .goPeakButtonFullWidth()

            Spacer().frame(height: 16)

            BrandSecondaryButton("Skip", action: onSkip)
                .goPeakButtonFullWidth()

            Spacer().frame(height: 40)

            HStack(spacing: 8) {
                FeatureCard(
                    color: GoPeakTheme.colors.onSurface,
                    icon: "ic_ft_live_tracking",
                    text: "Live Tracking"
                )
                FeatureCard(
                    color: GoPeakTheme.colors.accent,
                    icon: "ic_ft_growth",
                    text: "Growth Visualizer"
                )
            }
            .fixedSize(horizontal: false, vertical: true)

            Spacer().frame(height: 24)
        }
    }
}

/// A feature highlight — a `surfaceVariant` tile with a 2pt colored spine peeking on its
/// left edge and a tinted icon above the label (mirrors Android's offset-layered `FeatureCard`).
private struct FeatureCard: View {
    let color: Color
    let icon: String
    let text: LocalizedStringKey

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Image(icon)
                .renderingMode(.template)
                .resizable()
                .scaledToFit()
                .frame(width: 16, height: 16)
                .foregroundStyle(color)

            Text(text)
                .goPeakTextStyle(GoPeakTheme.typography.label)
                .foregroundStyle(GoPeakTheme.colors.onSurface)
        }
        .padding(12)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(GoPeakTheme.shapes.rounded(GoPeakTheme.shapes.small).fill(GoPeakTheme.colors.surfaceVariant))
        .padding(.leading, 2)
        .background(GoPeakTheme.shapes.rounded(GoPeakTheme.shapes.small).fill(color))
    }
}

// MARK: - Previews

#Preview("OnboardingPage1 – Light") {
    OnboardingPage1(onGetStarted: {}, onSkip: {})
        .background(GoPeakTheme.colors.background)
        .preferredColorScheme(.light)
}

#Preview("OnboardingPage1 – Dark") {
    OnboardingPage1(onGetStarted: {}, onSkip: {})
        .background(GoPeakTheme.colors.background)
        .preferredColorScheme(.dark)
}
