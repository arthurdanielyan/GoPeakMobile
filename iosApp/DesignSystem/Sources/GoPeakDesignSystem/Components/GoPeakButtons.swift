import SwiftUI

/// A button icon on exactly one side — mirrors Android's sealed `ButtonIcon`, making
/// "leading AND trailing at once" unrepresentable. The associated value is an **SF Symbol** name.
public enum GoPeakButtonIcon: Sendable {
    case none
    case leading(String)
    case trailing(String)

    fileprivate var leadingSymbol: String? { if case let .leading(name) = self { return name } else { return nil } }
    fileprivate var trailingSymbol: String? { if case let .trailing(name) = self { return name } else { return nil } }
}

// MARK: - Public buttons

/// High-emphasis brand CTA (filled). Optional single icon.
public struct BrandPrimaryButton: View {
    private let title: LocalizedStringKey
    private let icon: GoPeakButtonIcon
    private let action: () -> Void

    public init(_ title: LocalizedStringKey, icon: GoPeakButtonIcon = .none, action: @escaping () -> Void) {
        self.title = title
        self.icon = icon
        self.action = action
    }

    public var body: some View {
        GoPeakButtonBase(
            title: title,
            icon: icon,
            textStyle: GoPeakTheme.typography.buttonLarge,
            contentColor: GoPeakTheme.colors.onBrand,
            fill: GoPeakTheme.colors.brand,
            border: nil,
            horizontalPadding: 24,
            verticalPadding: 12,
            action: action
        )
    }
}

/// High-emphasis alternative (outlined). Optional single icon.
public struct BrandSecondaryButton: View {
    private let title: LocalizedStringKey
    private let icon: GoPeakButtonIcon
    private let action: () -> Void

    public init(_ title: LocalizedStringKey, icon: GoPeakButtonIcon = .none, action: @escaping () -> Void) {
        self.title = title
        self.icon = icon
        self.action = action
    }

    public var body: some View {
        GoPeakButtonBase(
            title: title,
            icon: icon,
            textStyle: GoPeakTheme.typography.buttonLarge,
            contentColor: GoPeakTheme.colors.onBackground,
            fill: nil,
            border: (GoPeakTheme.colors.outline, 2),
            horizontalPadding: 24,
            verticalPadding: 14,
            action: action
        )
    }
}

/// Compact primary action (filled peach). Supports a leading OR trailing icon.
public struct PrimaryButton: View {
    private let title: LocalizedStringKey
    private let icon: GoPeakButtonIcon
    private let action: () -> Void

    public init(_ title: LocalizedStringKey, icon: GoPeakButtonIcon = .none, action: @escaping () -> Void) {
        self.title = title
        self.icon = icon
        self.action = action
    }

    public var body: some View {
        GoPeakButtonBase(
            title: title,
            icon: icon,
            textStyle: GoPeakTheme.typography.buttonCompact,
            contentColor: GoPeakTheme.colors.onPrimary,
            fill: GoPeakTheme.colors.primary,
            border: nil,
            horizontalPadding: 16,
            verticalPadding: 8,
            action: action
        )
    }
}

/// Compact low-emphasis action (text only, primary-colored). Supports a leading OR trailing icon.
public struct SecondaryButton: View {
    private let title: LocalizedStringKey
    private let icon: GoPeakButtonIcon
    private let action: () -> Void

    public init(_ title: LocalizedStringKey, icon: GoPeakButtonIcon = .none, action: @escaping () -> Void) {
        self.title = title
        self.icon = icon
        self.action = action
    }

    public var body: some View {
        GoPeakButtonBase(
            title: title,
            icon: icon,
            textStyle: GoPeakTheme.typography.buttonCompact,
            contentColor: GoPeakTheme.colors.primary,
            fill: nil,
            border: nil,
            horizontalPadding: 16,
            verticalPadding: 8,
            action: action
        )
    }
}

/// Lowest-emphasis action (text only, muted). No icon (matches Android `TertiaryButton`).
public struct TertiaryButton: View {
    private let title: LocalizedStringKey
    private let icon: GoPeakButtonIcon
    private let action: () -> Void

