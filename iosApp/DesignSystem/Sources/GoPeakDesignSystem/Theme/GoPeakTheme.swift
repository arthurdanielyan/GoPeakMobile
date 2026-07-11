import SwiftUI

/// Entry point for GoPeak design tokens — the SwiftUI counterpart of the Android `GoPeakTheme`
/// object. Read tokens with `GoPeakTheme.colors`, `GoPeakTheme.typography`, `GoPeakTheme.shapes`.
///
/// Colors are dynamic (auto light/dark), so — unlike Android — there is no `GoPeakTheme { }`
/// wrapper to install: just reference the tokens directly. To force a mode in a preview or a
/// screen, use SwiftUI's `.preferredColorScheme(_:)`.
public enum GoPeakTheme {
    public static let colors = GoPeakColors()
    public static let typography = GoPeakTypography()
    public static let shapes = GoPeakShapes()
}
