import SwiftUI

/// A text style = font + tracking (letter spacing) + line spacing. SwiftUI's `Font` doesn't carry
/// tracking/line-height, so those travel alongside it and are applied by `goPeakTextStyle(_:)`.
public struct GoPeakTextStyle: Sendable {
    public let font: Font
    public let tracking: CGFloat
    public let lineSpacing: CGFloat

    public init(font: Font, tracking: CGFloat = 0, lineSpacing: CGFloat = 0) {
        self.font = font
        self.tracking = tracking
        self.lineSpacing = lineSpacing
    }
}

/// GoPeak type scale — the SwiftUI counterpart of Android's `GoPeakTypography`.
/// Uses the device default font (`.system`), matching the Android `FontFamily.Default`.
public struct GoPeakTypography: Sendable {

    public let logo = GoPeakTextStyle(font: .system(size: 24, weight: .heavy), tracking: -1.2, lineSpacing: 8)
    public let heading = GoPeakTextStyle(font: .system(size: 24, weight: .bold), lineSpacing: 8)
    public let eyebrow = GoPeakTextStyle(font: .system(size: 16, weight: .regular), tracking: 3.2, lineSpacing: 8)
    public let body = GoPeakTextStyle(font: .system(size: 14, weight: .regular), lineSpacing: 6)
    public let label = GoPeakTextStyle(font: .system(size: 11, weight: .bold), tracking: 0.55, lineSpacing: 5)
    public let buttonLarge = GoPeakTextStyle(font: .system(size: 20, weight: .semibold), lineSpacing: 6)
    public let buttonCompact = GoPeakTextStyle(font: .system(size: 16, weight: .bold), tracking: 0.55, lineSpacing: 5)
    public let buttonText = GoPeakTextStyle(font: .system(size: 14, weight: .medium), lineSpacing: 6)

    public init() {}
}

public extension View {
    /// Applies a `GoPeakTextStyle` (font + tracking + line spacing).
    func goPeakTextStyle(_ style: GoPeakTextStyle) -> some View {
        self
            .font(style.font)
            .tracking(style.tracking)
            .lineSpacing(style.lineSpacing)
    }
}