    public init(_ title: LocalizedStringKey, icon: GoPeakButtonIcon = .none, action: @escaping () -> Void) {
        self.title = title
        self.icon = icon
        self.action = action
    }

    public var body: some View {
        GoPeakButtonBase(
            title: title,
            icon: icon,
            textStyle: GoPeakTheme.typography.buttonText,
            contentColor: GoPeakTheme.colors.onSurfaceVariant,
            fill: nil,
            border: nil,
            horizontalPadding: 12,
            verticalPadding: 8,
            action: action
        )
    }
}

// MARK: - Shared internals

/// Internal button impl. Public buttons delegate here (parity with Android's `TextIconButton`).
private struct GoPeakButtonBase: View {
    let title: LocalizedStringKey
    let icon: GoPeakButtonIcon
    let textStyle: GoPeakTextStyle
    let contentColor: Color
    let fill: Color?
    let border: (color: Color, width: CGFloat)?
    let horizontalPadding: CGFloat
    let verticalPadding: CGFloat
    let action: () -> Void

    @Environment(\.goPeakButtonFullWidth) private var isFullWidth

    var body: some View {
        Button(action: action) {
            HStack(spacing: 8) {
                if let symbol = icon.leadingSymbol {
                    Image(systemName: symbol).font(.system(size: 16))
                }
                Text(title).goPeakTextStyle(textStyle)
                if let symbol = icon.trailingSymbol {
                    Image(systemName: symbol).font(.system(size: 16))
                }
            }
            .foregroundStyle(contentColor)
            .padding(.horizontal, horizontalPadding)
            .padding(.vertical, verticalPadding)
            .frame(maxWidth: isFullWidth ? .infinity : nil)
            .background {
                if let fill {
                    GoPeakTheme.shapes.rounded(GoPeakTheme.shapes.small).fill(fill)
                }
            }
            .overlay {
                if let border {
                    GoPeakTheme.shapes.rounded(GoPeakTheme.shapes.small)
                        .stroke(border.color, lineWidth: border.width)
                }
            }
            .contentShape(GoPeakTheme.shapes.rounded(GoPeakTheme.shapes.small))
        }
        .buttonStyle(GoPeakPressStyle())
    }
}

/// Press feedback (the iOS analogue of the Android ripple).
private struct GoPeakPressStyle: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .opacity(configuration.isPressed ? 0.7 : 1.0)
            .animation(.easeOut(duration: 0.1), value: configuration.isPressed)
    }
}

// MARK: - Full-width modifier

private struct GoPeakButtonFullWidthKey: EnvironmentKey {
    static let defaultValue = false
}

extension EnvironmentValues {
    var goPeakButtonFullWidth: Bool {
        get { self[GoPeakButtonFullWidthKey.self] }
        set { self[GoPeakButtonFullWidthKey.self] = newValue }
    }
}

public extension View {
    /// Makes the GoPeak buttons in this subtree stretch to fill the available width — the SwiftUI
    /// equivalent of Compose's `Modifier.fillMaxWidth()`. Apply it to a single button or to any
    /// container to affect every GoPeak button inside it.
    func goPeakButtonFullWidth(_ enabled: Bool = true) -> some View {
        environment(\.goPeakButtonFullWidth, enabled)
    }
}

// MARK: - Previews

private struct ButtonsGallery: View {
    var body: some View {
        ZStack {
            VStack(
                alignment: .leading,
                spacing: 18,
            ) {
                BrandPrimaryButton("Get Started", icon: .trailing("arrow.right")) {}
                    .goPeakButtonFullWidth()
                BrandSecondaryButton("Watch Demo") {}
                    .goPeakButtonFullWidth()
                PrimaryButton("Continue", icon: .trailing("arrow.right")) {}
                SecondaryButton("Login") {}
                TertiaryButton("Skip", icon: .trailing("chevron.forward")) {}
            }
            .frame(width: 250)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(GoPeakTheme.colors.background)
    }
}

#Preview("Buttons – Dark") {
    ButtonsGallery().preferredColorScheme(.dark)
}

#Preview("Buttons – Light") {
    ButtonsGallery().preferredColorScheme(.light)
}
