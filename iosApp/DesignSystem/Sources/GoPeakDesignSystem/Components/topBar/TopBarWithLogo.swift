import SwiftUI

/// Top bar with the GoPeak brand logo as its title and an optional trailing action.
/// SwiftUI counterpart of Android's `TopBarWithLogo`.
public struct TopBarWithLogo<Trailing: View>: View {
    private let trailing: Trailing

    public init(@ViewBuilder trailing: () -> Trailing = { EmptyView() }) {
        self.trailing = trailing()
    }

    public var body: some View {
        BasicTopBar {
            Image("brand_logo", bundle: .module)
                .renderingMode(.template)
                .resizable()
                .scaledToFit()
                .frame(height: 32)
                .foregroundStyle(GoPeakTheme.colors.primary)
        } trailing: {
            trailing
        }
    }
}

// MARK: - Previews

#Preview("TopBarWithLogo – Dark") {
    VStack(spacing: 0) {
        TopBarWithLogo {
            TertiaryButton("Skip") {}
        }
        Spacer()
    }
    .frame(maxWidth: .infinity, maxHeight: .infinity)
    .background(GoPeakTheme.colors.background)
    .preferredColorScheme(.dark)
}

#Preview("TopBarWithLogo – Light") {
    VStack(spacing: 0) {
        TopBarWithLogo {
            TertiaryButton("Skip") {}
        }
        Spacer()
    }
    .frame(maxWidth: .infinity, maxHeight: .infinity)
    .background(GoPeakTheme.colors.background)
    .preferredColorScheme(.light)
}
