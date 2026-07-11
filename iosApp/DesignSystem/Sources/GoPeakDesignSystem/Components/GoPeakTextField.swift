import SwiftUI

/// GoPeak text field — the SwiftUI counterpart of Android's `GoPeakTextField`.
///
/// Has a decorative `leadingIcon` (SF Symbol) and a `trailingIcon` view slot, so the trailing
/// slot can host an interactive control such as a password-reveal toggle. Set `isSecure` to mask
/// input (the caller flips it from a trailing button to implement reveal).
public struct GoPeakTextField<Trailing: View>: View {

    @Binding private var text: String
    private let label: String?
    private let placeholder: String?
    private let leadingSymbol: String?
    private let isSecure: Bool
    private let trailing: () -> Trailing

    public init(
        text: Binding<String>,
        label: String? = nil,
        placeholder: String? = nil,
        leadingIcon: String? = nil,
        isSecure: Bool = false,
        @ViewBuilder trailingIcon: @escaping () -> Trailing = { EmptyView() }
    ) {
        self._text = text
        self.label = label
        self.placeholder = placeholder
        self.leadingSymbol = leadingIcon
        self.isSecure = isSecure
        self.trailing = trailingIcon
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            if let label {
                Text(label.uppercased())
                    .goPeakTextStyle(GoPeakTheme.typography.label)
                    .foregroundStyle(GoPeakTheme.colors.onSurfaceVariant)
            }

            HStack(spacing: 12) {
                if let leadingSymbol {
                    Image(systemName: leadingSymbol)
                        .font(.system(size: 16))
                        .foregroundStyle(GoPeakTheme.colors.onSurfaceVariant)
                }

                field
                    .font(GoPeakTheme.typography.body.font)
                    .foregroundStyle(GoPeakTheme.colors.onSurface)
                    .tint(GoPeakTheme.colors.primary)

                trailing()
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 14)
            .background(
                GoPeakTheme.shapes.rounded(GoPeakTheme.shapes.small)
                    .fill(GoPeakTheme.colors.surfaceVariant)
            )
            .overlay(
                GoPeakTheme.shapes.rounded(GoPeakTheme.shapes.small)
                    .stroke(GoPeakTheme.colors.outline, lineWidth: 1)
            )
        }
    }

    @ViewBuilder
    private var field: some View {
        let prompt = placeholder.map {
            Text($0).foregroundStyle(GoPeakTheme.colors.onSurfaceVariant.opacity(0.5))
        }
        if isSecure {
            SecureField("", text: $text, prompt: prompt)
        } else {
            TextField("", text: $text, prompt: prompt)
        }
    }
}

// MARK: - Previews

private struct TextFieldGallery: View {
    @State private var name = ""
    @State private var password = ""
    @State private var revealed = false

    var body: some View {
        VStack(spacing: 16) {
            GoPeakTextField(
                text: $name,
                label: "Full name",
                placeholder: "John Doe",
                leadingIcon: "person"
            )
            GoPeakTextField(
                text: $password,
                label: "Password",
                placeholder: "••••••••",
                leadingIcon: "lock",
                isSecure: !revealed
            ) {
                Button { revealed.toggle() } label: {
                    Image(systemName: revealed ? "eye.slash" : "eye")
                        .font(.system(size: 16))
                        .foregroundStyle(GoPeakTheme.colors.onSurfaceVariant)
                }
            }
        }
        .padding(24)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(GoPeakTheme.colors.background)
    }
}

#Preview("TextField – Dark") {
    TextFieldGallery().preferredColorScheme(.dark)
}

#Preview("TextField – Light") {
    TextFieldGallery().preferredColorScheme(.light)
}
