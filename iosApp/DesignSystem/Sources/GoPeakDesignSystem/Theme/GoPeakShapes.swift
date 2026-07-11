import SwiftUI

/// GoPeak corner-radius scale (Figma: 4 / 8 / 12 / 16) — counterpart of Android's `GoPeakShapes`.
public struct GoPeakShapes: Sendable {
    public let extraSmall: CGFloat = 4
    public let small: CGFloat = 8
    public let medium: CGFloat = 12
    public let large: CGFloat = 16

    public init() {}

    /// Convenience `RoundedRectangle` for a given radius token.
    public func rounded(_ radius: CGFloat) -> RoundedRectangle {
        RoundedRectangle(cornerRadius: radius, style: .continuous)
    }
}
