import SwiftUI

/// GoPeak's bespoke color roles — the SwiftUI counterpart of Android's `GoPeakColors`.
///
/// Each role is a **dynamic** `Color` that resolves light/dark automatically, so there is no
/// separate "dark scheme"/"light scheme" object to select — iOS picks the right value per
/// `userInterfaceStyle`. Values are copied exactly from
/// `core/designSystem/.../theme/GoPeakColors.kt` (light / dark).
public struct GoPeakColors: Sendable {

    /// Expressive orange — the highest-emphasis brand CTA (LargePrimaryButton).
    public let brand = Color(light: 0xFFAC8A, dark: 0xFF6B00)
    public let onBrand = Color(light: 0x351000, dark: 0x572000)

    /// Peach — the primary action color (PrimaryButton, SecondaryButton label).
    public let primary = Color(light: 0xA04100, dark: 0xFFB693)
    public let onPrimary = Color(light: 0xFFFFFF, dark: 0x561F00)

    /// Blue highlight used for accents/metrics. Used as foreground and, at low alpha, as a fill.
    public let accent = Color(light: 0x00639A, dark: 0x9CCAFF)
    public let onAccent = Color(light: 0x0A2E4D, dark: 0x0A2E4D)
    /// `accent` at reduced opacity — background behind accent-colored content.
    public let accentContainer = Color(light: 0xCEE5FF, lightAlpha: 0.5, dark: 0x9CCAFF, darkAlpha: 0.2)

    public let background = Color(light: 0xFFF8F5, dark: 0x1D100A)
    public let onBackground = Color(light: 0x231A14, dark: 0xF8DDD2)

    public let surface = Color(light: 0xFFF8F5, dark: 0x261812)
    public let onSurface = Color(light: 0x1D100A, dark: 0xF8DDD2)

    public let surfaceVariant = Color(light: 0xF0E4DD, dark: 0x41312A)
    public let onSurfaceVariant = Color(light: 0x5A4136, dark: 0xE2BFB0)

    public let outline = Color(light: 0xB8A196, dark: 0x5A4136)

    public init() {}
}
