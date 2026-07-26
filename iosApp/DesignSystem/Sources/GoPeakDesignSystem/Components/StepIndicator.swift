import SwiftUI

/// Paged progress dots — the active step stretches from a circle into a pill.
/// SwiftUI counterpart of Android's `StepIndicator`.
///
/// The idiomatic SwiftUI layout is an `HStack` of `Capsule`s that reflows on its own when the
/// active dot changes width — there's no need for the manual `translationX` offset the Compose
/// version uses (that only existed because its `Canvas` box was a fixed width).
public struct StepIndicator: View {
    private let stepCount: Int
    private let currentStep: Int
    private let dotSize: CGFloat
    private let spacing: CGFloat

    /// Active dot is `dotSize * activeScale` wide (matches Android's `ActiveScale = 3f`).
    private let activeScale: CGFloat = 3

    public init(stepCount: Int, currentStep: Int, dotSize: CGFloat = 8, spacing: CGFloat = 8) {
        self.stepCount = stepCount
        self.currentStep = currentStep
        self.dotSize = dotSize
        self.spacing = spacing
    }

    public var body: some View {
        HStack(spacing: spacing) {
            ForEach(0..<stepCount, id: \.self) { index in
                let isActive = index == currentStep
                Capsule()
                    .fill(isActive ? GoPeakTheme.colors.primary : GoPeakTheme.colors.surfaceVariant)
                    .frame(width: isActive ? dotSize * activeScale : dotSize, height: dotSize)
            }
        }
        // Springy, expressive motion — parity with Android's MotionScheme.expressive fast spatial spec.
        .animation(.snappy(duration: 0.35, extraBounce: 0.1), value: currentStep)
    }
}

// MARK: - Previews

private struct StepIndicatorGallery: View {
    @State private var step = 0

    var body: some View {
        VStack(spacing: 32) {
            StepIndicator(stepCount: 4, currentStep: 0)
            StepIndicator(stepCount: 4, currentStep: 2)
            StepIndicator(stepCount: 4, currentStep: step)
                .onTapGesture { step = (step + 1) % 4 }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(GoPeakTheme.colors.background)
    }
}

#Preview("StepIndicator – Dark") {
    StepIndicatorGallery().preferredColorScheme(.dark)
}

#Preview("StepIndicator – Light") {
    StepIndicatorGallery().preferredColorScheme(.light)
}
