import SwiftUI
import UIKit

extension Color {

    /// A static color from a 0xRRGGBB hex value.
    init(hex: UInt, alpha: Double = 1.0) {
        self.init(uiColor: UIColor(hex: hex, alpha: alpha))
    }

    /// A color that resolves to `light` or `dark` automatically based on the current
    /// user interface style — this is how GoPeak gets automatic light/dark support on iOS
    /// (the equivalent of picking `GoPeakDarkColors`/`GoPeakLightColors` on Android).
    init(light: UInt, lightAlpha: Double = 1.0, dark: UInt, darkAlpha: Double = 1.0) {
        self.init(uiColor: UIColor { traits in
            traits.userInterfaceStyle == .dark
                ? UIColor(hex: dark, alpha: darkAlpha)
                : UIColor(hex: light, alpha: lightAlpha)
        })
    }
}

extension UIColor {
    convenience init(hex: UInt, alpha: Double = 1.0) {
        self.init(
            red: CGFloat((hex >> 16) & 0xFF) / 255.0,
            green: CGFloat((hex >> 8) & 0xFF) / 255.0,
            blue: CGFloat(hex & 0xFF) / 255.0,
            alpha: CGFloat(alpha)
        )
    }
}
