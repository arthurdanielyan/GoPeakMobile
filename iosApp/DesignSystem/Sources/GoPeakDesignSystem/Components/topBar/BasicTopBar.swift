import SwiftUI

/// Fixed height of a GoPeak top bar. Public counterpart of Android's `TopBarHeight`.
public let goPeakTopBarHeight: CGFloat = 64

/// Fixed-height top bar with leading / title / trailing slots — SwiftUI counterpart of Android's
/// `BasicTopBar`. The title sits on the left (not centered); a spacer pushes any trailing content
/// to the far right. `leading` and `trailing` are optional (default to nothing).
public struct BasicTopBar<Title: View, Leading: View, Trailing: View>: View {
    private let title: Title
    private let leading: Leading
    private let trailing: Trailing

    public init(
        @ViewBuilder title: () -> Title,
        @ViewBuilder leading: () -> Leading = { EmptyView() },
        @ViewBuilder trailing: () -> Trailing = { EmptyView() }
    ) {
        self.title = title()
        self.leading = leading()
        self.trailing = trailing()
    }

    public var body: some View {
        HStack(spacing: 16) {
            leading
            title
            Spacer(minLength: 0)
            trailing
        }
        .frame(maxWidth: .infinity)
        .frame(height: goPeakTopBarHeight)
        .padding(.horizontal, 16)
    }
}

// MARK: - Previews

#Preview("BasicTopBar – Dark") {
    VStack(spacing: 0) {
        BasicTopBar {
            Text("Profile").goPeakTextStyle(GoPeakTheme.typography.heading)
                .foregroundStyle(GoPeakTheme.colors.onBackground)
        } leading: {
            Image(systemName: "arrow.backward")
                .foregroundStyle(GoPeakTheme.colors.onBackground)
        } trailing: {
            TertiaryButton("Edit") {}
        }
        Spacer()
    }
    .frame(maxWidth: .infinity, maxHeight: .infinity)
    .background(GoPeakTheme.colors.background)
    .preferredColorScheme(.dark)
}

#Preview("BasicTopBar – Light") {
    VStack(spacing: 0) {
        BasicTopBar {
            Text("Profile").goPeakTextStyle(GoPeakTheme.typography.heading)
                .foregroundStyle(GoPeakTheme.colors.onBackground)
        } trailing: {
            TertiaryButton("Edit") {}
        }
        Spacer()
    }
    .frame(maxWidth: .infinity, maxHeight: .infinity)
    .background(GoPeakTheme.colors.background)
    .preferredColorScheme(.light)
}
